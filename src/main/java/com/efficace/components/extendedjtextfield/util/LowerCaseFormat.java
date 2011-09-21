package com.efficace.components.extendedjtextfield.util;

public class LowerCaseFormat extends PlainDocumentFormat {

	private static final long serialVersionUID = 5396196358431403730L;

	public LowerCaseFormat(){}
	
	public LowerCaseFormat(int maxLength){
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
			return string.substring(0, string.length() - 1).toLowerCase();
		} else {
			return string.toLowerCase();
		}
	}

}
