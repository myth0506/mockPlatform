package com.mockCommon.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author li_zhe
 *0：成功
 *1：验签失败
 *2：全部承保失败
 *3：在零点前5分钟调用时，返回暂时不能承保，请重试
 *4：部分成功部分失败,失败的订单不会存在policyNo节点
 *5：正在承保中，请稍后重试
 */
public class AcceptShopOrder {
	private static final String ORDER_SUCCESS = "成功";
	private static final String ORDER_YANQIAN_FAILED = "验签失败";
	private static final String ORDER_CHENGBAO_FAILED= "全部承保失败";
	private static final String ORDER_BEFORE5 = "在零点前5分钟调用时，返回暂时不能承保，请重试";
	private static final String ORDER_PART_FAILED = "部分成功部分失败,失败的订单不会存在policyNo节点";
	private static final String ORDER_CHENGBAO_ING = "正在承保中，请稍后重试";
	
	public static String getTypeVal(String type){
		switch(type){
		case "0":
			return ORDER_SUCCESS;
		case "1":
			return ORDER_YANQIAN_FAILED;
		case "2":
			return ORDER_CHENGBAO_FAILED;
		case "3":
			return ORDER_BEFORE5;
		case "4":
			return ORDER_PART_FAILED;
		case "5":
			return ORDER_CHENGBAO_ING;
		}
		return "";
	}
	
	public static List<String> getAllTypes(){
		List<String> list = new ArrayList<String>();
		list.add(ORDER_SUCCESS);
		list.add(ORDER_YANQIAN_FAILED);
		list.add(ORDER_CHENGBAO_FAILED);
		list.add(ORDER_BEFORE5);
		list.add(ORDER_PART_FAILED);
		list.add(ORDER_CHENGBAO_ING);
		return list;
	}
}