<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/insurance/pingan/zaiBei.js", "/js/common/bootbox.js", "/js/date/WdatePicker.js"]
/>

<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">灾备测试</h1>
            </div>
        </div>
        <br><br>
  		
  		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">续保校验</span>
            				<select id="setXuBao" class="form-control" onchange="changeDelayTime('xb','setXuBao','xuBaoDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>	                
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div class="col-lg-3" id="xb-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="xuBaoDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('xuBao')">设置</button>
				</div>
		</div> 
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">生日校验</span>
            				<select id="setShengRi" class="form-control" onchange="changeDelayTime('sr','setShengRi','shengRiDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div class="col-lg-3" id="sr-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="shengRiDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('shengRi')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">查询车辆信息</span>
            				<select id="setSearchCarInfo" class="form-control" onchange="changeDelayTime('sci','setSearchCarInfo','searchCarInfoDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3" id="sci-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="searchCarInfoDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('searchCarInfo')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">报价</span>
            				<select id="setBaoJia" class="form-control" onchange="changeDelayTime('bj','setBaoJia','baoJiaDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>	
                    			<option value="session">session失效</option>                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3" id="bj-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="baoJiaDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('baoJia')">设置</button>
				</div>
		</div>
		
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">商业险报价</span>
            				<select id="setBusinessBaoJia" class="form-control" onchange="changeDelayTime('bbj','setBusinessBaoJia','businessBaoJiaDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3"  id="bbj-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="businessBaoJiaDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('businessBaoJia')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">交强险报价</span>
            				<select id="setJiaoQiangBaoJia" class="form-control" onchange="changeDelayTime('jqxbj','setJiaoQiangBaoJia','jiaoQiangBaoJiaDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3" id="jqxbj-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="jiaoQiangBaoJiaDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('jiaoQiangBaoJia')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">保存报价信息</span>
            				<select id="setSaveBaoJiaoInfo" class="form-control" onchange="changeDelayTime('sbj','setSaveBaoJiaoInfo','saveBaoJiaInfoDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3"  id="sbj-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="saveBaoJiaInfoDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('saveBaoJiaInfo')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">获取配送地址</span>
            				<select id="getAddress" class="form-control" onchange="changeDelayTime('ga','getAddress','getAddressDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3"  id="ga-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="getAddressDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('getAddress')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">获取特别约定</span>
            				<select id="getSpecialPromise" class="form-control" onchange="changeDelayTime('gsp','getSpecialPromise','getSpecialPromiseDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3"  id="gsp-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="getSpecialPromiseDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('getSpecialPromise')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">手机验证</span>
            				<select id="phoneAssert" class="form-control" onchange="changeDelayTime('pa','phoneAssert','phoneAssertDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3"  id="pa-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="phoneAssertDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('phoneAssert')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">核保</span>
            				<select id="heBao" class="form-control" onchange="changeDelayTime('hb','heBao','heBaoDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3"  id="hb-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="heBaoDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('heBao')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">支付检查</span>
            				<select id="payCheck" class="form-control" onchange="changeDelayTime('pc','payCheck','payCheckDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3"  id="pc-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="payCheckDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('payCheck')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">出单</span>
            				<select id="order" class="form-control" onchange="changeDelayTime('cd','order','orderDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
                    			<option value="session">session失效</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3"  id="cd-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="orderDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('order')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">		
            		<button class="btn btn-primary" type="button" onClick="setAllIni()">批量设置</button>			
                	<button class="btn btn-primary" type="button" onClick="setBackUp()">还原设置</button>
				</div>
		</div>
		 		     
    </div>

<script type="text/javascript">
		$('#setXuBao').val('${xuBaoZaiBeiPingAn}');
		$('#xuBaoDelayTime').val('${xuBaoZaiBeiPingAnDelayTime!}');
		$('#setShengRi').val('${shengRiZaiBeiPingAn}');
		$('#shengRiDelayTime').val('${shengRiZaiBeiPingAnDelayTime!}');
		$('#setSearchCarInfo').val('${searchCarInfoZaiBeiPingAn}');
		$('#searchCarInfoDelayTime').val('${searchCarInfoZaiBeiPingAnDelayTime!}');
		
		$('#setBaoJia').val('${baoJiaZaiBeiPingAn}');
		$('#baoJiaDelayTime').val('${baoJiaZaiBeiPingAnDelayTime!}');
		$('#setBusinessBaoJia').val('${businessBaoJiaZaiBeiPingAn}');
		$('#businessBaoJiaDelayTime').val('${businessBaoJiaZaiBeiPingAnDelayTime!}');
		$('#setJiaoQiangBaoJia').val('${jiaoQiangBaoJiaZaiBeiPingAn}');
		$('#jiaoQiangBaoJiaDelayTime').val('${jiaoQiangBaoJiaZaiBeiPingAnDelayTime!}');
		
		$('#setSaveBaoJiaoInfo').val('${saveBaoJiaInfoZaiBeiPingAn}');
		$('#saveBaoJiaInfoDelayTime').val('${saveBaoJiaInfoZaiBeiPingAnDelayTime!}');
		$('#getAddress').val('${getAddressZaiBeiPingAn}');
		$('#getAddressDelayTime').val('${getAddressZaiBeiPingAnDelayTime!}');
		$('#getSpecialPromise').val('${getSpecialPromiseZaiBeiPingAn}');
		$('#getSpecialPromiseDelayTime').val('${getSpecialPromiseZaiBeiPingAnDelayTime!}');
		
		$('#phoneAssert').val('${phoneAssertZaiBeiPingAn}');
		$('#phoneAssertDelayTime').val('${phoneAssertZaiBeiPingAnDelayTime!}');
		$('#heBao').val('${heBaoZaiBeiPingAn}');
		$('#heBaoDelayTime').val('${heBaoZaiBeiPingAnDelayTime!}');
		$('#payCheck').val('${payCheckZaiBeiPingAn}');
		$('#payCheckDelayTime').val('${payCheckZaiBeiPingAnDelayTime!}');
		
		$('#order').val('${orderZaiBeiPingAn}');
		$('#orderDelayTime').val('${orderZaiBeiPingAnDelayTime!}');
		
		$('select').change();

</script>
	
<@htmlNavFoot />
<@htmlFoot/>

