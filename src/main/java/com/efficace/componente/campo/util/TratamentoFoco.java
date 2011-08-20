package com.efficace.componente.campo.util;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 * Posiciona o cursor no final do campo
 * ao ganhar o foco
 */
public class TratamentoFoco implements FocusListener{
	
	private JTextField jTextField;
		
    public TratamentoFoco(JTextField jTextField) {
		super();
		this.jTextField = jTextField;
	}
	
    public void focusGained(FocusEvent e) {
    	jTextField.setCaretPosition(jTextField.getText().length());
    }

    public void focusLost(FocusEvent e) {
    }

}
