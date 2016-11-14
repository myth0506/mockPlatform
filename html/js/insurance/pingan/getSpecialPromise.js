$(document).ready(function() {
	$.ajax({
		url: '/InsrGetSpecialPromise/iniSpecialPromise',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			$("#bizSpecialPromise").val(json.bizPromise);
			$("#jqxSpecialPromise").val(json.forcePromise);
		}
	});
})

function submitSpecialPromise() {
	var data = {};
	data.bizSpecialPromise = $("#bizSpecialPromise").val();
	data.jqxSpecialPromise = $("#jqxSpecialPromise").val();
	if (isNullOrEmpty(data.bizSpecialPromise)
			|| isNullOrEmpty(data.jqxSpecialPromise)){
		bootbox.alert("请填写完整信息！");
		return;
	}
	$.ajax({
		url: '/InsrGetSpecialPromise/getSpecialPromise',
		data: data,
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			bootbox.alert(json.retDesc);
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