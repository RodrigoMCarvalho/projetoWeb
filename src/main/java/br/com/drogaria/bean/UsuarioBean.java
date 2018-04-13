package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.dao.UsuarioDAO;
import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	Usuario usuario;
	List<Usuario> listUsuarios;
	List<Pessoa> listPessoas;

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			listUsuarios = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para carregar a lista de usuários");
		}
	}

	public void novo() {
		usuario = new Usuario(); // inicializa usuario
		try {
			PessoaDAO dao = new PessoaDAO();
			listPessoas = dao.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para carregar lista de pessoas");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.merge(usuario);
			novo();
			listar();
			Messages.addGlobalInfo("Usuário salvo com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para salvar o usuário.");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		try {
			UsuarioDAO dao = new UsuarioDAO();
			listUsuarios = dao.listar();
			PessoaDAO pdao = new PessoaDAO();
			listPessoas = pdao.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro para editar o usuário.");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.excluir(usuario);
			Messages.addFlashGlobalInfo("Usuário excluído com sucesso!");
			novo();
			listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro para excluir o usuário.");
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public List<Pessoa> getListPessoas() {
		return listPessoas;
	}

	public void setListPessoas(List<Pessoa> listPessoas) {
		this.listPessoas = listPessoas;
	}

}
