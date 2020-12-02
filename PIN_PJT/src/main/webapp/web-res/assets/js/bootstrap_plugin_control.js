/**
 * 경고 팝업 열기
 * @param text
 * @returns
 */
function openAlert(text) {
	if($("#layerWrapAlert").length > 0) {
		$("#alertText").html(text);
		$("#layerWrapAlert").modal("show");
		$("#layerWrapAlert").css("top", $(window).height()/2 - $("#alertDiv").height()/2);
	} else {
		$(parent.document).find("#alertText").html(text);
		$(parent.document).find("#layerWrapAlert").css("top", $(parent.window).height()/2 - $(parent.document).find("#alertDiv").height()/2);
		$(parent.document).find("#layerWrapAlert").modal("show");
	}
}
function openAlert(text, callback) {
	$("#alertText").html(text);	
	//$("#layerWrapAlert").css("top", $(window).height() - $("#layerWrapAlert").height()/2);
	$("#layerWrapAlert").modal("show");
	$("#layerWrapAlert").css("top", $(window).height()/2 - $("#alertDiv").height()/2);
	$("#layerWrapAlert .confirmBtn").on("click", function(e) {		//팝업 오픈 버튼 클릭시		
		$("#alertText").html("");		
		$("#layerWrapAlert").modal("hide");
		if( typeof callback != 'undefined' && callback) {
			callback();
		}
	});	
}
/**
 * 알림 팝업 닫기
 * @param
 * @returns
 */
function closeAlert() {
	if($("#layerWrapAlert").length > 0) {
		$("#alertText").html("");
		$("#layerWrapAlert").modal("hide");
	} else {
		$(parent.document).find("#alertText").html("");
		$(parent.document).find("#layerWrapAlert").modal("hide");
	}
}

/**
 * 컨펌 팝업 열기
 * @param
 * @returns
 */
function openConfirm(text,callback,count) {
	$("#confirmText").html(text);	
	//$("#layerWrapAlert").css("top", $(window).height() - $("#layerWrapAlert").height()/2);
	$("#layerWrapConfirm").modal("show");
	$("#layerWrapConfirm").css("top", $(window).height()/2 - $("#confirmDiv").height()/2);
	if(count != undefined || count > 0){
		$("#layerWrapConfirm .confirmBtn").each(function(){
			$(this).off().on("click",function(){
				var idx = $(this).index();
				$("#confirmText").html("");		
				$("#layerWrapConfirm").modal("hide");
				if( typeof callback != 'undefined' && callback) {
					callback(idx);
				}
			});
		});
	} else {
		$("#layerWrapConfirm .confirmBtn").on("click", function(e) {		//팝업 오픈 버튼 클릭시		
			$("#confirmText").html("");		
			$("#layerWrapConfirm").modal("hide");
			if( typeof callback != 'undefined' && callback) {
				callback();
			}
		});	
	}
}
/**
 * 컨펌 팝업 닫기
 * @param
 * @returns
 */
function closeConfirm() {
	if($("#layerWrapConfirm").length > 0) {
		$("#confirmText").html("");
		$("#layerWrapConfirm").modal("hide");
	} else {
		$(parent.document).find("#confirmText").html("");
		$(parent.document).find("#layerWrapConfirm").modal("hide");
	}
}