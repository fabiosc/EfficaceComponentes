/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.campo.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import com.efficace.componente.excecoes.DigitoException;

/**
 * FormataDados.java
 * @author Fabio Sicupira Cavalcante - Brasil, 21/04/2011
 * @version 0.1-SNAPTSHOT 21/04/2011
 */
public abstract class FormataDados extends PlainDocument{

	private static final long serialVersionUID = 3487665593490143688L;
	
	protected String mascaraFormatada = "";
    protected StringBuilder digitos = new StringBuilder();
    protected int quantidadeDigitos = 10;
    

    public StringBuilder getDigitos(){
    	return this.digitos;
    }
    
    public void setDigitos(StringBuilder digitos){
    	this.digitos = digitos;
    }
    
    public int getQuantidadeDigitos() {
		return this.quantidadeDigitos;
	}

	public void setQuantidadeDigitos(int quantidadeDigitos) {
		this.quantidadeDigitos = quantidadeDigitos;
	}
	
	public void set(String string) {
		try {
			replace(0, getLength(), string, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}		
	}
	
	public String get() {
		try {
			return getText(0, getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	@Override
	public void replace(int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
		
    	String resultado = (get().equals("")?this.mascaraFormatada:get());
		int tamanho = getLength();

		if (this.digitos.length() < this.quantidadeDigitos) {
			try {
				resultado = formata(string, true);
			} catch (DigitoException e) {}
		}
		
		super.replace(0, tamanho, resultado, null);
		
	}
	
    @Override
    public void remove(int offset, int length) throws BadLocationException {
    	
        if (this.digitos.length() > 0){
            this.digitos.deleteCharAt(this.digitos.length() - 1);
        }
        
        super.remove(offset, length);
        
    	String resultado = this.mascaraFormatada;
		int tamanho = getLength();
		
		try {
			resultado = formata(this.digitos.toString(), false);
		} catch (DigitoException e) {}
		
		super.replace(0, tamanho, resultado, null);
		
    }
    
    public abstract String formata(String string, boolean insereDigito) throws DigitoException;
    
}
