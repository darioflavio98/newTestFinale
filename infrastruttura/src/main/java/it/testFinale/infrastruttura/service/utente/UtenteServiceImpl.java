package it.testFinale.infrastruttura.service.utente;

import it.testFinale.infrastruttura.object.dto.UtenteDTO;
import it.testFinale.infrastruttura.object.model.Utente;
import it.testFinale.infrastruttura.repository.UtenteRepository;
import it.testFinale.infrastruttura.tools.ConvertiInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtenteServiceImpl implements UtenteService {

    private UtenteRepository utenteRepository;

    @Autowired
    public UtenteServiceImpl(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Override
    public UtenteDTO salvaUtente(UtenteDTO utenteDTO) {
        Utente utente = new Utente();
        utente.setNome(utenteDTO.getNome());
        utente.setEmail(utenteDTO.getEmail());
        utente = utenteRepository.save(utente);
        return  ConvertiInDto.convertiInUtenteDTO(utente);

    }
    @Override
    public List<UtenteDTO> getAllutenti() {
        List<Utente> utenti = utenteRepository.findAll();
        return utenti.stream()
                .map(ConvertiInDto::convertiInUtenteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UtenteDTO findUtenteById(Long id) {

        return utenteRepository.findById(id).
                map(ConvertiInDto::convertiInUtenteDTO)
                .orElseThrow(()->new RuntimeException("Utente con id " + id +" non trovato."));

    }

    @Override
    public UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO) {
        Optional<Utente> utenteOptional = utenteRepository.findById(id);
        if(utenteOptional.isPresent()){
            Utente utente = utenteOptional.get();
            utente.setNome(utenteDTO.getNome());
            utente.setEmail(utenteDTO.getEmail());
            Utente utenteAggiornato = utenteRepository.save(utente);
            return ConvertiInDto.convertiInUtenteDTO(utenteAggiornato);
        }
        else {
            throw new RuntimeException("Utente con id " + id +" non trovato.");
        }
    }

    @Override
    public void deleteUtente(Long id) {
        if(utenteRepository.findById(id).isPresent()){
            utenteRepository.deleteById(id);
        }else {
            throw new RuntimeException("L'utente con id " +id+ " non esiste");
        }
    }
}
