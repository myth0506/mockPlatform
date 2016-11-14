$(document).ready(function() { 

	$("#setOrderStatus").change( 
		function() { 
			if($("#setOrderStatus option:selected").val() == '0'){
				$("#orderInfo").show(800);
			}else if($("#setOrderStatus option:selected").val() == '4'){
				$("#orderInfo").show(800);
			}else{
				$("#orderInfo").hide(900);
			}
	});
	$.ajax({
		url: '/InsrPolicyAndOrder/iniPolicyAndOrder',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			if(json.retCode == 200){
				var payCheck = json.payCheck;
				var retStatus = json.retStatus;
				var retBiz = json.retBiz;
				var retJqx = json.retJqx;
				if(payCheck=="0"){
					$("#paySuccess").attr("checked", "checked");
				}
				if(payCheck=="1"){
					$("#payBothFailed").attr("checked", "checked");
				}
				if(payCheck=="21"){
					$("#payBizFailed").attr("checked", "checked");
				}
				if(payCheck=="22"){
					$("#payJqxFailed").attr("checked", "checked");
				}
				$("#setOrderStatus").val(retStatus);
				if(retBiz){
					if(retBiz=="0"){
						$("#bizOrderSuccess").attr("checked", "checked");
					}
					if(retBiz=="1"){
						$("#bizOrderFailed").attr("checked", "checked");
					}
					if(retBiz=="2"){
						$("#bizOrderNo").attr("checked", "checked");
					}
				}
				if(retJqx){
					if(retJqx=="0"){
						$("#jqxOrderSuccess").attr("checked", "checked");
					}
					if(retJqx=="1"){
						$("#jqxOrderFailed").attr("checked", "checked");
					}
					if(retJqx=="2"){
						$("#jqxOrderNo").attr("checked", "checked");
					}
				}
				if(retStatus=="0" || retStatus=="4"){
					$("#orderInfo").show();
				}else{
					$("#orderInfo").hide();
				}
				
			}
		}
	});
});

function submitPolicyAndOrder(){
	var data = {};
	data.policyStatus = $("input[name='policyStatus']:checked").val();
	data.setOrderStatus = $("#setOrderStatus").val();
	data.bizOrder = $("input[name='bizOrder']:checked").val();
	data.jqxOrder = $("input[name='jqxOrder']:checked").val();

	$.ajax({
		url: '/InsrPolicyAndOrder/submitPolicyAndOrder',
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