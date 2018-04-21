package br.com.drogaria.dao;

import java.util.Date;

import org.junit.Test;

import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Pessoa;

public class FuncionarioDAOTest {
	
	@Test
	public void salvar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario f1 = new Funcionario();
		PessoaDAO pdao = new PessoaDAO();
		Pessoa p = pdao.buscar(1L);
	
		f1.setPessoa(p);
		f1.setDataAdmissao(new Date(2018-05-01));
		f1.setCarteiraTrabalho("5845658");
		dao.salvar(f1);
	}
}
 