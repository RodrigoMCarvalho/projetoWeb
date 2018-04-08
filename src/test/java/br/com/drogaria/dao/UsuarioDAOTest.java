package br.com.drogaria.dao;

import org.junit.Test;

import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	@Test
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
}
