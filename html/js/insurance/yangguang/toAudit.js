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
		url: '/yangguang/InsrToAudit/iniAuditInfo',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			if(json.retCode == 200){
				var auditStatus = json.auditStatus;
				if(auditStatus=="0"){
					$("#auditFailed").attr("checked","checked");
				}
				if(auditStatus=="1"){
					$("#auditSuccess").attr("checked","checked");
				}
				if(auditStatus=="2"){
					$("#auditing").attr("checked","checked");
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
				
				var bizOrderNo = json.bizOrderNo;
				var forceOrderNo = json.forceOrderNo;
				var date = new Date();
				var year = date.getFullYear();
				if(isNullOrEmpty(bizOrderNo)){
					var bizNo = year + "000" + getRandomString(8);
					$("#bizProposalNo").val(bizNo);
				}else{
					$("#bizProposalNo").val(bizOrderNo);
				}
				if(isNullOrEmpty(forceOrderNo)){
					var jqxNo = year + "000" + getRandomString(8);
					$("#forceProposalNo").val(jqxNo);
				}else{
					$("#forceProposalNo").val(forceOrderNo);
				}
			}
		}
	});
});

function submitAudit(){
	var data = {};
	data.auditStatus = $("input[name='audit']:checked").val();
	data.smsCodeStatus = $("input[name='smsCode']:checked").val();
	data.auditBizOrderNo = $("#bizProposalNo").val();
	data.auditJqxOrderNo = $("#forceProposalNo").val();
	if(isNullOrEmpty(data.auditBizOrderNo) || isNullOrEmpty(data.auditJqxOrderNo)){
		bootbox.alert("请填写商业险和交强险保单号！");
		return;
	}
	data.auditSmsCode = $("#auditSmsCode").val();
	if($("input[name='smsCode']:checked").val() == '1' && isNullOrEmpty(data.auditSmsCode)){
		bootbox.alert("请填写手机验证码！");
		return;
	}
	$.ajax({
		url: '/yangguang/InsrToAudit/submitAuditInfo',
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