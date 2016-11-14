function setZaiBei(key){	
	
	var data = {};
	if(key=="cityConfig"){		
		data.iniName = "cityConfigZaiBeiYouBi";
		data.iniValue = $("#getCityConfig").val();
		data.iniDesc = "城市配置设置";
		data.iniNameDelay = "cityConfigZaiBeiYouBiDelayTime";
		data.iniValueDelay = $("#cityConfigDelayTime").val();
		data.iniDescDelay = "城市配置延时时长";
	}else if(key=="searchCarType"){
		data.iniName = "searchCarTypeZaiBeiYouBi";
		data.iniValue = $("#searchCarType").val();
		data.iniDesc = "查询车型设置";
		data.iniNameDelay = "searchCarTypeZaiBeiYouBiDelayTime";
		data.iniValueDelay = $("#searchCarTypeDelayTime").val();
		data.iniDescDelay = "查询车型延时时长";
	}else if(key=="inputCarInfo"){
		data.iniName = "inputCarInfoZaiBeiYouBi";
		data.iniValue = $("#inputCarInfo").val();
		data.iniDesc = "输入车型设置";
		data.iniNameDelay = "inputCarInfoZaiBeiYouBiDelayTime";
		data.iniValueDelay = $("#inputCarInfoDelayTime").val();
		data.iniDescDelay = "输入车型延时时长";
	}else if(key=="createBaoJia"){
		data.iniName = "createBaoJiaZaiBeiYouBi";
		data.iniValue = $("#createBaoJia").val();
		data.iniDesc = "创建报价设置";
		data.iniNameDelay = "createBaoJiaZaiBeiYouBiDelayTime";
		data.iniValueDelay = $("#createBaoJiaDelayTime").val();
		data.iniDescDelay = "创建报价延时时长";
	}else if(key=="getBaoJia"){
		data.iniName = "getBaoJiaZaiBeiYouBi";
		data.iniValue = $("#createBaoJia").val();
		data.iniDesc = "创建报价设置";
		data.iniNameDelay = "getBaoJiaZaiBeiYouBiDelayTime";
		data.iniValueDelay = $("#createBaoJiaDelayTime").val();
		data.iniDescDelay = "创建报价延时时长";
	}
	
	if(data.iniValue=="delay" && (data.iniValueDelay==null || data.iniValueDelay=="")){
		bootbox.alert("延时时长不能为空");
	}else{	
		$.ajax({
			url: '/zaiBeiYouBi/setIniValue.html',
			type: 'POST',
			data: data,
			dataType: 'json',
			async: false,
			success: function(json){
				if(json.retCode=="200"){
					bootbox.alert("设置成功");
				}else{
					bootbox.alert("设置失败");
				}
			}
		});
	}	
	
}


function setBackUp(){	
	
	var data = {};
		
	$.ajax({
			url: '/zaiBeiYouBi/setBackUp.html',
			type: 'POST',
			data: data,
			dataType: 'json',
			async: false,
			success: function(json){
				if(json.retCode=="200"){
					bootbox.alert({  
						message: '还原成功',
			            buttons: {  
			               ok: {  
			                    label: '确定',  
			                    className: 'btn-myStyle',
			                }  
			            },
			            callback: function() {  
	                    	window.location.reload();
			            }
			        });
				}else{
					bootbox.alert("还原失败");
				}
			}
	});
	
}


function setAllIni(){
	var data = {};
	data.cityConfig = $("#setCityConfig").val();
	data.cityConfigDelayTime = $("#cityConfigDelayTime").val();
	
	$.ajax({
			url: '/zaiBeiYouBi/setAllIni.html',
			type: 'POST',
			data: data,
			dataType: 'json',
			async: false,
			success: function(json){
				if(json.retCode=="200"){
					bootbox.alert({  
						message: '批量设置成功',
			            buttons: {  
			               ok: {  
			                    label: '确定',  
			                    className: 'btn-myStyle',
			                }  
			            },
			            callback: function() {  
	                    	window.location.reload();
			            }
			        });
				}else{
					bootbox.alert("批量设置失败");
				}
			}
	});
}

function changeDelayTime(divId, type, des){
	if($("#"+type).val()=='delay'){
		$('#' + divId + '-delay').css({'display': ''});
	}else{
		$('#' + divId + '-delay').css({'display': 'none'});
	}
}
