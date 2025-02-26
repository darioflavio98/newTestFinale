package it.testFinale.infrastruttura.object.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "utente")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ordine> ordini;
}
