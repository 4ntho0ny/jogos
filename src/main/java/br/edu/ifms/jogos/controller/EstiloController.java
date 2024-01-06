package br.edu.ifms.jogos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifms.jogos.Repository.EstiloRepository;
import br.edu.ifms.jogos.model.Estilo;

@Controller
@RequestMapping("/estilos")
public class EstiloController {
    
    @Autowired EstiloRepository estiloRepository;

    @GetMapping("/novo")
    public String novoEstilo(Model model){
        model.addAttribute("estilo", new Estilo());
        return "/publica-criar-estilos";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Estilo estilo, BindingResult result){
        if(result.hasErrors()){
            return "/publica-criar-estilos";
        }
        estiloRepository.save(estilo);
        System.out.println(estilo.getNomeEstilo());
        return "redirect:/";
    }
}
