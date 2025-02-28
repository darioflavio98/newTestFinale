package it.testFinale.infrastruttura.controller;

import it.testFinale.infrastruttura.object.dto.OrdineDTO;
import it.testFinale.infrastruttura.service.ordine.OrdineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

    private final OrdineService ordineService;

    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    @PostMapping
    public OrdineDTO creaOrdine(@RequestBody OrdineDTO ordineDTO){
        return ordineService.creaOrdine(ordineDTO);
    }

    @GetMapping
    public List<OrdineDTO> getAllOrdini(){
        return ordineService.getAllOrdini();
    }

    @GetMapping("/{id}")
    public OrdineDTO getOrdineById(@PathVariable Long id){
        return ordineService.getOrdineById(id);
    }


    @GetMapping("/utente/{utenteId}/ordini")  // ✅ Il nome corrisponde al parametro
    public List<OrdineDTO> getOrdiniByUtenteId(@PathVariable Long utenteId) {
        return ordineService.findOrdiniByUtenteId(utenteId);
    }
    @GetMapping("/totale-spesa/{id}")
    public Double getTotaleSpesaUtente(@PathVariable Long id) {

        Double totaleSpesa = ordineService.totaleSpesaUtente(id);
        return totaleSpesa;
    }

    @PutMapping("/{id}")
    public OrdineDTO updateOrdineById(@PathVariable Long id, @RequestBody OrdineDTO ordineDTO){
        return ordineService.updateOrdine(id,ordineDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdineById(@PathVariable Long id){
        ordineService.deleteOrdine(id);
    }
}
