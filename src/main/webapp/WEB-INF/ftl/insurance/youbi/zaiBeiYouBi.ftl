<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/insurance/youbi/zaiBeiYouBi.js", "/js/common/bootbox.js", "/js/date/WdatePicker.js"]
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
							<span class="input-group-addon">获取城市配置</span>
            				<select id="getCityConfig" class="form-control" onchange="changeDelayTime('hcp','getCityConfig','cityConfigDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>	                                
            				</select>
                		</div>
                	</div>
                	
                	<div class="col-lg-3" id="hcp-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="cityConfigDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('cityConfig')">设置</button>
				</div>
		          </div> 
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">查询车型</span>
            				<select id="searchCarType" class="form-control" onchange="changeDelayTime('ccx','searchCarType','carTypeDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>               
            				</select>
                		</div>
                	</div>
                	
                	<div class="col-lg-3" id="ccx-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
							<input id="searchCarTypeDelayTime" class="form-control">
						</div>
					</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('searchCarType')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">输入车型</span>
            				<select id="inputCarInfo" class="form-control" onchange="changeDelayTime('sci','inputCarInfo','inputCarInfoDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>              
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3" id="sci-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="inputCarInfoDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('inputCarInfo')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">创建报价请求</span>
            				<select id="createBaoJia" class="form-control" onchange="changeDelayTime('bj','createBaoJia','baoJiaDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>	             
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3" id="bj-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="createBaoJiaDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('createBaoJia')">设置</button>
				</div>
		</div>
		
		
		<div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">获取报价</span>
            				<select id="getBaoJia" class="form-control" onchange="changeDelayTime('hbj','getBaoJia','getBaoJiaDelayTime')">
                    			<option value="success">成功</option>
                    			<option value="delay">延时</option>
                    			<option value="failure">失败</option>	                
            				</select>
                		</div>
                	</div>
                	
                	<div  class="col-lg-3"  id="hbj-delay" style="display: none;">
						<div class="form-group input-group">
							<span class="input-group-addon">延时时长</span>
            				<input id="getBaoJiaDelayTime" class="form-control">                   				                
            				</input>
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="setZaiBei('getBaoJia')">设置</button>
				</div>
		</div>
		
		<div class="row">
            	<div class="col-lg-12">		
            		<button class="btn btn-primary" type="button" onClick="setAllIni()">批量设置</button>			
                	<button class="btn btn-primary" type="button" onClick="setBackUp()">还原设置</button>
				</div>
		</div> 		     
    </div>

<!-- <script type="text/javascript">
		$('#getCityConfig').val('${cityConfigZaiBeiYouBi}');
		$('#cityConfigDelayTime').val('${cityConfigZaiBeiYouBiDelayTime!}');
		$('select').change();
</script> -->
	
<@htmlNavFoot />
<@htmlFoot/>

