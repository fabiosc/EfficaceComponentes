package com.efficace.componente.campo.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

/**
 * Posiciona o cursor no final do campo
 * a qualquer ação do mouse no campo
 */
public class TratamentoMouse implements MouseListener {
	
	private JTextField jTextField;
	
	
    public TratamentoMouse(JTextField jTextField) {
		super();
		this.jTextField = jTextField;
	}

	public void mouseClicked(MouseEvent e) {
		jTextField.setCaretPosition(jTextField.getText().length());
    }

    public void mousePressed(MouseEvent e) {
    	jTextField.setCaretPosition(jTextField.getText().length());
    }

    public void mouseReleased(MouseEvent e) {
    	jTextField.setCaretPosition(jTextField.getText().length());
    }

    public void mouseEntered(MouseEvent e) {
    	jTextField.setCaretPosition(jTextField.getText().length());
    }

    public void mouseExited(MouseEvent e) {
    	jTextField.setCaretPosition(jTextField.getText().length());
    }

}
