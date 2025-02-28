package it.testFinale.infrastruttura.object.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrdineDTO {
    private Long id;
    private LocalDateTime data;
    private String stato;
    private Double totale;
    private Long utenteId;
    private List<DettaglioOrdineDTO> dettagli;
}
