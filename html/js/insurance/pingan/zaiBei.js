function setZaiBei(key){	
	
	var data = {};
	if(key=="xuBao"){		
		data.iniName = "xuBaoZaiBeiPingAn";
		data.iniValue = $("#setXuBao").val();
		data.iniDesc = "续保灾备设置";
		data.iniNameDelay = "xuBaoZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#xuBaoDelayTime").val();
		data.iniDescDelay = "续保延时时长";
	}else if(key=="shengRi"){		
		data.iniName = "shengRiZaiBeiPingAn";
		data.iniValue = $("#setShengRi").val();
		data.iniDesc = "生日灾备设置";
		data.iniNameDelay = "shengRiZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#shengRiDelayTime").val();
		data.iniDescDelay = "生日延时时长";
	}else if(key=="searchCarInfo"){		
		data.iniName = "searchCarInfoZaiBeiPingAn";
		data.iniValue = $("#setSearchCarInfo").val();
		data.iniDesc = "查询车辆信息灾备设置";
		data.iniNameDelay = "searchCarInfoZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#searchCarInfoDelayTime").val();
		data.iniDescDelay = "查询车辆信息延时时长";
	}else if(key=="baoJia"){		
		data.iniName = "baoJiaZaiBeiPingAn";
		data.iniValue = $("#setBaoJia").val();
		data.iniDesc = "报价灾备设置";
		data.iniNameDelay = "baoJiaZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#baoJiaDelayTime").val();
		data.iniDescDelay = "报价延时时长";
	}else if(key=="businessBaoJia"){		
		data.iniName = "businessBaoJiaZaiBeiPingAn";
		data.iniValue = $("#setBusinessBaoJia").val();
		data.iniDesc = "商业险报价灾备设置";
		data.iniNameDelay = "businessBaoJiaZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#businessBaoJiaDelayTime").val();
		data.iniDescDelay = "商业险报价延时时长";
	}else if(key=="jiaoQiangBaoJia"){		
		data.iniName = "jiaoQiangBaoJiaZaiBeiPingAn";
		data.iniValue = $("#setJiaoQiangBaoJia").val();
		data.iniDesc = "交强险报价灾备设置";
		data.iniNameDelay = "jiaoQiangBaoJiaZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#jiaoQiangBaoJiaDelayTime").val();
		data.iniDescDelay = "交强险报价延时时长";
	}else if(key=="saveBaoJiaInfo"){		
		data.iniName = "saveBaoJiaInfoZaiBeiPingAn";
		data.iniValue = $("#setSaveBaoJiaoInfo").val();
		data.iniDesc = "保存报价信息灾备设置";
		data.iniNameDelay = "saveBaoJiaInfoZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#saveBaoJiaInfoDelayTime").val();
		data.iniDescDelay = "保存报价信息延时时长";
	}else if(key=="getAddress"){		
		data.iniName = "getAddressZaiBeiPingAn";
		data.iniValue = $("#getAddress").val();
		data.iniDesc = "获取配送地址灾备设置";
		data.iniNameDelay = "getAddressZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#getAddressDelayTime").val();
		data.iniDescDelay = "获取配送地址延时时长";
	}else if(key=="getSpecialPromise"){		
		data.iniName = "getSpecialPromiseZaiBeiPingAn";
		data.iniValue = $("#getSpecialPromise").val();
		data.iniDesc = "获取特别约定灾备设置";
		data.iniNameDelay = "getSpecialPromiseZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#getSpecialPromiseDelayTime").val();
		data.iniDescDelay = "获取特别约定延时时长";
	}else if(key=="phoneAssert"){		
		data.iniName = "phoneAssertZaiBeiPingAn";
		data.iniValue = $("#phoneAssert").val();
		data.iniDesc = "手机验证灾备设置";
		data.iniNameDelay = "phoneAssertZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#phoneAssertDelayTime").val();
		data.iniDescDelay = "手机验证延时时长";
	}else if(key=="heBao"){		
		data.iniName = "heBaoZaiBeiPingAn";
		data.iniValue = $("#heBao").val();
		data.iniDesc = "核保灾备设置";
		data.iniNameDelay = "heBaoZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#heBaoDelayTime").val();
		data.iniDescDelay = "核保延时时长";
	}else if(key=="payCheck"){		
		data.iniName = "payCheckZaiBeiPingAn";
		data.iniValue = $("#payCheck").val();
		data.iniDesc = "支出检查设置";
		data.iniNameDelay = "payCheckZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#payCheckDelayTime").val();
		data.iniDescDelay = "支出检查延时时长";
	}else if(key=="order"){		
		data.iniName = "orderZaiBeiPingAn";
		data.iniValue = $("#order").val();
		data.iniDesc = "出单灾备设置";
		data.iniNameDelay = "orderZaiBeiPingAnDelayTime";
		data.iniValueDelay = $("#orderDelayTime").val();
		data.iniDescDelay = "出单延时时长";
	}
	
	
	if(data.iniValue=="delay" && (data.iniValueDelay==null || data.iniValueDelay=="")){
		bootbox.alert("延时时长不能为空");
	}else{	
		$.ajax({
			url: '/zaiBeiPingAn/setIniValue.html',
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
			url: '/zaiBeiPingAn/setBackUp.html',
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
	data.xuBao = $("#setXuBao").val();
	data.xuBaoDelayTime = $("#xuBaoDelayTime").val();
	
	data.shengRi = $("#setShengRi").val();
	data.shengRiDelayTime = $("#shengRiDelayTime").val();
	
	data.searchCarInfo = $("#setSearchCarInfo").val();
	data.searchCarInfoDelayTime = $("#searchCarInfoDelayTime").val();
	
	data.baoJia = $("#setBaoJia").val();
	data.baoJiaDelayTime = $("#baoJiaDelayTime").val();
	
	data.businessBaoJia = $("#setBusinessBaoJia").val();
	data.businessBaoJiaDelayTime = $("#businessBaoJiaDelayTime").val();
	
	data.jiaoQiangBaoJia = $("#setJiaoQiangBaoJia").val();
	data.jiaoQiangBaoJiaDelayTime = $("#jiaoQiangBaoJiaDelayTime").val();
	
	data.saveBaoJiaInfo = $("#setSaveBaoJiaoInfo").val();
	data.saveBaoJiaInfoDelayTime = $("#saveBaoJiaInfoDelayTime").val();
	
	data.getAddress = $("#getAddress").val();
	data.getAddressDelayTime = $("#getAddressDelayTime").val();
	
	data.getSpecialPromise = $("#getSpecialPromise").val();
	data.getSpecialPromiseDelayTime = $("#getSpecialPromiseDelayTime").val();
	
	data.phoneAssert = $("#phoneAssert").val();
	data.phoneAssertDelayTime = $("#phoneAssertDelayTime").val();
	
	data.heBao = $("#heBao").val();
	data.heBaoDelayTime = $("#heBaoDelayTime").val();
	
	data.payCheck = $("#payCheck").val();
	data.payCheckDelayTime = $("#payCheckDelayTime").val();
	
	data.order = $("#order").val();
	data.orderDelayTime = $("#orderDelayTime").val();
	
	$.ajax({
			url: '/zaiBeiPingAn/setAllIni.html',
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
