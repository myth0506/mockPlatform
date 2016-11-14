$(document).ready(function(){
    //加载违章查询的下拉列表，将处于当前有效状态的置于选中的状态
	$.ajax({
		url:'/InsrWzcx/selectWzStatus',
		type:'POST',
		dataType:'json',
		success:function(json){
			if(json.retCode == "200"){
				statusList = json.retDesc;
				for(var i=0; i<statusList.length; i++){
					var status = statusList[i];
					$("#setWzSelectStatus").append(
							"<option value=" + status.id + ">" + status.resp_retDesc + "</option>");
				}
				$("#setWzStatus").append("<option value='1'>是</option> <option value='0'>否</option>");
			}else{
				bootbox.alert("违章查询状态加载失败");
			}
		}
	});
});
var wzcx_id = null;
var wzcx_car_num = null;

function searchWzcx(){
	$("#table_search_task tbody").empty();
	wzcx_car_num = $("#carNumber").val();
	if(wzcx_car_num != null && wzcx_car_num != ""){
		var data = {};
		data.wzcx_car_num = wzcx_car_num;
		$.ajax({
			url:'/InsrWzcx/searchWzcx',
			type: 'POST',
			data: data,
			dataType: 'json',
			success: function(json){
				if(json.retCode == "200"){
				
					var retWzcxInfo = json.retWzcxInfo;
					wzcx_id = retWzcxInfo.wzcx_id;
					if(retWzcxInfo.wzcx_status != null){
						$("#setWzSelectStatus").val(retWzcxInfo.wzcx_status);
					}
					if(retWzcxInfo.wzcx_sfwz != null){
						$("#setWzStatus").val(retWzcxInfo.wzcx_sfwz);
					}
					if(json.retWzcxRecord != null && retWzcxInfo != null && retWzcxInfo.wzcx_sfwz == 1){
						addWzcxRecordList(json.retWzcxRecord, wzcx_car_num);
					}
				}else{
					bootbox.alert("没有查询到对应车牌信息！");
					wzcx_id = null;
				}
			}
		});
	}else{
		bootbox.alert("请填写车牌号！");
	}
}

function addWzcxRecordList(retWzcxRecord, wzcx_car_num){
				var list = retWzcxRecord;
				var len = list.length;
				$("#table_search_task tbody").empty();
				for (var i = 0; i < len; i++) {
					var res = list[i];
					var wzsj = res.record_wzsj;
					wzsj = getDate(wzsj);
					var str;
					if (i % 2 == 0) {
						var j = eval(i + 1);
						str = "<tr class=\"gradeA odd\" role=\"row\">"
								+ "	<td width=\"8%\">" + j + "</td>"
								+ "	<td width=\"15%\">"
								+ wzsj + "</td>"
								+ "	<td width=\"10%\">"
								+ res.record_wzdd + "</td>"
								+ " <td width=\"25%\">"
								+ res.record_wzyy + "</td>"
								+ "	<td width=\"10%\">";
								if(res.record_kf == null){
									str +=  "</td>";
								}else{
									str += res.record_kf+ "</td>";
								}
								
								str += "<td width=\"10%\">" + res.record_fk
								+ "</td>" 
								+ "<td width=\"10%\">" + getType(res.record_wzzt)
								+ "</td>" 
								+ "	<td width=\"20%\"> "
								+ " <input record_id='"
								+ res.record_id
								+ "' type=\"button\" class='btn btn-success btn-sm modify' font color=\"red\" value=\"修  改\" id='modify'/>"
								+ "&nbsp <input record_id='"
								+ res.record_id
								+ "' type=\"button\" class='btn btn-danger btn-sm delete' font color=\"red\" value=\"删  除\" id='delete'/>"
								+ "</td>  </tr>";
					} else {
						var j = eval(i + 1);
						str = "<tr class=\"gradeA even\" role=\"row\">"
							+ "	<td width=\"8%\">" + j + "</td>"
								+ "	<td width=\"15%\">"
								+ wzsj + "</td>"
								+ "	<td width=\"10%\">"
								+ res.record_wzdd + "</td>"
								+ " <td width=\"25%\">"
								+ res.record_wzyy + "</td>"
								+ "	<td width=\"10%\">";
								if(res.record_kf == null){
									str +=  "</td>";
								}else{
									str += res.record_kf+ "</td>";
								}
								
								str += "<td width=\"10%\">" + res.record_fk
								+ "</td>" 
								+ "<td width=\"10%\">" + getType(res.record_wzzt)
								+ "</td>" 
								+ "	<td width=\"20%\"> "
								+ " <input record_id='"
								+ res.record_id
								+ "' type=\"button\" class='btn btn-success btn-sm modify' font color=\"red\" value=\"修  改\" id='modify'/>"
								+ "&nbsp <input record_id='"
								+ res.record_id
								+ "' type=\"button\" class='btn btn-danger btn-sm delete' font color=\"red\" value=\"删  除\" id='delete'/>"
								+ "</td>  </tr>";
				 }
					$("#table_search_task tbody").append(str);
				}

				$('.modify')
						.click(
								function() {
									var record_id = $(this).attr(
											"record_id");
									getWzcxRecord(record_id);
									bootbox
											.dialog({
												title : "修改违章记录信息：",
												message : 
											"<div>"
											+"<div class=\"row\">"
												+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
													+"<label class=\"col-sm-1 control-label\""
														+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">违章时间："
													+"</label>"
													+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
														+"<input type=\"text\" id=\"modifyWzsj\" onClick=\"WdatePicker({readOnly:true})\" class=\"form-control\">"
													+"</div>"
											    +"</div>"
											+"</div>"
											    
											+"<br>"
											+"<div class=\"row\">"
											    +"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
													+"<label class=\"col-sm-1 control-label\""
														+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">违章地点："
													+"</label>"
													+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
														+"<input type=\"text\" id=\"modifyWzdd\" class=\"form-control\" >"
													+"</div>"
												+"</div>"
											+"</div>"
												
											+"<br>"
											+"<div class=\"row\">"
												+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
													+"<label class=\"col-sm-1 control-label\""
														+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">违章原因："
													+"</label>"
													+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
														+"<textarea type=\"text\" id=\"modifyWzyy\" rows=\"2\" class=\"form-control\" > </textarea>"
													+"</div>"
												+"</div>"
											+"</div>"
											
											+"<br>"
											+"<div class=\"row\">"
												+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
													+"<label class=\"col-sm-1 control-label\""
														+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">扣分："
													+"</label>"
													+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
														+"<input type=\"text\" id=\"modifyKf\" class=\"form-control\" >"
													+"</div>"
												+"</div>"
											+"</div>"
											
											+"<br>"
											+"<div class=\"row\">"
												+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
													+"<label class=\"col-sm-1 control-label\""
														+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">罚款："
													+"</label>"
													+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
														+"<input type=\"text\" id=\"modifyFk\" class=\"form-control\" >"
													+"</div>"
												+"</div>"
											+"</div>"
											
											+"<br>"
											+"<div class=\"row\">"
												+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
													+"<label class=\"col-sm-1 control-label\""
														+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">违章状态："
													+"</label>"
													+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
														+ "<select id=\"modifyWzzt\" class='form-control'>"
														+ "<option value=\"0\" >未处理</option>"
														+ "<option value=\"1\" >已处理</option>"
														+ "</select>"
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
															var data = {};
															data.record_id = record_id;
															data.record_wzsj = $("#modifyWzsj").val();
															data.record_wzdd = $("#modifyWzdd").val();
															data.record_wzyy = $("#modifyWzyy").val();
															data.record_kf = $("#modifyKf").val();
															data.record_fk = $("#modifyFk").val();
															data.record_wzzt = $("#modifyWzzt").val();
															
															if(!isNullOrEmpty(data.record_wzsj) && !isNullOrEmpty(data.record_wzdd) 
															&& !isNullOrEmpty(data.record_wzyy) && !isNullOrEmpty(data.record_kf)
															&& !isNullOrEmpty(data.record_fk) && !isNullOrEmpty(data.record_wzzt)){
																$.ajax({
																	url : '/InsrWzcx/saveRecord',
																	type : 'POST',
																	data : data,
																	dataType : 'json',
																	success : function(json) {
																		if (json.retCode == 200) {
																			bootbox.alert("修改成功！");
																			refreshWzcx(wzcx_car_num);
																		}else{
																			bootbox.alert("修改信息失败，请稍后再试！");
																		}
																	}
																});
															}else{
																bootbox.alert("请填写完整的违章记录信息！");
															}
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
			});
				        
			$('.delete').click(function(){
				var record_id = $(this).attr("record_id");
				var data = {};
				data.record_id = record_id;
				$.ajax({
					url: '/InsrWzcx/deleteRecord',
					type: 'POST',
					data: data,
					dataType: 'json',
					success: function(json){
						if (json.retCode == 200) {
							bootbox.alert("删除成功！");
							refreshWzcx(wzcx_car_num);
						}else{
							bootbox.alert("删除失败，请稍后再试！");
						}
					}
				});
			});
}

function refreshWzcx(wzcx_car_num){
	if(wzcx_car_num != null && wzcx_car_num != ""){
		var data = {};
		data.wzcx_car_num = wzcx_car_num;
		$("#table_search_task tbody").empty();
		$.ajax({
			url : '/InsrWzcx/searchWzcx',
			type : 'POST',
			dataType : 'json',
			data : data,
			success : function(json) {
				if (json.retCode == 200) {
				var retWzcxRecord = json.retWzcxRecord;
				var retWzcxInfo = json.retWzcxInfo;
					if(retWzcxRecord != null && retWzcxInfo != null && retWzcxInfo.wzcx_sfwz == 1){
						addWzcxRecordList(retWzcxRecord, wzcx_car_num);
					}
				}else{
					bootbox.alert("更新违章记录信息失败！");
				}
			}
		});
	}
}
function getWzcxRecord(record_id) {
	var data = {};
	data.record_id = record_id;
	$.ajax({
		url : '/InsrWzcx/searchWzcxRecord',
		type : 'POST',
		dataType : 'json',
		data : data,
		success : function(json) {
			if (json.retCode == 200) {
				var modify = json.retWzcxRecord;
				$("#modifyWzsj").empty();
				$("#modifyWzdd").empty();
				$("#modifyWzyy").empty();
				$("#modifyKf").empty();
				$("#modifyFk").empty();
				
				$("#modifyWzsj").val(getDate(modify.record_wzsj));
				$("#modifyWzdd").val(modify.record_wzdd);
				$("#modifyWzyy").val(modify.record_wzyy);
				$("#modifyKf").val(modify.record_kf);
				$("#modifyFk").val(modify.record_fk);
				$("#modifyWzzt option[value='"
					+ modify.record_wzzt + "']").attr(
					"selected", true);
			} else {
				bootbox.alert("获得用户信息失败，请联系后台人员修改！");
			}
		}
	});
}

function addWzcxRecord(){
				if(wzcx_id != null){
					bootbox
							.dialog({
								title : "新增违章记录信息：",
								message : 
							"<div>"
							+"<div class=\"row\">"
								+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
									+"<label class=\"col-sm-1 control-label\""
										+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">违章时间："
									+"</label>"
									+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
										+"<input type=\"text\" id=\"modifyWzsj\" onClick=\"WdatePicker({readOnly:true})\" class=\"form-control\">"
									+"</div>"
							    +"</div>"
							+"</div>"
							    
							+"<br>"
							+"<div class=\"row\">"
							    +"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
									+"<label class=\"col-sm-1 control-label\""
										+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">违章地点："
									+"</label>"
									+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
										+"<input type=\"text\" id=\"modifyWzdd\" class=\"form-control\" >"
									+"</div>"
								+"</div>"
							+"</div>"
								
							+"<br>"
							+"<div class=\"row\">"
								+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
									+"<label class=\"col-sm-1 control-label\""
										+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">违章原因："
									+"</label>"
									+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
										+"<textarea type=\"text\" id=\"modifyWzyy\" rows=\"2\" class=\"form-control\" > </textarea>"
									+"</div>"
								+"</div>"
							+"</div>"
							
							+"<br>"
							+"<div class=\"row\">"
								+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
									+"<label class=\"col-sm-1 control-label\""
										+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">扣分："
									+"</label>"
									+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
										+"<input type=\"text\" id=\"modifyKf\" class=\"form-control\" >"
									+"</div>"
								+"</div>"
							+"</div>"
							
							+"<br>"
							+"<div class=\"row\">"
								+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
									+"<label class=\"col-sm-1 control-label\""
										+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">罚款："
									+"</label>"
									+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
										+"<input type=\"text\" id=\"modifyFk\" class=\"form-control\" >"
									+"</div>"
								+"</div>"
							+"</div>"
							
							+"<br>"
							+"<div class=\"row\">"
								+"<div class=\"col-sm-30\" style=\"width: 800px; padding-top: 5px; padding-left: 0px; padding-right: 5px;\">"
									+"<label class=\"col-sm-1 control-label\""
										+"style=\"width: 110px; padding-top: 5px; padding-left: 15px; padding-right: 5px;\">违章状态："
									+"</label>"
									+"<div class=\"col-sm-2\" style=\"width: 450px; padding-left: 0px;\">"
										+ "<select id=\"modifyWzzt\" class='form-control'>"
										+ "<option value=\"0\" >未处理</option>"
										+ "<option value=\"1\" >已处理</option>"
										+ "</select>"
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
											var data = {};
											data.wzcx_id = wzcx_id;
											data.record_wzsj = $("#modifyWzsj").val();
											data.record_wzdd = $("#modifyWzdd").val();
											data.record_wzyy = $("#modifyWzyy").val();
											data.record_kf = $("#modifyKf").val();
											data.record_fk = $("#modifyFk").val();
											data.record_wzzt = $("#modifyWzzt").val();
											
											if(!isNullOrEmpty(data.record_wzsj) && !isNullOrEmpty(data.record_wzdd) 
											&& !isNullOrEmpty(data.record_wzyy) && !isNullOrEmpty(data.record_kf)
											&& !isNullOrEmpty(data.record_fk) && !isNullOrEmpty(data.record_wzzt)){
												$.ajax({
													url : '/InsrWzcx/insertRecord',
													type : 'POST',
													data : data,
													dataType : 'json',
													success : function(json) {
														if (json.retCode == 200) {
															bootbox.alert("添加违章记录成功！");
															refreshWzcx(wzcx_car_num);
														}else{
															bootbox.alert("添加违章记录失败，请稍后再试！");
														}
													}
												});
											}else{
												bootbox.alert("请填写完整的违章记录信息！");
											}
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
					}else{
						bootbox.alert("在违章查询后才能添加违章记录！");
					}
}

function submitWzcx(){
	wzcx_car_num = $("#carNumber").val();
	if(!isNullOrEmpty(wzcx_car_num)){
		var data = {};
		data.wzcx_car_num = wzcx_car_num;
		data.wzcx_status = $("#setWzSelectStatus").val();
		data.wzcx_sfwz = $("#setWzStatus").val();
		if(!isNullOrEmpty(data.wzcx_car_num) && !isNullOrEmpty(data.wzcx_status) && !isNullOrEmpty(data.wzcx_sfwz)){
			$.ajax({
				url: '/InsrWzcx/submitWzcx',
				data: data,
				type: 'POST',
				dataType: 'json',
				success: function(json){
					if (json.retCode == 200) {
						bootbox.alert("提交违章查询成功！");
						wzcx_id = json.wzcx_id;
						refreshWzcx(wzcx_car_num);
					}else{
						bootbox.alert("提交违章查询失败，请稍后再试！");
					}
				}
			});
		}else{
			bootbox.alert("请填写完整的违章记录信息！");
		}
	}
}

function saveWzSelectStatus(){
	var status = $("#setWzSelectStatus").val();
	var data = {};
	data.statusId = status;
	$.ajax({
		url:'/InsrWzcx/updateWzStatus',
		data: data,
		type:'POST',
		dataType:'json',
		success:function(json){
			bootbox.alert(json.retDesc);
		}
	});
}

function getType(type){
	var res = "未处理";
	if(type == 1)
		res = "已处理";
	else if(type == 0){
		res = "未处理";
	}
	return res;
}

function getDate(time){
	
	var createDate = new Date(time);
	var month = createDate.getMonth() + 1;
	month = month < 10 ? "0"+month : month;
	var date = createDate.getDate();
	date = date < 10 ? "0" + date : date;
	var hour = createDate.getHours();
	hour = hour < 10 ? "0" + hour : hour;
	var minute = createDate.getMinutes();
	minute = minute < 10 ? "0" + minute : minute;
	var second = createDate.getSeconds();
	second = second < 10 ? "0" + second : second;
	
	createTime = createDate.getFullYear() + "-" + month + "-" + date;
	return createTime;
}

function isNullOrEmpty(strVal) {
	if (strVal == '' || strVal == null || strVal == undefined) {
	return true;
	} else {
	return false;
	}
}