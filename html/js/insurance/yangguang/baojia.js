$(document).ready(function() {
	$.ajax({
		url: '/yangguang/baojia/iniPage',
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.dateShyx){
				$("#shyxstart").val(json.dateShyx);
			}
			if(json.dateJqx){
				$("#jqxstart").val(json.dateJqx);
			}
			if(json.defaultConfig=="0"){
				$("#defaultLuxury").attr("checked","checked");
			}else if(json.defaultConfig=="1"){
				$("#defaultAffordable").attr("checked","checked");
			}else if(json.defaultConfig=="2"){
				$("#defaultRenewal").attr("checked","checked");
			}
			var list = json.returnList;
			var len = list.length;
			for(var i=0; i<len; i++){
				var ck = list[i];
				if(ck == "0"){
					$("#returnLuxury").prop('checked',true);
				}else if(ck == "1"){
					$("#returnAffordable").prop('checked',true);
				}else if(ck == "2"){
					$("#returnRenewal").prop('checked',true);
				}else if(ck == "3"){
					$("#returnOptional").prop('checked',true);
				}
			}
		}
	});
});
function edit(type){
	var tcName = "";
	if(type=="returnLuxury"){
		tcName = "全险方案";
		tcType = "0";
	}else if(type=="returnAffordable"){
		tcName = "经济方案";
		tcType = "1";
	}else if(type=="returnRenewal"){
		tcName = "续保方案";
		tcType = "2";
	}
	$.ajax({
		url: '/yangguang/getAllDict',
		type: 'POST',
		dataType: 'json',
		async: false,
		success: function(json){
			if(json.retCode=="200"){
				var configList = json.retDesc;
				createDialog(tcName, tcType, configList);
				initInsrValue(tcType);
			}
		}
	});
}

//创建选择险种对话框
function createDialog(tcName, tcType, configList){
	var len = configList.length;
	bootbox.dialog({
		title : tcName + "选择险种",
		message : 
			  "<table id='businessInsrTable' width='100%' class=\"table table-striped\">" +
			  		"<thead>" +
			  			"<tr>" +
			  				"<td width='8%'><input id=\"selectAll\" name=\"check\" type=\"checkbox\" onClick=\"selectAll()\">全选</td>" +
			  				"<td width='8%'>险种名称</td>" +
			  				"<td width='8%'>是否投保</td>" +
			  			"</tr>" +
			  		"</thead>" +
			  		"<tbody>" +
			  		"</tbody>" +
			  	"</table>",
		locale : "zh_CN",
		buttons : {
		Modify:{
				label : '确定',
				className : "btn-primary",
				callback : function() {
					var codeStr = "";
					var valStr = "";
					for(var i=0; i<len; i++){
						var dict = configList[i];
						var dictCode = dict.businessCode;
						var dictVal = "";
						if($("#chk"+dictCode).prop("checked")==true){
							dictVal = $("#"+dictCode).val();
						}else{
							dictVal = "-1";
						}
						codeStr += dictCode + ",";
						valStr += dictVal + ",";
					}
					if(codeStr.length > 0){
						codeStr = codeStr.substring(0, codeStr.length-1);
					}
					if(valStr.length > 0){
						valStr = valStr.substring(0, valStr.length-1);
					}
					var data = {};
					data.tcType = tcType;
					data.tcName = tcName;
					data.codeStr = codeStr;
					data.valStr = valStr;
					$.ajax({
						url: '/yangguang/baojia/updateConfigs',
						type: 'POST',
						dataType: 'json',
						data : data,
						success: function(json){
							if(json.retCode=="200"){
								bootbox.alert("套餐设置保存成功");
							}else{
								bootbox.alert("套餐设置保存失败");
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
	$("#businessInsrTable tbody").empty();
	for(var i=0; i<len; i++){
		var dict = configList[i];
		var str = "";
		if(dict.type=="0"){
			if(dict.businessCode=="cov_231"){
				str = "<tr>" +
					"<td><input name=\"chkItem\" type=\"checkbox\" id=\"chk"+dict.businessCode+"\"></td>"+
					"<td>"+dict.businessName+"</td>" +
					"<td><select id=\""+dict.businessCode+"\" class=\"form-control\">" +
							"<option value=\"0\">不投保</option>" +
							"<option value=\"1\">国产玻璃</option>" +
							"<option value=\"2\">进口玻璃</option>" +
						"</select>" +
					"</td>" +
					"</tr>";
			}else{
				str = "<tr>" +
				"<td><input name=\"chkItem\" type=\"checkbox\" id=\"chk"+dict.businessCode+"\"></td>" +
				"<td>"+dict.businessName+"</td>" +
				"<td><select id=\""+dict.businessCode+"\" class=\"form-control\">" +
						"<option value=\"0\">不投保</option>" +
						"<option value=\"1\">投保</option>" +
					"</select>" +
				"</td>" +
				"</tr>";
			}
		}else{
			var code = dict.businessCode;
			var data = {};
			data.businessCode = code;
			$.ajax({
				url: '/yangguang/queryConfig',
				type: 'POST',
				data: data,
				dataType: 'json',
				async: false,
				success: function(json){
					if(json.retCode=="200"){
						var list = json.retDesc;
						var configList = list.length;
						str = "<tr>" + 
						"<td><input name=\"chkItem\" type=\"checkbox\" id=\"chk"+dict.businessCode+"\"></td>" +
						"<td>"+dict.businessName+"</td>"+
						"<td><select id=\""+dict.businessCode+"\" class=\"form-control\">";
						for(var i=0; i < configList; i++){
							var config = list[i];
							str += "<option value=\""+config.baoE+"\">"+config.wenAn+"</option>";
						}
						str += "</select></td></tr>";
					}
				}
			});
		}
		$("#businessInsrTable tbody").append(str);
	}
}

//获取险种的最新配置
function initInsrValue(tcType){
	var data = {};
	data.tcType = tcType;
	$.ajax({
		url: '/yangguang/baojia/getLatestConfigs',
		type: 'POST',
		dataType: 'json',
		data: data,
		async: false,
		success: function(json){
			if(json.retCode=="200"){
				var latestConfig = json.retDesc;
				var inis = json.retDict;
				var chkConfig = json.retChk;
				var len = inis.length;
				for(var i=0; i<len; i++){
					var dict = inis[i];
					var dictCode = dict.businessCode;
					var dictVal = "";
					var chkVal = "";
					if(dictCode=="cov_200"){
						dictVal = latestConfig.cov_200;
						chkVal = chkConfig.cov_200;
					}else if(dictCode=="cov_600"){
						dictVal = latestConfig.cov_600;
						chkVal = chkConfig.cov_600;
					}else if(dictCode=="cov_500"){
						dictVal = latestConfig.cov_500;
						chkVal = chkConfig.cov_500;
					}else if(dictCode=="cov_701"){
						dictVal = latestConfig.cov_701;
						chkVal = chkConfig.cov_701;
					}else if(dictCode=="cov_702"){
						dictVal = latestConfig.cov_702;
						chkVal = chkConfig.cov_702;
					}else if(dictCode=="cov_321"){
						dictVal = latestConfig.cov_321;
						chkVal = chkConfig.cov_321;
					}else if(dictCode=="cov_310"){
						dictVal = latestConfig.cov_310;
						chkVal = chkConfig.cov_310;
					}else if(dictCode=="cov_231"){
						dictVal = latestConfig.cov_231;
						chkVal = chkConfig.cov_231;
					}else if(dictCode=="cov_210"){
						dictVal = latestConfig.cov_210;
						chkVal = chkConfig.cov_210;
					}else if(dictCode=="cov_390"){
						dictVal = latestConfig.cov_390;
						chkVal = chkConfig.cov_390;
					}else if(dictCode=="cov_291"){
						dictVal = latestConfig.cov_291;
						chkVal = chkConfig.cov_291;
					}else if(dictCode=="cov_640"){
						dictVal = latestConfig.cov_640;
						chkVal = chkConfig.cov_640;
					}else if(dictCode=="cov_921"){
						dictVal = latestConfig.cov_921;
						chkVal = chkConfig.cov_921;
					}else if(dictCode=="cov_922"){
						dictVal = latestConfig.cov_922;
						chkVal = chkConfig.cov_922;
					}else if(dictCode=="cov_911"){
						dictVal = latestConfig.cov_911;
						chkVal = chkConfig.cov_911;
					}else if(dictCode=="cov_912"){
						dictVal = latestConfig.cov_912;
						chkVal = chkConfig.cov_912;
					}else if(dictCode=="cov_928"){
						dictVal = latestConfig.cov_928;
						chkVal = chkConfig.cov_928;
					}else if(dictCode=="cov_929"){
						dictVal = latestConfig.cov_929;
						chkVal = chkConfig.cov_929;
					}else if(dictCode=="cov_734"){
						dictVal = latestConfig.cov_734;
						chkVal = chkConfig.cov_734;
					}else if(dictCode=="cov_733"){
						dictVal = latestConfig.cov_733;
						chkVal = chkConfig.cov_733;
					}
					if(dictVal != "-1"){
						$('#'+dictCode).val(dictVal);
					}
					if(dictVal=="-1"){
						$('#chk'+dictCode).prop('checked',false);
					}else{
						$('#chk'+dictCode).prop('checked',true);
					}
				}
			}
		}
	});
}
//全选
function selectAll(){
	if($('#selectAll').is(':checked')){ 
        $("#businessInsrTable input[name='chkItem']").each(function () {
        	this.checked = true;
        });
    }else{ 
        $("#businessInsrTable input[name='chkItem']").each(function () {
        	this.checked = false;
        });
    } 
}
function isNullOrEmpty(strVal) {
	if (strVal == '' || strVal == null || strVal == undefined) {
		return true;
	} else {
		return false;
	}
}
function saveBjjk(){
	var defaultTclb = "";
	var returnlb = "";
	var defaultTc = $("input[name='default']:checked").val();
	var syxqbrq = $("#shyxstart").val();
	var jqxqbrq = $("#jqxstart").val();
	var luxury = $("#returnLuxury").prop("checked");
	var affordable = $("#returnAffordable").prop("checked");
	var renewal = $("#returnRenewal").prop("checked");
	var optional = $("#returnOptional").prop("checked");
	if(isNullOrEmpty(defaultTc)){
		bootbox.alert("请选择默认套餐");
		return;
	}
	if(isNullOrEmpty(syxqbrq)){
		bootbox.alert("请选择商业险起保日期");
		return;
	}
	if(isNullOrEmpty(jqxqbrq)){
		bootbox.alert("请选择交强险起保日期");
		return;
	}
	if(luxury==false && affordable==false && renewal==false && optional==false){
		bootbox.alert("请选择返回的套餐类别");
		return;
	}
	if(luxury==true){
		returnlb += "0,";
	}
	if(affordable==true){
		returnlb += "1,";
	}
	if(renewal==true){
		returnlb += "2,";
	}
	if(optional==true){
		returnlb += "3,";
	}
	
	returnlb = returnlb.substring(0, returnlb.length-1);
	if(defaultTc=="defaultLuxury"){
		defaultTclb = "0";
	}else if(defaultTc == "defaultAffordable"){
		defaultTclb = "1";
	}else if(defaultTc == "defaultRenewal"){
		defaultTclb = "2";
	}
	var data = {};
	data.defaultTclb = defaultTclb;
	data.returnlb = returnlb;
	data.syxqbrq = syxqbrq;
	data.jqxqbrq = jqxqbrq;
	$.ajax({
		url: '/yangguang/baojia/saveReturnInsures',
		type: 'POST',
		data: data,
		dataType: 'json',
		success: function(json){
			if(json.retCode=="200"){
				bootbox.alert("设置保存成功");
			} else{
				bootbox.alert("设置保存失败");
			}
		}
	});
}