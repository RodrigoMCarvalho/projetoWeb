package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Funcionario;
import br.com.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable{

	private List<Funcionario> listFuncionarios;
	private List<Pessoa> listPessoas;
	private Funcionario funcionario;
	
	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			listFuncionarios = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para carregar os funcionários");
		}
	}
	
	public void novo() {
		funcionario = new Funcionario();
		try {
			PessoaDAO dao = new PessoaDAO();
			listPessoas = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para carregar lista de pessoas");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		funcionario = (Funcionario)evento.getComponent().getAttributes().get("funcionarioSelecionado");
		try {
			PessoaDAO dao = new PessoaDAO();
			listPessoas = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para editar funcionário");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.excluir(funcionario);
			novo();
			listar();
			Messages.addGlobalInfo("Funcionário excluído com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para excluir funcionário");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.merge(funcionario);
			novo();
			listar();
			Messages.addGlobalInfo("Funcionário salvo com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para salvar o funcionário");
			erro.printStackTrace();
		}
	}

	public List<Funcionario> getListFuncionarios() {
		return listFuncionarios;
	}

	public void setListFuncionarios(List<Funcionario> listFuncionarios) {
		this.listFuncionarios = listFuncionarios;
	}

	public List<Pessoa> getListPessoas() {
		return listPessoas;
	}

	public void setListPessoas(List<Pessoa> listPessoas) {
		this.listPessoas = listPessoas;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
}
