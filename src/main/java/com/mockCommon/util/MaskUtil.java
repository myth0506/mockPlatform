package com.mockCommon.util;

public class MaskUtil {
	public static String mask(String str) {
		String s = null;
		if (str != null) {
			int len = str.length();
			if (len > 0) {
				if(len == 1){
					s = "*";
				}else if(len == 2){
					s = str.replace(str.charAt(1), '*');
				}else if(len == 3){
					s = str.replace(str.charAt(1), '*');
				}else if(len == 4){
					s = str.replace(str.charAt(2), '*');
					s = s.replace(s.charAt(3), '*');
				}else{
					s = str.replace(str.subSequence(len-4, len-1), "****");
				}
			}
		}
		return s;
	}
}