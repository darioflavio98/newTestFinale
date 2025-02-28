package it.testFinale.infrastruttura.service.prodotto;

import it.testFinale.infrastruttura.object.dto.ProdottoDTO;
import it.testFinale.infrastruttura.object.model.Prodotto;
import it.testFinale.infrastruttura.repository.ProdottoRepository;
import it.testFinale.infrastruttura.tools.ConvertiInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoServiceImpl implements ProdottoService {

    private final ProdottoRepository prodottoRepository;

    @Autowired
    public ProdottoServiceImpl(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    @Override
    public List<ProdottoDTO> getAllProdotti() {
        List<Prodotto> prodotti = prodottoRepository.findAll();
        return prodotti.stream().map(ConvertiInDto::convertiInProdottoDTO).toList();
    }

    @Override
    public ProdottoDTO getProdottoById(Long id) {

        return prodottoRepository.findById(id)
                .map(ConvertiInDto::convertiInProdottoDTO)
                .orElseThrow(() -> new RuntimeException("Prodotto con id " + id + " non trovato."));
    }

    @Override
    public ProdottoDTO creaProdotto(ProdottoDTO prodottoDTO) {
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(prodottoDTO.getNome());
        prodotto.setPrezzo(prodottoDTO.getPrezzo());
        Prodotto nuovoProdotto = prodottoRepository.save(prodotto);
        return ConvertiInDto.convertiInProdottoDTO(nuovoProdotto);
    }

    @Override
    public ProdottoDTO updateProdotto(Long id, ProdottoDTO prodottoDTO) {
        Optional<Prodotto> prodottoOptional = prodottoRepository.findById(id);
        if (prodottoOptional.isPresent()) {
            Prodotto prodotto = prodottoOptional.get();
            prodotto.setNome(prodottoDTO.getNome());
            prodotto.setPrezzo(prodottoDTO.getPrezzo());
            Prodotto prodottoAggiornato = prodottoRepository.save(prodotto);
            return ConvertiInDto.convertiInProdottoDTO(prodottoAggiornato);
        } else {
            throw new RuntimeException("Prodotto con id " + id + " non trovato");
        }
    }

    @Override
    public void deleteProdotto(Long id) {
        if (prodottoRepository.findById(id).isPresent()) {
            prodottoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Il prodotto con id " + id + " non è stato trovato");
        }
    }

}

