package com.efficace.componente.excecoes;

public class DigitoException extends Exception {

	private static final long serialVersionUID = 3235934642543239299L;
	
	public DigitoException(){
		super("O dígito informado não é um dígito válido");
	}
	
	public DigitoException(String message) {
		super(message);
	}
	
}