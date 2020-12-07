let pin = {
	//가상키패드--------------------------------------------------------START
	// 키패드 객체
	keypad : {
		wrapDiv : ''				// 키패드 템플릿 감싸는 div 객체
		, keyObj : ''				// 키패드 숫자패드 객체
		, fakeObj : ''				// 특수문자로 비밀번호 입력 확인시켜주는 객체
		, msgObj : ''				// 입력, 재입력 등의 정상 메시지 객체
		, errMsgObj : ''			// 비밀번호 불일치 등의 에러 메시지 객체
		, msg : ''					// init option으로부터 세팅되는 메시지
		, keyVal : ''				// pin code 데이터 입력받는 객체
		, keyConfirm : ''			// pin code 두번 입력시 첫번째 입력한 값 담는 객체
		, keyLen : 0				// 전체 길이
		, flag : ''					// 키패드 용도구분 (js 용 flag 값)
		, token : ''				// 키패드 세션에서 조회하기 위한 token 값
		, encCertPem : ''				// 암호화용 공개키
		// 데이터 set(display & request data)
		, setData : function(data) {
			let t = this;
			let beforeTxt = "○○○○○○○○○○○○○○○○○○○○"; //max 20자
			let afterTxt = "●●●●●●●●●●●●●●●●●●●●";
			let dataLen = data.length / 4; // 실제 입력 데이터는 1자리 이므로 키패드별로 세팅된 랜덤문자열 4자리만큼 나눔
			let tmpKeyLen = t.keyLen / 4;
			let countValue = afterTxt.substring(0, dataLen) + beforeTxt.substring(0, tmpKeyLen - dataLen);
			t.fakeObj.html(countValue);
			t.keyVal = data;
		}
		// 데이터 reset
		, resetData : function(){
			this.setData("");
		}
		// 랜덤문자열 키패드에 세팅
		, setDataCode : function(keyObj){
			let t = this;
			// keypad
			let pinCodeStr = '';
			$.post('/getRandomPinCode',function(resp){
				t.token = resp.token;
				t.encCertPem = resp.encCertPem;
				pinCodeStr = resp.pinCode;
			}).done(function(){
				t.keyObj.find('.numbtn').each(function(i,v){
					let tmp = pinCodeStr.substring(0,4);
					// 원본 pin code 잘라내기
					pinCodeStr = pinCodeStr.substring(4);
					$(v).attr('data-code',tmp);
				});
				t.keyObj.find(".shuffle").triggerHandler("click");
			});
		}
		// 키패드.keyVal에 입력데이터 설정
		, getData : function() {
			return this.keyVal;
		}
		// 메시지 설정
		, setMsg : function(msg) {
			this.msgObj.html(msg);
		}
		// 에러메시지 설정
		, setErrtMsg : function(msg) {
			let tmpObj = this.errMsgObj;
			tmpObj.html(msg);
			setTimeout(function(){
				tmpObj.html('');
			},2000);
		}
		// option값 메시지 설정
		, resetMsg : function() {
			this.msgObj.html(this.msg);
		}
		// 키패드 보임
		, showPad : function() {
			this.wrapDiv.show();
		}
		// 키패드 숨김
		, hidePad : function() {		
			this.wrapDiv.hide();
		}
		// 키패드 삭제
		, deletePad : function() {
			this.wrapDiv.html('');
			this.initData();
		}
		// 키패드 데이터 초기화
		, initData : function() {
			let t = this;
			for(let p in t){
			    if(typeof t[p] === "function"){
			        continue;
			    }
			    if(typeof t[p] === "object" || typeof t[p] === "string") {
			    	t[p] = '';
			    }
			    if(typeof t[p] === "number"){
			    	t[p] = 0;
			    }
			};
		}
		// 키패드 초기화
		, initKeypad : function(options, callback) {
			let t = this;
			let o = options||{};
			
			t.flag = o.flag;
			t.msg = o.msg;
			t.keyLen = o.length * 4;
			
			t.wrapDiv = $(document).find('[data-id="pinDrawArea"]');
			
			// 키패드 html ajax get으로 불러오기
			$.get(contextPath+"/resources/assets/tmpl/numpad_template.html" ,async function(jqXHR, resp, stts){
				t.wrapDiv.html(jqXHR);
								
				t.msgObj = t.wrapDiv.find('[data-id="pinMsg"]');				
				t.errMsgObj = t.wrapDiv.find('[data-id="pinErrMsg"]');
				t.keyObj = t.wrapDiv.find('[data-id="numpad"]');
				t.fakeObj = t.wrapDiv.find('[data-id="fakePin"]');
				
				t.setMsg(t.msg);
				t.setData('');
				t.eventListener(callback);
				await t.setDataCode();
				t.showPad();
			});
		}
		// 이벤트 리스너
		, eventListener : function(callback){
			let t = this;
			
			// 숫자패드 이벤트 리스너
			t.keyObj.find(".numbtn").off().on('click', function(e) {
				let data = t.getData();			
				let input = $(e.target).data('code');
	        	if (data.length < t.keyLen) { //최대 자리수 넘는지 확인
	        		t.setData(data + input);
	        		data = t.getData();
	        	}
	        	if (data.length == t.keyLen) {
	        		if (t.flag=='CREATE' && t.keyConfirm == '') { // 가변적 조건 && 확인용 data가 빈문자열 일때
						t.setMsg("다시 한 번 입력해주세요");
						// 데이터 일치 확인용 객체에 현재 데이터 세팅
						t.keyConfirm = t.getData();
						
						// 저장된 데이터 reset
						t.resetData();
					} else if (t.flag=='CREATE' && t.keyConfirm!='') { // 가변적 조건 && 확인용 data가 빈문자열이 아닐때
						if (data == t.keyConfirm) { // 1회 입력값 equal 2회 입력값
							let rtData = {};
							
							// 랜덤 키패드 데이터 암호화
							debugger;
							let encPpn = forge.pki.encWithPublicKey(t.encCertPem, forge.util.encodeUtf8(t.getData()));
							rtData.authType = 'pin';
							rtData.flag = t.flag;
							rtData.data = encPpn;
							rtData.token = t.token;
							callback(rtData);
							t.deletePad();
						} else { // 입력값 불일치
							t.setErrtMsg("입력값이 일치하지 않습니다.");
							t.setMsg("다시 한 번 입력해주세요");
							
							// 저장된 데이터 reset
							t.resetData();
						}						
					}
	        	}
			});

			// 입력된 값 삭제 버튼 리스너
			t.keyObj.find(".del").off().on('click', function(e) {
				let data = t.getData();			
				let input = $(e.target).data('code');
	        	if (data.length > 0) {
	        		data = data.substring(0, data.length-4);
	        	}
	        	t.setData(data);
			});
			
			// 숫자패드 재배열 버튼 리스너
			t.keyObj.find(".shuffle").off().on('click', function(e) {
				var allElems = $(e.target).closest('.numpad').find('.numbtn');			            
		        var shuffled = $.map(allElems, function(){
		        	var random = Math.floor(Math.random() * allElems.length);
		        	randEl = $(allElems[random]).clone(true)[0];
		        	allElems.splice(random, 1);
		        	return randEl;
		        });
		        $(this).closest('.numpad').find('.numbtn').each(function(i){
		            $(this).replaceWith($(shuffled[i]));
		        });
			});
		}
	}
    //가상키패드--------------------------------------------------------END
}