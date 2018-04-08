package br.com.drogaria.dao;

import java.math.BigDecimal;

import org.junit.Test;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTest {

	@Test
	public void salvar() {
		
		FabricanteDAO fdao = new FabricanteDAO();
		Fabricante f1 = fdao.buscar(1L);
		
		Produto produto = new Produto();
		produto.setDescricao("Catafran");
		produto.setFabricante(f1);
		produto.setPreco(new BigDecimal("13.70"));
		produto.setQuantidade(new Short("8"));
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(produto);
	}
}
