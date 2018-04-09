package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;

@ManagedBean
public class EstadoBean {
	public void salvar() {
		Messages.addGlobalInfo("Salvo com sucesso!");
		
	}
}
