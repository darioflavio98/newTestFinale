package it.testFinale.infrastruttura.service.dettaglio;

import it.testFinale.infrastruttura.object.dto.DettaglioOrdineDTO;
import it.testFinale.infrastruttura.object.dto.OrdineDTO;

import java.util.List;

public interface DettaglioOrdineService {

    List<DettaglioOrdineDTO> getAllDettaglioOrdine();

    DettaglioOrdineDTO getDettaglioOrdineById(Long id);

    DettaglioOrdineDTO creaDettaglioOrdine(DettaglioOrdineDTO dettaglioOrdineDTO);

    DettaglioOrdineDTO updateDettaglioOrdine(Long id, DettaglioOrdineDTO dettaglioOrdineDTO);

    void deleteDettaglioOrdine(Long id);
}
