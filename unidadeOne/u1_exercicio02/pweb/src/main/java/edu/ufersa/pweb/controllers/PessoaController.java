package edu.ufersa.pweb.controllers;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.ufersa.pweb.models.Pessoa;
import edu.ufersa.pweb.repositories.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class PessoaController {

    @Autowired
    PessoaRepository pr;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/listarPessoas")
    public ModelAndView listarPessoas(){
        ModelAndView mv = new ModelAndView("listarPessoas");
        mv.addObject("pessoas", pr.findAll());
        return mv;
    }

    @GetMapping("/adicionarPessoa")
    public ModelAndView adicionarPessoa() {
        ModelAndView mv = new ModelAndView("adicionarPessoa");
        mv.addObject("pessoa", new Pessoa());
        return mv;
    }
    @PostMapping("/salvar")
    public String salvarPessoa(@Valid Pessoa pessoa) {
        pr.save(pessoa);
        return "redirect:/adicionarPessoa";
    }
    @GetMapping("/editar/{id}")
    public ModelAndView editarPessoa( @PathVariable Long id) {
        ModelAndView mv = new ModelAndView("adicionarPessoa");
        mv.addObject("pessoa", pr.getReferenceById(id));
        return mv;
    }
    @PutMapping("salvar/{id}")
    public String salvarPessoaEditada(@PathVariable Long id, Pessoa pessoa) {
        Optional<Pessoa> pe = pr.findById(id);
        if (pe.isEmpty()) {
            return "redirect:/";
        }
        pr.save(pessoa);
        return "redirect:/listarPessoas";
    }
    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Long id) {
        pr.deleteById(id);
        return "redirect:/";
    }
    
    
    
       

    
}
