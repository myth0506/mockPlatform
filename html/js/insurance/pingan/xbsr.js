$(document).ready(function() {
	$.ajax({
		url: '/InsrXbsr/ini',
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.sfxb=="1"){
				$("#xbyhYes").attr("checked","checked");
			}
			if(json.sfxb=="0"){
				$("#xbyhNo").attr("checked","checked");
			}
			if(json.birthday){
				$("#birthday").val(json.birthday);
			}
			if(json.vehicleNo){
				$("#vehicleNo").val(json.vehicleNo);
			}
		}
	});
});
function submitXbyh(){
	var isXbyh = $("#xbyhRadio input:radio:checked").val();
	if(!isNullOrEmpty(isXbyh)){
		var data = {};
		data.isXbyh = isXbyh;
		$.ajax({
			url: '/InsrXbsr/renewalCheck',
			data: data,
			type: 'POST',
			dataType: 'json',
			success: function(json){
				if(json.retCode == 200){
					bootbox.alert("设置是否续保用户成功！");
				}else{
					bootbox.alert("设置是否续保用户出错！");
				}
			}
		});
	}else{
		bootbox.alert("请选择是否是续保用户！");
	}
}

function submitBirthday(){
	var birthday = $("#birthday").val();
	if(!isNullOrEmpty(birthday)){
		var data = {};
		data.birthday = birthday;
		$.ajax({
			url: '/InsrXbsr/renewalConfirm',
			data: data,
			type: 'POST',
			dataType: 'json',
			success: function(json){
				if(json.retCode == 200){
					bootbox.alert("设置生日校验成功！");
				}else{
					bootbox.alert("设置生日校验出错！");
				}
			}
		});
	}else{
		bootbox.alert("请填写校验日期！");
	}
}

function isNullOrEmpty(strVal) {
	if (strVal == '' || strVal == null || strVal == undefined) {
	return true;
	} else {
	return false;
	}
}
//保存车牌号
function saveVehiclNo(){
	var vehicleNo = $("#vehicleNo").val();
	if(isNullOrEmpty(vehicleNo)){
		bootbox.alert("请输入车牌号码！");
		return;
	}
	var data = {};
	data.vehicleNo = vehicleNo;
	$.ajax({
		url: '/InsrXbsr/saveVehicleNo',
		data: data,
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.retCode == 200){
				bootbox.alert("车牌号保存成功！");
			}else{
				bootbox.alert("车牌号保存出错！");
			}
		}
	});
}