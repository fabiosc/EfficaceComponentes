/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.campo;

import com.efficace.componente.campo.util.FabricaFormatoDados;
import com.efficace.componente.campo.util.FormatoDados;
import com.efficace.componente.campo.util.TratamentoFoco;
import com.efficace.componente.campo.util.TratamentoMouse;
import com.efficace.componente.campo.util.TratamentoTecla;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/**
 * Campo.java
 * @author Fabio Sicupira Cavalcante - Brasil, 21/04/2011
 * @version 0.1-SNAPTSHOT 21/04/2011
 */
public class Campo extends JTextField{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3908628583363836754L;
	
	private String mascara;
    private Integer quantidadeDigitos = 0;
    private Boolean padraoBancario = true;
    private FabricaFormatoDados fabrica = new FabricaFormatoDados();
    private FormatoDados fd;

    /**
     * Construtor da classe Campo
     */
    public Campo() {
        alinhaDireita();
        posicionaCursor();
        addKeyListener(new TratamentoTecla());
        addFocusListener(new TratamentoFoco(this));
        addMouseListener(new TratamentoMouse(this));
    }

    /**
     * Retorna a máscara do campo
     * @return String - Máscara do campo
     */
    public String getMascara() {
        return mascara;
    }

    /**
     * Atribui uma máscara ao campo
     * Máscara: #.#, #.## ou #.### - Números decimais no padrão americano
     *        : #,#, #,## ou #,### - Números decimais no padrão brasileiro
     *        : ,#                 - Números inteiros no padrão americano
     *        : .#                 - Números inteiros no padrão brasileiro
     *        : #                  - Números inteiros sem formato
     * 
     * @param mascara String - Máscara do campo
     * @throws BadLocationException 
     */
    public void setMascara(String mascara) throws BadLocationException {
        fd = fabrica.criar(mascara);
        setCaretPosition(getText().length());
        setDocument(fd);
        this.mascara = mascara;
    }

    /**
     * Retorna a quantidade de caracteres que podem ser digitados no campo
     * @return Inteiro - Quantidade de caracteres
     */
    public Integer getQuantidadeDigitos() {
        return quantidadeDigitos;
    }

    /**
     * Atribui a quantidade de caracteres que podem ser digitados no campo
     * @param tamanhoMaximo Inteiro - Quantidade de caracteres
     */
    public void setQuantidadeDigitos(Integer tamanhoMaximo) {
        this.quantidadeDigitos = tamanhoMaximo;
        if (this.quantidadeDigitos != 0 && fd != null){
            fd.setTamanhoMaximo(this.quantidadeDigitos);
        }
    }

    /**
     * Retorna true se a digitação segue o padrão bancário
     * ou false se não segue.
     * @return Boleano - Padrão bancário
     */
    public Boolean getPadraoBancario() {
        return padraoBancario;
    }

    /**
     * Atribui o padrão bancário a um campo
     * @param padraoBancario Boleano - Padrão bancário
     */
    public void setPadraoBancario(Boolean padraoBancario) {
        this.padraoBancario = padraoBancario;
        if (fd != null){
            fd.setPadraoBancario(this.padraoBancario);
        }
    }

    /**
     * Alinha os caracteres do campo a direita
     */
    private void alinhaDireita(){
        setHorizontalAlignment(JTextField.TRAILING);
    }

    /**
     * Posiciona o cursos no campo
     */
    private void posicionaCursor(){
        setCaretPosition(getText().length());
    }

    /**
     * Retorna o valor como número duplo
     * @return Duplo -  Valor do campo
     */
    public Double getValor(){
        return fd.getValorCampo();
    }
    
    /**
     * Retorna o valor em uma string com o formato no padrão americano
     * @return String - Valor do campo
     */
    public String getValorString(){
        return fd.getValorCampoString();
    }
    
}
