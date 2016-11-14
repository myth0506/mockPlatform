$(document).ready(function(){
	initIni();
});

function initIni(){
	$.ajax({
		url:'/youbi/initBaoJiaConfig',
		type:'POST',
		dataTpey:'json',
		success:function(json){
			if(json.bjkg){
				$("#bjRadio").val(json.bjkg);
			}
			if(json.sffg){
				$("#fgRadio").val(json.sffg);
			}
			if(json.syxqbrq){				
				$("#syxstart").val(json.syxqbrq);
			}		
			if(json.jqxqbrq){
				$("#jqxstart").val(json.jqxqbrq);
			}
			if(json.jqxbf){
				$("#jqxbf").val(json.jqxbf);
			}
			if(json.ccs){
				$("#ccs").val(json.ccs);
			}
			if(json.jqxqnzbrq){
				$("#jqxend").val(json.jqxqnzbrq);
			}
			if(json.jqxdqts){
				$("#jqxdqts").val(json.jqxdqts);
			}
			if(json.jqxsbxx){
				$("#jqxsbxx").val(json.jqxsbxx);
			}
			if(json.jqxsbxxma){
				$("#jqxsbxxma").val(json.jqxsbxxma);
			}
			if(json.sbjqxsbxx){
				$("#sbjqxsbxx").val(json.sbjqxsbxx);
			}
			if(json.sbjqxsbxxma){
				$("#sbjqxsbxxma").val(json.sbjqxsbxxma);
			}
			if(json.defaultRadio == 1){
				$("input[name='defaultRadio'][value='1']").prop("checked",true);
			}else{
				$("input[name='defaultRadio'][value='0']").prop("checked",true);
			}
		}
	});
}

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

function savebjxx(){
	
	var sffg =  $("#fgRadio input:radio:checked").val();
	var bjkg =  $("#bjRadio input:radio:checked").val();
	var sbjqxsbxx = $("#sbjqxsbxx").val();
	var sbjqxsbxxma = $("#sbjqxsbxxma").val();
	
	var defaultRadio = $("input[name='defaultRadio']:checked").val();
	var syxqbrq = $('#syxstart').val();
	
	var jqxqbrq = $("#jqxstart").val();
	var jqxbf = $("#jqxbf").val();
	var ccs = $("#ccs").val();
	var jqxqnzbrq =  $("#jqxend").val();
	var jqxdqts = $("#jqxdqts").val();
	var jqxsbxx = $("#jqxsbxx").val();
	var jqxsbxxma = $("#jqxsbxxma").val();
	
	var data = {};
	data.sffg = sffg;
	data.bjkg = bjkg;
	data.sbjqxsbxx = sbjqxsbxx;
	data.sbjqxsbxxma = sbjqxsbxxma;
	
	data.defaultRadio = defaultRadio;
	data.syxqbrq = syxqbrq;
	
	data.jqxqbrq = jqxqbrq;
	data.jqxbf = jqxbf;
	data.ccs = ccs;
	data.jqxqnzbrq = jqxqnzbrq;
	data.jqxdqts = jqxdqts;
	data.jqxsbxx = jqxsbxx;
	data.jqxsbxxma = jqxsbxxma;
	$.ajax({
	url:'/youbi/savebjxx',
	type:'POST',
	data:data,
	dataType:'json',
	success:function(json){
		if(json.retCode =="200"){
			bootbox.alert("设置保存成功");
		}else{
			bootbox.alert("设置保存失败");
		}
	}
	});
}


/*function setNoVisibility(){
	document.getElementById("bjkgan").style.visibility = "hidden";
	document.getElementById("jqxsbma").style.visibility = "hidden";
	document.getElementById("sbjqxsbxx").style.visibility = "hidden";
	document.getElementById("jqxsbxx").style.visibility = "hidden";
	document.getElementById("sbjqxsbxxma").style.visibility = "hidden";	
}

function setVisibility(){
	document.getElementById("bjkgan").style.visibility = "visible";
	document.getElementById("jqxsbma").style.visibility = "visible";
	document.getElementById("sbjqxsbxx").style.visibility = "visible";
	document.getElementById("jqxsbxx").style.visibility = "visible";
	document.getElementById("sbjqxsbxxma").style.visibility = "visible";	
}*/

function setNoVisibility(){
	document.getElementById("bjkgan").style.display = "none";
	document.getElementById("jqxsbma").style.display = "none";
	document.getElementById("sbjqxsbxx").style.display = "none";
	document.getElementById("jqxsbxx").style.display = "none";
	document.getElementById("sbjqxsbxxma").style.display = "none";	
}

function setVisibility(){
	document.getElementById("bjkgan").style.display = "";
	document.getElementById("jqxsbma").style.display = "";
	document.getElementById("sbjqxsbxx").style.display = "";
	document.getElementById("jqxsbxx").style.display = "";
	document.getElementById("sbjqxsbxxma").style.display = "";	
}

function edit(type){
	var tcName = "";
	if(type=="newDefault"){
		tcName = "新保默认方案";
		tcType = "0";
	}else if(type=="xubaoDefault"){
		tcName = "续保默认方案";
		tcType = "1";
	}
	$.ajax({
		url:'/youbi/getAllDict',
		type:'POST',
		dataTpey:'json',
		async: false,
		success:function(json){
			if(json.retCode=="200"){
				var list = json.dictList;
				createDialog(tcName, tcType, list);
				initInsrValue(tcType);
			}else{
				alert(json.retDesc);
			}
		}
	});
}

//创建选择险种对话框
function createDialog(tcName, tcType, list){
	var len = list.length;
	bootbox.dialog({
		title : tcName,
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
					var configIds = "";
					for(var i=0; i<len; i++){
						var dict = list[i];
						if($("#chk"+dict.businessCode).prop("checked")==true){
							var configId;
							var baoE = $("#wenan"+dict.businessCode).val();
							var data1 = {};
							data1.businessCode = dict.businessCode;
							data1.baoE = baoE;
							$.ajax({
								url: '/youbi/getConfigId',
								type: 'POST',
								dataType: 'json',
								data : data1,
								async: false,
								success: function(json){
									if(json.retCode=="200"){
										configId = json.retDesc;
										configIds += configId + ",";
									}
								}
							});
						}
					}
					if(configIds == ""){
						alert("请勾选险种！");
						return false;
					}
					configIds = configIds.substring(0, configIds.length-1);
					var data = {};
					data.tcType = tcType;
					data.tcName = tcName;
					data.configIds = configIds;
					$.ajax({
						url: '/youbi/updateConfigs',
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
	var str = "";
	$("#businessInsrTable tbody").empty();
	for (var i=0; i<len; i++){
		var dict = list[i];
		var code = dict.businessCode;
		if(dict.type=="0"){
			str = "<tr>" +
			"<td><input name=\"chkItem\" type=\"checkbox\" id=\"chk"+code+"\"></td>" +
			"<td>"+dict.businessName+"</td>" +
			"<td><select id=\"wenan"+code+"\" class=\"form-control\">" +
					"<option value=\"0\">不投保</option>" +
					"<option value=\"1\">投保</option>" +
				"</select>" +
			"</td>" +
			"</tr>";
		}else{
			var data = {};
			data.businessCode = code;
			$.ajax({
				url: '/youbi/queryConfigs',
				type: 'POST',
				data: data,
				dataType: 'json',
				async: false,
				success: function(json){
					if(json.retCode=="200"){
						var configList = json.retDesc;
						var configListlen = configList.length;
						str = "<tr>" + 
						"<td><input name=\"chkItem\" type=\"checkbox\" id=\"chk"+code+"\"></td>" +
						"<td>"+dict.businessName+"</td>"+
						"<td><select id=\"wenan"+code+"\" class=\"form-control\">";
						for(var i=0; i < configListlen; i++){
							var config = configList[i];
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
		url: '/youbi/getLatestConfigs',
		type: 'POST',
		dataType: 'json',
		data: data,
		async: false,
		success: function(json){
			if(json.retCode=="200"){
				if(json.retDesc!=null){
					var configIds = json.retDesc.iniValue;
					if(configIds!=null){
						var ids = configIds.split(",");
						for(var i=0;i<ids.length;i++){
							var configId = ids[i];
							var data1 = {};
							data1.configId = configId;
							$.ajax({
								url: '/youbi/queryConfig',
								type: 'POST',
								dataType: 'json',
								data: data1,
								async: false,
								success: function(json1){
									if(json1.retCode == 200){
										var configInfo = json1.retDesc;
										var code = configInfo.businessCode;
										var baoE = configInfo.baoE;
										$('#chk'+code).prop('checked',true);
										$('#wenan'+code).val(baoE);
									}
								}
							});
						}
					}
				}
			}
		}
	});
}