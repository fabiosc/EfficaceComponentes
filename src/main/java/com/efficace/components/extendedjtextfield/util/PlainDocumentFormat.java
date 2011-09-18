package com.efficace.components.extendedjtextfield.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public abstract class PlainDocumentFormat extends PlainDocument {

	private static final long serialVersionUID = 4207731047062897064L;
	protected int maxLength = 0;
	protected String maskValue = "";
	protected boolean byPass = false;
	
	
	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public String getMaskValue() {
		return maskValue;
	}

	public void setMaskValue(String maskValue) {
		this.maskValue = maskValue;
	}
	
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		super.insertString(offs, str, a);
	}
	
	public void replace(int offset, int length, String string, AttributeSet attrs) throws BadLocationException {
		if (string.length() == 1) {
			super.replace(offset, length, string, attrs);
			int actualLength = getLength();
			String actualString = format(getText(0, actualLength));
			super.replace(0, actualLength, actualString, attrs);
		} else {
			super.replace(offset, length, string, attrs);
		}
	}
	
	public void remove(int offset, int length) throws BadLocationException {
		super.remove(offset, length);
		int tamanho = getLength();
		String result = getText(0, tamanho);
		try {
			result = format(result);
		} catch (Exception e) {}
		super.replace(0, tamanho, result, null);
	}
	
	public abstract String format(String string);
	
	public boolean isOverflow(String string) {
		if (string.length() > this.maxLength) {
			return true;
		}
		return false;
	}

}
