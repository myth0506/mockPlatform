package com.mockCommon.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netease.common.util.CodecUtil;

/**
 * @author Dell
 */
public class CookiesUtil {
	
	public static final int TIMEOUT = 3600 * 24 * 365;
	
	public static final TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT+0");
	
	/**
	 * delete cookie.
	 * 
	 * @param res
	 * @param cookieName
	 */
	public static void delCookie(HttpServletResponse response, String cookieName) {

		String cookieValue = "";
		Cookie ntesCookie = new Cookie(cookieName, cookieValue);
		ntesCookie.setPath("/");
		ntesCookie.setDomain("163.com");
		ntesCookie.setMaxAge(0);
		response.addCookie(ntesCookie);
	}
	
	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @return
	 */
	public static boolean setCookie(HttpServletResponse response, String cookieName, String cookieValue) {

		try {
			
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(cookieName);
			stringBuffer.append("=");
			stringBuffer.append(cookieValue);
			stringBuffer.append("; path=/;");
			stringBuffer.append("expires=");
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, d-MMM-yyyy hh:mm:ss ", Locale.US);
			sdf.setTimeZone(TIME_ZONE);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.SECOND, TIMEOUT);
			stringBuffer.append(sdf.format(c.getTime()) + "GMT");
			stringBuffer.append(";");
			response.addHeader("Set-Cookie", stringBuffer.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @return
	 */
	public static boolean setCookie(HttpServletResponse response, String cookieName, String cookieValue, int timeout) {

		try {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(cookieName);
			stringBuffer.append("=");
			stringBuffer.append(cookieValue);
			stringBuffer.append("; path=/;");
			stringBuffer.append("expires=");
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, d-MMM-yyyy HH:mm:ss ", Locale.US);
			sdf.setTimeZone(TIME_ZONE);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.SECOND, timeout);
			stringBuffer.append(sdf.format(c.getTime()) + "GMT");
			stringBuffer.append(";");
			response.addHeader("Set-Cookie", stringBuffer.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 设置session cookie
	 * 
	 * @param response
	 * @param cookieName
	 * @param cookieValue
	 * @return
	 */
	public static boolean setSessionCookie(HttpServletResponse response, String cookieName, String cookieValue) {

		try {
			
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(cookieName);
			stringBuffer.append("=");
			stringBuffer.append(cookieValue);
			stringBuffer.append("; path=/;");
			// stringBuffer.append("expires=");
			// SimpleDateFormat sdf = new SimpleDateFormat("EEE, d-MMM-yyyy
			// hh:mm:ss ", Locale.US);
			// sdf.setTimeZone(TIME_ZONE);
			// Calendar c = Calendar.getInstance();
			// c.add(Calendar.SECOND, TIMEOUT);
			// stringBuffer.append(sdf.format(c.getTime()) + "GMT");
			// stringBuffer.append(";");
			response.addHeader("Set-Cookie", stringBuffer.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 得到cookie
	 * 
	 * @param request
	 * @return
	 */
	public static String getCookie(HttpServletRequest request, String cookieName) {

		try {
			String ntesCookieValue = null;
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if ((cookies[i].getName()).equals(cookieName)) {
					ntesCookieValue = cookies[i].getValue();
					break;
				}
			}
			return CodecUtil.urlDecode(ntesCookieValue);
		} catch (Exception e) {
			return null;
		}
	}
	
}
