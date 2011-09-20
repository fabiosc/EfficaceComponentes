package com.efficace.components.extendedjtextfield.util;

import com.efficace.components.util.StringUtil;

public class MaskFormat extends PlainDocumentFormat {

	private static final long serialVersionUID = -174164953498282266L;
	
	public MaskFormat() {}
	
	public MaskFormat(int maxLength, String maskValue) {
		super.maxLength = maxLength;
		super.maskValue = maskValue;
	}
	

	public String format(String string) {
		
		if (byPass && (string == null || string.equals(""))) {
			return this.maskValue;
		} else {
			byPass = false;
		}
		
		String unformattedNumber = StringUtil.removeDefinedChar(string, "[1-9]", 4);
		unformattedNumber = StringUtil.removeAnyDefinedChar(unformattedNumber, "[^0-9][\\.,-/]?", "");
		unformattedNumber = StringUtil.completeStringLeft(unformattedNumber, "0", this.maxLength);
		if (isOverflow(unformattedNumber)) {
			unformattedNumber = unformattedNumber.substring(0, unformattedNumber.length() - 1);
			unformattedNumber = StringUtil.formatMask(unformattedNumber, this.maskValue);
		} else {
			unformattedNumber = StringUtil.formatMask(unformattedNumber, this.maskValue);
		}
		byPass = true;
		return unformattedNumber;
		
	}
	

}
