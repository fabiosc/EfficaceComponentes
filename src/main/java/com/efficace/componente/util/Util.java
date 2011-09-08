package com.efficace.componente.util;

import java.util.regex.Pattern;

public class Util {
	public static String completaStringAEsquerda(String string, String caractere, int quantidadeDigitos){
		return String.format("%1$" + quantidadeDigitos +"s" , string).replaceAll(" ", caractere);
	}
	
	public static String completaStringADireita(String string, String caractere, int quantidadeDigitos){
		return String.format("%1$-" + quantidadeDigitos +"s" , string).replaceAll(" ", caractere);
	}
	
    public static String mascaraFormatada(String mascara){
    	String mascaraFormatada = mascara;
    	if (mascara.contains("#")){
    		return mascaraFormatada.replace("#", "0");
    	}
    	return "";
    }
    
    public static boolean isNumero(String string){
    	if (string.matches("^[\\d]+")){
    		return true;
    	}
    	return false;
    }
    
    public static boolean isPontoOuVirgula(String string){
        if (string.contains(".") || string.contains(",")) {
            return true;
        }
        return false;
    }
    
    public static String formataNumeroDecimal(
    		String numeroSemFormato, 
    		String caractereDigitoMilhar, 
    		String caractereDigitoDecimal, 
    		int quantidadeDigitos, 
    		int quantidadeDigitosDecimais){
    	
        StringBuilder regex = new StringBuilder();
        StringBuilder mask = new StringBuilder();
        
        int tamanhoTexto = numeroSemFormato.trim().length();
        int resto = tamanhoTexto - quantidadeDigitosDecimais;
        int quantidadeClasses = Math.round(resto/3);
        quantidadeClasses = (quantidadeClasses==1?1:quantidadeClasses--);
        int auxQuantidadeClasses = quantidadeClasses;
        
        while (resto > 3){
            resto = resto - 3;
            quantidadeClasses++;
        }
        
        quantidadeClasses = quantidadeClasses - auxQuantidadeClasses;
        regex.append("(\\d{").append(resto).append("})");
        
        for (int i = 1; i <= quantidadeClasses; i++){
            regex.append("(\\d{3})");
        }
        
        regex.append("(\\d{").append(quantidadeDigitosDecimais).append("})");
        
        for (int i = 1; i <= (quantidadeClasses + 2); i++){
            mask.append("$").append(i);
            if (i < (quantidadeClasses + 1)) {
                mask.append(caractereDigitoMilhar);
            } else if ((i == (quantidadeClasses + 1))) {
                mask.append(caractereDigitoDecimal);
            }
        }
        
        String resultado = Pattern.compile(regex.toString()).matcher(numeroSemFormato).replaceAll(mask.toString());
        return resultado.trim();
    }
    
    public static String formataNumeroInteiro(String numeroSemFormato, 
    		String caractereDigitoMilhar, 
    		int quantidadeDigitos){
    	
        StringBuilder regex = new StringBuilder();
        StringBuilder mask = new StringBuilder();
        
        int tamanhoTexto = numeroSemFormato.trim().length();
        int resto = tamanhoTexto;
        int quantidadeClasses = Math.round(resto/3);
        quantidadeClasses = (quantidadeClasses==1?1:quantidadeClasses--);
        int auxQuantidadeClasses = quantidadeClasses;
        
        while (resto > 3){
            resto = resto - 3;
            quantidadeClasses++;
        }
        
        quantidadeClasses = quantidadeClasses - auxQuantidadeClasses;
        regex.append("(\\d{").append(resto).append("})");
        
        for (int i = 1; i <= quantidadeClasses; i++){
            regex.append("(\\d{3})");
        }
        
        if (quantidadeClasses == 0) {
            mask.append("$").append(1);
        } else {
	        for (int i = 1; i <= quantidadeClasses; i++){
	            mask.append("$").append(i);
            	mask.append(caractereDigitoMilhar);
	        }
            mask.append("$").append(quantidadeClasses + 1);
        }
        
        String resultado = Pattern.compile(regex.toString()).matcher(numeroSemFormato).replaceAll(mask.toString());
        return resultado.trim();
    	
    }
    
    public static String caixaAlta(String string) {
    	return string.toUpperCase();
    }

}
