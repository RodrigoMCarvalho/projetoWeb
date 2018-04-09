package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.drogaria.dao.ClienteDAO;
import br.com.drogaria.domain.Cliente;
import br.com.drogaria.domain.Pessoa;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	
	private List<Cliente> listClientes;
	private Pessoa pessoa;
	private Cliente cliente;
	
	public void salvar() {
		try {
			
			
			
		} catch (RuntimeException e) {
			// TODO: handle exception
		}
	}
	
	public void novo() {
		pessoa = new Pessoa();
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

	public List<Cliente> getListClientes() {
		return listClientes;
	}

	public void setListClientes(List<Cliente> listClientes) {
		this.listClientes = listClientes;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
