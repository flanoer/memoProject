package com.narui.pin.home.util;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.io.BaseEncoding;
import com.itextpdf.io.codec.Base64;

public class StrUtil {
	
	static Logger logger = Logger.getLogger(StrUtil.class);
	
	private StrUtil() {}
	
	/**
	 * 메소드 : 파라미터를 "&"과 "=" 으로 자른다.
	 * @param paramStr
	 * @return : params
	 */
	public static Map<String, List<String>> splitParmList(String paramStr) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		 
		for (String param : paramStr.split("&")) {
		   String[] pair = param.split("=");
		   String key = pair[0];//URLDecoder.decode(pair[0], "UTF-8");
		   String value = "";
		   if (pair.length > 1) {
		      value = pair[1]; //URLDecoder.decode(pair[1], "UTF-8");
		   }
	
		   List<String> values = params.get(key);
		   if (values == null) {
		      values = new ArrayList<String>();
		      params.put(key, values);
		   }
		   values.add(value);
		}
		return params;
	}
	
	/**
	 * 메소드 : 파라미터를 "&"과 "=" 으로 자른다.
	 * @param paramStr
	 * @return : params
	 */
	public static Map<String, String> splitParm(String paramStr) {
		Map<String, String> params = new HashMap<String, String>();
		 
		for (String param : paramStr.split("&")) {
		   String[] pair = param.split("=");
		   String key = pair[0];//URLDecoder.decode(pair[0], "UTF-8");
		   String value = "";
		   if (pair.length > 1) {
		      value = pair[1]; //URLDecoder.decode(pair[1], "UTF-8");
		   }
		   params.put(key, value);		   
		}
		return params;
	}
	
	/**
	 * 메소드 : null 체크
	 * @param str	
	 * @return : str
	 */
	public static String stringNvl(String str) {
		return stringNvl(str, "");
	}	
	public static String objStrNvl(Object str) {
		return stringNvl((String) str, "");
	}
	
	/**
	 * 메소드 : String 변수가 null 경우 기본값 셋팅 
	 * @param str
	 * @param defaultValue	
	 * @return : rtnValue
	 */
	public static String stringNvl(String str, String defaultValue) {
		String rtnValue = "";
		if(str == null || str.length() == 0) {
			rtnValue = defaultValue;
		} else {
			rtnValue = str;
		}		
		return rtnValue;
	}
	
	/**
	 * 메소드 : map을 파라미터 형태로 변경 ex) abc=abc&def=def&
	 * @param map
	 * @return
	 */
	public static String mapToParams(Map<String, ?> map) {
		String params = "";
		
		for (String mapKeys : map.keySet()) {
			params += mapKeys + "=" + map.get(mapKeys) + "&";
		}
		
		return params;
	}
	
	/**
	 * 메소드 : 숫자에 , 표시
	 * @param String 
	 * @return String
	 */
	public static String commaNum(String num) {
		int thisNum = Integer.parseInt(num);
		
		return NumberFormat.getIntegerInstance().format(thisNum);
	}
	
	/**
	 * 메소드 : 만나이 계산
	 * @param String
	 * @return Integer
	 */
	public static int getManAge(String birth) {
		int manAge = 0;
		
		Calendar calendar = Calendar.getInstance();
		
		int yBirth = Integer.parseInt( birth.substring(0, 4) );
		int mBirth = Integer.parseInt( birth.substring(4, 6) );
		int dBirth = Integer.parseInt( birth.substring(6, 8) );
		
		int yToday = calendar.get(Calendar.YEAR);
		int mToday = calendar.get(Calendar.MONTH) + 1;
		int dToday = calendar.get(Calendar.DATE);
		
		int yDiff = yToday - yBirth;
		int mDiff = mToday - mBirth - (dToday < dBirth ? 1 : 0);
		
		if(mDiff < 0) {
			mDiff += 12;
			--yDiff;
		}
		
		manAge = yDiff;
		
		return manAge;
	}
	
	/**
	 * 문자열 뒤에 붙을 말이 을(이/은)인지 를(가/는)인지 알려주는 함수
	 * @param word
	 * @return
	 */
	public static boolean eulrlega(String word) {
		char last = word.charAt(word.length()-1);
		
		if(last < 0xAC00)
			return true;	
		last -= 0xAC00;		
		char jong = (char) ((last % 28) + 0x11a7);				
		return (jong != 4519);
	}	
	public static String getEulrl(String word) {
		return eulrlega(word)? word + "을" : word+"를";
	}	
	public static String getEga(String word) {
		return eulrlega(word)? word+"이" : word+"가";
	}	
	public static String getEunnn(String word) {
		return eulrlega(word)? word+"은" : word+"는";		
	}
	
	
	public static String bytesToHex(byte[] bytes) {
		char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
		
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}	
	
	public static byte[] hexToByte(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	public static String hexToB64(String s) {
		return Base64.encodeBytes(hexToByte(s));		
	}
			
	public static String b64urlToB64(String s) {
		return Base64.encodeBytes(BaseEncoding.base64Url().decode(s));
	}
	
	public static String toB64(String s) {
		return Base64.encodeBytes(s.getBytes());
	}
}
