package com.efficace.components.extendedjtextfield.util;

public class LowerCaseFormat extends PlainDocumentFormat {

	private static final long serialVersionUID = 5396196358431403730L;

	public LowerCaseFormat(){}
	
	public LowerCaseFormat(int maxLength){
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
			return string.substring(0, string.length() - 1).toLowerCase();
		} else {
			return string.toLowerCase();
		}
	}

}
