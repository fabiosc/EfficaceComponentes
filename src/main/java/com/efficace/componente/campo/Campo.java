/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.campo;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.efficace.componente.campo.util.FabricaFormatoDados;
import com.efficace.componente.campo.util.FormataDados;
import com.efficace.componente.excecoes.MascaraException;

/**
 * Campo.java
 * @author Fabio Sicupira Cavalcante - Brasil, 21/04/2011
 * @version 0.1-SNAPTSHOT 21/04/2011
 */
public class Campo extends JTextField{
	
	private static final long serialVersionUID = 3908628583363836754L;
	
	private String mascara;
    private Integer quantidadeDigitos = 10;
    private FabricaFormatoDados fabrica = new FabricaFormatoDados();
    private FormataDados fd;

    /**
     * Construtor da classe Campo
     */
    public Campo() {}

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
     *        : #                  - Numeros inteiros
     * 
     * @param mascara - String - Mascara do campo
     * @throws MascaraException 
     * @throws BadLocationException 
     */
    public void setMascara(String mascara) {
    	try {
			fd = fabrica.criar(this, mascara, this.quantidadeDigitos);
    		this.setDocument(fd);
    		setCaretPosition(getText().length());
            ((AbstractDocument)this.getDocument()).setDocumentFilter(new DocumentFilter());
    	} catch (BadLocationException e){
    		e.printStackTrace();
    	}
    	
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
            fd.setQuantidadeDigitos(this.quantidadeDigitos);
        }
    }

}
