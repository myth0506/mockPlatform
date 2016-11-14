package com.mockCommon.constant;

/**
 * @author li_zhe
 * 状态对应
 *0: 全部可以支付
 *1：全部不可以支付 
 *21：商业险不能支付
 *22：交强险不能支付
 */
public class queryPolicyStatus {
	public static final String PAY_SUCCESS = "全部可以支付";
	public static final String PAY_BOTH_FAILED = "全部不可以支付";
	public static final String PAY_BIZ_FAILED = "商业险不能支付";
	public static final String PAY_JQX_FAILED = "交强险不能支付";
	
	public static String getValue(String s){
		if(s.equals("0")){
			return PAY_SUCCESS;
		}
		else if(s.equals("1")){
			return PAY_BOTH_FAILED;
		}else if(s.equals("21")){
			return PAY_BIZ_FAILED;
		}else{
			return PAY_JQX_FAILED;
		}
	}
}