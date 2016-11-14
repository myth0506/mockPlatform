<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/insurance/pingan/policyAndOrder.js"]
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
						  <input type="radio" name="policyStatus" class="policyStatus" id="paySuccess" value="0"> 全部可以支付
						</label>
	       				<label class="checkbox-inline" style="padding-left: 100px;">
						  <input type="radio" name="policyStatus" class="policyStatus" id="payBothFailed" value="1"> 全部不可以支付
						</label>
						<label class="checkbox-inline" style="padding-left: 100px;">
						  <input type="radio" name="policyStatus" class="policyStatus" id="payBizFailed" value="21"> 商业险不能支付
						</label>		        	
	        			<label class="checkbox-inline" style="padding-left: 100px;">
						  <input type="radio" name="policyStatus" class="policyStatus" id="payJqxFailed" value="22"> 交强险不能支付
						</label>
	       			</div>
	        	</div>
	        	
	        	<br><br>
	        	<div class="row" >
	        		<h4 class="page-header">出单信息</h4>
	        		<br>
	        		<div class="col-lg-6" style="padding-left: 130px;">
	        			<div class="form-group input-group">
							<span class="input-group-addon">订单返回状态：</span>
                    		<select id="setOrderStatus" class="form-control">
                    			<#if orderStatusList ??>
		                    		<#list orderStatusList as orderStatus>
		                    			<option value="${orderStatus_index!''}"> ${orderStatus!''}</option>
		                    		</#list>
		                    	</#if>
                    		</select>
                		</div>
	        		</div><br><br><br><br><br>
	        		<div id="orderInfo">
	       				<div class="col-lg-12">
		       				<label class="checkbox-inline" style="padding-left: 100px;">商业险</label>
							<label class="checkbox-inline" style="padding-left: 100px;">
							  <input type="radio" name="bizOrder" class="bizOrder" id="bizOrderSuccess" value="0"> 出单成功
							</label>
		       				<label class="checkbox-inline" style="padding-left: 100px;">
							  <input type="radio" name="bizOrder" class="bizOrder" id="bizOrderFailed" value="1"> 出单失败
							</label>
							<label class="checkbox-inline" style="padding-left: 100px;">
							  <input type="radio" name="bizOrder" class="bizOrder" id="bizOrderNo" value="2"> 未投保
							</label>		        	
			       		</div>
		       			<div class="col-lg-12">
	       					<label class="checkbox-inline" style="padding-left: 100px;">交强险</label>
							<label class="checkbox-inline" style="padding-left: 100px;">
							  <input type="radio" name="jqxOrder" class="jqxOrder" id="jqxOrderSuccess" value="0"> 出单成功
							</label>
		       				<label class="checkbox-inline" style="padding-left: 100px;">
							  <input type="radio" name="jqxOrder" class="jqxOrder" id="jqxOrderFailed" value="1"> 出单失败
							</label>
							<label class="checkbox-inline" style="padding-left: 100px;">
							  <input type="radio" name="jqxOrder" class="jqxOrder" id="jqxOrderNo" value="2"> 未投保
							</label>		        	
		       			</div>
	       			</div>
	        	</div>
				
				<div class="row">
				<h4 class="page-header" style="width: 1200px;"></h4>
					<div class="col-lg-2" style="padding-left: 850px;">
            			<button class="btn btn-primary" type="button" onClick="submitPolicyAndOrder()">确定</button>
            		</div>
				</div>
	       </div>
	        <!-- /.col-lg-12 -->
	   
    </div>
    <!-- #page-wrapper -->

<@htmlNavFoot />
<@htmlFoot/>