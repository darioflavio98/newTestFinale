package it.testFinale.infrastruttura.object.dto;

import lombok.Data;

@Data
public class DettaglioOrdineDTO {
    private Long id;
    private Integer quantita;
    private Double prezzoTotale;
    private Long prodottoId;
}
