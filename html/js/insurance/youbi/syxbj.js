$(document).ready(function(){
	loadAllDynamicConfigs();
});
function loadAllDynamicConfigs(){
	reloadTable1("商业险配置", "car01Table", "car01");
	reloadTable2("车辆损失险", "car02Table", "damage");
	reloadTable2("车身划痕险", "car03Table", "scratch");
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

//新增商业险
function newBusi(){
	var title = "新增险种";
	var tableId = "car01Table";
	var code = "car01";
	createDialog1(title, tableId, code);
}

//车辆损失险
function newCar01(){
	var title = "车辆损失险";
	var tableId = "car02Table";
	var code = "damage";
	createDialog2(title, tableId, code);
}

//车身划痕险
function newCar02(){
	var title = "车身划痕险";
	var tableId = "car03Table";
	var code = "scratch";
	createDialog2(title, tableId, code);
}

function createDialog1(title, tableId, code){
	bootbox.dialog({
		title : title,
		message : 
	"<div>"
	+"<div class=\"row\">"
		+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">名称："
			+"</label>"
			+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
				+"<input type=\"text\" id=\"name\" class=\"form-control\">"
			+"</div>"
	    +"</div>"
	+"</div>"
	    
	+"<br>"
	+"<div class=\"row\">"
	    +"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">code："
			+"</label>"
			+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
				+"<input type=\"text\" id=\"code\" class=\"form-control\" >"
			+"</div>"
		+"</div>"
	+"</div>",
		locale : "zh_CN",
		buttons : {
		Modify:{
				label : '确定',
				className : "btn-primary",
				callback : function() {
					var name = $("#name").val();
					var code = $("#code").val();
					if(isNullOrEmpty(name) || isNullOrEmpty(code)){
						bootbox.alert("请输入正确的值");
						return;
					}
				/*	if(!isNumber(code)){
						bootbox.alert("请输入正确的数值");
						return;
					}*/
					var data = {};
					data.name = name;
					data.code = code;
					$.ajax({
						url: '/youbi/insertMulti',
						data: data,
						type: 'POST',
						dataType: 'json',
						sync : true,
						success: function(json){
							if(json.retCode == 200){
								bootbox.alert("险种配置成功！");
								/*var id = json.retDesc;*/
								var str = "<tr>"+
								"<td>" + name + "</td>" +
								"<td>" + code + "</td>" +
								"<td><button class=\"btn btn-info btn-sm edit\"  onclick=\"modifyInsr1('"+title+"','"+insr.id+"', '"+name+"','"+tableId+"','"+code+"')\">修改</button>" +
								   " <button class=\"btn-warning btn-sm delete\" onclick=\"deleteInsr1('"+title+"','"+insr.id+"', '"+tableId+"', '"+code+"')\">删除</button>" +
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

function createDialog2(title, tableId, code){
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
	+"</div>"
	
	+"<br>"
	+"<div class=\"row\">"
		+"<div class=\"col-sm-30\" id=\"clssRadio\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">是否有效：&nbsp&nbsp&nbsp"
			+"</label>"
			+"<label>"
			+"&nbsp&nbsp&nbsp<input type=\"radio\" name=\"yxRadios\" id=\"yxYes\"  value=\"1\" checked>是"
			+"</label>"
			+"<label>"
			+" &nbsp&nbsp&nbsp<input type=\"radio\" name=\"yxRadios\" id=\"yxNo\"  value=\"0\" checked>否"
			+"</label>"
		+"</div>"
	+"</div>"
	+ "</div>"
	
	+"<br>"
	+"<div class=\"row\">"
		+"<div class=\"col-sm-30\" id=\"cshhRadio\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">是否可投：&nbsp&nbsp&nbsp"
			+"</label>"
			+"<label>"
			+"&nbsp&nbsp&nbsp<input type=\"radio\" name=\"ktRadios\" id=\"ktYes\"  value=\"1\" checked>是"
			+"</label>"
			+"<label>"
			+"&nbsp&nbsp&nbsp<input type=\"radio\" name=\"ktRadios\" id=\"ktNo\"  value=\"0\" checked>否"
			+"</label>"
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
					var isvalid = $("#clssRadio input:radio:checked").val();
					var isvote = $("#cshhRadio input:radio:checked").val();
					if(isNullOrEmpty(wenan) || isNullOrEmpty(baoe) || isNullOrEmpty(baofei)){
						bootbox.alert("请输入正确的值");
						return;
					}
					var data = {};
					data.wenan = wenan;
					data.baoe = baoe;
					data.baofei = baofei;
					data.isvalid = isvalid;
					data.isvote = isvote;
					data.businesscode = code;
					
					$.ajax({
						url: '/youbi/insertConfigInfo',
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
								"<td>" + isvalid + "</td>" +
								"<td>" + isvote + "</td>" +
								"<td><button class=\"btn btn-info btn-sm edit\"  onclick=\"modifyInsr2('"+title+"', '"+id+"', '"+wenan+"', '"+baoe+"', '"+baofei+"', '"+tableId+"', '"+code+"', '"+isvalid+"', '"+isvote+"')\">修改</button>" +
										" <button class=\"btn-warning btn-sm delete\" onclick=\"deleteInsr2('"+title+"', '"+id+"', '"+tableId+"', '"+code+"')\">删除</button>" +
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
function reloadTable1(title, tableId, code){
	var data = {};
	data.code = code;
	$.ajax({
			url: '/youbi/queryConfigModel',
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
						"<td>" + insr.name + "</td>" +
						"<td>" + insr.code + "</td>" +
						"<td><button class=\"btn btn-info btn-sm edit\"  onclick=\"modifyInsr1('"+title+"', '"+insr.id+"', '"+insr.name+"', '"+tableId+"', '"+insr.code+"')\">修改</button>" +"&nbsp&nbsp&nbsp"+
						    "<button class=\"btn-warning btn-sm delete\" onclick=\"deleteInsr1('"+title+"', '"+insr.id+"', '"+tableId+"', '"+code+"')\">删除</button>" +"</td>" +
						"</tr>"
						$("#"+tableId).append(str);
					}
				}else{
					bootbox.alert("查询险种失败！");
				}
			}
	});
}

function reloadTable2(title, tableId, code){
	var data = {};
	data.businesscode = code;
	$.ajax({
			url: '/youbi/queryConfigInfo',
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
						"<td>" + insr.wenan + "</td>" +
						"<td>" + insr.baoe + "</td>" +
						"<td>" + insr.baofei + "</td>" +
						"<td>" + insr.isvalid + "</td>" +
						"<td>" + insr.isvote + "</td>" +
						"<td><button class=\"btn btn-info btn-sm edit\"  onclick=\"modifyInsr2('"+title+"', '"+insr.id+"', '"+insr.wenan+"', '"+insr.baoe+"', '"+insr.baofei+"', '"+tableId+"', '"+insr.isvalid+"', '"+insr.isvote+"', '"+insr.code+"')\">修改</button>" +"&nbsp&nbsp&nbsp"+
						    "<button class=\"btn-warning btn-sm delete\" onclick=\"deleteInsr2('"+title+"', '"+insr.id+"', '"+tableId+"', '"+code+"')\">删除</button>" +"</td>" +
						"</tr>"
						$("#"+tableId).append(str);
					}
				}else{
					bootbox.alert("查询险种失败！");
				}
			}
	});
}
function deleteInsr1(title, id,tableId, code){
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
							url: '/youbi/DeleteInsr',
							data: data,
							type: 'POST',
							dataType: 'json',
							success: function(json){
								if(json.retCode == 200){
									bootbox.alert("险种配置删除成功！");
									reloadTable1(title, tableId, code);
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
function modifyInsr1(title,id,name,tableId,code){
	
	bootbox.dialog({
		title : title,
		message : 
	"<div>"
	+"<div class=\"row\">"
		+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">名称："
			+"</label>"
			+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
				+"<input type=\"text\" id=\"name\" class=\"form-control\" value="+name+">"
			+"</div>"
	    +"</div>"
	+"</div>"
	    
	+"<br>"
	+"<div class=\"row\">"
	    +"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">code："
			+"</label>"
			+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
				+"<input type=\"text\" id=\"code\" class=\"form-control\" value="+code+">"
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
					var name = $("#name").val();
					var code = $("#code").val();

					if(isNullOrEmpty(name) || isNullOrEmpty(code)){
						bootbox.alert("请输入正确的值");
						return;
					}
					var data = {};
					data.id = id;
					data.name = name;
					data.code = code;

					$.ajax({
						url: '/youbi/ModifyInsr',
						data: data,
						type: 'POST',
						dataType: 'json',
						success: function(json){
							if(json.retCode == 200){
								bootbox.alert("险种配置成功！");
								reloadTable1(title, tableId, code);
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

function modifyInsr2(title,id,wenan,baoe,baofei,tableId,code,isvalid,isvote){
	
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
				+"<input type=\"text\" id=\"wenan\" class=\"form-control\" value="+wenan+">"
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
				+"<input type=\"text\" id=\"baoe\" class=\"form-control\" value="+baoe+">"
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
				+"<input type=\"text\" id=\"baofei\" class=\"form-control\" value="+baofei+">"
			+"</div>"
		+"</div>"
	+"</div>"
		
	+"<br>"
	+"<div class=\"row\">"
		+"<div class=\"col-sm-30\" id=\"clssRadio\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">是否有效：&nbsp&nbsp&nbsp"
			+"</label>"
			+"<label>"
			+"&nbsp&nbsp&nbsp<input type=\"radio\" name=\"yxRadios\" id=\"yxYes\"  value=\"1\" checked>是"
			+"</label>"
			+"<label>"
			+" &nbsp&nbsp&nbsp<input type=\"radio\" name=\"yxRadios\" id=\"yxNo\"  value=\"0\" checked>否"
			+"</label>"
		+"</div>"
	+"</div>"
	+ "</div>"
	
	+"<br>"
	+"<div class=\"row\">"
		+"<div class=\"col-sm-30\" id=\"cshhRadio\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
			+"<label class=\"col-sm-1 control-label\""
				+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">是否可投：&nbsp&nbsp&nbsp"
			+"</label>"
			+"<label>"
			+"&nbsp&nbsp&nbsp<input type=\"radio\" name=\"ktRadios\" id=\"ktYes\"  value=\"1\" checked>是"
			+"</label>"
			+"<label>"
			+"&nbsp&nbsp&nbsp<input type=\"radio\" name=\"ktRadios\" id=\"ktNo\"  value=\"0\" checked>否"
			+"</label>"
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
					var isvalid = $("#clssRadio input:radio:checked").val();
					var isvote = $("#cshhRadio input:radio:checked").val();

					if(isNullOrEmpty(wenan) || isNullOrEmpty(baoe) || isNullOrEmpty(baofei)){
						bootbox.alert("请输入正确的值");
						return;
					}
					var data = {};
					data.id = id;
					data.wenan = wenan;
					data.baoe = baoe;
					data.baofei = baofei;
					data.isvalid = isvalid;
					data.isvote = isvote;

					$.ajax({
						url: '/youbi/ModifyConfigInfo',
						data: data,
						type: 'POST',
						dataType: 'json',
						success: function(json){
							if(json.retCode == 200){
								bootbox.alert("险种配置成功！");
								reloadTable2(title, tableId, code);
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


function deleteInsr2(title, id,tableId, code){
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
							url: '/youbi/DeleteConfigInfo',
							data: data,
							type: 'POST',
							dataType: 'json',
							success: function(json){
								if(json.retCode == 200){
									bootbox.alert("险种配置删除成功！");
									reloadTable2(title, tableId, code);
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