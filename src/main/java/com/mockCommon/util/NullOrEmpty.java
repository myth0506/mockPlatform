package com.mockCommon.util;

public class NullOrEmpty {
	public static boolean isNullOrEmpty(String str){
		if(str == null || "".equals(str)){
			return true;
		}
		return false;
	}
}