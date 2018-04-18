package br.com.drogaria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

@Path("fabricante") //http://localhost:8080/Drogaria/rest/fabricante
public class FabricanteService {
	@GET
	public String listar() {
		FabricanteDAO dao = new FabricanteDAO();
		List<Fabricante> listFabricantes = dao.listar("descricao");
		
		Gson gson = new Gson();
		String json = gson.toJson(listFabricantes); //converter Gson para String
		
		return json;
	}
	
	@GET //http://localhost:8080/Drogaria/rest/fabricante/1
	@Path("{codigo}") //número digitado na URL
	public String buscar (@PathParam("codigo") Long codigo) {
		FabricanteDAO dao = new FabricanteDAO();
		Fabricante f1 = dao.buscar(codigo);
		
		Gson gson = new Gson();
		String json = gson.toJson(f1);
		
		return json;
	}
	
	@POST //http://localhost:8080/Drogaria/rest/fabricante
	public String salvar(String json) {
		Gson gson = new Gson();
		//pegar a string que veio do navegador e converter para um objeto tipo Fabricante
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		
		FabricanteDAO dao = new FabricanteDAO();
		dao.merge(fabricante);
		
		String jsonSaida = gson.toJson(fabricante);	
		
		return jsonSaida;
	
	}
	
	@PUT //http://localhost:8080/Drogaria/rest/fabricante
	public String editar(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		
		FabricanteDAO dao = new FabricanteDAO();
		dao.editar(fabricante);
		
		String jsonSaida = gson.toJson(fabricante);
		
		return jsonSaida;
		
	}
	
	@DELETE //http://localhost:8080/Drogaria/rest/fabricante
	public String excluir(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		
		FabricanteDAO dao = new FabricanteDAO();
		fabricante = dao.buscar(fabricante.getCodigo());
		
		dao.excluir(fabricante);
		String jsonSaida = gson.toJson(fabricante);
		
		return jsonSaida;
	}
}
