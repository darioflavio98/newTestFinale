package it.testFinale.infrastruttura.controller;


import it.testFinale.infrastruttura.object.dto.ProdottoDTO;
import it.testFinale.infrastruttura.service.prodotto.ProdottoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    private final ProdottoService prodottoService;

    public ProdottoController(ProdottoService prodottoService) {
        this.prodottoService = prodottoService;
    }

    @PostMapping
    public ProdottoDTO salvaProdotto(@RequestBody ProdottoDTO prodottoDTO) {
        return prodottoService.creaProdotto(prodottoDTO);
    }

    @GetMapping
    public List<ProdottoDTO> getAllProdotti() {
        return prodottoService.getAllProdotti();
    }

    @GetMapping("/{id}")
    public ProdottoDTO getProdottoById(@PathVariable Long id) {
        return prodottoService.getProdottoById(id);
    }

    @PutMapping
    public ProdottoDTO updateProdottoById(@PathVariable Long id, @RequestBody ProdottoDTO prodottoDTO) {
        return prodottoService.updateProdotto(id, prodottoDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProdotto(@PathVariable Long id) {
        prodottoService.deleteProdotto(id);
    }
}
