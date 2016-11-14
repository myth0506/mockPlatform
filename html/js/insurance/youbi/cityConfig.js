$(document).ready(function() {
	init();
});

function init(){
	$.ajax({
		url: '/youbi/initCityConfig',
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.cityConfigMock){
				if(json.cityConfigMock){
					$("input[name='mockSwitch'][value='" + json.cityConfigMock +"']").prop("checked",true);
				}
			}
			if(json.cityCode){
				$("#cityCode").val(json.cityCode);
			}
			if(json.provinceCode){
				$("#province_code").val(json.provinceCode);
			}
			if(json.license_prefix){
				$("#license_prefix").val(json.license_prefix);
			}
			$("input[name='carInfo']").each(function(){
				$(this).prop("checked",true);
			});
			if(json.local_car){
				for(var i=0;i<6;i++){
					if(json.local_car.substring(i,i+1) == "0"){
						$("#local_" + (i+1)).prop("checked",false);
					}
				}
			}
			if(json.out_car){
				for(var i=0;i<6;i++){
					if(json.out_car.substring(i,i+1) == "0"){
						$("#out_" + (i+1)).prop("checked",false);
					}
				}
			}
			$("#pic_1").prop("checked",true);
			$("#pic_2").prop("checked",true);
			if(json.need_pic){
				for(var i=0;i<5;i++){
					if(json.need_pic.substring(i,i+1) == "0"){
						$("#pic_" + (i+1)).prop("checked",false);
					}else{
						$("#pic_" + (i+1)).prop("checked",true);
					}
				}
			}
			if(json.force_flag){
				$("input[name='jqxbzw'][value='" + json.force_flag +"']").prop("checked",true);
			}else{
				$("input[name='jqxbzw'][value='1']").prop("checked",true);
			}
			if(json.biz_days){
				$("#biz_days").attr("value",json.biz_days);
			}
			if(json.support_driving_area){
				$("input[name='zdjsqy'][value='" + json.support_driving_area +"']").prop("checked",true);
			}else{
				$("input[name='zdjsqy'][value='0']").prop("checked",true);
			}
			if(json.support_assign_driver){
				$("input[name='zdjsr'][value='" + json.support_assign_driver +"']").prop("checked",true);
			}else{
				$("input[name='zdjsr'][value='0']").prop("checked",true);
			}
			
		}
	});
}

function saveCityCode(){
	
	var cityCode = $("#cityCode").val();
	var isMock = $("input[name='mockSwitch']:checked").val();
	var data = {};
	data.cityCode = cityCode;
	data.isMock = isMock;
	
	$.ajax({
		url: '/youbi/saveCityCode',
		data: data,
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.retCode == 200){
				bootbox.alert("设置城市代码成功！");
			}else{
				bootbox.alert("设置城市代码失败！");
			}
		}
	});
}

function save(){
	var province_code = $("#province_code").val();
	var license_prefix = $("#license_prefix").val();
	var local_vehicle_name = $("#local_1").prop("checked");
	var local_frame_no = $("#local_2").prop("checked");
	var local_engine_no = $("#local_3").prop("checked");
	var local_enroll_date = $("#local_4").prop("checked");
	var local_seat_count = $("#local_5").prop("checked");
	var local_vehicle_weight = $("#local_6").prop("checked");
	var local_car = "";
	if(local_vehicle_name){
		local_car = local_car + "1"; 
	}else{
		local_car = local_car + "0"; 
	}
	if(local_frame_no){
		local_car = local_car + "1"; 
	}else{
		local_car = local_car + "0"; 
	}
	if(local_engine_no){
		local_car = local_car + "1"; 
	}else{
		local_car = local_car + "0"; 
	}
	if(local_enroll_date){
		local_car = local_car + "1"; 
	}else{
		local_car = local_car + "0"; 
	}
	if(local_seat_count){
		local_car = local_car + "1"; 
	}else{
		local_car = local_car + "0"; 
	}
	if(local_vehicle_weight){
		local_car = local_car + "1"; 
	}else{
		local_car = local_car + "0"; 
	}
	
	var out_vehicle_name = $("#out_1").prop("checked");
	var out_frame_no = $("#out_2").prop("checked");
	var out_engine_no = $("#out_3").prop("checked");
	var out_enroll_date = $("#out_4").prop("checked");
	var out_seat_count = $("#out_5").prop("checked");
	var out_vehicle_weight = $("#out_6").prop("checked");
	var out_car = "";
	if(out_vehicle_name){
		out_car = out_car + "1"; 
	}else{
		out_car = out_car + "0"; 
	}
	if(out_frame_no){
		out_car = out_car + "1"; 
	}else{
		out_car = out_car + "0"; 
	}
	if(out_engine_no){
		out_car = out_car + "1"; 
	}else{
		out_car = out_car + "0"; 
	}
	if(out_enroll_date){
		out_car = out_car + "1"; 
	}else{
		out_car = out_car + "0"; 
	}
	if(out_seat_count){
		out_car = out_car + "1"; 
	}else{
		out_car = out_car + "0"; 
	}
	if(out_vehicle_weight){
		out_car = out_car + "1"; 
	}else{
		out_car = out_car + "0"; 
	}
	
	var xszzy = $("#pic_1").prop("checked");
	var xszfy = $("#pic_2").prop("checked");
	var njy = $("#pic_3").prop("checked");
	var sfzsy = $("#pic_4").prop("checked");
	var sfzbm = $("#pic_5").prop("checked");
	var need_pic = "";
	if(xszzy){
		need_pic = need_pic + "1";
	}else{
		need_pic = need_pic + "0";
	}
	if(xszfy){
		need_pic = need_pic + "1";
	}else{
		need_pic = need_pic + "0";
	}
	if(njy){
		need_pic = need_pic + "1";
	}else{
		need_pic = need_pic + "0";
	}
	if(sfzsy){
		need_pic = need_pic + "1";
	}else{
		need_pic = need_pic + "0";
	}
	if(sfzbm){
		need_pic = need_pic + "1";
	}else{
		need_pic = need_pic + "0";
	}
	
	var force_flag = $("input[name='jqxbzw']:checked").val();
	
	var support_driving_area = $("input[name='zdjsqy']:checked").val();
	var support_assign_driver = $("input[name='zdjsr']:checked").val();
	
	var biz_days = $("#biz_days").val();
	
	var data = {};
	data.province_code = province_code;
	data.license_prefix = license_prefix;
	data.local_car = local_car;
	data.out_car = out_car;
	data.need_pic = need_pic;
	data.force_flag =force_flag;
	data.biz_days = biz_days;
	data.support_driving_area = support_driving_area;
	data.support_assign_driver = support_assign_driver;
	
	$.ajax({
		url: '/youbi/saveCityConfig',
		data: data,
		type: 'POST',
		dataType: 'json',
		success: function(json){
			if(json.retCode == 200){
				bootbox.alert("设置城市配置成功！");
			}else{
				bootbox.alert("设置城市配置失败！");
			}
		}
	});
	
}