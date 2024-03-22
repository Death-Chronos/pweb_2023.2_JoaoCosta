package pweb.roupa.entities.PK;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import pweb.roupa.entities.Pedido;
import pweb.roupa.entities.Produto;

@Embeddable
@Data
@NoArgsConstructor
public class PedidoProdutoPK {

    @ManyToOne
	@JoinColumn(name = "pedido")
    private Pedido pedido;

    @ManyToOne
	@JoinColumn(name = "produto")
    private Produto produto;
    
}
