package pweb.roupa.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pweb.roupa.controllers.exceptions.ResourceNotFoundException;
import pweb.roupa.entities.Produto;
import pweb.roupa.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository pr;

    public List<Produto> findAll() {
        return pr.findAll();
    }

    public Produto findById(Long id) {
        Optional<Produto> optional = pr.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com o id:" + id));
    }

    public Produto save(Produto produto) {
        produto.setDataCadastro(Instant.now());
        return pr.save(produto);
    }

    public void delete(Long id) {
        if (pr.existsById(id)) {
            pr.deleteById(id);

        } else {
            throw new ResourceNotFoundException("Produto não encontrado com o id:" + id);
        }

    }

    public void update(Produto produto, Long id) {
        if (pr.existsById(id)) {
            pr.save(produto);
        } else {
            throw new ResourceNotFoundException("Produto não encontrado com o id:" + id);
        }

    }

    public List<Produto> buscarProdutosPorNome(String nome){
        List<Produto> list = pr.buscarPorNome(nome);
        
        return list;
    }

}
