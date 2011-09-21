package com.efficace.components.extendedjtextfield.util;

import com.efficace.components.util.StringUtil;

public class MaskFormat extends PlainDocumentFormat {

	private static final long serialVersionUID = -174164953498282266L;
	
	public MaskFormat() {}
	
	public MaskFormat(String maskValue) {
		String auxMaxLength = StringUtil.removeAnyDefinedChar(maskValue, "[\\.,-/]?", "");
		super.setMaxLength(auxMaxLength.length()); 
		super.setMaskValue(maskValue);
	}
	

	public String format(String string) {
		
		if (super.isByPass() && (string == null || string.equals(""))) {
			return super.getMaskValue();
		} else {
			super.setByPass(false);
		}
		
		String unformattedNumber = StringUtil.removeDefinedChar(string, "[0-9]", 4);
		unformattedNumber = StringUtil.removeAnyDefinedChar(unformattedNumber, "[^0-9][\\.,-/_]?", "");
		unformattedNumber = StringUtil.completeStringLeft(unformattedNumber, "_", super.getMaxLength());
		if (isOverflow(unformattedNumber)) {
			unformattedNumber = unformattedNumber.substring(0, unformattedNumber.length() - 1);
			unformattedNumber = StringUtil.formatMask(unformattedNumber, super.getMaskValue());
		} else {
			unformattedNumber = StringUtil.formatMask(unformattedNumber, super.getMaskValue());
		}
		super.setByPass(true);
		return unformattedNumber;
		
	}
	

}
