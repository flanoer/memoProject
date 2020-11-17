package com.nkw.note.cmmn.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CommonUtil{
	
	public static String right(String src, int length){
		if (length > src.length()) {
			return src;
		}
		return src.substring(src.length() - length, src.length());
	}

	public static String replace(String src, String oldstr, String newstr){
		if (src == null) {
			return null;
		}
		StringBuffer dest = new StringBuffer("");
		int len = oldstr.length();
		int srclen = src.length();
		int pos = 0;
		int oldpos = 0;
		if ((pos = src.indexOf(oldstr, oldpos)) >= 0){
			dest.append(src.substring(oldpos, pos));
			dest.append(newstr);
			oldpos = pos + len;
		}
		if (oldpos < srclen) {
			dest.append(src.substring(oldpos, srclen));
		}
		return dest.toString();
	}

	public static String replaceAll(String src, String oldstr, String newstr){
		if (src == null) {
			return null;
		}
		StringBuffer dest = new StringBuffer("");
		int len = oldstr.length();
		int srclen = src.length();
		int pos = 0;
		int oldpos = 0;
		while ((pos = src.indexOf(oldstr, oldpos)) >= 0){
			dest.append(src.substring(oldpos, pos));
			dest.append(newstr);
			oldpos = pos + len;
		}
		if (oldpos < srclen) {
			dest.append(src.substring(oldpos, srclen));
		}
		return dest.toString();
	}

	public static String cutStr(String str, int length){
		if (str.length() > length) {
			str = str.substring(0, length);
		}
		return str;
	}
	
	public static String URLEncode(String str)throws Exception{
		try{
			return str == null ? null : URLEncoder.encode(str);
		}catch (Exception e){
			throw new Exception(e);
		}
	}

	public static String URLDecode(String str)throws Exception{
		try{
			return str == null ? null : URLDecoder.decode(str);
		}catch (Exception e){
			throw new Exception(e);
		}
	}

	public static int getYear(){
		return Calendar.getInstance().get(1);
	}

	public static int getMonth(){
		return Calendar.getInstance().get(2) + 1;
	}

	public static int getDay(){
		return Calendar.getInstance().get(5);
	}

	public static int getHour(){
		return Calendar.getInstance().get(11);
	}

	public static int getMinute(){
		return Calendar.getInstance().get(12);
	}

	public static boolean isEmpty(String str){
		if ((str != null) && (!str.trim().equals(""))) {
			return false;
		}
		return true;
	}

	public static boolean isNotEmpty(String str){
		if ((str == null) || (str.trim().equals(""))) {
			return false;
		}
		return true;
	}

	public static String getTodayDate(){
		return getYear() + "-" + right(new StringBuilder("0").append(getMonth()).toString(), 2) + "-" + right(new StringBuilder("0").append(getDay()).toString(), 2);
	}

	public static String httpConnection(String url){
		try{
			URL tbUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)tbUrl.openConnection();
			conn.setDoOutput(true);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String resultValue = "";
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				resultValue = resultValue + inputLine;
			}
			in.close();
			
			conn.disconnect();
			conn = null;
			return resultValue;
		}catch (IOException localIOException) {
			
		}
		return "[ERROR]";
	}

	public static String nvl(String str, String replace){
		String result = str;
		if (isEmpty(str)) {
			result = replace;
		}
		return result;
	}

	public static String encrypt(String str){
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(str.getBytes());
	}

	public static String decrypt(String str)throws IOException{
		BASE64Decoder decoder = new BASE64Decoder();
		return new String(decoder.decodeBuffer(str));
	}
	
	
	public static String getRandomStr(int lth)throws IOException{
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < lth; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // A-Z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		return temp.toString();
	}
	
	
	
	/* 엑셀 공통. html화면의 테이블을 이용하여 엑셀 생성 */
	@Bean
	public static String excelCreater(String excelDownPath, String title, String thVal, String trVal){
		
		String[] thValArr = thVal.split("\\|");
		String[] trValArr = trVal.split("@");
		String fNm = title+"_"+getTodayDate()+".xlsx";
		String path = excelDownPath;
		String filePath = path+"/"+fNm;
		
		try {
			
			FileOutputStream fileOut = new FileOutputStream(filePath);
			Workbook wb = new XSSFWorkbook();
	
			// 첫번째시트
			Sheet sheet = wb.createSheet("sheet1");
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, thValArr.length-1));//열시작, 열종료, 행시작, 행종료
			
			try {
				//제목 레코드
				Row row = sheet.createRow(0);
				row.createCell(0).setCellValue(title);
				
				row = sheet.createRow(1);
				for( int i=0; i<thValArr.length; i++ ){
					row.createCell(i).setCellValue(thValArr[i]);
				}
				
				//내용 레코드
				for(int r=0 ; r < trValArr.length; r++){
					row = sheet.createRow(r+2);//행
					for( int i=0; i<thValArr.length; i++ ){
						String[] tdValArr = trValArr[r].split("\\|");
						row.createCell(i).setCellValue(tdValArr[i]);
					}
				}
				
				// 엑셀파일에 상위에서 세팅된 데이터 쓰기
				wb.write(fileOut);
				fileOut.close();
				
			} catch (Exception e) {
				fileOut.close();
				e.printStackTrace();
			}
		
		} catch (Exception e) {
		}
		
		return fNm;
	}
  
}
