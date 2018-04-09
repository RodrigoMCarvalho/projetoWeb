package br.com.drogaria.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Fabricante f1 = new Fabricante();
		f1.setDescricao("EMS");
		
		FabricanteDAO dao = new FabricanteDAO();
		dao.salvar(f1);
	}
	
	@Test
	public void merge() {
		Fabricante f1 = new Fabricante();
		f1.setDescricao("Neo Química");
		
		FabricanteDAO dao = new FabricanteDAO();
		dao.merge(f1);
	}
}
