<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/insurance/pingan/toAudit.js"]
/>
<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">核保与手机验证</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        
	        <div  class="form-horizontal">
	        	<div class="row" >
	        		<h4 class="page-header">核保信息</h4>
	        		<div class="col-lg-12">
						<label class="radio-inline" style="padding-left: 100px;">
						  <input type="radio" name="audit" class="audit" id="auditSuccess" value="C0000"> 核保成功
						</label>
	       				<label class="radio-inline" style="padding-left: 100px;">
						  <input type="radio" name="audit" class="audit" id="auditBothFailed" value="C6001"> 商业险+交强险核保失败
						</label>
						<label class="radio-inline" style="padding-left: 100px;">
						  <input type="radio" name="audit" class="audit" id="auditBizFailed" value="C6002"> 商业险核保失败
						</label>		        	
	        			<label class="radio-inline" style="padding-left: 100px;">
						  <input type="radio" name="audit" class="audit" id="auditJqxFailed" value="C6003"> 交强险核保失败
						</label>
						<label class="radio-inline" style="padding-left: 100px;">
						  <input type="radio" name="audit" id="auditDiffer" value="C0009"> 两次报价不一致
						</label>
	       			</div>
	        	</div>
	        	<br>
	        	<div class="row" >
	        		<h4 class="page-header">手机验证</h4>
       				<div class="col-lg-12">
       					<span style="padding-left: 70px;">是否验证手机：</span>
       					<label class="radio-inline" style="padding-left: 100px;">
						  <input type="radio" name="smsCode" class="smsCode" id="smsCodeYes" value="1"> 是
						</label>
	       				<label class="radio-inline" style="padding-left: 100px;">
						  <input type="radio" name="smsCode" class="smsCode" id="smsCodeNo" value="0"> 否
						</label>
       				</div>
	        	</div>
	        	
	        	<br><br>
	        	<div class="row">
	        		<div class="col-lg-5" style="padding-left: 100px;">
	        			<div class="form-group input-group" id="smsCodeInfo">
                    		<span class="input-group-addon">验证码：</span>
                    		<input id="auditSmsCode" class="form-control" type="text" placeholder="手机验证码"/>
                		</div>
	        		</div>
	        	</div>
								
				<div class="row">
				<h4 class="page-header" style="width: 1200px;"></h4>
					<div class="col-lg-2" style="padding-left: 1030px;">
            			<button class="btn btn-primary" type="button" onClick="submitAudit()">确定</button>
            		</div>
				</div>
	       </div>
	        <!-- /.col-lg-12 -->
	   
    </div>
    <!-- #page-wrapper -->

<@htmlNavFoot />
<@htmlFoot/>