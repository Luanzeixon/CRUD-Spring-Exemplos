package br.edu.ifrn.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class cadastroController {
	
	@GetMapping("/cadastro")
	public String entrarCadastro() {
		return "usuario/cadastro";
	}
	
}
