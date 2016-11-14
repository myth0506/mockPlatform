$(document).ready(function() { 
	$("#bizInfo").hide();
	$("#jqxInfo").hide();
	$("#smsCodeInfo").hide();
	$(".smsCode").change( 
			function() {
				if($("input[name='smsCode']:checked").val() == '1'){
					$("#smsCodeInfo").show(800);
				}else{
					$("#smsCodeInfo").hide(900);
				}
			});
	$.ajax({
		url: '/InsrToAudit/iniAuditInfo',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			if(json.retCode == 200){
				var auditStatus = json.auditStatus;
				if(auditStatus=="C0000"){
					$("#auditSuccess").attr("checked","checked");
				}
				if(auditStatus=="C6001"){
					$("#auditBothFailed").attr("checked","checked");
				}
				if(auditStatus=="C6002"){
					$("#auditBizFailed").attr("checked","checked");
				}
				if(auditStatus=="C6003"){
					$("#auditJqxFailed").attr("checked","checked");
				}
				if(auditStatus=="C0009"){
					$("#auditDiffer").attr("checked","checked");
				}
				var smsStatus = json.auditSms;
				var smsCode = json.auditSmsCode;
				if(smsStatus=="0"){
					$("#smsCodeNo").attr("checked","checked");
				}
				if(smsStatus=="1"){
					$("#smsCodeYes").attr("checked","checked");
					if(!isNullOrEmpty(smsCode)){
						$("#smsCodeInfo").show();
						$("#auditSmsCode").val(smsCode);
					}
				}
			}
		}
	});
});

function submitAudit(){
	var data = {};
	var date = new Date();
	var year = date.getFullYear();
	var bizNo = year + "000" + getRandomString(8);
	var jqxNo = year + "000" + getRandomString(8);
	data.auditStatus = $("input[name='audit']:checked").val();
	data.smsCodeStatus = $("input[name='smsCode']:checked").val();
	data.auditBizOrderNo = bizNo;
	data.auditJqxOrderNo = jqxNo;
	data.auditSmsCode = $("#auditSmsCode").val();
	if($("input[name='smsCode']:checked").val() == '1' && isNullOrEmpty(data.auditSmsCode)){
		bootbox.alert("请填写手机验证码！");
		return;
	}
	$.ajax({
		url: '/InsrToAudit/submitAuditInfo',
		data: data,
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			if(json.retCode == 200){
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
//获取长度为len的随机字符串  
function getRandomString(len) {  
    len = len || 32;  
    var $chars = '0123456789';  
    var maxPos = $chars.length;  
    var str = '';  
    for (i = 0; i < len; i++) {  
        str += $chars.charAt(Math.floor(Math.random() * maxPos));  
    }  
    return str;  
}