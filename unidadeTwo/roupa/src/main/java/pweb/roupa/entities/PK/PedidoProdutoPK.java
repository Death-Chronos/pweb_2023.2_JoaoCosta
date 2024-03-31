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

    public PedidoProdutoPK(Pedido pedido, Produto produto) {
        this.pedido = pedido;
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
        result = prime * result + ((produto == null) ? 0 : produto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PedidoProdutoPK other = (PedidoProdutoPK) obj;
        if (pedido == null) {
            if (other.pedido != null)
                return false;
        } else if (!pedido.equals(other.pedido))
            return false;
        if (produto == null) {
            if (other.produto != null)
                return false;
        } else if (!produto.equals(other.produto))
            return false;
        return true;
    }

    @Override
    public String toString() {
        if (this.pedido != null && this.produto != null) {
            return "PedidoProdutoPK [pedido=" + this.pedido.getId() + ", produto=" + this.produto.getId() + "]";
        } else {
            return "PedidoProdutoPK [pedido=null, produto=null]";
        }
    }

}
