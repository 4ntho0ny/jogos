package br.edu.ifms.jogos.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.jogos.Repository.EstiloRepository;
import br.edu.ifms.jogos.Repository.JogosRepository;
import br.edu.ifms.jogos.model.Estilo;
import br.edu.ifms.jogos.model.Jogo;

@Controller
public class JogosController {

	@Autowired
	JogosRepository jogosRepository;
	
	@Autowired
	EstiloRepository estiloRepository;

	@RequestMapping("/")
	public String listarJogos(Model model) {
		List<Jogo> listaJogos = jogosRepository.findAll();
		model.addAttribute("jogos", listaJogos);	
		return "/listar-jogos";
	}

	@GetMapping("/novo")
	public String adicionar(Model model) {
		List<Estilo> estilos = estiloRepository.findAll();
		model.addAttribute("jogo", new Jogo());
		model.addAttribute("listaEstilos", estilos);	
		return "/publica-criar-jogo";
	}
	
	@PostMapping("/salvar")
	public String salvarUsuario(@Valid Jogo jogo, BindingResult result, 
				RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/publica-criar-jogo";
		}	
		jogosRepository.save(jogo);
		return "redirect:/";
	}
	
	@GetMapping("/admin/editar/{id}")
	public String editar( @PathVariable("id") Long id, Model model) {
		List<Estilo> estilos = estiloRepository.findAll();
		Optional<Jogo> oldJogo = jogosRepository.findById(id);
		if(!oldJogo.isPresent()){
			throw new IllegalArgumentException("Invalid game:"+id);
		}
		Jogo jogo = oldJogo.get();
		model.addAttribute("jogo", jogo);
		model.addAttribute("listaEstilos", estilos);	
		return "/auth/admin/admin-editar-jogo";
	}

	@PostMapping("/admin/editar/{id}")
	public String editar(@PathVariable("id") Long id, @Valid Jogo jogo, 
		BindingResult result) {
			if (result.hasErrors()) {
				jogo.setId(id);
				return "/auth/admin/admin-editar-jogo";
			}
			jogosRepository.save(jogo);
			return "redirect:/";
	}

	@GetMapping("/admin/apagar/{id}")
	public String deleteJogo(@PathVariable("id") long id, Model model) {
		Jogo jogo = jogosRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		jogosRepository.delete(jogo);
	    return "redirect:/";
	}

}

	