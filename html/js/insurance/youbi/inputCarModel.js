function submitSrcx(){
	var isSrcx = $('#srcxRadio input:radio:checked').val();
	data = {};
	data.isSrcx = isSrcx;
	$.ajax({
		url:'/InsrSrcx/vehicleInput',
		date:data,
		type:'POST',
		datatype:'json',
		success:function(json){
			if(json.retCode == 200){
				bootbox.alert("mock开关打开成功！");
		    }else{
		    	bootbox.alert("mock开关打开失败！");
		    }
		}	
	});
}
