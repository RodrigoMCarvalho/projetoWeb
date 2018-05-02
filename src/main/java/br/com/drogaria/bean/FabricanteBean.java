package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {
	private Fabricante fabricante;
	private List<Fabricante> listFabricantes;

	@PostConstruct
	public void listar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			listFabricantes = dao.listar("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para carregar a lista de fabricantes");
		}
	}

	public void novo() {
		fabricante = new Fabricante();
	}

	public void excluir(ActionEvent evento) {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(fabricante);
			Messages.addGlobalInfo("Fabricante excluído com sucesso!");
			novo();
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para excluir o fabricante");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
	}
	
	public void salvar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.merge(fabricante);
			Messages.addGlobalInfo("Fabricante salvo com sucesso!");
			novo();
			listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para salvar o fabricante.");
			erro.printStackTrace();
		}
	}
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getListFabricantes() {
		return listFabricantes;
	}

	public void setListFabricantes(List<Fabricante> listFabricantes) {
		this.listFabricantes = listFabricantes;
	}

}
