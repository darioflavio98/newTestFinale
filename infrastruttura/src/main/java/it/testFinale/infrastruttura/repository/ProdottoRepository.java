package it.testFinale.infrastruttura.repository;

import it.testFinale.infrastruttura.object.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
}
