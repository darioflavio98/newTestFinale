package it.testFinale.infrastruttura.service.dettaglio;

import it.testFinale.infrastruttura.object.dto.DettaglioOrdineDTO;
import it.testFinale.infrastruttura.object.model.DettaglioOrdine;
import it.testFinale.infrastruttura.object.model.Ordine;
import it.testFinale.infrastruttura.object.model.Prodotto;
import it.testFinale.infrastruttura.repository.DettaglioOrdineRepository;
import it.testFinale.infrastruttura.repository.OrdineRepository;
import it.testFinale.infrastruttura.repository.ProdottoRepository;
import it.testFinale.infrastruttura.tools.ConvertiInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DettaglioOrdineServiceImpl implements DettaglioOrdineService {

    private final DettaglioOrdineRepository dettaglioOrdineRepository;
    private final OrdineRepository ordineRepository;
    private final ProdottoRepository prodottoRepository;

    @Autowired
    public DettaglioOrdineServiceImpl(DettaglioOrdineRepository dettaglioOrdineRepository, OrdineRepository ordineRepository, ProdottoRepository prodottoRepository) {
        this.dettaglioOrdineRepository = dettaglioOrdineRepository;
        this.ordineRepository = ordineRepository;
        this.prodottoRepository = prodottoRepository;
    }

    @Override
    public List<DettaglioOrdineDTO> getAllDettaglioOrdine() {
        return dettaglioOrdineRepository.findAll().stream().map(ConvertiInDto::convertiInDettaglioDTO).toList();
    }

    @Override
    public DettaglioOrdineDTO getDettaglioOrdineById(Long id) {


        return dettaglioOrdineRepository.findById(id)
                .map(ConvertiInDto::convertiInDettaglioDTO)
                .orElseThrow(() -> new RuntimeException("Dettaglio Ordine con id " + id + " non trovato."));
    }

    @Override
    public DettaglioOrdineDTO creaDettaglioOrdine(DettaglioOrdineDTO dettaglioOrdineDTO) {

        Ordine ordine = ordineRepository.findById(dettaglioOrdineDTO.getId())
                .orElseThrow(() -> new RuntimeException("Ordine con id " + dettaglioOrdineDTO.getId() + " non trovato."));

        Prodotto prodotto = prodottoRepository.findById(dettaglioOrdineDTO.getProdottoId())
                .orElseThrow(() -> new RuntimeException("Prodotto Ordine con id " + dettaglioOrdineDTO.getProdottoId() + " non trovato."));

        DettaglioOrdine dettaglioOrdine = new DettaglioOrdine();

        dettaglioOrdine.setOrdine(ordine);
        dettaglioOrdine.setProdotto(prodotto);
        dettaglioOrdine.setQuantita(dettaglioOrdineDTO.getQuantita());

        dettaglioOrdine = dettaglioOrdineRepository.save(dettaglioOrdine);

        return ConvertiInDto.convertiInDettaglioDTO(dettaglioOrdine);
    }

    @Override
    public DettaglioOrdineDTO updateDettaglioOrdine(Long id, DettaglioOrdineDTO dettaglioOrdineDTO) {

        Optional<DettaglioOrdine> dettaglioOrdineOptional = dettaglioOrdineRepository.findById(id);
        if (dettaglioOrdineOptional.isPresent()) {

            Ordine ordine = ordineRepository.findById(dettaglioOrdineDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Ordine con id " + dettaglioOrdineDTO.getId() + " non trovato."));

            Prodotto prodotto = prodottoRepository.findById(dettaglioOrdineDTO.getProdottoId())
                    .orElseThrow(() -> new RuntimeException("Prodotto Ordine con id " + dettaglioOrdineDTO.getProdottoId() + " non trovato."));

            DettaglioOrdine dettaglioOrdine = dettaglioOrdineOptional.get();
            dettaglioOrdine.setProdotto(prodotto);
            dettaglioOrdine.setOrdine(ordine);
            dettaglioOrdine.setQuantita(dettaglioOrdineDTO.getQuantita());
            DettaglioOrdine dettaglioOrdineAggiornato = dettaglioOrdineRepository.save(dettaglioOrdine);

            return ConvertiInDto.convertiInDettaglioDTO(dettaglioOrdineAggiornato);
        } else {
            throw new RuntimeException("Dettaglio Ordine con id " + dettaglioOrdineDTO.getProdottoId() + " non trovato.");
        }
    }

    @Override
    public void deleteDettaglioOrdine(Long id) {
        if (dettaglioOrdineRepository.findById(id).isPresent()) {
            dettaglioOrdineRepository.deleteById(id);
        } else {
            throw new RuntimeException("Dettaglio Ordine con id " + id + " non è stato trovato");
        }
    }
}