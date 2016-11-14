package com.mockCommon.constant;

public class AuditResultCode {
	public static final String C0000 = "核保成功";
	public static final String C6001 = "商交险核保失败";
	public static final String C6002 = "商业险核保失败";
	public static final String C6003 = "交强险核保失败";
	public static final String C9102 = "当前投保机构需要较验动态验证码,请输入后再进行核保";
	public static final String C9103 = "较验动态验证码不通过，请确认正确后重新输入";
	
	public static String getValue(String s){
		if(s.equals("C0000")){
			return C0000;
		}
		else if(s.equals("C6001")){
			return C6001;
		}else if(s.equals("C6002")){
			return C6002;
		}else if(s.equals("C6003")){
			return C6003;
		}else if(s.equals("C9102")){
			return C9102;
		}else {
			return C9103;
		}
	}
} 