package it.testFinale.infrastruttura.service.ordine;

import it.testFinale.infrastruttura.object.dto.OrdineDTO;

import java.util.List;

public interface OrdineService {

    List<OrdineDTO> getAllOrdini();

    OrdineDTO getOrdineById(Long id);

    OrdineDTO creaOrdine(OrdineDTO ordineDTO);

    OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO);

    void deleteOrdine(Long id);
}
