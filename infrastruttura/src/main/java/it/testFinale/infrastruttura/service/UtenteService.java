package it.testFinale.infrastruttura.service;

import it.testFinale.infrastruttura.object.dto.UtenteDTO;

import java.util.List;


public interface UtenteService {

    List<UtenteDTO> getAllutenti();


    UtenteDTO findUtenteById(Long id);


    UtenteDTO salvaUtente(UtenteDTO libroDTO);


    UtenteDTO updateUtente(Long id, UtenteDTO libro);


    void deleteUtente(Long id);
}
