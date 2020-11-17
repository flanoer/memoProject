package com.nkw.note.cmmn.util;

public class StringUtil{
	
	public static String nvl(String orgStr, String str) {
		if (orgStr == null || orgStr.equals("")) {
			return str;
		}
		return orgStr;
	}
	public static String nvl(Object orgStr, String str) {
		if (orgStr == null || orgStr.equals("")) {
			return str;
		}
		return orgStr.toString();
	}
}