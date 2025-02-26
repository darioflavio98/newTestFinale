package it.testFinale.infrastruttura.service.utente;

import it.testFinale.infrastruttura.object.dto.UtenteDTO;

import java.util.List;


public interface UtenteService {

    List<UtenteDTO> getAllutenti();


    UtenteDTO findUtenteById(Long id);


    UtenteDTO salvaUtente(UtenteDTO utenteDTO);


    UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO);


    void deleteUtente(Long id);
}
