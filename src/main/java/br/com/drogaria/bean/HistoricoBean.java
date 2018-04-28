package br.com.drogaria.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class HistoricoBean implements Serializable{
	private Produto produto;
	
	@PostConstruct
	public void novo() {
		produto = new Produto();
	}
	
	public void buscar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			Produto resultado = dao.buscar(produto.getCodigo());
			
			if (resultado == null) {
				produto = new Produto();
				Messages.addGlobalWarn("Não existe produto cadastrado para o código informado");
			} else {
				produto = resultado; //se nao for null, produto recebe o resultado da busca.
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para buscar o produto");
			erro.printStackTrace();
		}
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
