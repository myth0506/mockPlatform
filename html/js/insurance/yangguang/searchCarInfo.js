$(document).ready(function(){
	$.ajax({
		url: '/yangguang/queryNpsInfo',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			var code = json.retCode;
			if(code=="200"){
				$("#isNpsFlow").val(json.isNpsFlow)
			}
		}
	});
	var vehicleNo = $.cookie('vehicleNo');
	if(!isNullOrEmpty(vehicleNo)){
		var data = {};
		data.vehicleNo = vehicleNo;
		$.ajax({
			url:'/yangguang/searchCarInfo',
			type:'POST',
			data: data,
			dataType:'json',
			success:function(json){
				if(json.retCode == "200"){
					var searchCarInfo = json.searchCarInfo;
					if(!isNullOrEmpty(searchCarInfo)){
						$("#registerName").val(searchCarInfo.ownerName);
						$("#registerIdNo").val(searchCarInfo.ownerIdNo);
						$("#registerMobile").val(searchCarInfo.ownerMobile);
						$("#registerEmail").val(searchCarInfo.ownerEmail);
						$("#applicantName").val(searchCarInfo.applicantName);
						$("#applicantIdNo").val(searchCarInfo.applicantIdNo);
						$("#applicantMobile").val(searchCarInfo.applicantMobile);
						$("#applicantEmail").val(searchCarInfo.applicantEmail);
						$("#insuredName").val(searchCarInfo.insuredName);
						$("#insuredIdNo").val(searchCarInfo.insuredIdNo);
						$("#insuredMobile").val(searchCarInfo.insuredMobile);
						$("#insuredEmail").val(searchCarInfo.insuredEmail);
						$("#vehicleFrameNo").val(searchCarInfo.vehicleFrameNo);
						$("#vehicleEngineNo").val(searchCarInfo.engineNo);
						$("#vehicleRegisterDate").val(getDate(searchCarInfo.registerDate));
						$("#vehicleModelName").val(searchCarInfo.vehicleModelName);
						$("#vehicleVehicleId").val(searchCarInfo.vehicleId);
						$("#vehicleSpecialCarFlag").val(searchCarInfo.specialCarFlag);
						if(searchCarInfo.specialCarDate != null){
							$("#vehicleSpecialCarDate").val(getDate(searchCarInfo.specialCarDate));
						}
					}
				}
			}
		});
	}
});

function statusChanged(){
	if($("#vehicleSpecialCarFlag").find("option:selected").val() == 0){
		$("#vehicleSpecialCarDate").val("");
	}
}

function applicantCheckBox(){		
	if($('#applicantCheckBox').is(':checked')){
		$("#applicantName").val($("#registerName").val());
		$("#applicantMobile").val($("#registerMobile").val());
		$("#applicantIdNo").val($("#registerIdNo").val());
		$("#applicantEmail").val($("#registerEmail").val());
	
	}
}

function insuredCheckBox(){
	if($('#insuredCheckBox').is(':checked')){
		$("#insuredName").val($("#registerName").val());
		$("#insuredMobile").val($("#registerMobile").val());
		$("#insuredIdNo").val($("#registerIdNo").val());
		$("#insuredEmail").val($("#registerEmail").val());
	}
}

function submitInfo(){
	var data = {};
	var vehicleNo = $.cookie('vehicleNo');

	data.vehicleNo = vehicleNo;
	if(isNullOrEmpty(data.vehicleNo)){
		bootbox.alert("未填写车牌号，请到初始页填写");
		return ;
	}
	
	if(isNullOrEmpty($("#registerName").val()) || isNullOrEmpty($("#registerIdNo").val()) 
		|| isNullOrEmpty($("#registerMobile").val()) || isNullOrEmpty($("#registerEmail").val())){
		bootbox.alert("车主信息未填写完整！");
		return ;
	}
	if(isNullOrEmpty($("#applicantName").val()) || isNullOrEmpty($("#applicantIdNo").val()) 
		|| isNullOrEmpty($("#applicantMobile").val()) || isNullOrEmpty($("#applicantEmail").val())){
		bootbox.alert("投保人信息未填写完整！");
		return ;
	}
	if(isNullOrEmpty($("#insuredName").val()) || isNullOrEmpty($("#insuredIdNo").val()) 
		|| isNullOrEmpty($("#insuredMobile").val()) || isNullOrEmpty($("#insuredEmail").val())){
		bootbox.alert("被投保人信息未填写完整！");
		return ;
	}
	
	if(isNullOrEmpty($("#vehicleFrameNo").val()) || isNullOrEmpty($("#vehicleEngineNo").val()) 
	|| isNullOrEmpty($("#vehicleRegisterDate").val())
	|| isNullOrEmpty($("#vehicleModelName").val()) || isNullOrEmpty($("#vehicleVehicleId").val())
	|| ($("#vehicleSpecialCarFlag").val() == "1" && isNullOrEmpty($("#vehicleSpecialCarDate").val()))){
		bootbox.alert("车辆信息未填写完整！");
		return ;
	}
	
	data.ownerName = $("#registerName").val();
	data.ownerIdNo = $("#registerIdNo").val();
	data.ownerMobile = $("#registerMobile").val();
	data.ownerEmail = $("#registerEmail").val();
	
	data.applicantName = $("#applicantName").val();
	data.applicantIdNo = $("#applicantIdNo").val();
	data.applicantMobile = $("#applicantMobile").val();
	data.applicantEmail = $("#applicantEmail").val();
	
	data.insuredName = $("#insuredName").val();
	data.insuredIdNo = $("#insuredIdNo").val();
	data.insuredMobile = $("#insuredMobile").val();
	data.insuredEmail = $("#insuredEmail").val();
	
	data.vehicleFrameNo = $("#vehicleFrameNo").val();
	data.engineNo = $("#vehicleEngineNo").val();
	data.registerDate = $("#vehicleRegisterDate").val();
	data.vehicleModelName = $("#vehicleModelName").val();
	data.vehicleId = $("#vehicleVehicleId").val();
	data.specialCarFlag = $("#vehicleSpecialCarFlag").val();
	data.specialCarDate = $("#vehicleSpecialCarDate").val();
	
	data.isNpsFlow = $("#isNpsFlow").val(); // 是否费改
	url = '/yangguang/submitSearchCarInfo';
	var res = AJAX(url, data);
	
	if(res == 200){
		bootbox.alert("提交信息成功！");
	}else{
		bootbox.alert("提交信息失败！");
	}
}

function statusChanged(){
	if($("#vehicleSpecialCarFlag").find("option:selected").val() == 0){
		$("#vehicleSpecialCarDate").val("");
	}
}

function AJAX(url, data){
	var retCode = -1;
	$.ajax({
		url: url,
		data: data,
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			retCode = json.retCode;
		}
	});
	return retCode;
}

function isNullOrEmpty(strVal) {
	if (strVal == '' || strVal == null || strVal == undefined) {
		return true;
	} else {
		return false;
	}
}

function getDate(time){
	
	var createDate = new Date(time);
	var month = createDate.getMonth() + 1;
	month = month < 10 ? "0"+month : month;
	var date = createDate.getDate();
	date = date < 10 ? "0" + date : date;
	var hour = createDate.getHours();
	hour = hour < 10 ? "0" + hour : hour;
	var minute = createDate.getMinutes();
	minute = minute < 10 ? "0" + minute : minute;
	var second = createDate.getSeconds();
	second = second < 10 ? "0" + second : second;
	
	createTime = createDate.getFullYear() + "-" + month + "-" + date;
	return createTime;
}