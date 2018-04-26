package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.ClienteDAO;
import br.com.drogaria.dao.PessoaDAO;
import br.com.drogaria.domain.Cliente;
import br.com.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	
	private List<Cliente> listClientes;
	private List<Pessoa> listPessoas;
	private Cliente cliente;
	
	public void salvar() {
		try {
			ClienteDAO dao = new ClienteDAO();
			dao.merge(cliente);
			novo();
			listar();
			Messages.addGlobalInfo("Cliente salvo com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para salvar o cliente.");
			erro.printStackTrace();
		}
	}
	
	public void novo() {
		cliente = new Cliente();
		try {
		PessoaDAO dao = new PessoaDAO();
		listPessoas = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao carregar a listagem de estados.");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct   //é chamado automáticamente quando clientebean é criado
	public void listar() {
		try {
			ClienteDAO dao = new ClienteDAO();
			listClientes = dao.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao carregar a listagem de clientes.");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		try {
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
		PessoaDAO dao = new PessoaDAO();
		listPessoas = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para editar o cliente");
			erro.printStackTrace();
		}
	}

	public List<Cliente> getListClientes() {
		return listClientes;
	}

	public void setListClientes(List<Cliente> listClientes) {
		this.listClientes = listClientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pessoa> getListPessoas() {
		return listPessoas;
	}

	public void setListPessoas(List<Pessoa> listPessoas) {
		this.listPessoas = listPessoas;
	}
	
}
