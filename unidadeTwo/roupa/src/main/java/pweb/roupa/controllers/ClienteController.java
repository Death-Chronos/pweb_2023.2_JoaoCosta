package pweb.roupa.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pweb.roupa.dtos.ClienteDTO;
import pweb.roupa.entities.Cliente;
import pweb.roupa.services.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService cs;

    @GetMapping("")
    public ModelAndView listarClientes() {
        return new ModelAndView("listaClientes", "clientes", cs.findAll());
    }
    
    @PostMapping("/salvar")
    public String salvarCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);
        cs.save(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarCliente(Cliente cliente,@PathVariable Long id) {
        cs.update(cliente, id);
        
        return "redirect:/clientes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        cs.delete(id);
        return "redirect:/clientes";
        
    }
    
    
}
