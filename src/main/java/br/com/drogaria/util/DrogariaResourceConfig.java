package br.com.drogaria.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest") //http://localhost:8080/Drogaria/rest
public class DrogariaResourceConfig extends ResourceConfig{
	public DrogariaResourceConfig() {
		packages("br.com.drogaria.service");
	}
}
