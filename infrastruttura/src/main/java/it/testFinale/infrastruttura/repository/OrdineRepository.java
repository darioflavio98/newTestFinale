package it.testFinale.infrastruttura.repository;

import it.testFinale.infrastruttura.object.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {
}
