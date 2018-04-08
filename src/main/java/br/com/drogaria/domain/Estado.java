package br.com.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Estado extends GenericDomain{
	
	@Column(length = 50, nullable = false)
	String nome;
	@Column(length= 2, nullable = false)
	String sigla;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	@Override
	public String toString() {
		return "Estado [nome=" + nome + ", sigla=" + sigla + "]";
	}
	
	
}
