package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.CidadeDAO;
import br.com.drogaria.dao.EstadoDAO;
import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Cidade;
import br.com.drogaria.domain.Estado;
import br.com.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable{
	Pessoa pessoa;
	List<Pessoa> listPessoas;
	Estado estado;
	List<Estado> listEstados;
	List<Cidade> listCidades;
	
	//método chamado ao clicar no botão Novo
	public void novo() {
		pessoa = new Pessoa();  //inicia pessoa
		try {
			estado = new Estado();
			EstadoDAO edao = new EstadoDAO();
			listEstados = edao.listar("nome");        //obtém a lista de estados ordenado por nome
			
			listCidades = new ArrayList<>();		//inicia a lista de cidades
		} catch (RuntimeException erro) {
		Messages.addGlobalError("Erro para realizar o carregamento.");
		erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		try {
		PessoaDAO dao = new PessoaDAO();
		listPessoas = dao.listar();
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro para carregar a lista de pessoas");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
		PessoaDAO dao = new PessoaDAO();
		dao.merge(pessoa);
		Messages.addGlobalInfo("Pessoa salva com sucesso!");
		novo();
		listar();
	
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro para salvar pessoa");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
		estado = pessoa.getCidade().getEstado();
		
		EstadoDAO edao = new EstadoDAO();
		listEstados = edao.listar("nome");
		
		CidadeDAO cdao = new CidadeDAO();
		listCidades = cdao.buscaPorEstado(estado.getCodigo());
		
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro para editar pessoa");
			erro.printStackTrace();
		}
	}
	
	//Método para preencher as cidades conforme o estado selecionado. Necessário updade Ajax no view
	public void popular() {
		try {
			if (estado != null) {
				CidadeDAO dao = new CidadeDAO();
				listCidades = dao.buscaPorEstado(estado.getCodigo());
			} else {
				listCidades = new ArrayList<>(); //para iniciar o combo cidade na segunda utilização
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro para filtrar as cidades");
			erro.printStackTrace();
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getListPessoas() {
		
		return listPessoas;
	}

	public void setListPessoas(List<Pessoa> listPessoas) {
		this.listPessoas = listPessoas;
	}

	public List<Estado> getListEstados() {
		return listEstados;
	}

	public void setListEstados(List<Estado> listEstados) {
		this.listEstados = listEstados;
	}

	public List<Cidade> getListCidades() {
		return listCidades;
	}

	public void setListCidades(List<Cidade> listCidades) {
		this.listCidades = listCidades;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
