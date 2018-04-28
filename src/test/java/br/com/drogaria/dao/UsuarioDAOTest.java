package br.com.drogaria.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

public class UsuarioDAOTest {
	@Test
	@Ignore
	public void salvar() {
		PessoaDAO pdao = new PessoaDAO();
		Pessoa pessoa = pdao.buscar(3L);
		
		System.out.println("Pessoa encontrada:");
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("CPF: " + pessoa.getCpf());
		
		Usuario user = new Usuario();
		user.setAtivo(true);
		user.setPessoa(pessoa);
		user.setSenhaSemCriptografia("1234");
		user.setTipo('B');
		
		//configura o tipo de criptografia, MD5 e o objeto NÃO criptografado
		SimpleHash hash = new SimpleHash("md5", user.getSenhaSemCriptografia());  
		user.setSenha(hash.toHex());
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.salvar(user);
	}
	
	@Test
	@Ignore
	public void listar() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> listUser = dao.listar();
		for (Usuario usuario : listUser) {
			System.out.println(usuario);
		}
	}
}
