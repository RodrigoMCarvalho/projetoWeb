package br.com.drogaria.dao;

import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import br.com.drogaria.domain.Cidade;
import br.com.drogaria.domain.Estado;

public class CidadeDAOTest {
	@Test
	@Ignore
	public void salvar() {
		
		EstadoDAO edao = new EstadoDAO();
		Estado estado = edao.buscar(2L);
		
		Cidade cidade = new Cidade();
		CidadeDAO dao = new CidadeDAO();
		cidade.setNome("Campinas");
		cidade.setEstado(estado);
		dao.salvar(cidade);
	}
	
	@Test
	@Ignore
	public void listar() {
		
		CidadeDAO dao = new CidadeDAO();
		List<Cidade> listCidades = dao.listar();
		
		for (Cidade cidade : listCidades) {
			System.out.println("Código: " + cidade.getCodigo());
			System.out.println("Cidade: " + cidade.getNome());
			System.out.println("Estado: " + cidade.getEstado().getNome() + ", " 
					+ cidade.getEstado().getSigla());
			System.out.println("");
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		
		CidadeDAO dao = new CidadeDAO();
		Cidade cidade = dao.buscar(1L);
		System.out.println(cidade);
	}
	
	@Test
	public void editar() {
		CidadeDAO dao = new CidadeDAO();
		Cidade cidade = dao.buscar(3L);
		cidade.setNome("Osasco");
		
		dao.editar(cidade);
		
	}
}
