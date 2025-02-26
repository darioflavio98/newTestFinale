package it.testFinale.infrastruttura.service;

import it.testFinale.infrastruttura.object.dto.UtenteDTO;
import it.testFinale.infrastruttura.object.model.Utente;
import it.testFinale.infrastruttura.repository.UtenteRepository;
import it.testFinale.infrastruttura.tools.ConvertiInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtenteServiceImpl implements UtenteService{

    @Autowired
    UtenteRepository utenteRepository;


  public UtenteDTO creaUtente(UtenteDTO utenteDTO){
      Utente utente = new Utente();
      utente.setNome(utenteDTO.getNome());
      utente.setEmail(utenteDTO.getEmail());
      utente = utenteRepository.save(utente);
      return  ConvertiInDto.convertiInUtenteDTO(utente);

  }


    @Override
    public List<UtenteDTO> getAllutenti() {
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream().map(ConvertiInDto::convertiInUtenteDTO).collect(Collectors.toList());
    }

    @Override
    public UtenteDTO findUtenteById(Long id) {
        return null;
    }

    @Override
    public UtenteDTO salvaUtente(UtenteDTO libroDTO) {
        return null;
    }

    @Override
    public UtenteDTO updateUtente(Long id, UtenteDTO libro) {
        return null;
    }

    @Override
    public void deleteUtente(Long id) {

    }
}
