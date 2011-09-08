package com.efficace.componente.excecoes;

public class MascaraException extends Exception {

	private static final long serialVersionUID = 906213080313453056L;

	public MascaraException() {
		super("A máscara informada não é válida");
	}
	
	public MascaraException(String message) {
		super(message);
	}
}
