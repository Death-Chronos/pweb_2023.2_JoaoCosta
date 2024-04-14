package pweb.roupa.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pweb.roupa.dtos.ProdutoDTO;
import pweb.roupa.entities.Produto;
import pweb.roupa.entities.enums.Tecido;
import pweb.roupa.services.ProdutoService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService ps;

    @GetMapping("")
    public ModelAndView listarProdutos() {
        return new ModelAndView("listaProdutos", "produtos", ps.findAll());
    }

    @GetMapping("/form")
public ModelAndView formProduto() {
    Produto produto = new Produto();
    ModelAndView mv = new ModelAndView("formProduto");
    mv.addObject("produto", produto);
    mv.addObject("tecidos", Tecido.values());

    return mv;
}


    @PostMapping("/salvar")
    public String salvarProduto(ProdutoDTO dto) {
        System.out.println(dto.marca());
        Produto produto = new Produto();
        BeanUtils.copyProperties(dto, produto);
        ps.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarProduto(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("edicaoProduto");
        mv.addObject("produto", ps.findById(id));
        mv.addObject("tecidos", Tecido.values());

        return mv;
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarProduto(Produto produto, @PathVariable Long id) {
        ps.update(produto, id);
        return "redirect:/produtos";
    }

    @GetMapping("/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        ps.delete(id);
        return "redirect:/produtos";

    }

    @GetMapping("/buscar")
    public ModelAndView buscarPorNome(@RequestParam String nome) {
        try {
            nome = URLDecoder.decode(nome, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("listaProdutos","produtos",ps.buscarProdutosPorNome(nome));
        return mv ;
    }
    

}
