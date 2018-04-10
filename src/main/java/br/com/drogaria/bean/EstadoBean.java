package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped // tempo de vida somente se estiver na tela de estado
public class EstadoBean implements Serializable {

	private Estado estado;
	private List<Estado> listEstados;

	public void salvar() {
		try {
			EstadoDAO dao = new EstadoDAO();
			dao.salvar(estado);
			Messages.addGlobalInfo("Estado salvo com sucesso!");
			novo(); // para reinstanciar e apagar o texto(update também no view)
			listar(); //atualizar a listagem na tabela (update também no view)
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para salvar o estado: " + erro);
			erro.printStackTrace(); // ativar log do erro no servidor
		}
	}

	// para inicializar o estado
	public void novo() {
		estado = new Estado();
	}

	@PostConstruct
	public void listar() {
		try {
			EstadoDAO dao = new EstadoDAO();
			listEstados = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para salvar o estado: " + erro);
			erro.printStackTrace(); // ativar log do erro no servidor
		}
	}

	// getComponent (componente clicado), getAttributes(todos os atributos dele)
	// "estadoSelecionado" é nome do evento enviado pelo view
	public void excluir(ActionEvent evento) {
		try {
		EstadoDAO dao = new EstadoDAO();
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		//Messages.addGlobalInfo("Nome: "+ estado.getNome()+" Sigla: "+estado.getSigla());
		dao.excluir(estado);
		Messages.addGlobalInfo("Estado excluído com sucesso");
		novo();// para reinstanciar e apagar o texto(update também no view)
		listar(); //atualizar a listagem na tabela (update também no view)
		
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para excluir o estado: " + erro);
			erro.printStackTrace(); // ativar log do erro no servidor
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
			EstadoDAO dao = new EstadoDAO();
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			dao.editar(estado);
			Messages.addGlobalInfo("Estado alterado com sucesso");
		} catch (RuntimeException erro) {
			 Messages.addGlobalError("Erro para editar o estado: " + erro);
			 erro.printStackTrace();
		}
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getListEstados() {
		return listEstados;
	}

	public void setListEstados(List<Estado> listEstados) {
		this.listEstados = listEstados;
	}

}
