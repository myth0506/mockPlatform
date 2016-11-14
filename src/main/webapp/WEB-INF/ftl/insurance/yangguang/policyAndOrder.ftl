<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/insurance/yangguang/policyAndOrder.js"]
/>
<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">支付检查与出单</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        
	        <div  class="form-horizontal">
	        	<div class="row" >
	        		<h4 class="page-header">支付检查</h4><br>
	        		<div class="col-lg-12">
						<label class="checkbox-inline" style="padding-left: 100px;">
						  <input type="radio" name="policyStatus" class="policyStatus" id="paySuccess" value="0"> 成功
						</label>
						<label class="checkbox-inline" style="padding-left: 100px;">
						  <input type="radio" name="policyStatus" class="policyStatus" id="paying" value="2"> 确认中
						</label>
	       				<label class="checkbox-inline" style="padding-left: 100px;">
						  <input type="radio" name="policyStatus" class="policyStatus" id="payFailed" value="1"> 失败
						</label>
	       			</div>
	        	</div>
	        	
	        	<br><br>
	        	<div class="row" >
	        		<h4 class="page-header">出单信息</h4>
	        		<br>
	        		<div class="col-lg-5" style="padding-left: 130px;">
	        			<div class="form-group input-group">
							<span class="input-group-addon">订单返回状态：</span>
							<select id="setOrderStatus" class="form-control">
	                    		<option value="0"> 全部成功</option>
	                    		<option value="1"> 全部失败</option>
                    		</select>
                    		<!--<select id="setOrderStatus" class="form-control">
                    			<#if orderStatusList ??>
		                    		<#list orderStatusList as orderStatus>
		                    			<option value="${orderStatus_index!''}"> ${orderStatus!''}</option>
		                    		</#list>
		                    	</#if>
                    		</select>-->
                		</div>
	        		</div>
	        	</div>
				
				<div class="row">
				<h4 class="page-header" style="width: 1200px;"></h4>
					<div class="col-lg-2" style="padding-left: 650px;">
            			<button class="btn btn-primary" type="button" onClick="submitPolicyAndOrder()">确定</button>
            		</div>
				</div>
	       </div>
	        <!-- /.col-lg-12 -->
	   
    </div>
    <!-- #page-wrapper -->

<@htmlNavFoot />
<@htmlFoot/>