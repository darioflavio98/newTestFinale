package it.testFinale.infrastruttura.service.ordine;

import it.testFinale.infrastruttura.object.dto.DettaglioOrdineDTO;
import it.testFinale.infrastruttura.object.dto.OrdineDTO;
import it.testFinale.infrastruttura.object.dto.UtenteDTO;
import it.testFinale.infrastruttura.object.model.DettaglioOrdine;
import it.testFinale.infrastruttura.object.model.Ordine;
import it.testFinale.infrastruttura.object.model.Prodotto;
import it.testFinale.infrastruttura.object.model.Utente;
import it.testFinale.infrastruttura.repository.DettaglioOrdineRepository;
import it.testFinale.infrastruttura.repository.OrdineRepository;
import it.testFinale.infrastruttura.repository.ProdottoRepository;
import it.testFinale.infrastruttura.repository.UtenteRepository;
import it.testFinale.infrastruttura.service.dettaglio.DettaglioOrdineServiceImpl;
import it.testFinale.infrastruttura.tools.ConvertiInDto;
import it.testFinale.infrastruttura.tools.ConvertiInEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static it.testFinale.infrastruttura.tools.ConvertiInEntity.convertiOrdineDTOinEntity;

@Service
public class OrdineServiceImpl implements OrdineService {


    private OrdineRepository ordineRepository;
    private UtenteRepository utenteRepository;
    private ProdottoRepository prodottoRepository;
    private DettaglioOrdineRepository dettaglioOrdineRepository;
    //private ConvertiInEntity convertiInEntity;


    @Autowired
    public OrdineServiceImpl(OrdineRepository ordineRepository, UtenteRepository utenteRepository, ProdottoRepository prodottoRepository, DettaglioOrdineRepository dettaglioOrdineRepository) {
        this.ordineRepository = ordineRepository;
        this.utenteRepository = utenteRepository;
        this.prodottoRepository = prodottoRepository;
        this.dettaglioOrdineRepository = dettaglioOrdineRepository;
        //this.convertiInEntity = convertiInEntity;
    }

    @Override
    public List<OrdineDTO> getAllOrdini() {
        return ordineRepository.findAll().stream().map(ConvertiInDto::convertiInOrdineDTO).collect(Collectors.toList());
    }

    @Override
    public OrdineDTO getOrdineById(Long id) {

        return ordineRepository.findById(id)
                .map(ConvertiInDto::convertiInOrdineDTO)
                .orElseThrow(() -> new RuntimeException("Ordine con id " + id + " non trovato."));
    }

    @Override
    public List<OrdineDTO> findOrdiniByUtenteId(Long utenteId) {

        return ordineRepository.findByUtenteId(utenteId).stream()
                .map(ConvertiInDto::convertiInOrdineDTO).toList();
    }

    @Override
    public Double totaleSpesaUtente(Long id) {
        List<OrdineDTO> ordiniDTO = ordineRepository.findByUtenteId(id).stream().map(ConvertiInDto::convertiInOrdineDTO).toList();

        return ordiniDTO.stream().mapToDouble(ordineDTO->ordineDTO.getTotale()).sum();

    }


    @Override
    @Transactional
    public OrdineDTO creaOrdine(OrdineDTO ordineDTO) {

        Utente utente = utenteRepository.findById(ordineDTO.getUtenteId())
                .orElseThrow(() -> new RuntimeException("Utente con ID " + ordineDTO.getUtenteId() + " non trovato."));

        //1.Mappare ordineDTO in entity Ordine
        Ordine ordine = new Ordine();
        ordine = convertiOrdineDTOinEntity(ordineDTO, utente);


        //2.Preparare DettaglioOrdine
        List<DettaglioOrdine> dettaglioOrdines = new ArrayList<>();
        double totaleOrdine = 0;
        for (DettaglioOrdineDTO dettaglioOrdineDTO : ordineDTO.getDettagli()) {
            Prodotto prodotto = prodottoRepository.findById(dettaglioOrdineDTO.getProdottoId()).orElse(null);
            if (prodotto == null) {
                break;
            }
            DettaglioOrdine dettaglioOrdine = new DettaglioOrdine();
            dettaglioOrdine.setOrdine(ordine);
            dettaglioOrdine.setProdotto(prodotto);
            dettaglioOrdine.setQuantita(dettaglioOrdineDTO.getQuantita());

            double prezzoTotale = prodotto.getPrezzo() * dettaglioOrdineDTO.getQuantita();
            dettaglioOrdine.setPrezzoTotale(prezzoTotale);
            totaleOrdine += prezzoTotale;
            dettaglioOrdines.add(dettaglioOrdine);
        }
        ordine.setTotale(totaleOrdine);
        ordine.setDettagli(dettaglioOrdines);
        ordine.setData(LocalDate.now());
        ordine.setStato("IN_ATTESA");
        ordine.setUtente(utente);

        //3.Salvare Ordine
        ordine = ordineRepository.save(ordine);

        //4.Salvare DettaglioOrdine
        for (DettaglioOrdine dettaglio : dettaglioOrdines) {

            dettaglioOrdineRepository.save(dettaglio);
        }
        return ConvertiInDto.convertiInOrdineDTO(ordine);
    }

    @Override
    public OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO) {
        /*
        Optional<Ordine> ordineOptional = ordineRepository.findById(id);
        if (ordineOptional.isPresent()) {
            Utente utente = utenteRepository.findById(ordineDTO.getUtenteId())
                    .orElseThrow(() -> new RuntimeException("Utente con ID " + ordineDTO.getUtenteId() + " non trovato."));
            Ordine ordine = ordineOptional.get();
            ordine.setId(ordineDTO.getId());
            ordine.setData(ordineDTO.getData());
            ordine.setTotale(ordineDTO.getTotale());
            ordine.setStato(ordineDTO.getStato());
            List<DettaglioOrdine> dettagli = ordineDTO.getDettagli().stream().map(ConvertiInEntity::convertiDettaglioDTOInEntity)
                    .collect(Collectors.toList());
            ordine.setDettagli(dettagli);
            ordine.setUtente(utente);
            Ordine ordineAggiornato = ordineRepository.save(ordine);

            return ConvertiInDto.convertiInOrdineDTO(ordineAggiornato);

        } else {
            throw new RuntimeException("L'Ordine con id " + id + " non è stato trovato");
        }*/
        return new OrdineDTO();
    }

    @Override
    public void deleteOrdine(Long id) {
        if (ordineRepository.findById(id).isPresent()){
            ordineRepository.deleteById(id);
        }else{
            throw new RuntimeException("L'Ordine con id " + id + " non è stato trovato");
        }
    }


}
