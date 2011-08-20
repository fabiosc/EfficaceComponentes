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
    /**
	 * 
	 */
	private static final long serialVersionUID = 3487665593490143688L;
	
	private StringBuilder numeros = new StringBuilder();
    private StringBuilder decimal = new StringBuilder();
    private Integer tamanhoMaximo = 0;
    private Integer tamanhoMaximoDecimal = 2;
    private Boolean possuiDecimal = true;
    private Boolean flagDecimal = false;
    private Boolean padraoBancario = true;

    /**
     * Retorna os numeros digitados sem formataçao
     * @return StringBuilder - Numeros digitadis
     */
    public StringBuilder getNumeros() {
        return this.numeros;
    }

    /**
     * Atribui os numeros digitados
     * @param numeros - StringBuilder - Numeros digitados
     */
    public void setNumeros(StringBuilder numeros) {
        this.numeros = numeros;
    }

    /**
     * Retorna os numeros decimais digitados quandi nao se usa o padrao bancario
     * @return StringBuilder - Numeros decimais
     */
    public StringBuilder getDecimal() {
        return this.decimal;
    }

    /**
     * Atribui os numeros decimais
     * @param decimal - StringBuilder - Numeros referente aos digitos, informados (digitados)
     */
    public void setDecimal(StringBuilder decimal) {
        this.decimal = decimal;
    }

    /**
     * Retorna a quantidade de caracteres que o campo pode receber
     * @return Integer - Quantide de caracteres que o campo pode receber
     */
    public Integer getTamanhoMaximo() {
        return this.tamanhoMaximo;
    }

    /**
     * Atribui a quantidade de caracteres que o campo pode receber
     * @param tamanhoMaximo - Integer - Quantidade de caracteres que o campo pode receber
     */
    public void setTamanhoMaximo(Integer tamanhoMaximo) {
        this.tamanhoMaximo = tamanhoMaximo;
    }

    /**
     * Retorna a quantidade de digitos decimais que o campo pode receber
     * @return Integer - Quantidade de digitos decimais que o campo pode receber
     */
    public Integer getTamanhoMaximoDecimal() {
        return this.tamanhoMaximoDecimal;
    }

    /**
     * Atribui a quantidade de digitos decimais que o campos pode receber
     * @param tamanhoMaximoDecimal - Integer - Quantidade de digitos decimais que o campo pode receber
     */
    public void setTamanhoMaximoDecimal(Integer tamanhoMaximoDecimal) {
        this.tamanhoMaximoDecimal = tamanhoMaximoDecimal;
    }

    /**
     * Retorna true se o campo possui digitos decimais ou false
     * caso nao possua
     * @return Boolean - True ou False caso o campo possua digitos decimais ou nao
     */
    public Boolean getPossuiDecimal() {
        return this.possuiDecimal;
    }

    /**
     * Especifica se o campo possuira digitos decimais ou nao
     * @param possuiDecimal - Boolean - True ou False caso o campo possua ou nao digitos decimais
     */
    public void setPossuiDecimal(Boolean possuiDecimal) {
        this.possuiDecimal = possuiDecimal;
    }

    /**
     * Retorna true caso a tecla do ponto ou a virgula sejam pressionados
     * @return Boolean - True ou False caso o ponto ou a virgula sejam digitados
     */
    public Boolean getFlagDecimal() {
        return this.flagDecimal;
    }

    /**
     * Atribui Abtribui True caso a tecla referente a virgula ou ao ponto seja pressionada
     * @param flagDecimal - Boolean - Abtribui True caso a tecla referente a virgula ou ao ponto seja pressionada 
     */
    public void setFlagDecimal(Boolean flagDecimal) {
        this.flagDecimal = flagDecimal;
    }

    /**
     * Retorna True ou False caso o campo siga ou nao o padrao bancario de digitaçao
     * @return Boolean - True ou False, caso o campo siga ou nao o padrao bancario de digitaçao
     */
    public Boolean getPadraoBancario() {
        return padraoBancario;
    }

    /**
     * Atribui True ou False caso o campo siga o padrao bancario de digitaçao
     * @param padraoBancario - Boolean - True ou False, caso o campo siga ou nao o padrao bancario de digitaçao
     */
    public void setPadraoBancario(Boolean padraoBancario) {
        this.padraoBancario = padraoBancario;
    }

    /**
     * Atribui dados ao campo
     * @param str - String - Valor do campo
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

    @Override
    public void remove(int offs, int len) throws BadLocationException {
        super.remove(offs, len);
        removeNumero(offs, len);
    }

    /**
     * Trata os dados digitados
     * @param str - String - Dados digitados
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
        //Remove qualquer caractere que nao seja numero
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
     * Remove numero digitado
     * @param offs - Integer - Posicao
     * @param len - Integer - tamanho
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
     * Verifica se o primeiro digito digitado e valido
     * @param str - String - Valor do primeiro digito
     * @return Boolean - True ou False caso o primeiro digito seja valido ou nao
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
     * Formata os dados digitados no padrao bancario
     * @param numeroSemFormato - String - Numero a ser formatado
     * @return String - Numero formatado
     */
    public abstract String formata(String numeroSemFormato);
    
    /**
     * Formata os dados digitados no padrao calculadora
     * @param numeroSemFormato - String - Numero inteiro a ser formatado
     * @param decimalSemFormato - String - Numero decimal a ser formatado
     * @return String - Numero formatado
     */
    public abstract String formata(String numeroSemFormato, String decimalSemFormato);
    
    /**
     * Retorna o valor como numero duplo
     * @return Double - Valor do campo
     */
    public abstract Double getValorCampo();
    
    /**
     * Retorna o valor em uma string com o formato no padrao americano
     * @return String - Valor do campo
     */
    public abstract String getValorCampoString();
    
}
