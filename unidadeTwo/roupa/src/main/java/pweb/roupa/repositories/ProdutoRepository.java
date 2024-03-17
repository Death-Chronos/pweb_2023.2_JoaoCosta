package pweb.roupa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pweb.roupa.entities.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto,Long>{
    
}
