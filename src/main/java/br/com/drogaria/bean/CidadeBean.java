package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CidadeDAO;
import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Cidade;
import br.com.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	private Cidade cidade;
	private List<Cidade> listCidades;
	private List<Estado> listEstados;

	public void salvar() {
		try {
			CidadeDAO dao = new CidadeDAO();
			dao.salvar(cidade);
			Messages.addGlobalInfo("Cidade salva com sucesso!");
			novo();

			listar();
			EstadoDAO edao = new EstadoDAO(); // atualizar a lista de cidades e estados
			edao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para salvar a cidade.");
			erro.printStackTrace();
		}
	}

	// método para instanciar cidade
	public void novo() {
		cidade = new Cidade();

		try {
			EstadoDAO dao = new EstadoDAO();
			listEstados = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao carregar os estados");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		CidadeDAO dao = new CidadeDAO();
		listCidades = dao.listar();
	}

	public List<Cidade> getListCidades() {
		return listCidades;
	}

	public void setListCidades(List<Cidade> listCidades) {
		this.listCidades = listCidades;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Estado> getListEstados() {
		return listEstados;
	}

	public void setListEstados(List<Estado> listEstados) {
		this.listEstados = listEstados;
	}

}
