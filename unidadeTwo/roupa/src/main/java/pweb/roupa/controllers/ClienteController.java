package pweb.roupa.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pweb.roupa.dtos.ClienteDTO;
import pweb.roupa.entities.Cliente;
import pweb.roupa.entities.composicao.Endereco;
import pweb.roupa.entities.enums.Genero;
import pweb.roupa.services.ClienteService;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService cs;

    @GetMapping("")
    public ModelAndView listarClientes() {
        return new ModelAndView("listaClientes", "clientes", cs.findAll());
    }

    @GetMapping("/form")
    public ModelAndView formCliente() {
        Cliente cliente = new Cliente();
        cliente.setEndereco(new Endereco());
        ModelAndView mv = new ModelAndView("formCliente");
        mv.addObject("cliente", cliente);
        mv.addObject("generos",Genero.values());


        return mv;
    }
    
    
    @PostMapping("/salvar")
    public String salvarCliente(ClienteDTO dto) {
        System.out.println(dto.email());
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(dto, cliente);
        System.out.println(cliente.getEmail());
        cs.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarCliente(@PathVariable Long id ){
        ModelAndView mv = new ModelAndView("edicaoCliente");
        mv.addObject("cliente", cs.findbyId(id));
        return mv;
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
