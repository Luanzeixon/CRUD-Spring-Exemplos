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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String salvar(Usuario usuario, RedirectAttributes attr, HttpSession sessao) { //já que nao tem BD o usuario vai ser salvo na memoria(seção do servidorpor isso o httpsession
		//RedirectAttributes serve como um modelMap mas quando retorna redirect em vez de forward
		//Pegando valores da sessão
		Integer id = (Integer) sessao.getAttribute("id"); //pesquisar na memoria(sessao) se tem algum id igual o id colocado no form
		List<Usuario> usuariosCadastrados = (List<Usuario>) sessao.getAttribute("usuariosCadastrados"); // ver se tem alguma lista de usuraios existente na memoria
		
		if(id == null) { //1 id cadastrado
			id = 1;
		}
		if(usuariosCadastrados == null) //1- se não tiver nenhum usuario cadastrado ainda
			usuariosCadastrados = new ArrayList<>(); //2- criar um array list com os usuarios
		
		usuario.setId(id); //adicionar o id ao usuario
		usuariosCadastrados.add(usuario); //adicionar o usuario ao array de usuario
		id++;
		
		sessao.setAttribute("id", id); //salvar na memoria o novo id para ser pesquisado na linha 33(comeco do codigo slavar)
		sessao.setAttribute("usuariosCadastrados", usuariosCadastrados); //salvar na memoria a lista de usuarios
		
		attr.addFlashAttribute("msgSucesso", "Cadastro realizado com sucesso!");
		//Tem que ser Flash pois é um atributo temporario.
		
		return "redirect:/usuarios/cadastro"; //usar redirect na url usuarios, para "apagar" os contatos salvos do form (tem que colocar /)
	}
	
	@ModelAttribute("profissoes")
	public List<String> getProfissoes(){
		return Arrays.asList("Professor","Medico","Advogado","Bombeiro","Policial","Outro");
	}
}
