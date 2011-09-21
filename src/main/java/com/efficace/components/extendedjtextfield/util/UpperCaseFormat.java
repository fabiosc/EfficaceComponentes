package com.efficace.components.extendedjtextfield.util;

public class UpperCaseFormat extends PlainDocumentFormat {

	private static final long serialVersionUID = 5433516020701088529L;
	
	public UpperCaseFormat(){}
	
	public UpperCaseFormat(int maxLength){
		super.setMaxLength(maxLength);
	}

	public String format(String string) {
		if (super.isByPass() && (string == null || string.equals(""))) {
			return super.getMaskValue();
		} else {
			super.setByPass(false);
		}
		super.setByPass(true);
		if (isOverflow(string)) {
			return string.substring(0, string.length() - 1).toUpperCase();
		} else {
			return string.toUpperCase();
		}
	}

}
