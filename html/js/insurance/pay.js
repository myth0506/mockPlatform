$(document).ready(function() {
	$.ajax({
		url: '/pay/ini',
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.retCode == 200){
				if(json.payStatus){
					$("input:radio[name='pay'][value='" + json.payStatus + "']").attr('checked','true');
				}
			}
		}
	});
});

function isNullOrEmpty(strVal) {
	if (strVal == '' || strVal == null || strVal == undefined) {
		return true;
	} else {
		return false;
	}
}
function savePay(){
	var payStatus = $("input[name='pay']:checked").val();
	if(isNullOrEmpty(payStatus)){
		bootbox.alert("请选择支付状态");
		return;
	}
	
	var data = {};
	data.payStatus = payStatus;
	$.ajax({
		url: '/pay/savePay',
		type: 'POST',
		data: data,
		dataType: 'json',
		success: function(json){
			if(json.retCode=="200"){
				bootbox.alert("设置保存成功");
			} else{
				bootbox.alert("设置保存失败");
			}
		}
	});
}