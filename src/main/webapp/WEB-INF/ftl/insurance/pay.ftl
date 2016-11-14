<#include "../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/date/WdatePicker.js", "/js/insurance/pay.js"]
/>
<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">支付</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        
	        <div  class="form-horizontal">
	        	<div class="row">
	        		<h4 class="page-header">网易宝支付</h4><br>
       				<label class="radio-inline col-sm-1 col-sm-offset-1">
					  <input type="radio" name="pay" id="paySuccess" value="1"> 支付成功
					</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="pay" id="payFailed" value="0"> 支付失败
					</label>
	        	</div>
				
				<div class="row">
				<h4 class="page-header" style="width: 1200px;"></h4>
					<div class="col-lg-2" style="padding-left: 500px;">
            			<button class="btn btn-primary" type="button" onClick="savePay()">确定</button>
            		</div>
				</div>
	       </div>
	        <!-- /.col-lg-12 -->
	   
    </div>
    <!-- #page-wrapper -->

<@htmlNavFoot />
<@htmlFoot/>