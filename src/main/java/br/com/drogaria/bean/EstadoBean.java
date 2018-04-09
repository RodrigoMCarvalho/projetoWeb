package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped             //tempo de vida somente se estiver na tela de estado
public class EstadoBean implements Serializable {

	private Estado estado;
	private List<Estado> listEstados;

	public void salvar() {
		try {
		EstadoDAO dao = new EstadoDAO();
		dao.salvar(estado);
		Messages.addGlobalInfo("Estado salvo com sucesso!");
		novo(); //para reinstanciar e apagar o texto(update também no view)
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para salvar o estado: " + erro);
			erro.printStackTrace(); //ativar log do erro no servidor
		}
	}
	//para inicializar o estado
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
			erro.printStackTrace(); //ativar log do erro no servidor
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
