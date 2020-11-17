<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.*" %>
<% request.setCharacterEncoding("utf-8"); %>

<title>NARUI</title>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=EDGE">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="/web/js/lib/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	// 코멘트 데이터 가져오기
	fncGetCommentData();
});

let fncGetCommentData = function(){
	let param = {};
	param.url = 'https://apis.naver.com/commentBox/cbox/web_naver_list_jsonp.json?ticket=news&templateId=default_economy&pool=cbox5&_callback=fncJSONDataInsert&lang=ko&country=KR&objectId=news015%2C0004274346&categoryId=&pageSize=20&indexSize=10&groupId=&listType=OBJECT&pageType=more&page=1&initialize=true&userType=&useAltSort=true&replyPageSize=20&sort=favorite&includeAllStatus=true&_=1604897380538';
	param = JSON.stringify(param);
	$.ajax({
		type: "POST",
		url: '/sample/crawling.do',
		data: param,
		async: false,
		success: function(resp){console.log(resp);},
		contentType: 'application/json',
		jsonpCallback: "fncJSONDataInsert",
		dataType: 'jsonp'
	});
}

let fncJSONDataInsert = function(resp){
	console.log(resp);
	fncInsertCommentData(resp);
}

let fncInsertCommentData = function(respData){
	console.log('after jsonpCallback!');
	let param = {};
	param.listData = respData.result.commentList;
	param = JSON.stringify(param)
	$.ajax({
		type: "POST",
		url: '/sample/insertCommentList.do',
		data: param,
		async: false,
		contentType: 'application/json',
		success: function(resp){console.log(resp);}
	});
}
</script>
</head>

<body>
HELLO WORLD!
</body>
</html>