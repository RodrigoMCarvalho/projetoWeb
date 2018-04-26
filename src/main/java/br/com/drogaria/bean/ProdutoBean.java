package br.com.drogaria.bean;


import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.sql.Connection;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

//import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
//import br.com.drogaria.util.HibernateUtil;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperPrintManager;
//import net.sf.jasperreports.engine.JasperPrint;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	Produto produto;
	List<Produto> listProdutos;
	List<Fabricante> listFabricantes;
	
	public void novo() {
		produto = new Produto();
		
		try {
			FabricanteDAO dao = new FabricanteDAO();
			listFabricantes = dao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para listar o Fabricante.");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {
		try {
			if (produto.getCaminho() == null) {
				Messages.addGlobalError("O campo foto é obrigatório.");
				return;
			}
			
			ProdutoDAO dao = new ProdutoDAO();
	
			Produto produtoRetorno = dao.merge(produto);  //vai preencher o produto com os dados salvos ou editados para o salvamento de imagens
			
			//para salvamento de imagens
			Path origem = Paths.get(produto.getCaminho());									//será criado um arquivo com o codigo do produto .png
			Path destino = Paths.get("C:/Users/rodri_000/Desktop/Desenvolvimento Web/Projeto DrogariaV2/Uploads/" + produtoRetorno.getCodigo() + ".png");
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			Messages.addGlobalInfo("Produto salvo com sucesso!");
			novo();
			listar();
		} catch (RuntimeException | IOException erro) {   //Files pede o IOException 
			Messages.addGlobalError("Erro para salvar o produto.");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar() {
		ProdutoDAO dao = new ProdutoDAO();
		listProdutos = dao.listar();
	}
	
	public void editar(ActionEvent evento) {
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");
		produto.setCaminho("C:/Users/rodri_000/Desktop/Desenvolvimento Web/Projeto DrogariaV2/Uploads/" + produto.getCodigo() + ".png");
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.listar();
			FabricanteDAO fdao = new FabricanteDAO();
			listFabricantes = fdao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro para editar o produto");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		produto =(Produto)evento.getComponent().getAttributes().get("produtoSelecionado");
		try {
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(produto);
			
			Path arquivo = Paths.get("C:/Users/rodri_000/Desktop/Desenvolvimento Web/Projeto DrogariaV2/Uploads/" + produto.getCodigo() + ".png");
			Files.deleteIfExists(arquivo);
			
			novo();
			listar();
		} catch (RuntimeException | IOException  erro) {
			Messages.addGlobalError("Erro para excluir o produto");
			erro.printStackTrace();
		}
	}
	
	public void upload(FileUploadEvent evento) {
		try {
		UploadedFile arquivoUpload = evento.getFile();
		Path arquivoTemporario = Files.createTempFile(null, null);  //novo do arquivo e extensão
		
		produto.setCaminho(arquivoTemporario.toString()); // seta o caminho de destino
		//System.out.println("Caminho: " + produto.getCaminho()); Mosta o caminho do arquivo temporário
		Files.copy(arquivoUpload.getInputstream(),arquivoTemporario, StandardCopyOption.REPLACE_EXISTING); //origem, destino e opções
		Messages.addGlobalInfo("Upload realizado com sucesso!");
		}catch (IOException erro) {
			Messages.addGlobalError("Ocorreu um erro para realizar o upload do arquivo.");
			erro.printStackTrace();
		}
	}
	
//	public void imprimir() {
//		try {
//		String caminho = Faces.getRealPath("reports/produtos.jrxml"); //captura o caminho de execução
//		Map<String, Object> parametros = new HashMap<>(); //guarda um nome e um valor associado
//		Connection conexao = HibernateUtil.getConection(); //cria uma Connection
//		caminho = caminho.replaceAll("jrxml", "jasper");
//		
//		JasperPrint relatorio =JasperFillManager.fillReport(caminho, parametros, conexao);   //classe do Jasper para preencher relatórios
//		JasperPrintManager.printReport(relatorio, true);
//	} catch (JRException  erro) {
//		Messages.addGlobalError("Ocorreu um erro para gerar o relatório");
//		erro.printStackTrace();
//		}
//	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	} 

	public List<Produto> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<Produto> listProdutos) {
		this.listProdutos = listProdutos;
	}

	public List<Fabricante> getListFabricantes() {
		return listFabricantes;
	}

	public void setListFabricantes(List<Fabricante> listFabricantes) {
		this.listFabricantes = listFabricantes;
	}
	
	
}
