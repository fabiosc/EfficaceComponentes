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
     * Retorna a mascara do campo
     * @return String - Mascara do campo
     */
    public String getMascara() {
        return mascara;
    }

    /**
     * Atribui uma mascara ao campo
     * Mascara: #.#, #.## ou #.### - Numeros decimais no padrao americano
     *        : #,#, #,## ou #,### - Numeros decimais no padrao brasileiro
     *        : ,#                 - Numeros inteiros no padrao americano
     *        : .#                 - Numeros inteiros no padrao brasileiro
     *        : #                  - Numeros inteiros sem formato
     * 
     * @param mascara - String - Mascara do campo
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
     * @return Integer - Quantidade de caracteres
     */
    public Integer getQuantidadeDigitos() {
        return quantidadeDigitos;
    }

    /**
     * Atribui a quantidade de caracteres que podem ser digitados no campo
     * @param tamanhoMaximo - Integer - Quantidade de caracteres
     */
    public void setQuantidadeDigitos(Integer tamanhoMaximo) {
        this.quantidadeDigitos = tamanhoMaximo;
        if (this.quantidadeDigitos != 0 && fd != null){
            fd.setTamanhoMaximo(this.quantidadeDigitos);
        }
    }

    /**
     * Retorna true se a digitaçao segue o padrao bancario
     * ou false se nao segue.
     * @return Boleano - Padrao bancario
     */
    public Boolean getPadraoBancario() {
        return padraoBancario;
    }

    /**
     * Atribui o padrao bancario a um campo
     * @param padraoBancario Boolean - Padrao bancario (true ou false)
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
     * Retorna o valor como numero duplo
     * @return Double - Valor do campo
     */
    public Double getValor(){
        return fd.getValorCampo();
    }
    
    /**
     * Retorna o valor em uma string com o formato no padrao americano
     * @return String - Valor do campo
     */
    public String getValorString(){
        return fd.getValorCampoString();
    }
    
}
