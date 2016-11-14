<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/insurance/yangguang/zaiBei.js", "/js/common/bootbox.js", "/js/date/WdatePicker.js"]
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
            	<div id="sci" class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">基础信息接口</span>
            				<select id="setSearchCarInfo" class="form-control" onchange="changeDelayTime('sciyg','setSearchCarInfo','searchCarInfoDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
            				</select>
                		</div>
                	</div>
					<div class="col-lg-3" id="sciyg-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="searchCarInfoDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('searchCarInfo')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div id="gbj" class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">获取报价接口</span>
            				<select id="getBaoJia" class="form-control" onchange="changeDelayTime('gbjyg','getBaoJia','getBaoJiaDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
            				</select>
                		</div>
                	</div>
                	<div class="col-lg-3" id="gbjyg-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="getBaoJiaDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('getBaoJia')">设置</button>
				</div>
		</div>
		
		
		<div class="row">
            	<div id="cbj" class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">修改报价接口</span>
            				<select id="changeBaoJia" class="form-control" onchange="changeDelayTime('cbjyg', 'changeBaoJia','changeBaoJiaDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>	
            				</select>
                		</div>
                	</div>
                	<div class="col-lg-3" id="cbjyg-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="changeBaoJiaDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('changeBaoJia')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div id="bcbf" class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">保存保费获取投保礼</span>
            				<select id="baoCunBaoFei" class="form-control" onchange="changeDelayTime('bcbfyg','baoCunBaoFei','baoCunBaoFeiDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
            				</select>
                		</div>
                	</div>
                	<div class="col-lg-3" id="bcbfyg-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="baoCunBaoFeiDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('baoCunBaoFei')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div id="hb" class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">核保请求</span>
            				<select id="heBao" class="form-control" onchange="changeDelayTime('hbyg','heBao','heBaoDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
            				</select>
                		</div>
                	</div>
                	<div class="col-lg-3" id="hbyg-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="heBaoDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('heBao')">设置</button>
				</div>
		</div>
		
		
		<div class="row">
            	<div id="pc" class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">支付校验</span>
            				<select id="payCheck" class="form-control" onchange="changeDelayTime('pcyg','payCheck','payCheckDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
            				</select>
                		</div>
                	</div>
                	<div class="col-lg-3" id="pcyg-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="payCheckDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('payCheck')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div id="cd" class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">出单请求</span>
            				<select id="order" class="form-control" onchange="changeDelayTime('cdyg','order','orderDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
            				</select>
                		</div>
                	</div>
                	<div class="col-lg-3" id="cdyg-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="orderDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('order')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div id='gvc' class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">获取验证码</span>
            				<select id="getVerifyCode" class="form-control" onchange="changeDelayTime('gvcyg','getVerifyCode','getVerifyCodeDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
            				</select>
                		</div>
                	</div>
                	<div class="col-lg-3" id="gvcyg-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="getVerifyCodeDelayTime" class="form-control">
						</div>
					</div>              	
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('getVerifyCode')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div id="svc" class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">保存验证码</span>
            				<select id="saveVerifyCode" class="form-control" onchange="changeDelayTime('svcyg','saveVerifyCode','saveVerifyCodeDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>
            				</select>
                		</div>
                	</div>  
                	<div class="col-lg-3" id="svcyg-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="saveVerifyCodeDelayTime" class="form-control">
						</div>
					</div>               	         	
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('saveVerifyCode')">设置</button>
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
		
		$('#setSearchCarInfo').val('${searchCarInfoZaiBeiYangGuang}');
		$('#searchCarInfoDelayTime').val('${searchCarInfoZaiBeiYangGuangDelayTime!}');
	
		$('#getBaoJia').val('${getBaoJiaZaiBeiYangGuang}');
		$('#getBaoJiaDelayTime').val('${getBaoJiaZaiBeiYangGuangDelayTime!}');		
		$('#changeBaoJia').val('${changeBaoJiaZaiBeiYangGuang}');
		$('#changeBaoJiaDelayTime').val('${changeBaoJiaZaiBeiYangGuangDelayTime!}');		
		$('#baoCunBaoFei').val('${baoCunBaoFeiZaiBeiYangGuang}');
		$('#baoCunBaoFeiDelayTime').val('${baoCunBaoFeiZaiBeiYangGuangDelayTime!}');
		
		$('#heBao').val('${heBaoZaiBeiYangGuang}');
		$('#heBaoDelayTime').val('${heBaoZaiBeiYangGuangDelayTime!}');
		$('#payCheck').val('${payCheckZaiBeiYangGuang}');
		$('#payCheckDelayTime').val('${payCheckZaiBeiYangGuangDelayTime!}');		
		$('#order').val('${orderZaiBeiYangGuang}');
		$('#orderDelayTime').val('${orderZaiBeiYangGuangDelayTime!}');
		
		$('#getVerifyCode').val('${getVerifyCodeZaiBeiYangGuang}');
		$('#getVerifyCodeDelayTime').val('${getVerifyCodeZaiBeiYangGuangDelayTime!}');
		$('#saveVerifyCode').val('${saveVerifyCodeZaiBeiYangGuang}');
		$('#saveVerifyCodeDelayTime').val('${saveVerifyCodeZaiBeiYangGuangDelayTime!}');
		
		$('select').change();
		
</script>
	
<@htmlNavFoot />
<@htmlFoot/>