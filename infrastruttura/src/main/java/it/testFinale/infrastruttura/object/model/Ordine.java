package it.testFinale.infrastruttura.object.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ordine")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false)
    private String stato; // IN_ATTESA, SPEDITO, CONSEGNATO
    @Column(nullable = false)
    private Double totale;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DettaglioOrdine> dettagli;
}
