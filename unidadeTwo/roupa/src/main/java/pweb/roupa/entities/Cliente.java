package pweb.roupa.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import pweb.roupa.entities.composicao.Endereco;
import pweb.roupa.entities.enums.Genero;

@Entity
@Data
@NoArgsConstructor
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Genero genero;

    @Embedded
    private Endereco endereco;

    private String email;

    private String telefone;
}
