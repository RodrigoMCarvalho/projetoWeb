package br.com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	@Ignore
	public void salvar() {
		PessoaDAO pdao = new PessoaDAO();
		Pessoa pessoa = pdao.buscar(1L);
		
		Usuario user = new Usuario();
		user.setAtivo(true);
		user.setSenha("12345");
		user.setTipo('A');
		user.setPessoa(pessoa);
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.salvar(user);
	}
	
	@Test
	public void listar() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> listUser = dao.listar();
		for (Usuario usuario : listUser) {
			System.out.println(usuario);
		}
	}
}
