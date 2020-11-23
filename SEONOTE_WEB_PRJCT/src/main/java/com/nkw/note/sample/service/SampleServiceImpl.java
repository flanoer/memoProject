package com.nkw.note.sample.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkw.note.cmmn.transaction.service.TransactionService;
import com.nkw.note.cmmn.util.StringUtil;

@Service
public class SampleServiceImpl implements SampleService{
	
	private static final Logger log = LoggerFactory.getLogger(SampleServiceImpl.class);
	
	@Autowired
	TransactionService trxService;
	
	public String getCrawlingData(Map<String, Object> param, HttpServletRequest req) throws Exception {
		log.info("======================================= getCrawlingData =======================================");
		String url = StringUtil.nvl(param.get("url"),"");
		URL conURL = new URL(url);
		HttpURLConnection con = (HttpURLConnection) conURL.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "*/*");
        con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        con.setRequestProperty("Authority", "apis.naver.com");
        con.setRequestProperty("Scheme", "https");
        con.setRequestProperty("Referer", "https://news.naver.com/main/read.nhn?m_view=1&includeAllCount=true&mode=LSD&mid=sec&sid1=101&oid=015&aid=0004274346");
        con.setRequestProperty("Sec-fetch-dest", "script");
        con.setRequestProperty("Sec-fetch-mode", "no-cors");
        con.setRequestProperty("Sec-fetch-site", "same-site");
        con.setRequestProperty("Cookie", "NNB=O6LFUU3EZKUF6");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36");
        con.setRequestMethod("GET");
        
        String sb = "";
        OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
        wr.flush();
        
        int HttpResult =con.getResponseCode(); 
        if(HttpResult ==HttpURLConnection.HTTP_OK){
	        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));  
	
	        String line = null;  
	
	        while ((line = br.readLine()) != null) {  
	        	sb = sb+line + "\n";  
	        }  
	
	        br.close();  
        }else{
            System.out.println(con.getResponseMessage());
            sb = con.getResponseMessage();
        }  
        
		return sb.toString();
	}

	public int insertCrawlingListData(List<Map<String, Object>> list) throws Exception{
		try {
			trxService.insertList(list, "SMPL_BOARD_QUERY.insertList", "mydb");
		} catch(Exception e) {
			
		}
		return 0;
	}
	
	public Map<String, Object> getList(Map<String, Object> param) {
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			result.put("result", "success");
			list.addAll(trxService.selectList(param, "MEMO_QUERY.selectMemoList", "mydb"));
			log.debug("selectList check >>> {}",list);
			result.put("data", list);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			result.put("result", "error");
		}
		return result;
	}

}
