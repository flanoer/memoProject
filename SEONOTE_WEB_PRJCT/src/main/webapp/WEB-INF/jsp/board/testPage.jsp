<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
<!-- favicon error 해결 -->
<link rel="shortcut icon" href="#">

<script type="text/javascript" src="/web/js/lib/jquery-3.4.1.min.js"></script>
<!-- 개발버전, 도움되는 콘솔 경고를 포함. -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
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
		fail : function(resp){ console.log(resp); }
	});
};

let successGetList = function(resp){
	console.log(resp);
	let data = resp.data;
	let ctgStr = '';
	let	mmStr = '';
	for(i in data){
		let ctgRow = `<li>\${data[i].CATEGORY_NAME}</li>\n`;
		let mmParent = '\${data[i].M_PRNT_SEQ}';
		let title = `\${data[i].TITLE}`;
		let mmRow = `<tr>
			<td>\${data[i].M_SEQ}</td>
			<td>\${title}</td>
			<td>\${data[i].CONTENTS}</td>
			<td>\${data[i].CATEGORY_NAME}</td>
			<td>\${data[i].USER_NICKNAME}</td>
		</tr>`;
		
		ctgStr += ctgRow;
		mmStr += mmRow;
	}
	
	let vm = new Vue({
		el: '#mmList'
		, data : function(){
			return {
				str : mmStr
			};
		}
	});
	
	$('#ctgList').append(ctgStr);
// 	$('#mmList').find('tbody').append(mmStr);
};
</script>
</head>
<body>
<div id="categoryDiv">
	<ul id="ctgList">
	</ul>
</div>
<div id="dataDiv">
	<table id="mmList">
		<thead>
			<tr>
				<th>메모번호(M_SEQ)</th>
				<th>제목</th>
				<th>내용</th>
				<th>분류</th>
				<th>등록한 사람</th>
			</tr>
		</thead>
		<tbody v-html="str">
		</tbody>
	</table>
</div>
</body>
</html>