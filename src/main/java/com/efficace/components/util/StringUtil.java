package com.efficace.components.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static String completeStringLeft(String string, String character, int numberDigits){
		return String.format("%1$" + numberDigits +"s" , string).replaceAll(" ", character);
	}

	public static String completeStringRight(String string, String character, int numberDigits){
		return String.format("%1$-" + numberDigits +"s" , string).replaceAll(" ", character);
	}
	
	public static String removeDefinedChar(String string, String regex, int maxStringIndex) {
		Matcher matcher = Pattern.compile(regex).matcher(string);
		if (matcher.find()) {
			int startIndex = (matcher.start() < maxStringIndex?matcher.start(): maxStringIndex); 
			return string.substring(startIndex, string.length());
		} else {
			return string.substring(string.length()-1, string.length());			
		}
	}
	
	public static String removeAnyDefinedChar(String string, String regex, String substituteChar){
		return Pattern.compile(regex).matcher(string).replaceAll(substituteChar);
	}
	
	public static String formatDecimalNumber(
    		String unformattedNumber, 
    		String thousandsDigit, 
    		String decimalDigit, 
    		int thousandsDigitNumber, 
    		int decimalDigitNumber){
    	
        StringBuilder regex = new StringBuilder();
        StringBuilder mask = new StringBuilder();
        
        int stringLength = unformattedNumber.trim().length();
        int remainder = stringLength - decimalDigitNumber;
        int numberClasses = Math.round(remainder/3);
        numberClasses = (numberClasses==1?1:numberClasses--);
        int auxNumberClasses = numberClasses;
        
        while (remainder > 3){
            remainder = remainder - 3;
            numberClasses++;
        }
        
        numberClasses = numberClasses - auxNumberClasses;
        regex.append("(\\d{").append(remainder).append("})");
        
        for (int i = 1; i <= numberClasses; i++){
            regex.append("(\\d{3})");
        }
        
        regex.append("(\\d{").append(decimalDigitNumber).append("})");
        
        for (int i = 1; i <= (numberClasses + 2); i++){
            mask.append("$").append(i);
            if (i < (numberClasses + 1)) {
                mask.append(thousandsDigit);
            } else if ((i == (numberClasses + 1))) {
                mask.append(decimalDigit);
            }
        }
        
		return format(unformattedNumber, regex, mask);
    }
	
	
    public static String formatIntegerNumber(
    		String unformattedNumber, 
    		String thousandsDigit, 
    		int digitNumber){
    	
        StringBuilder regex = new StringBuilder();
        StringBuilder mask = new StringBuilder();
        
        int stringLength = unformattedNumber.trim().length();
        int remainder = stringLength;
        int numberClasses = Math.round(remainder/3);
        numberClasses = (numberClasses==1?1:numberClasses--);
        int auxnumberClasses = numberClasses;
        
        while (remainder > 3){
            remainder = remainder - 3;
            numberClasses++;
        }
        
        numberClasses = numberClasses - auxnumberClasses;
        regex.append("(\\d{").append(remainder).append("})");
        
        for (int i = 1; i <= numberClasses; i++){
            regex.append("(\\d{3})");
        }
        
        if (numberClasses == 0) {
            mask.append("$").append(1);
        } else {
	        for (int i = 1; i <= numberClasses; i++){
	            mask.append("$").append(i);
            	mask.append(thousandsDigit);
	        }
            mask.append("$").append(numberClasses + 1);
        }
        
		return format(unformattedNumber, regex, mask);
    	
    }
    
    public static String formatMask(String unformattedNumber, String formatedMask) {
    	int lastIndexDigit = 0;

		List<String> digits = new ArrayList<String>();
		List<String> separators = new ArrayList<String>();
    	
        StringBuilder regex = new StringBuilder();
        StringBuilder mask = new StringBuilder();

		for(int i = 0; i < formatedMask.length(); i++) {
			String digit = formatedMask.substring(i, i + 1);
			if (!Pattern.matches("[0-9_]", digit)){
				digits.add(formatedMask.substring(lastIndexDigit, i));
				separators.add(formatedMask.substring(i, i + 1));
				lastIndexDigit = i + 1;
			}
		}
		if (formatedMask.length() > lastIndexDigit) {
			digits.add(formatedMask.substring(lastIndexDigit));
		}


		for (int it = 0; it < digits.size(); it++) {
			String s = digits.get(it);
			regex.append("(\\w{").append(s.length()).append("})");
			if (separators.size() > it) {
				mask.append("$").append(it+1).append(separators.get(it));
			} else {
				mask.append("$").append(it+1);
			}
		}

		return format(unformattedNumber, regex, mask);    	
    }
	
	public static String format(String unformattedNumber, StringBuilder regex, StringBuilder mask) {
		String result = Pattern.compile(regex.toString()).matcher(unformattedNumber).replaceAll(mask.toString());
		return result.trim();
	}
}
