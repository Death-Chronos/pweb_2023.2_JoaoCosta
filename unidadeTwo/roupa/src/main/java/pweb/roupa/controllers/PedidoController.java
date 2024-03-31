package pweb.roupa.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pweb.roupa.controllers.exceptions.ResourceNotFoundException;
import pweb.roupa.entities.Pagamento;
import pweb.roupa.entities.Pedido;
import pweb.roupa.entities.PedidoProduto;
import pweb.roupa.entities.Produto;
import pweb.roupa.entities.enums.Metodo;
import pweb.roupa.services.ClienteService;
import pweb.roupa.services.PedidoService;
import pweb.roupa.services.ProdutoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ClienteService clienteService;

    @GetMapping("")
    public ModelAndView listarPedidos() {
        return new ModelAndView("listaPedidos", "pedidos", pedidoService.findAll());
    }

    @GetMapping("/escolherCliente/{id}")
    public ModelAndView escolherCliente(@PathVariable Long id) {
        Pedido pedido = new Pedido();
        if (clienteService.existsById(id)) {

            pedido.setCliente(clienteService.findById(id));
            System.out.println("Escolher CLiente: " + pedido.getCliente().getId());
            pedidoService.save(pedido);
        } else {
            throw new ResourceNotFoundException("Cliente não encontrado com o id: " + id);
        }
        return catalogoProdutos(pedido.getId());
    }

    @GetMapping("/catalogo")
    public ModelAndView catalogoProdutos(Long id) {
        ModelAndView md = new ModelAndView("loja");

        md.addObject("produtos", produtoService.findAll());
        md.addObject("idPedido", id);

        return md;
    }

    @PostMapping("/{id}/adicionarProdutos")
    public String adicionarProdutos(@RequestParam List<Long> idsProdutos, @PathVariable Long id) {
        Pedido pedido = pedidoService.findById(id);

        for (Long idProduto : idsProdutos) {
            Produto produto = produtoService.findById(idProduto);
            PedidoProduto pedidoProduto = new PedidoProduto(produto, pedido, produto.getPrecoVenda());
            pedidoService.saveItem(pedidoProduto);
            pedido.adicionarItem(pedidoProduto);
        }
        pedidoService.save(pedido);
        System.out.println("adicionarProduto: " + pedido.getCliente().getId());
        return "redirect:/pedidos/" + pedido.getId() + "/finalizar";
    }

    @GetMapping("{id}/finalizar")
    public ModelAndView finalizarPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.findById(id);

        ModelAndView md = new ModelAndView("finalizarPedido");

        md.addObject("pedido", pedido);
        md.addObject("metodosPagamento", Metodo.values());


        return md;
    }

    @PostMapping("/finalizar")
    public String finalizarPedido(Pedido pedido) {

        Pagamento pagamento = new Pagamento(null,pedido.getPagamento().getMetodoPagamento(), pedido);
        pedido.setPagamento(pagamento);
        // System.out.println("Finalizar: " + pedido.getCliente().getId());

        pedidoService.save(pedido);

        return "redirect:/pedidos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return "redirect:/pedidos";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarPedido(@PathVariable Long id) {
        ModelAndView md = new ModelAndView("edicaoPedido", "pedido", pedidoService.findById(id));
        
        md.addObject("metodosPagamento", Metodo.values());
        List<Long> clientesId = clienteService.findAll().stream().map(x -> x.getId()).collect(Collectors.toList());
        md.addObject("clientes", clientesId);
        return md;
    }

    @PostMapping("/{id}/atualizar")
    public String atualizarPedido(@RequestParam("metodoPagamento")Metodo metodo,@RequestParam("clienteId") Long clienteID, @PathVariable Long id) {
        
        pedidoService.update(metodo,clienteService.findById(clienteID), id);
        return "redirect:/pedidos";
    }
    
    
    

}
