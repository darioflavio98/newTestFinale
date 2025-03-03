package it.testFinale.infrastruttura.repository;

import it.testFinale.infrastruttura.object.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
