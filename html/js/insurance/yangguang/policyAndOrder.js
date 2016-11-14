$(document).ready(function() { 

	$.ajax({
		url: '/yangguang/InsrPolicyAndOrder/iniPolicyAndOrder',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			if(json.retCode == 200){
				var payCheck = json.payCheck;
				var retStatus = json.retStatus;
				if(payCheck=="0"){
					$("#paySuccess").attr("checked", "checked");
				}
				if(payCheck=="1"){
					$("#payFailed").attr("checked", "checked");
				}
				if(payCheck=="2"){
					$("#paying").attr("checked", "checked");
				}
				
				$("#setOrderStatus").val(retStatus);
			}
		}
	});
});

function submitPolicyAndOrder(){
	var data = {};
	data.policyStatus = $("input[name='policyStatus']:checked").val();
	data.setOrderStatus = $("#setOrderStatus").val();

	$.ajax({
		url: '/yangguang/InsrPolicyAndOrder/submitPolicyAndOrder',
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