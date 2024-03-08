package pweb.roupa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pweb.roupa.entities.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente,Long>{
    
}
