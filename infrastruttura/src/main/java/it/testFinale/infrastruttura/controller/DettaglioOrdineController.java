package it.testFinale.infrastruttura.controller;

import it.testFinale.infrastruttura.object.dto.DettaglioOrdineDTO;
import it.testFinale.infrastruttura.service.dettaglio.DettaglioOrdineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dettagliOrdini")
public class DettaglioOrdineController {

    private final DettaglioOrdineService dettaglioOrdineService;

    public DettaglioOrdineController(DettaglioOrdineService dettaglioOrdineService) {
        this.dettaglioOrdineService = dettaglioOrdineService;
    }

    @PostMapping
    public DettaglioOrdineDTO creaDettaglioOrdine(@RequestBody DettaglioOrdineDTO dettaglioOrdineDTO) {
        return dettaglioOrdineService.creaDettaglioOrdine(dettaglioOrdineDTO);
    }

    @GetMapping
    public List<DettaglioOrdineDTO> dettagliOrdiniDTO() {
        return dettaglioOrdineService.getAllDettaglioOrdine();
    }

    @GetMapping("/{id}")
    public DettaglioOrdineDTO dettaglioOrdineDTO(@PathVariable Long id) {
        return dettaglioOrdineService.getDettaglioOrdineById(id);
    }

    @PutMapping("/{id}")
    public DettaglioOrdineDTO updateDettaglioOrdine(@PathVariable Long id, @RequestBody DettaglioOrdineDTO dettaglioOrdineDTO) {
        return dettaglioOrdineService.updateDettaglioOrdine(id, dettaglioOrdineDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDettaglioOrdine(@PathVariable Long id) {
        dettaglioOrdineService.deleteDettaglioOrdine(id);
    }
}
