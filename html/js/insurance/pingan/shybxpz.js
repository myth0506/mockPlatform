$(document).ready(function(){
	loadAllDynamicConfigs();
});
function loadAllDynamicConfigs(){
	reloadTable("第三者责任保险", "amount02Table", "amount02");
	reloadTable("新增车上人员责任险（司机）", "amount04Table", "amount04");
	reloadTable("新增车上人员责任险（乘客）", "amount05Table", "amount05");
	reloadTable("新增车辆划痕责任险", "amount17Table", "amount17");
}
//新增其他险种（不需设置可选项的险种）
function saveOtherInsr(){
	var str = "";
	//车损险
	var defaultAmount01 = $("#defaultAmount01").val();
	var lowAmount01 = $("#lowAmount01").val();
	var heighAmount01 = $("#heighAmount01").val();
	var isAmount01 = $("#amount01").val();
	var zidingyiAmount01 = $("#zidingyiAmount01").val();
	if(isNullOrEmpty(defaultAmount01) || isNullOrEmpty(lowAmount01) || isNullOrEmpty(heighAmount01)
			||isNullOrEmpty(isAmount01) || isNullOrEmpty(zidingyiAmount01) || !isNumber(defaultAmount01)
			|| !isNumber(lowAmount01) || !isNumber(heighAmount01) || !isNumber(isAmount01) || !isNumber(zidingyiAmount01)){
		bootbox.alert("车损险请填写完整的参数");
		return;
	}
	str += "defaultAmount01="+defaultAmount01+"&lowAmount01="+lowAmount01
			+"&heighAmount01="+heighAmount01+"&amount01="+isAmount01
			+"&zidingyiAmount01="+zidingyiAmount01;
	//全车盗抢险
	var isAmount03 = $("#amount03").val();
	if(isNullOrEmpty(isAmount03) || !isNumber(isAmount03)){
		bootbox.alert("全车盗抢险请填写完整的参数");
		return;
	}
	str += "&amount03="+isAmount03;
	//玻璃单独破碎险
	var gcAmount08 = $("#gcAmount08").val();
	var jkAmount08 = $("#jkAmount08").val();
	if(isNullOrEmpty(gcAmount08) || isNullOrEmpty(jkAmount08) 
			|| !isNumber(gcAmount08) || !isNumber(jkAmount08)){
		bootbox.alert("玻璃破碎险请填写完整的参数");
		return;
	}
	str += "&gcAmount08="+gcAmount08+"&jkAmount08="+jkAmount08;
	//自然损失险
	var isAmount18 = $("#amount18").val();
	if(isNullOrEmpty(isAmount18) || !isNumber(isAmount18)){
		bootbox.alert("自然损失险请填写完整的参数");
		return;
	}
	str += "&amount18="+isAmount18;
	//不计免赔险（车损）
	var isAmount27 = $("#amount27").val();
	if(isNullOrEmpty(isAmount27) || !isNumber(isAmount27)){
		bootbox.alert("不计免险（车损）请填写完整的参数");
		return;
	}
	str += "&amount27="+isAmount27;
	//不计免赔险（三者）
	var isAmount28 = $("#amount28").val();
	if(isNullOrEmpty(isAmount28) || !isNumber(isAmount28)){
		bootbox.alert("不计免险（三者）请填写完整的参数");
		return;
	}
	str += "&amount28="+isAmount28;
	//不计免赔险（盗抢）
	var isAmount48 = $("#amount48").val();
	if(isNullOrEmpty(isAmount48) || !isNumber(isAmount48)){
		bootbox.alert("不计免险（盗抢）请填写完整的参数");
		return;
	}
	str += "&amount48="+isAmount48;
	//不计免赔险（车上人员）
	var isAmount49 = $("#amount49").val();
	if(isNullOrEmpty(isAmount49) || !isNumber(isAmount49)){
		bootbox.alert("不计免险（车上人员）请填写完整的参数");
		return;
	}
	str += "&amount49="+isAmount49;
	//不计免赔险（车附加险）
	var isAmount50 = $("#amount50").val();
	if(isNullOrEmpty(isAmount50) || !isNumber(isAmount50)){
		bootbox.alert("不计免险（附加险）请填写完整的参数");
		return;
	}
	str += "&amount50="+isAmount50;
	//涉水驾驶损失险
	var isAmount41 = $("#amount41").val();
	if(isNullOrEmpty(isAmount41) || !isNumber(isAmount41)){
		bootbox.alert("涉水驾驶损失险请填写完整的参数");
		return;
	}
	str += "&amount41="+isAmount41;
	//指定专修厂特约条款
	var isAmount57 = $("#amount57").val();
	if(isNullOrEmpty(isAmount57) || !isNumber(isAmount57)){
		bootbox.alert("指定专修厂特约条款请填写完整的参数");
		return;
	}
	str += "&amount57="+isAmount57;
	//倒车镜、车灯单独损坏险
	var isAmount59 = $("#amount59").val();
	if(isNullOrEmpty(isAmount59) || !isNumber(isAmount59)){
		bootbox.alert("倒车镜、车灯单独损坏险请填写完整的参数");
		return;
	}
	str += "&amount59="+isAmount59;
	//机动车损失保险无法找到第三方特约险
	var isAmount63 = $("#amount63").val();
	if(isNullOrEmpty(isAmount63) || !isNumber(isAmount63)){
		bootbox.alert("机动车损失保险无法找到第三方特约险请填写完整的参数");
		return;
	}
	str += "&amount63="+isAmount63;
	//商业险原价
	var originalPremium = $("#originalPremium").val();
	if(isNullOrEmpty(originalPremium) || !isNumber(originalPremium)){
		bootbox.alert("商业险原价请填写完整的参数");
		return;
	}
	str += "&originalPremium="+originalPremium;
	var data = {};
	data.content = str;
	$.ajax({
		url: '/business/updateCache',
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
//新增第三者责任险
function newAmount02(){
	var title = "第三者责任保险";
	var tableId = "amount02Table";
	var code = "amount02";
	createDialog(title, tableId, code);
}
//新增车上人员责任险（司机）
function newAmount04(){
	var title = "新增车上人员责任险（司机）";
	var tableId = "amount04Table";
	var code = "amount04";
	createDialog(title, tableId, code);
}
//新增车上人员责任险（乘客）
function newAmount05(){
	var title = "新增车上人员责任险（乘客）";
	var tableId = "amount05Table";
	var code = "amount05";
	createDialog(title, tableId, code);
}
//新增车辆划痕责任险
function newAmount17(){
	var title = "新增车辆划痕责任险";
	var tableId = "amount17Table";
	var code = "amount17";
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
						url: '/business/insertMulti',
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
										" <button class=\"btn-warning btn-sm delete\" onclick=\"deleteInsr('"+title+"', '"+id+"', '"+tableId+"', '"+code+"')\">删除</button>" +
										"</td>" +
								"</tr>"
								$("#"+tableId).append(str);
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
function validateWenan(code){
	var wenan = $("#wenan").val();
	if(isNullOrEmpty(wenan)){
		return;
	}
	var data = {};
	data.wenan = wenan;
	data.businessCode = code;
	$.ajax({
		url: '/business/validateWenAn',
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
			url: '/business/queryConfig',
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
							url: '/business/DeleteInsr',
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
						url: '/business/ModifyInsr',
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