<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- narui 키패드 샘플페이지 입니다. --%>
<!DOCTYPE html>
<html>
<head>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">		

<!-- ================== BEGIN BASE CSS STYLE ================== -->
<!-- favicon error 해결 -->
<link rel="shortcut icon" href="#">

<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/assets/css/default/app.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/assets/css/default/pin_pattern.css" rel="stylesheet" />
<!-- ================== END BASE CSS STYLE ================== -->
	
<title>narui 키패드 샘플</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/forge.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
<script>
const contextPath = "${pageContext.request.contextPath}";
$(document).ready(function(){
	let p = pin;
	let keypad = p.keypad;
	
	// 키패드 생성
	$("#pinCreate").off().on('click',function(){
		let options = {};
		options.flag = 'CREATE';
		options.length = 6;
		options.msg = `인증에 사용할 숫자 \${options.length}자리를 입력해주세요.`;
		keypad.initKeypad(options, function(res){
			console.log(res);
			let sendData = {
				pinData : res.data
				, token : res.token
			};
			$.post('/decPinCode',sendData,function(res){
				// error 및 exception 관련된 처리 필요
				console.log(res);
				$('#pinShow, #pinHide, #pinDelete').hide();
			});
		});
		
		$('#pinShow, #pinHide, #pinDelete').show();
		
		// 키패드 삭제
		$('#pinDelete').off().on('click',function(){
			keypad.deletePad();
			$('#pinShow, #pinHide, #pinDelete').hide();
		});
		
		// 키패드 보이기
		$('#pinShow').off().on('click',function(){
			keypad.showPad();
		});
		
		// 키패드 숨기기
		$('#pinHide').off().on('click',function(){
			keypad.hidePad();
		});
	});
});
</script>
</head>
<body>	
	<div id="page-container" >
		<div id="header" class="header navbar-default">
			<!-- begin navbar-header -->
			<div class="navbar-header">
				<a href="/pinMain" class="navbar-brand">
					<img src="${pageContext.request.contextPath}/resources/img/cropped-naru_top_logo2.png" alt="NARU Interactive">
				</a>				
			</div>
			<!-- end navbar-header -->						
		</div>
		
		<div id="content" class="content">
			<div class="row">
				<div class="col-xm-3">
					<button id="pinCreate" type="button" class="btn btn-default">핀 버튼 생성</button>
				</div>
				<div class="col-xm-3">
					<button id="pinDelete" type="button" class="btn btn-default">핀 버튼 삭제</button>
				</div>
			</div>
			<div class="row">
				<div class="col-xm-3">
					<button id="pinShow" type="button" class="btn btn-default">핀 버튼 보이기</button>
				</div>
				<div class="col-xm-3">
					<button id="pinHide" type="button" class="btn btn-default">핀 버튼 숨기기</button>
				</div>
			</div>
			<div class="row">
				<div class="col-xl-6">
					<div class="form-group row" data-id="pinDrawArea" ></div>
				</div>
			</div>	
		</div>
	</div>
	<!-- layerWrap -->
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/bootstrap_plugin_control.js"></script>
	<!-- ================== END BASE JS ================== -->
</body>
</html>