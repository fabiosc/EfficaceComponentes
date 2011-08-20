/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.campo.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;

/**
 * FormatoNumeroSimples.java
 * @author Fabio Sicupira Cavalcante - Brasil, 21/04/2011
 * @version 0.1-SNAPTSHOT 21/04/2011
 */
public class FormatoNumeroSimples extends FormatoDados {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4447395362613727055L;
	
	private String valorMascara;
    private String digitoMilhar = "";
    
    /**
     * Construtor da classe
     * @throws BadLocationException 
     */
    public FormatoNumeroSimples() throws BadLocationException {
        this.valorMascara = "0";
        configuraPontoDecimal(this.valorMascara);
        configuraMascara(this.valorMascara);
        set(valorMascara);
    }

    /**
     * Construtor da classe
     * @param mascara String - Máscara para formatação de dados
     * @throws BadLocationException 
     */
    public FormatoNumeroSimples(String mascara) throws BadLocationException {
        configuraPontoDecimal(mascara);
        configuraMascara(mascara);
        set(this.valorMascara);
    }
    
    /**
     * Configura o ponto decimal no padrão americado ou brasileiro
     * @param mascara String - Máscara para a formatação dos dados
     */
    private void configuraPontoDecimal(String mascara){
        if (mascara.contains(".")){
            this.digitoMilhar = ".";
        } else if (mascara.contains(",")){
            this.digitoMilhar = ",";
        } else {
            this.digitoMilhar = "";
        }
    }
    
    /**
     * Configura a máscara atribuindo zeros no lugar das cerquilhas
     * @param mascara String - Máscara para a formatação dos dados
     */
    private void configuraMascara(String mascara){
        this.valorMascara = mascara.replaceAll("[#]", "0");
        if (getTamanhoMaximo() == 0){
            setTamanhoMaximo(10);
        }
        this.valorMascara = this.valorMascara.replaceAll("[,]", "").replaceAll("[.]", "");
        setTamanhoMaximoDecimal(0);
        setPossuiDecimal(false);
    }
    
    

    @Override
    public String formata(String numeroSemFormato) {
        if (numeroSemFormato.isEmpty()) {
            return this.valorMascara;
        }
        return numeroSemFormato;
    }

    @Override
    public String formata(String numeroSemFormato, String decimalSemFormato) {
        if (numeroSemFormato.isEmpty()) {
            return this.valorMascara;
        }
        return numeroSemFormato;
    }

    @Override
    public Double getValorCampo() {
        String valorCampo = "";
        try {
            valorCampo = get();
        } catch (BadLocationException ex) {
            Logger.getLogger(FormatoDecimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.digitoMilhar.matches("[,.]")){
            valorCampo = valorCampo.replaceAll("[,.]", "");
        }
        return new Double(valorCampo);
    }

    @Override
    public String getValorCampoString() {
        String valorCampo = "";
        try {
            valorCampo = get();
        } catch (BadLocationException ex) {
            Logger.getLogger(FormatoQuantidade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.digitoMilhar.matches("[,.]")){
            valorCampo = valorCampo.replaceAll("[,.]", "");
        }
        return valorCampo;
    }
    
}
