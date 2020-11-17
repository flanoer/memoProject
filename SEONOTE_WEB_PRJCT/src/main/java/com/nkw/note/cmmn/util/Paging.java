package com.nkw.note.cmmn.util;

import java.util.HashMap;
import java.util.List;


public class Paging{
	
	
	/*
	 * 페이지번호로 startNo, endNo 처리.
	 * param: 페이지번호 pageNo (디폴트 1)
	 * param: 페이지 사이즈 pageSize. 1페이지의 목록 건수 (디폴트 10)
	 * param: 페이지 카운트 pageCnt 페이징 범위 (디폴트 10)
	 * */
	public static void cmmnPageNo(HashMap<String, Object> paramMap) {
		// TODO Auto-generated method stub
		int pageNo = Integer.parseInt(CommonUtil.nvl((String)paramMap.get("pageNo"), "1"));
		int pageSize = Integer.parseInt(CommonUtil.nvl((String)paramMap.get("pageSize"), "10"));
		int pageCnt = Integer.parseInt(CommonUtil.nvl((String)paramMap.get("pageCnt"), "10"));
		
		int startNo = (pageNo*pageSize-pageSize);
		int endNo = (pageNo*pageSize);
		
		paramMap.put("pageNo", pageNo);
		paramMap.put("pageSize", pageSize);
		paramMap.put("pageCnt", pageCnt);
		paramMap.put("startNo", startNo);//페이징 쿼리 between 시작
		paramMap.put("endNo", endNo);//페이징쿼리 between 종료
		
	}


	/*
	 * 목록 화면 페이징 처리.
	 * param: 맵 paramMap => 페이징 쿼리를 호출했던 맵. pageNo, pageSize가 담겨있는.
	 * param: 리스트 list => totCnt 값이 존재하는 list
	 * */
	public static HashMap<String, Object> cmmnPaging(HashMap<String, Object> paramMap, List<?> dataList, int totCnt) {
		
		String clickFnNm = "fn_PageClick";
		
		int pageNo = Integer.parseInt(CommonUtil.nvl(paramMap.get("pageNo").toString(), "1"));
		int pageSize = Integer.parseInt(CommonUtil.nvl(paramMap.get("pageSize").toString(), "10"));
		int pageCnt = Integer.parseInt(CommonUtil.nvl(paramMap.get("pageCnt").toString(), "10"));
		int first = 1;
		int prev = 0;
		int next = 0;
		int last = 0;
		double totPerSize = totCnt/(pageSize+0.0);
		last = (int) Math.ceil(totPerSize);
		
		prev = ((pageNo-1)/pageCnt)*pageCnt;
		next = prev+pageCnt+1;
		
		int pageArea1 = (int) Math.ceil(pageNo/(pageCnt+0.0));
		int pageArea2 = (int) Math.ceil(last/(pageCnt+0.0));
		
		String pageingHtml = "";
		
		if( prev > 0 ) {
//			pageingHtml += "<a href='#' onclick='"+clickFnNm+"(this); return false;' data-no='"+first+"'><<</a>";
//			pageingHtml += "<a href='#' onclick='"+clickFnNm+"(this); return false;' data-no='"+prev+"'><</a>";
			pageingHtml += "<li class='first'><a href='#' onclick='"+clickFnNm+"(this); return false;' data-no='"+first+"'></a><li>";
			pageingHtml += "<li class='prev'><a href='#' onclick='"+clickFnNm+"(this); return false;' data-no='"+prev+"'></a></li>";
		}
		
		for( int i=1; i<=pageCnt; i++ ) {
			int createNo = prev+i;
			
			if(createNo <= last) {
				
				String on = "";
				if(createNo == pageNo) {
					on = "on";
				}
				//pageingHtml += "<a href='#' onclick='"+clickFnNm+"(this); return false;' class='"+on+"' data-no='"+createNo+"'>"+createNo+"</a>";
				pageingHtml += "<li class='"+on+"'><a href='#' onclick='"+clickFnNm+"(this); return false;' data-no='"+createNo+"'>"+createNo+"</a></li>";
			}
		}
		
		
		if( totCnt/pageSize > pageCnt && pageArea2 > pageArea1 ) {
//			pageingHtml += "<a href='#' onclick='"+clickFnNm+"(this); return false;' data-no='"+next+"'>></a>";
//			pageingHtml += "<a href='#' onclick='"+clickFnNm+"(this); return false;' data-no='"+last+"'>>></a>";
			pageingHtml += "<li class='next'><a href='#' onclick='"+clickFnNm+"(this); return false;' data-no='"+next+"'></a></li>";
			pageingHtml += "<li class='last'><a href='#' onclick='"+clickFnNm+"(this); return false;' data-no='"+last+"'></a></li>";
		}
		
		if(totCnt == 0) {
			pageNo = 0;
		}
		
		HashMap<String, Object> pagingMap = new HashMap<String, Object>();
		pagingMap.put("pageNo", pageNo);
		pagingMap.put("pageSize", pageSize);
		pagingMap.put("pageCnt", pageCnt);
		pagingMap.put("totCnt", totCnt);
		pagingMap.put("totPerCnt", pageNo+"/"+last);
		pagingMap.put("pageingHtml", "<ul>"+pageingHtml+"</ul>");
		
		return pagingMap;
	}
	
}