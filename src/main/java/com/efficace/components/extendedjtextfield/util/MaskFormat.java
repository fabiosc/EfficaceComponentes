package com.efficace.components.extendedjtextfield.util;

public class MaskFormat extends PlainDocumentFormat {

	private static final long serialVersionUID = -174164953498282266L;

	public String format(String string) {
		if (byPass && (string == null || string.equals(""))) {
			return this.maskValue;
		} else {
			byPass = false;
		}
		
		if (isOverflow(null)) {
		} else {
		}
		byPass = true;
		return null;
	}
	
	private void regexConfig() {
		
	}

}
