package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class TesteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String email;
	private String estado;
	private String cidade;
	private String pais;
	private String telefone;
	private String sexo;
	private List<String> listEstados = new ArrayList<>();
	private List<String> listCidades = new ArrayList<>();
	private List<String> listPaises = new ArrayList<>();
	
	public TesteBean() {
		listEstados.add("RJ");
		listEstados.add("SP");
		listEstados.add("MG");
		listEstados.add("SC");
		
		listPaises.add("Brasil");
		listPaises.add("Bulgária");
		listPaises.add("Bélgica");
		listPaises.add("Argentina");
		listPaises.add("Canadá");
		listPaises.add("Chile");
	}
	
	public void carregarCidades() {
		listCidades.clear();
		if (estado.equals("RJ")) {
			listCidades.add("Niterói");
			listCidades.add("Rio das Ostras");
			listCidades.add("Rio de Janeiro");
		} else if (estado.equals("SP")) {
			listCidades.add("Barueri");
			listCidades.add("Santos");
			listCidades.add("São Paulo");
		} else if (estado.equals("MG")) {
			listCidades.add("Belo Horizonte");
		}
	}
	
	public void verificarLogin() {
		//simula a demora no processamento
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (login.equals("rodrigo")) {
			Messages.addGlobalWarn("Login em uso");
		} else {
			Messages.addGlobalInfo("Login disponível!");
		}
	}
	
	public List<String> sugerirPaises(String consulta) {
		List<String> paisesSugeridos = new ArrayList<>();
		for (String pais : listPaises) {
			if (pais.toLowerCase().startsWith(consulta.toLowerCase())) { //compara letras minusculas
				paisesSugeridos.add(pais);
			}
		}
		return paisesSugeridos;	
	}
	
	public void salvar() {
		System.out.println("Login: "+ login);
		System.out.println("Email: " + email);
		
		Messages.addGlobalInfo("Cadastro realizado com sucesso");
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getListEstados() {
		return listEstados;
	}

	public List<String> getListCidades() {
		return listCidades;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<String> getListPaises() {
		return listPaises;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
}
