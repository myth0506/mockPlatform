package com.mockCommon.util;

public class WzcxUtil {
	public static String getWzcxHandleStatus(int status){
		String handle = "";
		switch(status){
		case 0:
			handle = "未处理";
			break;
		case 1:
			handle = "已处理";
			break;
		}
		return handle;
	}
}