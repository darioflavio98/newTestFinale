package it.testFinale.infrastruttura.object.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prodotto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private Double prezzo;
}
