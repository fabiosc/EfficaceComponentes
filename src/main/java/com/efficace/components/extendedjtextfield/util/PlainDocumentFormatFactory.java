package com.efficace.components.extendedjtextfield.util;

import com.efficace.components.extendedjtextfield.ExtendedJTextField;

public class PlainDocumentFormatFactory {
	
	public static PlainDocumentFormat create(ExtendedJTextField textField){
		String mask = textField.getMask();
		int maxLength = textField.getMaxLength();
		if (mask.contains("#,#") || mask.contains("#.#") &&
				!(mask.contains("-") || mask.contains("/"))){
			DecimalFormat decimalFormat = new DecimalFormat(maxLength, mask.replace("#", "0"));
			decimalFormat.setDecimalDigit(mask.contains(",") ? "," : ".");
			decimalFormat.setThousandsDigit(mask.contains(",") ? "." : ",");
			decimalFormat.setDecimalDigitNumber(mask.substring(2).length());
			return decimalFormat;
		} else if (mask.contains("#.0") || mask.contains("#,0")) {
			IntegerFormat integerFormat = new IntegerFormat(maxLength, mask.substring(0,1).replace("#", "0"));
			integerFormat.setThousandsDigit(mask.contains(",") ? "," : ".");
			return integerFormat;
		} else if (mask.equals("A")) {
			return new UpperCaseFormat(maxLength);
		} else if (mask.equals("a")) {
			return new LowerCaseFormat(maxLength);
		} else {
			MaskFormat maskFormat = new MaskFormat(mask.replaceAll("#", "_"));
			return maskFormat;
		}
	}
}
