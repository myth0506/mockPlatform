$(document).ready(function() {
	$.ajax({
		url: '/yangguang/InsrJqxbj/iniQuote',
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.isJqx){
				$("#jqxFail").val(json.isJqx);
			}
			if(json.jqxFee){
				$("#jqxjg").val(json.jqxFee);
			}
			if(json.taxFee){
				$("#ccsjg").val(json.taxFee);
			}
		}
	});
});
function submitjqxbj(){
	var isJqx = $("#jqxFail").val();
	var jqxjg = $("#jqxjg").val();
	var ccsjg = $("#ccsjg").val();
	if(!isNullOrEmpty(jqxjg) && !isNullOrEmpty(ccsjg)){
		var data = {};
		data.isJqx = isJqx;
		data.jqxjg = jqxjg;
		data.ccsjg = ccsjg;
		$.ajax({
			url: '/yangguang/InsrJqxbj/forceQuote',
			data: data,
			type: 'POST',
			dataType: 'json',
			success: function(json){
				if(json.retCode == 200){
					bootbox.alert("设置交强险报价成功！");
				}else{
					bootbox.alert("设置交强险报价失败！");
				}
			}
		});
	}else{
		bootbox.alert("请填写完整的交强险报价信息！");
	}
}

function isNullOrEmpty(strVal) {
	if (strVal == '' || strVal == null || strVal == undefined) {
	return true;
	} else {
	return false;
	}
}