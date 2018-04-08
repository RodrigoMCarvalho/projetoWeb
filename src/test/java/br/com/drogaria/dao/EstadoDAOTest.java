package br.com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Estado;

public class EstadoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("São Pausslo");
		estado.setSigla("SP");
		
		EstadoDAO dao = new EstadoDAO();
		dao.salvar(estado);
	}
	
	@Test
	@Ignore
	public void editar() {
		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscar(5L);
		estado.setNome("Minas Gerais");
		estado.setSigla("MG");
		
		dao.editar(estado);
	}
	
	@Test
	@Ignore
	public void listar() {
		EstadoDAO dao = new EstadoDAO();
		List<Estado> listEstado = dao.listar();
		
		for (Estado estado : listEstado) {
			System.out.println(estado);
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscar(1L);
		
		System.out.println(estado);
	}
	
	@Test
	@Ignore
	public void excluir() {
		EstadoDAO dao = new EstadoDAO();
		Estado estado = dao.buscar(3L);
		
		if (estado == null) {
			System.out.println("Estado não localizado.");
		} else {
			dao.excluir(estado);
			System.out.println("Estado excluído com sucesso.");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
