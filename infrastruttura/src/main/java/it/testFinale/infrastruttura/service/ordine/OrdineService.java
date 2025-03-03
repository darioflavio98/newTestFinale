package it.testFinale.infrastruttura.service.ordine;

import it.testFinale.infrastruttura.object.dto.OrdineDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdineService {

    List<OrdineDTO> getAllOrdini();

    OrdineDTO getOrdineById(Long id);

    OrdineDTO creaOrdine(OrdineDTO ordineDTO);

    OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO);

    void deleteOrdine(Long id);

    List<OrdineDTO> findOrdiniByUtenteId(Long id);

    Double totaleSpesaUtente(Long id);

    List<OrdineDTO> findOrdiniIntervalloDate(LocalDateTime inizio, LocalDateTime fine);
}
