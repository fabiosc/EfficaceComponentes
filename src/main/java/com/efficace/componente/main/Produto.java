package com.efficace.componente.main;

import java.io.Serializable;

public class Produto implements Serializable {
	
	private static final long serialVersionUID = 980849606788305501L;
	
	private String nome;
	private String valor;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", valor=" + valor + "]\n";
	}
	
	
}
