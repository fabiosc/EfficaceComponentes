/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 07-09-2011
 */
package com.efficace.componente.campo.util;

import com.efficace.componente.excecoes.DigitoNumericoException;
import com.efficace.componente.excecoes.MascaraException;
import com.efficace.componente.util.Util;

/**
 * FormataInteiro.java
 * @author Fabio Sicupira Cavalcante - Brasil, 07/09/2011
 * @version 0.1-SNAPTSHOT 07/09/2011
 */
public class FormataInteiro extends FormataDados {
	
	private static final long serialVersionUID = -4937420504903116581L;
	
	private String caractereDigitoMilhar = ".";

	public FormataInteiro(String mascaraFormatada, int quantidadeDigitos) throws MascaraException {
		super.mascaraFormatada = mascaraFormatada;
		super.quantidadeDigitos = quantidadeDigitos;
		configuraDigitos();
	}
	
    public String formata(String string, boolean insereDigito) throws DigitoNumericoException  {
    	
        if (isPrimeiroDigitoValido(string)
        		&& Util.isNumero(string)
        		&& !Util.isPontoOuVirgula(string)) {
        	
        	if (insereDigito) {
        		this.digitos.append(string);
        	}
        	
            String numeroSemFormato = this.digitos.toString();
        	return Util.formataNumeroInteiro(numeroSemFormato, 
        		this.caractereDigitoMilhar,  
        		this.quantidadeDigitos );
        } else {
        	throw new DigitoNumericoException();
        }
        
    }
    
    private Boolean isPrimeiroDigitoValido(String string){
    	if (this.digitos.length() < 1 
    			&& string != null 
    			&& !string.equals("") 
    			&& string.substring(0, 1) .contains("0")){
            return false;
        }
        return true;
    }
    
    private void configuraDigitos() throws MascaraException {
    	if (this.mascaraFormatada.contains(",")) {
    		this.caractereDigitoMilhar = ",";
    		this.mascaraFormatada = this.mascaraFormatada.substring(0, 1);
    	} else {
    		this.caractereDigitoMilhar = ".";
    		this.mascaraFormatada = this.mascaraFormatada.substring(0, 1);
    	}
    }
    

}
