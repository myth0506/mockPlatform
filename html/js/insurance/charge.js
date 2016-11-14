$(document).ready(function() {
	$.ajax({
		url: '/charge/ini',
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.retCode == 200){
				if(json.cardInfo){
					$("input:radio[name='cardInfo'][value='" + json.cardInfo + "']").attr('checked','true');
				}
				if(json.charge){
					$("input:radio[name='charge'][value='" + json.charge + "']").attr('checked','true');
				}
				if(json.chargeRes){
					$("input:radio[name='chargeRes'][value='" + json.chargeRes + "']").attr('checked','true');
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

function saveCharge(){
	var cardInfo = $("input[name='cardInfo']:checked").val();
	var charge = $("input[name='charge']:checked").val();
	var chargeRes = $("input[name='chargeRes']:checked").val();
	if(isNullOrEmpty(cardInfo) || isNullOrEmpty(charge) || isNullOrEmpty(chargeRes)){
		bootbox.alert("请填写完整充值信息！");
		return;
	}
	
	var data = {};
	data.cardInfo = cardInfo;
	data.charge = charge;
	data.chargeRes = chargeRes;
	$.ajax({
		url: '/charge/saveCharge',
		type: 'POST',
		data: data,
		dataType: 'json',
		success: function(json){
			if(json.retCode=="200"){
				bootbox.alert("保存成功");
			} else{
				bootbox.alert("保存失败");
			}
		}
	});
}