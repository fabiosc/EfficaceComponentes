package com.efficace.components.extendedjtextfield.util;

import com.efficace.components.util.StringUtil;

public class IntegerFormat extends PlainDocumentFormat {

	private static final long serialVersionUID = 6306108874178162855L;
	private String thousandsDigit = ".";
	
	public IntegerFormat() {}
	
	public IntegerFormat(int maxLength, String maskValue) {
		super.maxLength = maxLength;
		super.maskValue = maskValue;
	}

	public String getThousandsDigit() {
		return thousandsDigit;
	}

	public void setThousandsDigit(String thousandsDigit) {
		this.thousandsDigit = thousandsDigit;
	}

	public String format(String string) {
		if (byPass && (string == null || string.equals(""))) {
			return this.maskValue;
		} else {
			byPass = false;
		}
		
		String unformattedNumber = StringUtil.removeDefinedChar(string, "[1-9]", 4);
		unformattedNumber = StringUtil.removeAnyDefinedChar(unformattedNumber, "[^0-9][\\.,-/]?", "");
		if (isOverflow(unformattedNumber)) {
			unformattedNumber = unformattedNumber.substring(0, unformattedNumber.length() - 1);
			unformattedNumber = StringUtil.formatIntegerNumber(unformattedNumber, this.thousandsDigit, super.maxLength );			
		} else {
			unformattedNumber = StringUtil.formatIntegerNumber(unformattedNumber, this.thousandsDigit, super.maxLength );
		}
		
		byPass = true;
		return unformattedNumber;
	}

}
