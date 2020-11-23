<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="/web/js/lib/jquery-3.4.1.min.js"></script>
<script type="text/javascript" charset="utf-8">
const abc = '가나다';
const bbc = '나나다';
console.log(abc+' 는' + bbc + '다');

// jsp 에서는 text interpolation 과 관련하여 el tag 와 충돌이 일어남.
// 따라서 일반적인 형식으로 template literal 을 사용할 수 없음.

// 1. js 파일로 따로 관리한다면 template literal 을 정상적으로 사용가능함.
// 2. 아래와 같이 el tag 내부에 js 의 변수를 넣어서 사용
// 3. backslash 로 jsp el tag가 무시되도록 함
console.log(`111 ${'${abc}'} 와 ${'${bbc}'} 확인`);
console.log(`222 \${abc} 와 \${bbc} 확인`);

$(document).ready(function(){
	console.log(`${'${abc}'} 와 ${'${bbc}'} 확인`);
	console.log(`${1+2} 는 3이다`);
// 	getCategory();
	getList();
// 	getUser();
});

let getList = function(sendData){
	if(sendData == undefined) sendData = {};
	$.ajax({
		url : '/sampleRest/getList.do',
		method : 'GET',
		data : sendData,
		dataType : "json",
		success : successGetList,
		fail : function(resp){
			console.log(resp);
		}
	});
};

let successGetList = function(resp){
	console.log(resp);
};
</script>
</head>
<body>
<div id="categoryDiv">
	<ul class="categoryList">
		<li></li>
	</ul>
</div>
<div id="dataDiv">
	<ul class="dataList">
		<li></li>
	</ul>
</div>
</body>
</html>