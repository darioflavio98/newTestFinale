package it.testFinale.infrastruttura.object.dto;

import it.testFinale.infrastruttura.object.model.DettaglioOrdine;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrdineDTO {
    private Long id;
    private LocalDate data;
    private String stato;
    private Double totale;
    private Long utenteId;
    private List<DettaglioOrdine> dettagli;
}
