package com.mockCommon.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author li_zhe
 *	0-身份证, 1-护照, 2-军官证, 
 *	3-驾驶证, 4-港澳回乡证或台胞证
 */
public class IdType {
	private static final String ID_CARD = "身份证";
	private static final String PASS_PORT = "护照";
	private static final String MILITARY_CARD= "军官证";
	private static final String DRIVING_LICENCE = "驾驶证";
	private static final String HOME_RETURN_CERTIFICATE = "港澳回乡证或台胞证";
	
	public static int getTypeVal(String type){
		int val = 0;
		switch(type){
		case ID_CARD:
			val = 0;
			break;
		case PASS_PORT:
			val = 1;
			break;
		case MILITARY_CARD:
			val = 2;
			break;
		case DRIVING_LICENCE:
			val = 3;
			break;
		case HOME_RETURN_CERTIFICATE:
			val = 4;
			break;
		}
		return val;
	}
	
	public static List<String> getAllTypes(){
		List<String> list = new ArrayList<String>();
		list.add(ID_CARD);
		list.add(PASS_PORT);
		list.add(MILITARY_CARD);
		list.add(DRIVING_LICENCE);
		list.add(HOME_RETURN_CERTIFICATE);
		return list;
	}
}