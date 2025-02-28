package it.testFinale.infrastruttura.object.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dettaglio_ordine")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DettaglioOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantita;

    @Column(nullable = false)
    private Double prezzoTotale;

    @ManyToOne
    @JoinColumn(name = "ordine_id", nullable = false)
    private Ordine ordine;

    @ManyToOne
    @JoinColumn(name = "prodotto_id", nullable = false)
    private Prodotto prodotto;
}
