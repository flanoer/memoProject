<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.*" %>
<% request.setCharacterEncoding("utf-8"); %>

<title>NARUI</title>
<meta charset="utf-8">
<link rel="icon" href="/logo.ico" type="image/x-icon" />
<link rel="shortcut icon" href="/logo.ico" type="image/x-icon" />
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=EDGE">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="/web/js/lib/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		let data = {};
		data.url = 'https://news.naver.com/main/read.nhn?m_view=1&includeAllCount=true&mode=LSD&mid=sec&sid1=101&oid=015&aid=0004274346#';
		data.elementId = 'cbox_module_wai_u_cbox_content_wrap_tabpanel';
		$.post('/sample/crawling.do',data,function(resp){
			console.log(resp);
		});
	});
</script>
</head>

<body>
HELLO WORLD!
</body>
</html>