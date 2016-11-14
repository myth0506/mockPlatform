$(document).ready(
		function() {
			$
					.ajax({
						url : '/InsrSendPolicyAddress/getProvinceInfo',
						type : 'POST',
						dataType : 'json',
						async : false,
						success : function(json) {
							if (json.retCode == 200) {
								var provinceList = json.provinceNameList;
								var len = provinceList.length;
								for (var i = 0; i < len; i++) {
									var province = provinceList[i];
									$("#addressProvinceName").append(
											"<option value="
													+ province.city_code + ">"
													+ province.city_name
													+ "</option>");
								}
								loadIniPolicy();
							}
						}
					});
		});
function loadIniPolicy(){
	$.ajax({
		url : '/InsrSendPolicyAddress/iniPolicyAddress',
		type : 'POST',
		dataType : 'json',
		async : false,
		success : function(json) {
			if (json.retCode == 200) {
					$("#addressName").val(json.name);
					$("#addressMobile").val(json.mobile);
					$("#addressProvinceName").val(json.province);
					provinceChanged();
					$("#addressCityName").val(json.city);
					cityChanged();
					$("#addressTownCityName").val(json.town);
					$("#addressDetail").val(json.detail);
				}
			}
	});
}
function provinceChanged() {
	$("#addressCityName").empty();
	$("#addressCityName").append("<option value=\"0\">请选择</option>");
	$("#addressTownCityName").empty();
	$("#addressTownCityName").append("<option value=\"0\">请选择</option>");
	if ($("#addressProvinceName").find("option:selected").val() != 0) {
		var data = {};
		data.parent_code = $("#addressProvinceName").find("option:selected")
				.val();
		$.ajax({
			url : '/InsrSendPolicyAddress/getCityInfo',
			type : 'POST',
			data : data,
			dataType : 'json',
			async : false,
			success : function(json) {
				if (json.retCode == 200) {
					var cityList = json.cityNameList;
					if (!isNullOrEmpty(cityList)) {
						var len = cityList.length;
						for (var i = 0; i < len; i++) {
							var city = cityList[i];
							$("#addressCityName").append(
									"<option value=" + city.city_code + ">"
											+ city.city_name + "</option>");
						}
					}
				}
			}
		});
	}
}

function cityChanged() {
	$("#addressTownCityName").empty();
	$("#addressTownCityName").append("<option value=\"0\">请选择</option>");
	if ($("#addressCityName").find("option:selected").val() != 0) {
		var data = {};
		data.parent_code = $("#addressCityName").find("option:selected")
				.val();
		$.ajax({
			url : '/InsrSendPolicyAddress/getTownInfo',
			type : 'POST',
			data : data,
			dataType : 'json',
			async : false,
			success : function(json) {
				if (json.retCode == 200) {
					var townList = json.townNameList;
					if (!isNullOrEmpty(townList)) {
						var len = townList.length;
						for (var i = 0; i < len; i++) {
							var town = townList[i];
							$("#addressTownCityName").append(
									"<option value=" + town.city_code + ">"
											+ town.city_name + "</option>");
						}
					}
				}
			}
		});
	}
}

function submitPolicyAddress() {
	var data = {};
	data.addressName = $("#addressName").val();
	data.addressMobile = $("#addressMobile").val();
	data.addressProvinceName = $("#addressProvinceName").val();
	data.addressCityName = $("#addressCityName").val();
	data.addressTownCityName = $("#addressTownCityName").val();
	data.addressDetail = $("#addressDetail").val();
	if(isNullOrEmpty(data.addressName)){
		bootbox.alert("收件人姓名未填写！");
		return;
	}
	if(isNullOrEmpty(data.addressMobile)){
		bootbox.alert("收件人手机号码未填写！");
		return;
	}
	if(isNullOrEmpty(data.addressProvinceName)){
		bootbox.alert("收件人所在省未选择！");
		return;
	}
	if(isNullOrEmpty(data.addressCityName)){
		bootbox.alert("收件人所在市未选择！");
		return;
	}
	if(isNullOrEmpty(data.addressTownCityName)){
		bootbox.alert("收件人所在区未选择！");
		return;
	}
	if(isNullOrEmpty(data.addressDetail)){
		bootbox.alert("收件人详细地址未填写！");
		return;
	}
	$.ajax({
		url : '/InsrSendPolicyAddress/sendPolicyAddress',
		data : data,
		type : 'POST',
		dataType : 'json',
		async : false,
		success : function(json) {
			if (json.retCode == 200) {
				bootbox.alert("提交信息成功！");
			}else{
				bootbox.alert("提交信息失败！");
			}
		}
	});
}

function isNullOrEmpty(strVal) {
	if (strVal == '' || strVal == null || strVal == undefined) {
		return true;
	} else {
		return false;
	}
}