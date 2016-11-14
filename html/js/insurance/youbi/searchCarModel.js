$(document).ready(function() {
	$.ajax({
		url: '/youbi/getSearchCarModelSwitch',
		type: 'GET',
		dataType: 'json',
		success: function(json){
			if(json.retCode == 200){
				var mockValue = json.result[0];
				$("input[name='mockSwitch'][value='" + mockValue +"']").prop("checked",true);
				var searchValue = json.result[1];
				$("input[name='searchSwitch'][value='" + searchValue +"']").prop("checked",true);
			}else{
				$("input[name='mockSwitch'][value='0']").prop("checked",true);
				$("input[name='searchSwitch'][value='0']").prop("checked",true);
			}
		}
	});
});
function submitMockSwitch(){
	var isMock = $("input[name='mockSwitch']:checked").val();
	var data = {};
	data.isMock = isMock;
	$.ajax({
		url: '/youbi/saveSearchCarModelIsMock',
		data: data,
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.retCode == 200){
				bootbox.alert("设置Mock开关成功！");
			}else{
				bootbox.alert("设置Mock开关失败！");
			}
		}
	});
}
function submitSearchSwitch(){
	var isSearch = $("input[name='searchSwitch']:checked").val();
	var data = {};
	data.isSearch = isSearch;
	$.ajax({
		url: '/youbi/saveSearchCarModelIsSearch',
		data: data,
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.retCode == 200){
				bootbox.alert("设置查询开关成功！");
			}else{
				bootbox.alert("设置查询开关失败！");
			}
		}
	});
}