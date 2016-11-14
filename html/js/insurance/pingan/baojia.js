$(document).ready(function() {
	$.ajax({
		url: '/business/iniAllDict',
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
				$("#defaultRecommand").attr("checked","checked");
			}else if(json.defaultConfig=="1"){
				$("#defaultBasic").attr("checked","checked");
			}else if(json.defaultConfig=="2"){
				$("#defaultAll").attr("checked","checked");
			}else if(json.defaultConfig=="3"){
				$("#defaultRenew").attr("checked","checked");
			}
			var list = json.returnList;
			var len = list.length;
			for(var i=0; i<len; i++){
				var ck = list[i];
				if(ck == "0"){
					$("#returnRecommand").prop('checked',true);
				}else if(ck == "1"){
					$("#returnBasic").prop('checked',true);
				}else if(ck == "2"){
					$("#returnAll").prop('checked',true);
				}else if(ck == "3"){
					$("#returnRenew").prop('checked',true);
				}else if(ck == "4"){
					$("#returnCustom").prop('checked',true);
				}
			}
		}
	});
});
function edit(type){
	var tcName = "";
	if(type=="defaultRecommand"){
		tcName = "推荐型套餐";
		tcType = "0";
	}else if(type=="defaultBasic"){
		tcName = "基本型套餐";
		tcType = "1";
	}else if(type=="defaultAll"){
		tcName = "全包型套餐";
		tcType = "2";
	}else if(type=="defaultRenew"){
		tcName = "续保套餐";
		tcType = "3";
	}
	$.ajax({
		url: '/business/getAllDict',
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
						url: '/baojia/updateConfigs',
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
			if(dict.businessCode=="amount08"){
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
				url: '/business/queryConfig',
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
						for(var i=0; i<configList; i++){
							var config = list[i];
							if(config.baoE==0){
								str += "<option value=\""+config.baoE+"\">"+config.wenAn+"</option>";
							}else{
								str += "<option value=\""+config.baoE+"\">"+config.wenAn+"("+config.baoE+")"+"</option>";
							}
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
		url: '/baojia/getLatestConfigs',
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
					if(dictCode=="amount01"){
						dictVal = latestConfig.amount01;
						chkVal = chkConfig.amount01;
					}else if(dictCode=="amount02"){
						dictVal = latestConfig.amount02;
						chkVal = chkConfig.amount02;
					}else if(dictCode=="amount03"){
						dictVal = latestConfig.amount03;
						chkVal = chkConfig.amount03;
					}else if(dictCode=="amount04"){
						dictVal = latestConfig.amount04;
						chkVal = chkConfig.amount04;
					}else if(dictCode=="amount05"){
						dictVal = latestConfig.amount05;
						chkVal = chkConfig.amount05;
					}else if(dictCode=="amount08"){
						dictVal = latestConfig.amount08;
						chkVal = chkConfig.amount08;
					}else if(dictCode=="amount17"){
						dictVal = latestConfig.amount17;
						chkVal = chkConfig.amount17;
					}else if(dictCode=="amount18"){
						dictVal = latestConfig.amount18;
						chkVal = chkConfig.amount18;
					}else if(dictCode=="amount27"){
						dictVal = latestConfig.amount27;
						chkVal = chkConfig.amount27;
					}else if(dictCode=="amount28"){
						dictVal = latestConfig.amount28;
						chkVal = chkConfig.amount28;
					}else if(dictCode=="amount41"){
						dictVal = latestConfig.amount41;
						chkVal = chkConfig.amount41;
					}else if(dictCode=="amount48"){
						dictVal = latestConfig.amount48;
						chkVal = chkConfig.amount48;
					}else if(dictCode=="amount49"){
						dictVal = latestConfig.amount49;
						chkVal = chkConfig.amount49;
					}else if(dictCode=="amount50"){
						dictVal = latestConfig.amount50;
						chkVal = chkConfig.amount50;
					}else if(dictCode=="amount57"){
						dictVal = latestConfig.amount57;
						chkVal = chkConfig.amount57;
					}else if(dictCode=="amount59"){
						dictVal = latestConfig.amount59;
						chkVal = chkConfig.amount59;
					}else if(dictCode=="amount63"){
						dictVal = latestConfig.amount63;
						chkVal = chkConfig.amount63;
					}
					if(dictVal != "-1"){
						$('#'+dictCode).val(dictVal);
					}
					if(chkVal=="-1"){
						$('#chk'+dictCode).prop('checked',false);
					}else{
						$('#chk'+dictCode).prop('checked',true);
					}
					/*if(dictVal=="-1"){
						$('#chk'+dictCode).prop('checked',false);
					}else{
						$('#chk'+dictCode).prop('checked',true);
						$('#'+dictCode).val(dictVal);
					}*/
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
	var jbtc = $("#returnBasic").prop("checked");
	var tjtc = $("#returnRecommand").prop("checked");
	var qbtc = $("#returnAll").prop("checked");
	var xbtc = $("#returnRenew").prop("checked");
	var zxtc = $("#returnCustom").prop("checked");
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
	if(tjtc==false && jbtc==false && qbtc==false && xbtc==false && zxtc==false){
		bootbox.alert("请选择返回的套餐类别");
		return;
	}
	if(tjtc==true){
		returnlb += "0,";
	}
	if(jbtc==true){
		returnlb += "1,";
	}
	if(qbtc==true){
		returnlb += "2,";
	}
	if(xbtc==true){
		returnlb += "3,";
	}
	if(zxtc==true){
		returnlb += "4,";
	}
	returnlb = returnlb.substring(0, returnlb.length-1);
	if(defaultTc=="defaultRecommand"){
		defaultTclb = "0";
	}else if(defaultTc == "defaultBasic"){
		defaultTclb = "1";
	}else if(defaultTc == "defaultAll"){
		defaultTclb = "2";
	}else{
		defaultTclb = "3";
	}
	var data = {};
	data.defaultTclb = defaultTclb;
	data.returnlb = returnlb;
	data.syxqbrq = syxqbrq;
	data.jqxqbrq = jqxqbrq;
	$.ajax({
		url: '/baojia/saveReturnInsures',
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