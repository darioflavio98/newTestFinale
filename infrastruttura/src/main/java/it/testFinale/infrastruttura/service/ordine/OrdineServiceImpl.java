package it.testFinale.infrastruttura.service.ordine;

import it.testFinale.infrastruttura.object.dto.OrdineDTO;
import it.testFinale.infrastruttura.object.model.Ordine;
import it.testFinale.infrastruttura.object.model.Utente;
import it.testFinale.infrastruttura.repository.OrdineRepository;
import it.testFinale.infrastruttura.repository.UtenteRepository;
import it.testFinale.infrastruttura.tools.ConvertiInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdineServiceImpl implements OrdineService {

    private OrdineRepository ordineRepository;
    private UtenteRepository utenteRepository;

    @Autowired
    public OrdineServiceImpl(OrdineRepository ordineRepository, UtenteRepository utenteRepository) {
        this.ordineRepository = ordineRepository;
        this.utenteRepository = utenteRepository;
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
    public OrdineDTO creaOrdine(OrdineDTO ordineDTO) {

        Utente utente = utenteRepository.findById(ordineDTO.getUtenteId())
                .orElseThrow(() -> new RuntimeException("Utente con ID " + ordineDTO.getUtenteId() + " non trovato."));

        Ordine ordine = new Ordine();
        ordine.setUtente(utente);
        ordine.setDettagli(ordineDTO.getDettagli());
        ordine.setTotale(ordineDTO.getTotale());
        ordine.setData(ordineDTO.getData());
        ordine.setStato(ordineDTO.getStato());
        ordine = ordineRepository.save(ordine);

        return ConvertiInDto.convertiInOrdineDTO(ordine);
    }

    @Override
    public OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO) {
        Optional<Ordine> ordineOptional = ordineRepository.findById(id);
        if (ordineOptional.isPresent()) {
            Utente utente = utenteRepository.findById(ordineDTO.getUtenteId())
                    .orElseThrow(() -> new RuntimeException("Utente con ID " + ordineDTO.getUtenteId() + " non trovato."));
            Ordine ordine = ordineOptional.get();
            ordine.setId(ordineDTO.getId());
            ordine.setData(ordineDTO.getData());
            ordine.setTotale(ordineDTO.getTotale());
            ordine.setStato(ordineDTO.getStato());
            ordine.setDettagli(ordineDTO.getDettagli());
            ordine.setUtente(utente);
            Ordine ordineAggiornato = ordineRepository.save(ordine);

            return ConvertiInDto.convertiInOrdineDTO(ordineAggiornato);

        } else {
            throw new RuntimeException("L'Ordine con id " + id + " non è stato trovato");
        }
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
