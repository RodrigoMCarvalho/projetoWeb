package br.com.drogaria.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("drogaria") //http://localhost:8080/Drogaria/rest/drogaria
public class DrogariaService {
	@GET
	public String exibir() {
		return "Curso de Java";
	}
}
