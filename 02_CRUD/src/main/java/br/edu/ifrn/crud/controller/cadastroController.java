package br.edu.ifrn.crud.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import br.edu.ifrn.crud.dominio.Usuario;

@Controller
@RequestMapping("/usuarios")
public class cadastroController {
	
	@GetMapping("/cadastro")
	public String entrarCadastro(ModelMap model) {
		model.addAttribute("usuario", new Usuario()); //envia uma variavel usuario que corresponde a um novo usuario em branco que sera defenida na pagina.
		return "usuario/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvar(Usuario usuario, HttpSession sessao) { //já que nao tem BD o usuario vai ser salvo na memoria(seção do servidorpor isso o httpsession
		//Pegando valores da sessão
		Integer id = (Integer) sessao.getAttribute("id");
		List<Usuario> usuariosCadastrados = (List<Usuario>) sessao.getAttribute("usuariosCadastrados");
		
		if(id == null) { //1 id cadastrado
			id = 1;
		}
		if(usuariosCadastrados == null) //1- se não tiver nenhum usuario cadastrado ainda
			usuariosCadastrados = new ArrayList<>(); //2- criar um array list com os usuarios
		
		usuario.setId(id); //adicionar o id ao usuario
		usuariosCadastrados.add(usuario); //adicionar o usuario ao array de usuario
		id++;
		
		sessao.setAttribute("id", id); //salvar na memoria
		sessao.setAttribute("usuariosCadastrados", usuariosCadastrados); //salvar na memoria
		
		
		return "usuario/cadastro";
	}
	
	@ModelAttribute("profissoes")
	public List<String> getProfissoes(){
		return Arrays.asList("Professor","Medico","Advogado","Bombeiro","Policial","Outro");
	}
}
