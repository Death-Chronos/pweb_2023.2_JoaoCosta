package pweb.roupa.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pweb.roupa.controllers.exceptions.ResourceNotFoundException;
import pweb.roupa.entities.Cliente;
import pweb.roupa.entities.Pedido;
import pweb.roupa.entities.PedidoProduto;
import pweb.roupa.entities.PK.PedidoProdutoPK;
import pweb.roupa.entities.enums.Metodo;
import pweb.roupa.repositories.PedidoProdutoRepository;
import pweb.roupa.repositories.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pr;

    @Autowired
    PedidoProdutoRepository itemRepository;

    public List<Pedido> findAll() {
        return pr.findAll();
    }

    public Pedido findById(Long id) {
        Optional<Pedido> optional = pr.findById(id);
        return optional.orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado com o id:" + id));
    }

    public Pedido save(Pedido pedido) {
        pedido.setData_criacao(Instant.now());
        return pr.save(pedido);
    }

    public void delete(Long id) {
        if (pr.existsById(id)) {
            Pedido pedido = findById(id);
            for (int i = 0; i < pedido.getItens().size(); i++) { 
                deleteItem(pedido.getItens().get(i).getId());
            }
            pr.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Pedido não encontrado com o id:" + id);
        }
    }

    public void update(Metodo metodo,Cliente cliente, Long id) {
        if (pr.existsById(id)) {
            Pedido pedido = findById(id);

            pedido.getPagamento().setMetodoPagamento(metodo);
            pedido.setCliente(cliente);
            
            pr.save(pedido);
        } else {
            throw new ResourceNotFoundException("Pedido não encontrado com o id:" + id);
        }
    }

    public void saveItem(PedidoProduto pp) {
        itemRepository.save(pp);
    }
    
    public void deleteItem(PedidoProdutoPK id) {
        itemRepository.deleteById(id);
    }
}
