package com.mockCommon.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String getDate(Timestamp date){
		String sDate = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			sDate = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sDate;
	}
	
	/**
	 * @param after   after天后的日期
	 * @return        返回当前日期after天后的日期
	 */
	public static String getDate(int after){
		String sDate = "";
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		  Calendar calendar = Calendar.getInstance();  
		  calendar.setTimeInMillis(new Date().getTime());
		  calendar.add(Calendar.DATE, after);        //after天后的日期
		  Date date= new Date(calendar.getTimeInMillis()); //将calendar转换成Date
		
		try {
			sDate = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sDate;
	}
	
	public static String getTimestamp(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		return str;
	}
	
	public static Timestamp getTimestamp(String date){
		Format simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp timestamp = null;
		Date time = null;
		try {
			time = (Date)simpleDateFormat.parseObject(date);
			if(time != null){
				timestamp = new Timestamp(time.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}
}