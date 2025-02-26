package it.testFinale.infrastruttura.repository;

import it.testFinale.infrastruttura.object.model.DettaglioOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DettaglioOrdineRepository extends JpaRepository<DettaglioOrdine, Long> {
}
