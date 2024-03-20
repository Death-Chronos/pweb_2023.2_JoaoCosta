package pweb.roupa.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import pweb.roupa.entities.enums.Genero;

@Entity
@Data
@NoArgsConstructor
public class Dependente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Genero genero;

    private LocalDate data_nascimento;

    @ManyToOne()
    @JoinColumn(name = "cliente")
    private Cliente cliente;
}
