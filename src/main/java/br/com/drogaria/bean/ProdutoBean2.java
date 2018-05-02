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
public class ProdutoBean2 implements Serializable{
	
	private Long codigoProduto;
	private Produto produto;
	private List<Produto> listProdutos;
	private List<Fabricante> listFabricantes;
	private ProdutoDAO produtoDAO;
	private FabricanteDAO fabricanteDAO;
	
	@PostConstruct
	public void iniciar() {
		produtoDAO = new ProdutoDAO();
		fabricanteDAO = new FabricanteDAO();
	}
	
	public void listar() {
		try {
			listProdutos = produtoDAO.listar("descricao");
			listFabricantes = fabricanteDAO.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para carregar a listagem.");
			erro.printStackTrace();
		}
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
	public void setListFabricantes(List<Fabricante> listFaricantes) {
		this.listFabricantes = listFaricantes;
	}

	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}


	
}
