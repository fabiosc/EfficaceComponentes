package com.efficace.components.extendedjtextfield.util;

import com.efficace.components.util.StringUtil;

public class DecimalFormat extends PlainDocumentFormat {

	private static final long serialVersionUID = 6578885177943624805L;
	private int decimalDigitNumber = 2;
	private String thousandsDigit = "."; 
	private String decimalDigit = ",";
	
	public DecimalFormat() {}
	
	public DecimalFormat(int maxLength, String maskValue) {
		super.maxLength = maxLength;
		super.maskValue = maskValue;
	}
	
	public int getDecimalDigitNumber() {
		return decimalDigitNumber;
	}

	public void setDecimalDigitNumber(int decimalDigitNumber) {
		this.decimalDigitNumber = decimalDigitNumber;
	}

	public String getThousandsDigit() {
		return thousandsDigit;
	}

	public void setThousandsDigit(String thousandsDigit) {
		this.thousandsDigit = thousandsDigit;
	}

	public String getDecimalDigit() {
		return decimalDigit;
	}

	public void setDecimalDigit(String decimalDigit) {
		this.decimalDigit = decimalDigit;
	}

	public String format(String string) {
		if (byPass && (string == null || string.equals(""))) {
			return this.maskValue;
		} else {
			byPass = false;
		}
		
		String unformattedNumber = StringUtil.removeDefinedChar(string, "[1-9]", 4);
		unformattedNumber = StringUtil.removeAnyDefinedChar(unformattedNumber, "[^0-9][\\.,-/]?", "");
		unformattedNumber = StringUtil.completeStringLeft(unformattedNumber, "0", this.decimalDigitNumber + 1);
		if (isOverflow(unformattedNumber)) {
			unformattedNumber = unformattedNumber.substring(0, unformattedNumber.length() - 1);
			unformattedNumber = StringUtil.formatDecimalNumber(unformattedNumber, 
					this.thousandsDigit, this.decimalDigit, super.maxLength, this.decimalDigitNumber);
		} else {
			unformattedNumber = StringUtil.formatDecimalNumber(unformattedNumber, 
					this.thousandsDigit, this.decimalDigit, super.maxLength, this.decimalDigitNumber);
		}
		byPass = true;
		return unformattedNumber;
	}
	
}
