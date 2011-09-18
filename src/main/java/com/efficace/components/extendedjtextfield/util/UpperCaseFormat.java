package com.efficace.components.extendedjtextfield.util;

public class UpperCaseFormat extends PlainDocumentFormat {

	private static final long serialVersionUID = 5433516020701088529L;
	
	public UpperCaseFormat(){}
	
	public UpperCaseFormat(int maxLength){
		super.maxLength = maxLength;
	}

	public String format(String string) {
		if (byPass && (string == null || string.equals(""))) {
			return this.maskValue;
		} else {
			byPass = false;
		}
		byPass = true;
		if (isOverflow(string)) {
			return string.substring(0, string.length() - 1).toUpperCase();
		} else {
			return string.toUpperCase();
		}
	}

}
