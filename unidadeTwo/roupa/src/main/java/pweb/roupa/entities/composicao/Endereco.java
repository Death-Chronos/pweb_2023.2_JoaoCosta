package pweb.roupa.entities.composicao;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Endereco {
    private String rua;
    private String bairro;
    private String numero;
    private String cidade;
}
