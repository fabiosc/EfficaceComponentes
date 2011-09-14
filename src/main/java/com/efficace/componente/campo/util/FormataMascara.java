package com.efficace.componente.campo.util;

import com.efficace.componente.excecoes.DigitoException;
import com.efficace.componente.excecoes.DigitoNumericoException;
import com.efficace.componente.util.Util;

public class FormataMascara extends FormataDados {

	private static final long serialVersionUID = 2958970736244927052L;

	public FormataMascara(String mascaraFormatada) {
		super.mascaraFormatada = mascaraFormatada;
		configuraQuantidadeDigitos();
	}
	
	public String formata(String string, boolean insereDigito) throws DigitoException {
        if (isPrimeiroDigitoValido(string)
        		&& Util.isNumero(string)
        		&& !Util.isPontoOuVirgula(string)) {
        	
        	if (insereDigito) {
        		this.digitos.append(string);
        	}
        	
        	String numeroSemFormato = Util.completaStringAEsquerda(this.digitos.toString(), "0", this.quantidadeDigitos);
        	return Util.formataMascara(this.mascaraFormatada, numeroSemFormato); 
        } else {
        	throw new DigitoNumericoException();
        }
	}
	
	private void configuraQuantidadeDigitos() {
		int quantidadeSeparadores = 0;
		for(int i = 0; i < this.mascaraFormatada.length(); i++) {
			String digito = this.mascaraFormatada.substring(i, i + 1);
			if (!Util.isNumero(digito)){
				quantidadeSeparadores++;
			}
		}
		this.quantidadeDigitos = this.mascaraFormatada.length() - quantidadeSeparadores;
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
	

}
