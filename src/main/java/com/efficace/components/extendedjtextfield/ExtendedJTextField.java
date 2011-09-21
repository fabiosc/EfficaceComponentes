package com.efficace.components.extendedjtextfield;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import com.efficace.components.extendedjtextfield.util.PlainDocumentFormat;
import com.efficace.components.extendedjtextfield.util.PlainDocumentFormatFactory;

public class ExtendedJTextField extends JTextField {

	private static final long serialVersionUID = -2355835962807899331L;
	private int maxLength = 0;
	private String mask = "";
	
	public ExtendedJTextField() {
		adjustExtendedJTextField();
	}

	public ExtendedJTextField(int maxLength, String mask) {
		this.maxLength = maxLength;
		this.mask = mask;
		adjustExtendedJTextField();
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
		adjustExtendedJTextField();
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
		adjustExtendedJTextField();
	}
	
	private void adjustExtendedJTextField() {
		PlainDocumentFormat pdf = PlainDocumentFormatFactory.create(this); 
		this.setDocument(pdf);
		((AbstractDocument)this.getDocument()).setDocumentFilter(new DocumentFilter());
		this.setText(pdf.getMaskValue());
		this.mask = pdf.getMaskValue();
		this.addFocusListener(new FocusListenerImplement(this));
	}
	
	class FocusListenerImplement implements FocusListener {

		private JTextField jTextField;

	    public FocusListenerImplement(JTextField jTextField) {
			super();
			this.jTextField = jTextField;
		}

	    public void focusGained(FocusEvent e) {
    		jTextField.setCaretPosition(jTextField.getText().length());
	    }

	    public void focusLost(FocusEvent e) {
	    }
	    
	}	
	
}