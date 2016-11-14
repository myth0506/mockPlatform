<#include "../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/date/WdatePicker.js", "/js/insurance/charge.js"]
/>
<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">充值</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        
	        <div class="form-horizontal">
	        	<h4 class="page-header">加油卡充值</h4><br>
	        	<div class="row">
	        		<label class="radio-inline col-sm-1" style="margin-left: 100px;">
	        			<strong>卡号信息：</strong>
	        		</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="cardInfo" id="jimingka" value="1"> 记名卡
					</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="cardInfo" id="bujimingka" value="2"> 不记名卡
					</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="cardInfo" id="fuka" value="3"> 副卡
					</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="cardInfo" id="kahaobucunzai" value="4"> 卡号不存在
					</label>
				</div><br>
				<div class="row">
					<label class="radio-inline col-sm-1" style="margin-left: 100px;">
	        			<strong>充值金额：</strong>
	        		</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="charge" id="yibai" value="100"> 100元
					</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="charge" id="erbai" value="200"> 200元
					</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="charge" id="wubai" value="500"> 500元
					</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="charge" id="yiqian" value="1000"> 1000元
					</label>
					<label class="radio-inline col-sm-1">
					  <input type="radio" name="charge" id="erqian" value="2000"> 2000元
					</label>
					<label class="radio-inline col-sm-1">
					  <input type="radio" name="charge" id="sanqian" value="3000"> 3000元
					</label>
				</div><br>
				<div class="row">
					<label class="radio-inline col-sm-1" style="margin-left: 100px;">
	        			<strong>充值结果：</strong>
	        		</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="chargeRes" id="charging" value="0"> 充值中
					</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="chargeRes" id="chargeSuccess" value="1"> 充值成功
					</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="chargeRes" id="chargeFailed" value="9"> 充值失败
					</label>
				</div>
	        </div>
	        
			<div class="row">
			<h4 class="page-header" style="width: 1200px;"></h4>
				<div class="col-lg-2" style="padding-left: 65%;">
        			<button class="btn btn-primary" type="button" onClick="saveCharge()">确定</button>
        		</div>
			</div>
	      </div>
	        <!-- /.col-lg-12 -->
	   
    </div>
    <!-- #page-wrapper -->

<@htmlNavFoot />
<@htmlFoot/>