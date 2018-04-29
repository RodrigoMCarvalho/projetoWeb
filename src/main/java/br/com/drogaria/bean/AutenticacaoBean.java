package br.com.drogaria.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.drogaria.dao.UsuarioDAO;
import br.com.drogaria.domain.Pessoa;
import br.com.drogaria.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;
	
	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		usuario.setPessoa(new Pessoa()); //para evitar nullpoint
	}
	
	public void logar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuarioLogado = dao.autenticar(usuario.getPessoa().getCpf(), usuario.getSenha());
			if (usuarioLogado != null) {
				Faces.redirect("./pages/principal.xhtml");
			} else {
				Messages.addGlobalError("Usuário e/ou senha inválidos");
				return;
			}
			
			
		} catch (RuntimeException | IOException erro){
			Messages.addGlobalError(erro.getMessage());
			erro.printStackTrace();
		} 
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
}
