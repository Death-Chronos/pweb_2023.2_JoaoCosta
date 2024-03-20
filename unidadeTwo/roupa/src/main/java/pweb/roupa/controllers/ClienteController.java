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
import pweb.roupa.entities.Dependente;
import pweb.roupa.entities.composicao.Endereco;
import pweb.roupa.entities.enums.Genero;
import pweb.roupa.services.ClienteService;
import pweb.roupa.services.DependenteService;


@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService cs;

    @Autowired
    DependenteService ds;

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
        mv.addObject("generos", Genero.values());

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
    public ModelAndView editarCliente(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("edicaoCliente");
        mv.addObject("cliente", cs.findById(id));
        return mv;
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarCliente(Cliente cliente, @PathVariable Long id) {
        cs.update(cliente, id);
        return "redirect:/clientes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Long id) {
        cs.delete(id);
        return "redirect:/clientes";

    }
    @GetMapping("/{id}/dependentes")
    public ModelAndView listaDependentes(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("listaDependentes");

        mv.addObject("dependentes", cs.findById(id).getDependentes());
        mv.addObject("idCliente", id);

        return mv;
    }

    @GetMapping("/{id}/dependenteForm")
    public ModelAndView dependenteForm(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("formDependente");

        Dependente dp = new Dependente();
        dp.setCliente(cs.findById(id));
        System.out.println(dp.getCliente().getNome());

        mv.addObject("dependente", dp);
        mv.addObject("generos", Genero.values());

        return mv;
    }


    @PostMapping("/{id}/dependente/salvar")
    public String salvarDependente(Dependente dp, @PathVariable Long id) {
        cs.salvarDependente(dp, id);
        return "redirect:/clientes/"+id+"/dependentes";
    }

    @GetMapping("/dependente/deletar/{id}")
    public String deletarDependente(@PathVariable Long id) {
        cs.deletarDependente(id);
        return "redirect:/clientes";
    }

    @GetMapping("/dependente/editar/{id}")
    public ModelAndView editarDependente (@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("edicaoDependente");
        mv.addObject("dependente", cs.findDependenteById(id));
        mv.addObject("generos", Genero.values());
        return mv;
    }

    @PostMapping("/dependente/{id}/atualizar")
    public String atualizarDependente(@PathVariable Long id, Dependente dp) {
        System.out.println(id);
         Long idCliente =cs.atualizarDependente(id, dp);

        return "redirect:/clientes/"+idCliente+"/dependentes";
    }
    
    

}
