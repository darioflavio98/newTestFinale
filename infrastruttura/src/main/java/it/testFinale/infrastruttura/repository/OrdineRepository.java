package it.testFinale.infrastruttura.repository;

import it.testFinale.infrastruttura.object.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {

   List<Ordine> findByUtenteId(Long id);

   List<Ordine> findOrdiniInIntervallo(LocalDateTime inzio, LocalDateTime fine);


}
