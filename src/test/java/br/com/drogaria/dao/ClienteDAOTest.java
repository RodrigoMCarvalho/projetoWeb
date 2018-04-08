package br.com.drogaria.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Cliente;
import br.com.drogaria.domain.Pessoa;

public class ClienteDAOTest {
	@Test
	@Ignore
	public void salvar() {
		PessoaDAO pdao = new PessoaDAO();
		Pessoa pessoa = pdao.buscar(1L);
		
		Cliente c1 = new Cliente();
		c1.setDataCadastro(new Date());
		c1.setLiberado(true);
		c1.setPessoa(pessoa);
		
		ClienteDAO cdao = new ClienteDAO();
		cdao.salvar(c1);
	}
	
	@Test
	public void buscar() {
		ClienteDAO dao = new ClienteDAO();
		Cliente c1 = dao.buscar(1L);
		
		System.out.println("Cliente: "+ c1.getPessoa().getNome()+ "\n" +
						   "Data: " + c1.getDataCadastro()+ "\n" +
						   "Liberado: "+ c1.getLiberado());
	}
}
