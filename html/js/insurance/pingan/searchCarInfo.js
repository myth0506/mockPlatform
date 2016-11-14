$(document).ready(function(){
	$.ajax({
		url: '/InsrSearchCarInfo/queryNpsInfo',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			var code = json.retCode;
			if(code=="200"){
				$("#isNpsFlow").val(json.retDesc)
			}
		}
	});
	var vehicleNo = $.cookie('vehicleNo');
	if(!isNullOrEmpty(vehicleNo)){
		var data = {};
		data.vehicleNo = vehicleNo;
		$.ajax({
			url:'/InsrSearchCarInfo/searchCarInfo',
			type:'POST',
			data: data,
			dataType:'json',
			success:function(json){
				if(json.retCode == "200"){
					var registerPerson = json.registerPerson;
					if(!isNullOrEmpty(registerPerson)){
						$("#registerName").val(registerPerson.personName);
						$("#registerGender").val(registerPerson.personGender);
						$("#registerMobile").val(registerPerson.personMobile);
						$("#registerIdType").val(registerPerson.personIdType);
						$("#registerIdNo").val(registerPerson.personIdNo);
						$("#registerBirthday").val(getDate(registerPerson.personBirthday));
						$("#registerEmail").val(registerPerson.personEmail);
						$("#registerAddress").val(registerPerson.personAddress);
					}
					var insuredPerson = json.insuredPerson;
					if(!isNullOrEmpty(insuredPerson)){
						$("#insuredName").val(insuredPerson.personName);
						$("#insuredGender").val(insuredPerson.personGender);
						$("#insuredMobile").val(insuredPerson.personMobile);
						$("#insuredIdType").val(insuredPerson.personIdType);
						$("#insuredIdNo").val(insuredPerson.personIdNo);
						$("#insuredBirthday").val(getDate(insuredPerson.personBirthday));
						$("#insuredEmail").val(insuredPerson.personEmail);
						$("#insuredAddress").val(insuredPerson.personAddress);
					}
					var applicantPerson = json.applicantPerson;
					if(!isNullOrEmpty(applicantPerson)){
						$("#applicantName").val(applicantPerson.personName);
						$("#applicantGender").val(applicantPerson.personGender);
						$("#applicantMobile").val(applicantPerson.personMobile);
						$("#applicantIdType").val(applicantPerson.personIdType);
						$("#applicantIdNo").val(applicantPerson.personIdNo);
						$("#applicantBirthday").val(getDate(applicantPerson.personBirthday));
						$("#applicantEmail").val(applicantPerson.personEmail);
						$("#applicantAddress").val(applicantPerson.personAddress);
					}
					var vehicleInfo = json.vehicleInfo;
					if(!isNullOrEmpty(vehicleInfo)){
						$("#vehicleFrameNo").val(vehicleInfo.vehicleFrameNo);
						$("#vehicleEngineNo").val(vehicleInfo.vehicleEngineNo);
						$("#vehicleRegisterDate").val(getDate(vehicleInfo.vehicleRegisterDate));
						$("#vehicleModel").val(vehicleInfo.vehicleModel);
						$("#vehicleModelName").val(vehicleInfo.vehicleModelName);
						$("#vehicleVehicleId").val(vehicleInfo.vehicleVehicleId);
						$("#vehicleSpecialCarFlag").val(vehicleInfo.vehicleSpecialCarFlag);
						if(vehicleInfo.vehicleSpecialCarDate != null){
							$("#vehicleSpecialCarDate").val(getDate(vehicleInfo.vehicleSpecialCarDate));
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
		$("#applicantGender").val($("#registerGender").val());
		$("#applicantMobile").val($("#registerMobile").val());
		$("#applicantIdType").val($("#registerIdType").val());
		$("#applicantIdNo").val($("#registerIdNo").val());
		$("#applicantBirthday").val($("#registerBirthday").val());
		$("#applicantEmail").val($("#registerEmail").val());
		$("#applicantAddress").val($("#registerAddress").val());
	}
}

function insuredCheckBox(){
	if($('#insuredCheckBox').is(':checked')){
		$("#insuredName").val($("#registerName").val());
		$("#insuredGender").val($("#registerGender").val());
		$("#insuredMobile").val($("#registerMobile").val());
		$("#insuredIdType").val($("#registerIdType").val());
		$("#insuredIdNo").val($("#registerIdNo").val());
		$("#insuredBirthday").val($("#registerBirthday").val());
		$("#insuredEmail").val($("#registerEmail").val());
		$("#insuredAddress").val($("#registerAddress").val());
	}
}

function submitInfo(){
	var url = '/InsrSearchCarInfo/submitPersonInfo';
	var data = {};
	var vehicleNo = $.cookie('vehicleNo');

	data.vehicleNo = vehicleNo;
	if(isNullOrEmpty(data.vehicleNo)){
		bootbox.alert("未填写车牌号，请到初始页填写");
		return ;
	}
	
	if(isNullOrEmpty($("#registerName").val()) || isNullOrEmpty($("#registerMobile").val()) 
	|| isNullOrEmpty($("#registerIdType").val()) || isNullOrEmpty($("#registerIdNo").val()) 
	|| isNullOrEmpty($("#registerBirthday").val()) || isNullOrEmpty($("#registerEmail").val()) 
	|| isNullOrEmpty($("#registerAddress").val())){
		bootbox.alert("车主信息未填写完整！");
		return ;
	}
	if(isNullOrEmpty($("#applicantName").val()) || isNullOrEmpty($("#applicantMobile").val()) 
	|| isNullOrEmpty($("#applicantIdType").val()) || isNullOrEmpty($("#applicantIdNo").val()) 
	|| isNullOrEmpty($("#applicantEmail").val()) || isNullOrEmpty($("#applicantEmail").val()) 
	|| isNullOrEmpty($("#applicantAddress").val())){
		bootbox.alert("投保人信息未填写完整！");
		return ;
	}
	if(isNullOrEmpty($("#insuredName").val()) || isNullOrEmpty($("#insuredMobile").val()) 
	|| isNullOrEmpty($("#insuredIdType").val()) || isNullOrEmpty($("#insuredIdNo").val()) 
	|| isNullOrEmpty($("#insuredBirthday").val()) || isNullOrEmpty($("#insuredEmail").val()) 
	|| isNullOrEmpty($("#insuredAddress").val())){
		bootbox.alert("被投保人信息未填写完整！");
		return ;
	}
	if(isNullOrEmpty($("#vehicleFrameNo").val()) || isNullOrEmpty($("#vehicleEngineNo").val()) 
	|| isNullOrEmpty($("#vehicleRegisterDate").val()) || isNullOrEmpty($("#vehicleModel").val()) 
	|| isNullOrEmpty($("#vehicleModelName").val()) || isNullOrEmpty($("#vehicleVehicleId").val())
	|| ($("#vehicleSpecialCarFlag").val() == "1" && isNullOrEmpty($("#vehicleSpecialCarDate").val()))){
		bootbox.alert("车辆信息未填写完整！");
		return ;
	}
	
	data.personName = $("#registerName").val();
	data.personGender = $("#registerGender").val();
	data.personMobile = $("#registerMobile").val();
	data.personIdType = $("#registerIdType").val();
	data.personIdNo = $("#registerIdNo").val();
	data.personBirthday = $("#registerBirthday").val();
	data.personEmail = $("#registerEmail").val();
	data.personAddress = $("#registerAddress").val();
	data.personType = 'register';
	var resRegister = AJAX(url, data);
	
	data.personName = $("#applicantName").val();
	data.personGender = $("#applicantGender").val();
	data.personMobile = $("#applicantMobile").val();
	data.personIdType = $("#applicantIdType").val();
	data.personIdNo = $("#applicantIdNo").val();
	data.personBirthday = $("#applicantBirthday").val();
	data.personEmail = $("#applicantEmail").val();
	data.personAddress = $("#applicantAddress").val();
	data.personType = 'applicant';
	var resApplicant = AJAX(url, data);
	
	data.personName = $("#insuredName").val();
	data.personGender = $("#insuredGender").val();
	data.personMobile = $("#insuredMobile").val();
	data.personIdType = $("#insuredIdType").val();
	data.personIdNo = $("#insuredIdNo").val();
	data.personBirthday = $("#insuredBirthday").val();
	data.personEmail = $("#insuredEmail").val();
	data.personAddress = $("#insuredAddress").val();
	data.personType = 'insured';
	var resInsured = AJAX(url, data);
	
	data.vehicleFrameNo = $("#vehicleFrameNo").val();
	data.vehicleEngineNo = $("#vehicleEngineNo").val();
	data.vehicleRegisterDate = $("#vehicleRegisterDate").val();
	data.vehicleModel = $("#vehicleModel").val();
	data.vehicleModelName = $("#vehicleModelName").val();
	data.vehicleVehicleId = $("#vehicleVehicleId").val();
	data.vehicleSpecialCarFlag = $("#vehicleSpecialCarFlag").val();
	data.vehicleSpecialCarDate = $("#vehicleSpecialCarDate").val();
	url = '/InsrSearchCarInfo/submitVehicleInfo';
	var resVehicle = AJAX(url, data);
	
	data.isNpsFlow = $("#isNpsFlow").val();
	var flowUrl = "/InsrSearchCarInfo/iniNpsInfo";
	var resNps = AJAX(flowUrl, data);
	if(resRegister == 200 && resApplicant == 200 && resInsured == 200 && resVehicle == 200 && resNps==200){
		bootbox.alert("提交信息成功！");
	}else{
		bootbox.alert("提交信息失败！");
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