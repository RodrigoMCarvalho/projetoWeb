package br.com.drogaria.util;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


import br.com.drogaria.bean.AutenticacaoBean;
import br.com.drogaria.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		UIViewRoot uiViewRoot = facesContext.getViewRoot(); //serve para navegar na arvore de componentes
		String paginaAtual = uiViewRoot.getViewId();
		//System.out.println(paginaAtual);
		//se a página atual for autenticacao.xhtml a variável abaixo será TRUE
		boolean paginaAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		//System.out.println("É a página autenticação? " + paginaAutenticacao); 
		
		if (!paginaAutenticacao) {
			ExternalContext externalContext = facesContext.getExternalContext();
			Map<String, Object> mapa = externalContext.getSessionMap(); //captura todas as variáves da sessão
			AutenticacaoBean autenticacaoBean = (AutenticacaoBean) mapa.get("autenticacaoBean");
			
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			
			if(usuario == null) {
				Application application = facesContext.getApplication();
				NavigationHandler navigationHandler = application.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null, "/pages/autenticacao.xhtml?faces-redirect=true");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
