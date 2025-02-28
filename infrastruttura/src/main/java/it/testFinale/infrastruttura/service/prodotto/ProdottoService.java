package it.testFinale.infrastruttura.service.prodotto;


import it.testFinale.infrastruttura.object.dto.ProdottoDTO;

import java.util.List;

public interface ProdottoService {

    List<ProdottoDTO> getAllProdotti();

    ProdottoDTO getProdottoById(Long id);

    ProdottoDTO creaProdotto(ProdottoDTO prodottoDTO);

    ProdottoDTO updateProdotto(Long id, ProdottoDTO prodottoDTO);

    void deleteProdotto(Long id);

}
