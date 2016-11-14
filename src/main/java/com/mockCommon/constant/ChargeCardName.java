package com.mockCommon.constant;

public class ChargeCardName {
	public static final String C64127500 = "全国中石化加油卡任意充";
	public static final String C64157001 = "全国中石化加油卡直充1000元";
	public static final String C64157002 = "全国中石化加油卡直充500元";
	public static final String C64157004 = "全国中石化加油卡直充100元";
	public static final String C64157003 = "全国中石化加油卡直充200元";
	public static final String C64157005 = "全国中石化加油卡直充50元";
	public static final String C64357107 = "全国中石化加油卡直充3000元";
	public static final String C64357108 = "全国中石化加油卡直充2000元";
	public static final String C64357109 = "全国中石化加油卡直充5000元";
	public static final String C64313604 = "中石化卡密100元";
	public static final String C64313603 = "中石化卡密200元";
	public static final String C64313602 = "中石化卡密500元";
	public static final String C64313601 = "中石化卡密1000元";
	public static final String C64313605 = "中石化卡密50元";
	public static final String C64349102 = "中石油任意充";
	
	public static String getValue(String cardId){
		switch(cardId){
		case "C64127500":
			return C64127500;
		case "C64157001":
			return C64157001;
		case "C64157002":
			return C64157002;
		case "C64157004":
			return C64157004;
		case "C64157003":
			return C64157003;
		case "C64157005":
			return C64157005;
		case "C64357107":
			return C64357107;
		case "C64357108":
			return C64357108;
		case "C64357109":
			return C64357109;
		case "C64313604":
			return C64313604;
		case "C64313603":
			return C64313603;
		case "C64313602":
			return C64313602;
		case "C64313601":
			return C64313601;
		case "C64313605":
			return C64313605;
		case "C64349102":
			return C64349102;
		default:
			return "";
		}
	}
}