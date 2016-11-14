package com.mockCommon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WipeTabEnterSpaceUtil {
	public static String wipe(String s){
		Pattern p = Pattern.compile("\\s{2,}|\t|\r|\n");
		Matcher m = p.matcher(s);
		s = m.replaceAll("");
		return s;
	}
}