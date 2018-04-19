package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.ItemVenda;
import br.com.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
public class VendaBean implements Serializable {
	
	List<Produto> listProdutos;
	List<ItemVenda> listItemVendas;
	Produto produto;
	
	@PostConstruct
	public void listar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			listProdutos = dao.listar();
			
			listItemVendas = new ArrayList<>();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro para carregar os produtos");
		}
	}
	
	public void adicionar(ActionEvent evento) {
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(new Short("1"));
		itemVenda.setValorParcial(produto.getPreco());
		
		listItemVendas.add(itemVenda);
	}

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}

	public List<ItemVenda> getListItemVendas() {
		return listItemVendas;
	}

	public void setListItemVendas(List<ItemVenda> listItemVendas) {
		this.listItemVendas = listItemVendas;
	}
	
	
}
