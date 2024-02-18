package edu.ufersa.pweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/")
public class PessoaController {
    @GetMapping("")
    public String index() {
        return "index";
    }
    
}
