package pweb.roupa.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import pweb.roupa.entities.PK.PedidoProdutoPK;

@Entity
@Data
@NoArgsConstructor
public class PedidoProduto {
    
    @EmbeddedId
    private PedidoProdutoPK id = new PedidoProdutoPK();

    private int quantidade;
    private double preco;

    public PedidoProduto(Produto produto, Pedido pedido, int quantidade, double preco) {
        id.setPedido(pedido);
        id.setProduto(produto);
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public double getSubtotal() {
        return preco * quantidade;
    }
    
}
