/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.campo.util;

import com.efficace.componente.excecoes.DigitoNumericoException;
import com.efficace.componente.excecoes.MascaraException;
import com.efficace.componente.util.Util;

/**
 * FormataDecimal.java
 * @author Fabio Sicupira Cavalcante - Brasil, 21/04/2011
 * @version 0.1-SNAPTSHOT 21/04/2011
 */
public class FormataDecimal extends FormataDados {
	
	private static final long serialVersionUID = 4217338560109348529L;
	
	private String caractereDigitoMilhar = ".";
	private String caractereDigitoDecimal = ",";
	private int quantidadeDigitosDecimais = 2;

	public FormataDecimal(String mascaraFormatada, int quantidadeDigitos) throws MascaraException {
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
        	
        	String numeroSemFormato = Util.completaStringAEsquerda(this.digitos.toString(), "0", this.quantidadeDigitosDecimais + 1);
        	return Util.formataNumeroDecimal(numeroSemFormato, 
    		this.caractereDigitoMilhar, this.caractereDigitoDecimal, 
    		this.quantidadeDigitos, this.quantidadeDigitosDecimais);
        	
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
    		String[] mascaraFormatadaSplit = this.mascaraFormatada.split(",");
    		if (mascaraFormatadaSplit.length <= 1 || mascaraFormatadaSplit.length > 2) {
    			throw new MascaraException();
    		}
    		this.quantidadeDigitosDecimais = mascaraFormatadaSplit[1].length();
    		this.caractereDigitoDecimal = ",";
    		this.caractereDigitoMilhar = ".";
    	} else if (this.mascaraFormatada.contains(".")) {
    		String[] mascaraFormatadaSplit = this.mascaraFormatada.split("\\.");
    		if (mascaraFormatadaSplit.length <= 1 || mascaraFormatadaSplit.length > 2) {
    			throw new MascaraException();
    		}
    		this.quantidadeDigitosDecimais = mascaraFormatadaSplit[1].length();
    		this.caractereDigitoDecimal = ".";
    		this.caractereDigitoMilhar = ",";
    	}
    }

}
