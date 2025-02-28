package it.testFinale.infrastruttura.controller;


import it.testFinale.infrastruttura.object.dto.UtenteDTO;
import it.testFinale.infrastruttura.service.utente.UtenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @PostMapping
    public UtenteDTO creaUtente(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.salvaUtente(utenteDTO);
    }

    @GetMapping()
    public List<UtenteDTO> getAllUtenti() {
        return utenteService.getAllutenti();
    }
    @GetMapping("/{id}")
    public UtenteDTO getUtenteById(@PathVariable Long id){
        return utenteService.findUtenteById(id);
    }
    @PutMapping("/{id}")
    public UtenteDTO updateUtente(@PathVariable Long id, @RequestBody UtenteDTO utenteDTO){
        return utenteService.updateUtente(id,utenteDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUtente(@PathVariable Long id){
        utenteService.deleteUtente(id);
    }
}
