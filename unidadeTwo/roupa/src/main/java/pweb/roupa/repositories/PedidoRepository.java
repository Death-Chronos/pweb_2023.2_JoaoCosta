package pweb.roupa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pweb.roupa.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
