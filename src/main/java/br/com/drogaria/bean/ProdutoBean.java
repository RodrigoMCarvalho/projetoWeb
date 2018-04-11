package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	Produto produto;
	List<Produto> listProdutos;
	List<Fabricante> listFabricantes;
	
	public void novo() {
		produto = new Produto();
		
		try {
			FabricanteDAO dao = new FabricanteDAO();
			listFabricantes = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para listar o Fabricante.");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.merge(produto);
			Messages.addGlobalInfo("Produto salvo com sucesso!");
			novo();
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para salvar o produto.");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		ProdutoDAO dao = new ProdutoDAO();
		listProdutos = dao.listar();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}

	public List<Fabricante> getListFabricantes() {
		return listFabricantes;
	}

	public void setListFabricantes(List<Fabricante> listFabricantes) {
		this.listFabricantes = listFabricantes;
	}
	
	
}
