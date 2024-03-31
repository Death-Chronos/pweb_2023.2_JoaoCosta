package pweb.roupa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pweb.roupa.entities.PedidoProduto;
import pweb.roupa.entities.PK.PedidoProdutoPK;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, PedidoProdutoPK> {
    
}
