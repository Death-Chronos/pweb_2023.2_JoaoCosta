package pweb.roupa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pweb.roupa.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE p.nome LIKE %:nomeProduto%")
    public List<Produto> buscarPorNome(@Param("nomeProduto") String nomeProduto);

}
