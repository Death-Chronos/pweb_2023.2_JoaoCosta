package pweb.roupa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pweb.roupa.entities.PK.PedidoProdutoPK;

public interface PedidoProduto extends JpaRepository<PedidoProduto, PedidoProdutoPK> {
    
}
