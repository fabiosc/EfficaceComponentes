/*
 * Desenvolvido por Efficace Tecnologia
 * Brasil, 21-04-2011
 */
package com.efficace.componente.campo.util;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import com.efficace.componente.excecoes.MascaraException;
import com.efficace.componente.util.Util;

/**
 * FabricaFormatoDados.java
 * @author Fabio Sicupira Cavalcante - Brasil, 21/04/2011
 * @version 0.1-SNAPTSHOT 21/04/2011
 */
public class FabricaFormatoDados {
	
	private FormataDados fd = null;
	
    /**
     * Construtor da classe
     * @param mascara - String - Mascara do campo
     * @return PlainDocument - Retorna o padrao de edicao do campo
     * @throws BadLocationException
     * @throws MascaraException 
     */
    public FormataDados criar(JTextField jTextField, String mascara, int quantidadeDigitos) throws BadLocationException {
    	if (mascara.equals("#,##") || mascara.equals("#.##")){
    		jTextField.setHorizontalAlignment(JTextField.TRAILING);
    		jTextField.setCaretPosition(jTextField.getText().length());

    		jTextField.addKeyListener(new TratamentoTecla());
    		jTextField.addFocusListener(new TratamentoFoco(jTextField));
    		jTextField.addMouseListener(new TratamentoMouse(jTextField));
    		
    		try {
				fd = new FormataDecimal(Util.mascaraFormatada(mascara), quantidadeDigitos);
			} catch (MascaraException e) {
				e.printStackTrace();
				System.exit(0);
			}
    		
    		fd.set(Util.mascaraFormatada(mascara));
    	} else if (mascara.equals("#") || mascara.contains("#.") || mascara.contains("#,")){
    		jTextField.setHorizontalAlignment(JTextField.TRAILING);
    		jTextField.setCaretPosition(jTextField.getText().length());

    		jTextField.addKeyListener(new TratamentoTecla());
    		jTextField.addFocusListener(new TratamentoFoco(jTextField));
    		jTextField.addMouseListener(new TratamentoMouse(jTextField));
    		
    		try {
				fd = new FormataInteiro(Util.mascaraFormatada(mascara), quantidadeDigitos);
			} catch (MascaraException e) {
				e.printStackTrace();
				System.exit(0);
			}
    		
    		fd.set(Util.mascaraFormatada(mascara));
    		
    	} else if (mascara.equals("A")) {
    		try {
				fd = new FormataCaixaAlta(quantidadeDigitos);
			} catch (MascaraException e) {
				e.printStackTrace();
				System.exit(0);
			}
    		
    	} else {
    	}
    	return fd;
    }
    
}
