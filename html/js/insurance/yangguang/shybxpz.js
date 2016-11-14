$(document).ready(function(){
	loadAllDynamicConfigs();
});
function loadAllDynamicConfigs(){
	reloadTable("车辆损失险", "cov_200Table", "cov_200");
	reloadTable("商业第三者责任险", "cov_600Table", "cov_600");
	reloadTable("司机座位责任险", "cov_701Table", "cov_701");
	reloadTable("乘客座位责任险", "cov_702Table", "cov_702");
	reloadTable("车辆划痕责任险", "cov_210Table", "cov_210");
	reloadTable("交通事故精神损害赔偿责任险", "cov_640Table", "cov_640");
}
//新增其他险种（不需设置可选项的险种）
function saveOtherInsr(){
	var str = "";
	//商业险原价
	var originalPremium = $("#originalPremium").val();
	if(isNullOrEmpty(originalPremium) || !isNumber(originalPremium)){
		bootbox.alert("商业险原价请填写完整的参数");
		return;
	}
	str += "originalPremium="+originalPremium;
	//全车盗抢险
	var cov_500 = $("#cov_500").val();
	if(isNullOrEmpty(cov_500) || !isNumber(cov_500)){
		bootbox.alert("全车盗抢险请填写完整的参数");
		return;
	}
	str += "&cov_500="+cov_500;
	//指定专修厂
	var cov_321 = $("#cov_321").val();
	if(isNullOrEmpty(cov_321) || !isNumber(cov_321)){
		bootbox.alert("指定专修厂请填写完整的参数");
		return;
	}
	str += "&cov_321="+cov_321;
	//自燃损失险
	var cov_310 = $("#cov_310").val();
	if(isNullOrEmpty(cov_310) || !isNumber(cov_310)){
		bootbox.alert("自燃损失险请填写完整的参数");
		return;
	}
	str += "&cov_310="+cov_310;
	//玻璃单独破碎险
	var gcCov_231 = $("#gcCov_231").val();
	var jkCov_231 = $("#jkCov_231").val();
	if(isNullOrEmpty(gcCov_231) || isNullOrEmpty(jkCov_231) 
			|| !isNumber(gcCov_231) || !isNumber(jkCov_231)){
		bootbox.alert("玻璃破碎险请填写完整的参数");
		return;
	}
	str += "&gcCov_231="+gcCov_231+"&jkCov_231="+jkCov_231;
	//高速高价救援险
	var cov_390 = $("#cov_390").val();
	if(isNullOrEmpty(cov_390) || !isNumber(cov_390)){
		bootbox.alert("高速高价救援险请填写完整的参数");
		return;
	}
	str += "&cov_390="+cov_390;
	//发动机特别损失险
	var cov_291 = $("#cov_291").val();
	if(isNullOrEmpty(cov_291) || !isNumber(cov_291)){
		bootbox.alert("发动机特别损失险请填写完整的参数");
		return;
	}
	str += "&cov_291="+cov_291;
	//不计免赔险（机动车盗抢险）
	var cov_921 = $("#cov_921").val();
	if(isNullOrEmpty(cov_921) || !isNumber(cov_921)){
		bootbox.alert("不计免赔险（机动车盗抢险）请填写完整的参数");
		return;
	}
	str += "&cov_921="+cov_921;
	//不计免赔险（车身划痕损失险）
	var cov_922 = $("#cov_922").val();
	if(isNullOrEmpty(cov_922) || !isNumber(cov_922)){
		bootbox.alert("不计免赔险（车身划痕损失险）请填写完整的参数");
		return;
	}
	str += "&cov_922="+cov_922;
	//不计免赔险(车损险)
	var cov_911 = $("#cov_911").val();
	if(isNullOrEmpty(cov_911) || !isNumber(cov_911)){
		bootbox.alert("不计免赔险(车损险)请填写完整的参数");
		return;
	}
	str += "&cov_911="+cov_911;
	//不计免赔险(三者险)
	var cov_912 = $("#cov_912").val();
	if(isNullOrEmpty(cov_912) || !isNumber(cov_912)){
		bootbox.alert("不计免赔险(三者险)请填写完整的参数");
		return;
	}
	str += "&cov_912="+cov_912;
	//不计免赔险(司机险)
	var cov_928 = $("#cov_928").val();
	if(isNullOrEmpty(cov_928) || !isNumber(cov_928)){
		bootbox.alert("不计免赔险(司机险)请填写完整的参数");
		return;
	}
	str += "&cov_928="+cov_928;
	//不计免赔险(乘客险)
	var cov_929 = $("#cov_929").val();
	if(isNullOrEmpty(cov_929) || !isNumber(cov_929)){
		bootbox.alert("不计免赔险(乘客险)请填写完整的参数");
		return;
	}
	str += "&cov_929="+cov_929;
	//修理期间费用补偿险
	var cov_734 = $("#cov_734").val();
	if(isNullOrEmpty(cov_734) || !isNumber(cov_734)){
		bootbox.alert("修理期间费用补偿险cov_734,请填写完整的参数");
		return;
	}
	str += "&cov_734="+cov_734;
	//修理期间费用补偿险 保额
	var cov_731 = $("#cov_731").val();
	if(isNullOrEmpty(cov_731) || !isNumber(cov_731)){
		bootbox.alert("修理期间费用补偿险cov_731，请填写完整的参数");
		return;
	}
	str += "&cov_731="+cov_731;
	//修理期间费用补偿险 天数
	var cov_732 = $("#cov_732").val();
	if(isNullOrEmpty(cov_732) || !isNumber(cov_732)){
		bootbox.alert("修理期间费用补偿险cov_732，请填写完整的参数");
		return;
	}
	str += "&cov_732="+cov_732;
	//机动车损失保险无法找到第三方特约险
	var cov_733 = $("#cov_733").val();
	if(isNullOrEmpty(cov_733) || !isNumber(cov_733)){
		bootbox.alert("修理期间费用补偿险cov_733，请填写完整的参数");
		return;
	}
	str += "&cov_733="+cov_733;
	
	var data = {};
	data.content = str;
	$.ajax({
		url: '/yangguang/business/updateCache',
		data: data,
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.retCode=="200"){
				bootbox.alert("配置保存成功");
			}else{
				bootbox.alert("配置保存失败");
			}
		}
	});
}

//新增车辆损失险
function newCov_200(){
	var title = "车辆损失险";
	var tableId = "cov_200Table";
	var code = "cov_200";
	createDialog(title, tableId, code);
}
//新增第三者责任险
function newCov_600(){
	var title = "商业第三者责任险";
	var tableId = "cov_600Table";
	var code = "cov_600";
	createDialog(title, tableId, code);
}
//新增司机座位责任险
function newCov_701(){
	var title = "司机座位责任险";
	var tableId = "cov_701Table";
	var code = "cov_701";
	createDialog(title, tableId, code);
}
//新增乘客座位责任险
function newCov_702(){
	var title = "乘客座位责任险";
	var tableId = "cov_702Table";
	var code = "cov_702";
	createDialog(title, tableId, code);
}
//新增车辆划痕责任险
function newCov_210(){
	var title = "车辆划痕责任险";
	var tableId = "cov_210Table";
	var code = "cov_210";
	createDialog(title, tableId, code);
}
//新增交通事故精神损害赔偿责任险
function newCov_640(){
	var title = "交通事故精神损害赔偿责任险";
	var tableId = "cov_640Table";
	var code = "cov_640";
	createDialog(title, tableId, code);
}

function createDialog(title, tableId, code){
	bootbox.dialog({
		title : title,
		message : 
	"<div>"
	+"<div class=\"row\">"
		+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">文案："
			+"</label>"
			+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
				+"<input type=\"text\" id=\"wenan\" class=\"form-control\">"
			+"</div>"
	    +"</div>"
	+"</div>"
	    
	+"<br>"
	+"<div class=\"row\">"
	    +"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">保额："
			+"</label>"
			+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
				+"<input type=\"text\" id=\"baoe\" class=\"form-control\" >"
			+"</div>"
		+"</div>"
	+"</div>"
		
	+"<br>"
	+"<div class=\"row\">"
		+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">保费："
			+"</label>"
			+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
				+"<input type=\"text\" id=\"baofei\" rows=\"2\" class=\"form-control\" > </input>"
			+"</div>"
		+"</div>"
	+"</div>"
	+ "</div>",
		locale : "zh_CN",
		buttons : {
		Modify:{
				label : '确定',
				className : "btn-primary",
				callback : function() {
					var wenan = $("#wenan").val();
					var baoe = $("#baoe").val();
					var baofei = $("#baofei").val();
					if(isNullOrEmpty(wenan) || isNullOrEmpty(baoe) || isNullOrEmpty(baofei)){
						bootbox.alert("请输入正确的值");
						return;
					}
					if(!isNumber(baoe) || !isNumber(baofei)){
						bootbox.alert("请输入正确的数值");
						return;
					}
					var data = {};
					data.wenan = wenan;
					data.baoe = baoe;
					data.baofei = baofei;
					data.businessCode = code;
					$.ajax({
						url: '/yangguang/business/insertMulti',
						data: data,
						type: 'POST',
						dataType: 'json',
						sync : true,
						success: function(json){
							if(json.retCode == 200){
								bootbox.alert("险种配置成功！");
								var id = json.retDesc;
								var str = "<tr>"+
								"<td>" + wenan + "</td>" +
								"<td>" + baoe + "</td>" +
								"<td>" + baofei + "</td>" +
								"<td><button class=\"btn btn-info btn-sm edit\"  onclick=\"modifyInsr('"+title+"', '"+id+"', '"+wenan+"', '"+baoe+"', '"+baofei+"', '"+tableId+"', '"+code+"')\">修改</button>" +
										"<button class=\"btn-warning btn-sm delete\" onclick=\"deleteInsr('"+title+"', '"+id+"', '"+tableId+"', '"+code+"')\">删除</button>" +
										"</td>" +
								"</tr>"
								$("#"+tableId).append(str);
							}else{
								bootbox.alert("险种配置失败！");
							}
						}
					});
				}
			},
		Cancel:{
				label : '取消',
				className : "btn-primary",
				callback : function() {
				}
			}
		}
	});
}
function validateWenan(code){
	var wenan = $("#wenan").val();
	if(isNullOrEmpty(wenan)){
		return;
	}
	var data = {};
	data.wenan = wenan;
	data.businessCode = code;
	$.ajax({
		url: '/yangguang/business/validateWenAn',
		data: data,
		type: 'POST',
		dataType: 'json',
		sync : true,
		success: function(json){
			if(json.retCode=="200"){
				bootbox.alert("该文案名称已存在");
				return false;
			}
		}
	});
	return true;
}
function reloadTable(title, tableId, code){
	var data = {};
	data.businessCode = code;
	$.ajax({
			url: '/yangguang/business/queryConfig',
			data: data,
			type: 'POST',
			dataType: 'json',
			success: function(json){
				if(json.retCode == 200){
					$("#"+tableId+" tbody").empty();
					var content = json.retDesc;
					var len = content.length;
					for(var i=0; i<len; i++){
						var insr = content[i];
						var str = "<tr>"+
						"<td>" + insr.wenAn + "</td>" +
						"<td>" + insr.baoE + "</td>" +
						"<td>" + insr.baoFei + "</td>" +
						"<td><button class=\"btn btn-info btn-sm edit\"  onclick=\"modifyInsr('"+title+"', '"+insr.id+"', '"+insr.wenAn+"', '"+insr.baoE+"', '"+insr.baoFei+"', '"+tableId+"', '"+code+"')\">修改</button>" +
								" <button class=\"btn-warning btn-sm delete\" onclick=\"deleteInsr('"+title+"', '"+insr.id+"', '"+tableId+"', '"+code+"')\">删除</button>" +
								"</td>" +
						"</tr>"
						$("#"+tableId).append(str);
					}
				}else{
					bootbox.alert("查询险种失败！");
				}
			}
	});
}
function deleteInsr(title, id, tableId, code){
	bootbox.dialog({
		title:"删除确认",
		message:"您确认要删除吗？",
			locale : "zh_CN",
			buttons : {
			Modify:{
					label : '确定',
					className : "btn-primary",
					callback : function() {
						var data = {};
						data.id = id;
						$.ajax({
							url: '/yangguang/business/DeleteInsr',
							data: data,
							type: 'POST',
							dataType: 'json',
							success: function(json){
								if(json.retCode == 200){
									bootbox.alert("险种配置删除成功！");
									reloadTable(title, tableId, code);
								}else{
									bootbox.alert("险种配置删除失败！");
								}
							}
						});
					}
				},
			Cancel:{
					label : '取消',
					className : "btn-primary",
					callback : function() {
					
					}
				}
			}
	});
}
function modifyInsr(title, id, wenan, baoe, baofei, tableId, code){
	
	bootbox.dialog({
		title : title,
		message : 
	"<div>"
	+"<div class=\"row\">"
		+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">文案："
			+"</label>"
			+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
				+"<input type=\"text\" id=\"modify_wenan\" class=\"form-control\" value="+wenan+">"
			+"</div>"
	    +"</div>"
	+"</div>"
	    
	+"<br>"
	+"<div class=\"row\">"
	    +"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">保额："
			+"</label>"
			+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
				+"<input type=\"text\" id=\"modify_baoe\" class=\"form-control\" value="+baoe+">"
			+"</div>"
		+"</div>"
	+"</div>"
		
	+"<br>"
	+"<div class=\"row\">"
		+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">保费："
			+"</label>"
			+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
				+"<input type=\"text\" id=\"modify_baofei\" rows=\"2\" class=\"form-control\" value="+baofei+"> </input>"
			+"</div>"
		+"</div>"
	+"</div>"
	+ "</div>",
		locale : "zh_CN",
		buttons : {
		Modify:{
				label : '确定',
				className : "btn-primary",
				callback : function() {
					var wenan = $("#modify_wenan").val();
					var baoe = $("#modify_baoe").val();
					var baofei = $("#modify_baofei").val();
					if(isNullOrEmpty(wenan) || isNullOrEmpty(baoe) || isNullOrEmpty(baofei)){
						bootbox.alert("请输入正确的值");
						return;
					}
					if(!isNumber(baoe) || !isNumber(baofei)){
						bootbox.alert("请输入正确的数值");
						return;
					}
					var data = {};
					data.id = id;
					data.wenan = wenan;
					data.baoe = baoe;
					data.baofei = baofei;
					$.ajax({
						url: '/yangguang/business/ModifyInsr',
						data: data,
						type: 'POST',
						dataType: 'json',
						success: function(json){
							if(json.retCode == 200){
								bootbox.alert("险种配置成功！");
								reloadTable(title, tableId, code);
							}else{
								bootbox.alert("险种配置成功！");
							}
						}
					});
				}
			},
		Cancel:{
				label : '取消',
				className : "btn-primary",
				callback : function() {
				
				}
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
function isNumber(val){
	var reg = /^[0-9]+\.?[0-9]*$/;
	var validate = reg.test(val);
	if(validate){
		return true;
	}else{
		return false;
	}
}