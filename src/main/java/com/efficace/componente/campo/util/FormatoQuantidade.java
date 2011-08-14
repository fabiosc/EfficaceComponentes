/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.campo.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;

/**
 * FormatoQuantidade.java
 * @author Fabio Sicupira Cavalcante - Brasil, 21/04/2011
 * @version 0.1-SNAPTSHOT 21/04/2011
 */
public class FormatoQuantidade extends FormatoDados{
    private String valorMascara;
    private String digitoMilhar = "";
        
    /**
     * Construtor da classe
     * @throws BadLocationException 
     */
    public FormatoQuantidade() throws BadLocationException {
        this.valorMascara = "0";
        configuraPontoDecimal(this.valorMascara);
        configuraMascara(this.valorMascara);
        set(this.valorMascara);
    }

    /**
     * Construtor da classe
     * @param mascara String - Máscara para formatação de dados
     * @throws BadLocationException 
     */
    public FormatoQuantidade(String mascara) throws BadLocationException {
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
            this.digitoMilhar = ".";
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
    
    /**
     * Formata os dados digitados no padrão bancário
     * @param numeroSemFormato String - Número a ser formatado
     * @return String - Número formatado
     */
    @Override
    public String formata(String numeroSemFormato) {
        if (numeroSemFormato.isEmpty()) {
            return this.valorMascara;
        }
        int tamanhoTexto = getNumeros().length();
        int quantidadeClasses = Math.round(tamanhoTexto / 3);
        int resto = (tamanhoTexto % 3);

        StringBuilder regex = new StringBuilder();
        StringBuilder mask = new StringBuilder();

        if (quantidadeClasses == 0 && resto > 0) {
            regex.append("(\\d{").append(resto).append("})");
            mask.append("$1");
        } else {
            //PARA C = 1
            if (quantidadeClasses == 1) {
                if (resto == 0){
                    regex.append("(\\d{3})");
                    mask.append("$1");
                } else {
                    regex.append("(\\d{").append((resto>0?resto:quantidadeClasses)).append("})");
                    regex.append("(\\d{3})");
                    mask.append("$1").append(this.digitoMilhar).append("$2");
                }
                //PARA C > 1
            } else if (quantidadeClasses > 1) {

                if (resto > 0){
                    regex.append("(\\d{").append(resto).append("})");
                    mask.append("$1").append(this.digitoMilhar);
                }
                for (int i = 0; i < quantidadeClasses; i++) {
                    regex.append("(\\d{3})");
                    if (resto == 0){
                        if (i < (quantidadeClasses - 1)) {
                            mask.append("$").append(i + 1).append(this.digitoMilhar);
                        } else {
                            mask.append("$").append(i + 1);
                        }
                    } else {
                        if (i < (quantidadeClasses - 1)) {
                            mask.append("$").append(i + 2).append(this.digitoMilhar);
                        } else {
                            mask.append("$").append(i + 2);
                        }
                    }
                }
            }
        }
        String resultado = Pattern.compile(regex.toString()).matcher(numeroSemFormato).replaceAll(mask.toString());
        return resultado;
    }

    /**
     * Formata os dados digitados no padrão calculadora
     * @param numeroSemFormato String - Número inteiro a ser formatado
     * @param decimalSemFormato String - Número decimal a ser formatado
     * @return String - Número formatado
     */
    @Override
    public String formata(String numeroSemFormato, String decimalSemFormato) {
        if (numeroSemFormato.isEmpty()) {
            return this.valorMascara;
        }
        int tamanhoTexto = getNumeros().length();
        int quantidadeClasses = Math.round(tamanhoTexto / 3);
        int resto = (tamanhoTexto % 3);

        StringBuilder regex = new StringBuilder();
        StringBuilder mask = new StringBuilder();

        if (quantidadeClasses == 0 && resto > 0) {
            regex.append("(\\d{").append(resto).append("})");
            mask.append("$1");
        } else {
            //PARA C = 1
            if (quantidadeClasses == 1) {
                if (resto == 0){
                    regex.append("(\\d{3})");
                    mask.append("$1");
                } else {
                    regex.append("(\\d{").append((resto>0?resto:quantidadeClasses)).append("})");
                    regex.append("(\\d{3})");
                    mask.append("$1").append(this.digitoMilhar).append("$2");
                }
                //PARA C > 1
            } else if (quantidadeClasses > 1) {

                if (resto > 0){
                    regex.append("(\\d{").append(resto).append("})");
                    mask.append("$1").append(this.digitoMilhar);
                }
                for (int i = 0; i < quantidadeClasses; i++) {
                    regex.append("(\\d{3})");
                    if (resto == 0){
                        if (i < (quantidadeClasses - 1)) {
                            mask.append("$").append(i + 1).append(this.digitoMilhar);
                        } else {
                            mask.append("$").append(i + 1);
                        }
                    } else {
                        if (i < (quantidadeClasses - 1)) {
                            mask.append("$").append(i + 2).append(this.digitoMilhar);
                        } else {
                            mask.append("$").append(i + 2);
                        }
                    }
                }
            }
        }
        String resultado = Pattern.compile(regex.toString()).matcher(numeroSemFormato).replaceAll(mask.toString());
        return resultado;
    }

    /**
     * Retorna o valor como número duplo
     * @return Duplo -  Valor do campo
     */
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

    /**
     * Retorna o valor em uma string com o formato no padrão americano
     * @return String - Valor do campo
     */
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
