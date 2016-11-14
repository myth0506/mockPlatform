function setZaiBei(key){	
	
	var data = {};
	if(key=="searchCarInfo"){		
		data.iniName = "searchCarInfoZaiBeiYangGuang";
		data.iniValue = $("#setSearchCarInfo").val();
		data.iniDesc = "查询车辆信息灾备设置";
		data.iniNameDelay = "searchCarInfoZaiBeiYangGuangDelayTime";
		data.iniValueDelay = $("#searchCarInfoDelayTime").val();
		data.iniDescDelay = "查询车辆信息延时时长";
	}else if(key=="getBaoJia"){		
		data.iniName = "getBaoJiaZaiBeiYangGuang";
		data.iniValue = $("#getBaoJia").val();
		data.iniDesc = "获取报价灾备设置";
		data.iniNameDelay = "getBaoJiaZaiBeiYangGuangDelayTime";
		data.iniValueDelay = $("#getBaoJiaDelayTime").val();
		data.iniDescDelay = "获取报价延时时长";
	}else if(key=="changeBaoJia"){		
		data.iniName = "changeBaoJiaZaiBeiYangGuang";
		data.iniValue = $("#changeBaoJia").val();
		data.iniDesc = "修改报价灾备设置";
		data.iniNameDelay = "changeBaoJiaZaiBeiYangGuangDelayTime";
		data.iniValueDelay = $("#changeBaoJiaDelayTime").val();
		data.iniDescDelay = "修改报价延时时长";
	}else if(key=="baoCunBaoFei"){		
		data.iniName = "baoCunBaoFeiZaiBeiYangGuang";
		data.iniValue = $("#baoCunBaoFei").val();
		data.iniDesc = "保存保费灾备设置";
		data.iniNameDelay = "baoCunBaoFeiZaiBeiYangGuangDelayTime";
		data.iniValueDelay = $("#baoCunBaoFeiDelayTime").val();
		data.iniDescDelay = "保存保费延时时长";
	}else if(key=="heBao"){		
		data.iniName = "heBaoZaiBeiYangGuang";
		data.iniValue = $("#heBao").val();
		data.iniDesc = "核保灾备设置";
		data.iniNameDelay = "heBaoZaiBeiYangGuangDelayTime";
		data.iniValueDelay = $("#heBaoDelayTime").val();
		data.iniDescDelay = "核保延时时长";
	}else if(key=="payCheck"){		
		data.iniName = "payCheckZaiBeiYangGuang";
		data.iniValue = $("#payCheck").val();
		data.iniDesc = "支出检查设置";
		data.iniNameDelay = "payCheckZaiBeiYangGuangDelayTime";
		data.iniValueDelay = $("#payCheckDelayTime").val();
		data.iniDescDelay = "支出检查延时时长";
	}else if(key=="order"){		
		data.iniName = "orderZaiBeiYangGuang";
		data.iniValue = $("#order").val();
		data.iniDesc = "出单灾备设置";
		data.iniNameDelay = "orderZaiBeiYangGuangDelayTime";
		data.iniValueDelay = $("#orderDelayTime").val();
		data.iniDescDelay = "出单延时时长";
	}else if(key=="getVerifyCode"){		
		data.iniName = "getVerifyCodeZaiBeiYangGuang";
		data.iniValue = $("#getVerifyCode").val();
		data.iniDesc = "获取验证码灾备设置";
		data.iniNameDelay = "getVerifyCodeZaiBeiYangGuangDelayTime";
		data.iniValueDelay = $("#getVerifyCodeDelayTime").val();
		data.iniDescDelay = "获取验证码延时时长";
	}else if(key=="saveVerifyCode"){		
		data.iniName = "saveVerifyCodeZaiBeiYangGuang";
		data.iniValue = $("#saveVerifyCode").val();
		data.iniDesc = "保存验证码灾备设置";
		data.iniNameDelay = "saveVerifyCodeZaiBeiYangGuangDelayTime";
		data.iniValueDelay = $("#saveVerifyCodeDelayTime").val();
		data.iniDescDelay = "保存验证码延时时长";
	}
	
	if(data.iniValue=="delay" && (data.iniValueDelay==null || data.iniValueDelay=="")){
		bootbox.alert("延时时长不能为空");
	}else{	
		$.ajax({
			url: '/zaiBeiYangGuang/setIniValue.html',
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
			url: '/zaiBeiYangGuang/setBackUp.html',
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
	
	data.searchCarInfo = $("#setSearchCarInfo").val();
	data.searchCarInfoDelayTime = $("#searchCarInfoDelayTime").val();
	
	data.getBaoJia = $("#getBaoJia").val();
	data.getBaoJiaDelayTime = $("#getBaoJiaDelayTime").val();
	
	data.changeBaoJia = $("#changeBaoJia").val();
	data.changeBaoJiaDelayTime = $("#changeBaoJiaDelayTime").val();
	
	data.baoCunBaoFei = $("#baoCunBaoFei").val();
	data.baoCunBaoFeiDelayTime = $("#baoCunBaoFeiDelayTime").val();
	
	data.heBao = $("#heBao").val();
	data.heBaoDelayTime = $("#heBaoDelayTime").val();
	
	data.payCheck = $("#payCheck").val();
	data.payCheckDelayTime = $("#payCheckDelayTime").val();
	
	data.order = $("#order").val();
	data.orderDelayTime = $("#orderDelayTime").val();
	
	data.getVerifyCode = $("#getVerifyCode").val();
	data.getVerifyCodeDelayTime = $("#getVerifyCodeDelayTime").val();
	
	data.saveVerifyCode = $("#saveVerifyCode").val();
	data.saveVerifyCodeDelayTime = $("#saveVerifyCodeDelayTime").val();
	
	
	$.ajax({
			url: '/zaiBeiYangGuang/setAllIni.html',
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
