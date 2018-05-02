package br.com.drogaria.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import br.com.drogaria.dao.CaixaDAO;
import br.com.drogaria.dao.FuncionarioDAO;
import br.com.drogaria.domain.Caixa;
import br.com.drogaria.domain.Funcionario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CaixaBean implements Serializable {
	private ScheduleModel caixas;
	private Caixa caixa;
	private List<Funcionario> listFuncionarios;

	@PostConstruct
	public void listar() {
		caixas = new DefaultScheduleModel();
	}

	public void novo(SelectEvent evento) {
		caixa = new Caixa();
		caixa.setDataAbertura((Date) evento.getObject());
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			listFuncionarios = fdao.listarOrdenado();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para listar os funcionários");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		Calendar calendar = Calendar.getInstance();  //ao selecionar uma data no calendário, é selecionada um dia depois
		calendar.setTime(caixa.getDataAbertura());	 //no view é convertido usando f:convertDateTime
		calendar.add(Calendar.DATE, 1);				 //no bean é realizado a conversão ao lado
		caixa.setDataAbertura(calendar.getTime());
		
		try {
			CaixaDAO dao = new CaixaDAO();
			dao.salvar(caixa);
			Messages.addGlobalInfo("Caixa aberto com sucesso.");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para abrir a caixa");
			erro.printStackTrace();
		}
	}

	public ScheduleModel getCaixas() {
		return caixas;
	}

	public void setCaixas(ScheduleModel caixas) {
		this.caixas = caixas;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public List<Funcionario> getListFuncionarios() {
		return listFuncionarios;
	}

	public void setListFuncionarios(List<Funcionario> listFuncionarios) {
		this.listFuncionarios = listFuncionarios;
	}

}
