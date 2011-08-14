/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.campo.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * FormatoDados.java
 * @author Fabio Sicupira Cavalcante - Brasil, 21/04/2011
 * @version 0.1-SNAPTSHOT 21/04/2011
 */
public abstract class FormatoDados extends PlainDocument{
    private StringBuilder numeros = new StringBuilder();
    private StringBuilder decimal = new StringBuilder();
    private Integer tamanhoMaximo = 0;
    private Integer tamanhoMaximoDecimal = 2;
    private Boolean possuiDecimal = true;
    private Boolean flagDecimal = false;
    private Boolean padraoBancario = true;

    /**
     * Retorna os números digitados sem formatação
     * @return StringBuilder - Números digitadis
     */
    public StringBuilder getNumeros() {
        return this.numeros;
    }

    /**
     * Atribui os números digitados
     * @param numeros Stringbuilder - Números digitados
     */
    public void setNumeros(StringBuilder numeros) {
        this.numeros = numeros;
    }

    /**
     * Retorna os números decimais digitados quandi não se usa o padrão bancário
     * @return StringBuilder - Números decimais
     */
    public StringBuilder getDecimal() {
        return this.decimal;
    }

    /**
     * Atribui os números decimais
     * @param decimal StringBuilder - Números digitais digitados
     */
    public void setDecimal(StringBuilder decimal) {
        this.decimal = decimal;
    }

    /**
     * Retorna a quantidade de caracteres que o campo pode receber
     * @return Inteiro - Quantide de caracteres que o campo pode receber
     */
    public Integer getTamanhoMaximo() {
        return this.tamanhoMaximo;
    }

    /**
     * Atribui a quantidade de caracteres que o campo pode receber
     * @param tamanhoMaximo Inteiro - Quantidade de caracteres que o campo pode receber
     */
    public void setTamanhoMaximo(Integer tamanhoMaximo) {
        this.tamanhoMaximo = tamanhoMaximo;
    }

    /**
     * Retorna a quantidade de digitos decimais que o campo pode receber
     * @return Inteiro - Quantidade de digitos decimais que o campo pode receber
     */
    public Integer getTamanhoMaximoDecimal() {
        return this.tamanhoMaximoDecimal;
    }

    /**
     * Atribui a quantidade de digitos decimais que o campos pode receber
     * @param tamanhoMaximoDecimal - Quantidade de digitos decimais que o campo pode receber
     */
    public void setTamanhoMaximoDecimal(Integer tamanhoMaximoDecimal) {
        this.tamanhoMaximoDecimal = tamanhoMaximoDecimal;
    }

    /**
     * Retorna true se o campo possui digitos decimais ou false
     * caso não possua
     * @return Boleano - True ou False caso o campo possua digitos decimais ou não
     */
    public Boolean getPossuiDecimal() {
        return this.possuiDecimal;
    }

    /**
     * Especifica se o campo possuirá digitos decimais ou não
     * @param possuiDecimal Boleano - True ou False caso o campo possua ou não digitos decimais
     */
    public void setPossuiDecimal(Boolean possuiDecimal) {
        this.possuiDecimal = possuiDecimal;
    }

    /**
     * Retorna true caso a tecla do ponto ou a virgula sejam pressionados
     * @return Boleano - True ou False caso o ponto ou a virgula sejam digitados
     */
    public Boolean getFlagDecimal() {
        return this.flagDecimal;
    }

    /**
     * Atribui True caso a tecla do ponto ou vírgula seja pressionada
     * @param flagDecimal 
     */
    public void setFlagDecimal(Boolean flagDecimal) {
        this.flagDecimal = flagDecimal;
    }

    /**
     * Retorna True ou False caso o campo siga ou não o padrão bancário de digitação
     * @return Boleano True ou False, caso o campo siga ou não o padrão bancário de digitação
     */
    public Boolean getPadraoBancario() {
        return padraoBancario;
    }

    /**
     * Atribui True ou False caso o campo siga o padrão bancário de digitação
     * @param padraoBancario Boleano - True ou False, caso o campo siga ou não o padrão bancário de digitação
     */
    public void setPadraoBancario(Boolean padraoBancario) {
        this.padraoBancario = padraoBancario;
    }

    /**
     * Atribui dados ao campo
     * @param str String - Valor do campo
     * @throws BadLocationException 
     */
    public void set(String str) throws BadLocationException {
        replace(0, getLength(), str, null);
    }

    /**
     * Retorna os dados do campo
     * @return String - dados do campo
     * @throws BadLocationException 
     */
    public String get() throws BadLocationException {
        return getText(0, getLength());
    }

    /**
     * Estendida de PlainDocument
     * @param offs
     * @param str
     * @param a
     * @throws BadLocationException 
     */
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (getPossuiDecimal()){
            if (get().length() == 0){
                super.insertString(offs, str, a);
            } else if ((getNumeros().length() + getDecimal().length()) < this.tamanhoMaximo){
                super.insertString(offs, str, a);
                insereNumero(str);
            }
        } else {
            if (get().length() == 0){
                super.insertString(offs, str, a);
            } else if (getNumeros().length() < this.tamanhoMaximo){
                super.insertString(offs, str, a);
                insereNumero(str);
            }
        }
    }

    /**
     * Extendida de PlaindDocument
     * @param offs
     * @param len
     * @throws BadLocationException 
     */
    @Override
    public void remove(int offs, int len) throws BadLocationException {
        super.remove(offs, len);
        removeNumero(offs, len);
    }

    /**
     * Trata os dados digitados
     * @param str String - Dados digitados
     * @throws BadLocationException 
     */
    private void insereNumero(String str) throws BadLocationException {
        //Remove virgula ou ponto digitados
        if (str.length() == 1) {
            if (str.matches("^[\\D]")) {
                if (str.matches("^[.,]")){
                    this.flagDecimal = true;
                }
                set(get().substring(0, get().length() - 1));
            }
        }
        //Remove qualquer caractere que não seja número
        if (get().length() > 1 && str.matches("^[\\d]")) {
            if (isPrimeiroDigitoValido(str)) {
                if (!getPossuiDecimal()){
                    if (getNumeros().length() < this.tamanhoMaximo){
                        this.numeros.append(str);
                    }
                } else {
                    if (getPadraoBancario()){
                        if (getNumeros().length() < this.tamanhoMaximo){
                            this.numeros.append(str);
                        }
                    } else {
                        if (!getFlagDecimal()){
                            if (getNumeros().length() < (this.tamanhoMaximo - this.tamanhoMaximoDecimal)){
                                this.numeros.append(str);
                            }
                        } else {
                            if (this.decimal.length() < this.tamanhoMaximoDecimal){
                                this.decimal.append(str);
                            }
                        }
                    }
                }
                remove(0, getLength());
                if (getPadraoBancario()){
                    set(formata(getNumeros().toString()));
                } else {
                    set(formata(getNumeros().toString(), getDecimal().toString()));
                }
            } else {
                remove(0, getLength());
                if (getPadraoBancario()){
                    set(formata(getNumeros().toString()));
                } else {
                    set(formata(getNumeros().toString(), getDecimal().toString()));
                }
            }
        }
    }

    /**
     * Remove número digitado
     * @param offs - Inteiro Posicao
     * @param len - Inteiro tamanho
     * @throws BadLocationException 
     */
    private void removeNumero(int offs, int len) throws BadLocationException {
        if (offs > 0) {
            if (!getPossuiDecimal()){
                if (this.numeros.length() > 0) {
                    this.numeros.deleteCharAt(this.numeros.length() - 1);
                }
            } else {
                if (!getFlagDecimal()){
                    if (this.numeros.length() > 0) {
                        this.numeros.deleteCharAt(this.numeros.length() - 1);
                    }
                } else {
                    if (this.decimal.length() > 0) {
                        this.decimal.deleteCharAt(this.decimal.length() - 1);
                    } else {
                        this.flagDecimal = false;
                    }
                    if (decimal.length() == 0){
                        this.flagDecimal = false;
                    }
                }
            }
            if (getPadraoBancario()){
                set(formata(getNumeros().toString()));
            } else {
                set(formata(getNumeros().toString(), getDecimal().toString()));
            }
        } else if (offs == 0 && len == 1){
            if (!getFlagDecimal()){
                if (this.numeros.length() > 0) {
                    this.numeros.deleteCharAt(this.numeros.length() - 1);
                }
            } else {
                if (this.decimal.length() > 0) {
                    this.decimal.deleteCharAt(this.decimal.length() - 1);
                } else {
                    this.flagDecimal = false;
                }
            }
            if (getPadraoBancario()){
                set(formata(getNumeros().toString()));
            } else {
                set(formata(getNumeros().toString(), getDecimal().toString()));
            }
        }
    }

    /**
     * Verifica se o primeiro digito digitado é válido
     * @param str String - Valor do primeiro digito
     * @return Boleano - True ou False caso o primeiro digito seja válido ou não
     */
    private Boolean isPrimeiroDigitoValido(String str){
        if (getNumeros().length() == 0 && getDecimal().length() == 0){
            if (str.contains("0")){
                return false;
            }
        }
        return true;
    }

    /**
     * Formata os dados digitados no padrão bancário
     * @param numeroSemFormato String - Número a ser formatado
     * @return String - Número formatado
     */
    public abstract String formata(String numeroSemFormato);
    
    /**
     * Formata os dados digitados no padrão calculadora
     * @param numeroSemFormato String - Número inteiro a ser formatado
     * @param decimalSemFormato String - Número decimal a ser formatado
     * @return String - Número formatado
     */
    public abstract String formata(String numeroSemFormato, String decimalSemFormato);
    
    /**
     * Retorna o valor como número duplo
     * @return Duplo -  Valor do campo
     */
    public abstract Double getValorCampo();
    
    /**
     * Retorna o valor em uma string com o formato no padrão americano
     * @return String - Valor do campo
     */
    public abstract String getValorCampoString();
    
/*
    FormatoMoeda ORIGINAL
     
    @Override
    public String formata(String numeroSemFormato) {
        if (numeroSemFormato.isEmpty()) {
            return "0,00";
        }
        int tamanhoTexto = getNumeros().length();
        int quantidadeClasses = Math.round(tamanhoTexto / 3);
        int resto = (tamanhoTexto % 3);

        StringBuilder regex = new StringBuilder();
        StringBuilder mask = new StringBuilder();

        if (quantidadeClasses == 0 && resto > 0) {
            regex.append("(\\d{").append(resto).append("})");
            mask.append((resto == 1 ? "0,0$1" : "0,$1"));
        } else {
            //PARA C = 1
            if (quantidadeClasses == 1) {
                regex.append("(\\d{").append(quantidadeClasses + resto).append("})");
                regex.append("(\\d{2})");
                mask.append("$1,$2");
                //PARA C > 1
            } else if (quantidadeClasses > 1) {
                int quantidadeClassesCompletas = (quantidadeClasses - 1);
                int posicaoPrimeiroDigito = (quantidadeClasses - quantidadeClassesCompletas) + resto;

                regex.append("(\\d{").append(posicaoPrimeiroDigito).append("})");
                mask.append("$1.");
                for (int i = 0; i < quantidadeClassesCompletas; i++) {
                    regex.append("(\\d{3})");
                    if (i < (quantidadeClassesCompletas - 1)) {
                        mask.append("$").append(i + 2).append(".");
                    } else {
                        mask.append("$").append(i + 2).append(",");
                    }
                }
                regex.append("(\\d{2})");
                mask.append("$").append((quantidadeClasses + 1));
            }
        }
        String resultado = Pattern.compile(regex.toString()).matcher(numeroSemFormato).replaceAll(mask.toString());
        return resultado;
    }
     
     
    FormataQuantidadeFracionada ORIGINAL
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
            mask.append((resto == 1 ? "0.00$1" : (resto==2 ? "0.0$1":"0.$1")));
        } else {
            //PARA C = 1
            if (quantidadeClasses == 1) {
                //System.out.println(" --> " + myDoc.getNumeros());
                if (resto == 0){
                    regex.append("(\\d{3})");
                    mask.append("0.$1");
                } else {
                    regex.append("(\\d{").append((resto>0?resto:quantidadeClasses)).append("})");
                    regex.append("(\\d{3})");
                    mask.append("$1.$2");
                }
                //PARA C > 1
            } else if (quantidadeClasses > 1) {

                if (resto > 0){
                    regex.append("(\\d{").append(resto).append("})");
                    mask.append("$1.");
                }
                for (int i = 0; i < quantidadeClasses; i++) {
                    regex.append("(\\d{3})");
                    if (resto == 0){
                        if (i < (quantidadeClasses - 1)) {
                            mask.append("$").append(i + 1).append(".");
                        } else {
                            mask.append("$").append(i + 1);
                        }
                    } else {
                        if (i < (quantidadeClasses - 1)) {
                            mask.append("$").append(i + 2).append(".");
                        } else {
                            mask.append("$").append(i + 2);
                        }
                    }
                }
            }
        }
        String resultado = Pattern.compile(regex.toString()).matcher(numeroSemFormato).replaceAll(mask.toString());
        //System.out.println("TEXTO: " + document.getNumeros() + " REGEX: " + regex.toString() + " MASK: " + mask.toString() + "\n" + resultado);
        return resultado;
    }
     
*/
    
}
