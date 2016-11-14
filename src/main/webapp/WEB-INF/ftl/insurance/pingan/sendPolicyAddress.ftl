<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/insurance/pingan/sendPolicyAddress.js"]
/>
<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">获取配送地址</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        
	        <div  class="form-horizontal">
	        	<div class="row" >
	        		<h4 class="page-header">收件人信息</h4>
	        		<br><br><br>
       				<div class="col-lg-12">
			            <div class="col-lg-3 col-md-offset-1">
		        			<div class="form-group input-group">
	                    		<span class="input-group-addon">收件人姓名：</span>
	                    		<input id="addressName" class="form-control" type="text" placeholder="姓名"/>
	                		</div>
	                	</div>
	                	<div class="col-lg-3 col-md-offset-2">
		        			<div class="form-group input-group">
								<span class="input-group-addon">收件人手机：</span>
	                    		<input id="addressMobile" class="form-control" type="text"  placeholder="手机"/>
	                		</div>
		                </div>
	        		</div>
	        	
	        		<br><br><br><br><br><br><br>
	       
	       			<div class="col-lg-12">
	       				<div class="col-lg-8 col-md-offset-1">
		       				<div class="form-group input-group">
								<span class="input-group-addon">省：</span>
	                    		<select id="addressProvinceName" class="form-control" onChange="provinceChanged()" style="width: 150px;">
	                    			<option value="0">请选择</option>
	                    		</select>
	                    		<span class="input-group-addon">市：</span>
	                    		<select id="addressCityName" class="form-control" onChange="cityChanged()" style="width: 150px;">
	                    			<option value="0">请选择</option>
	                    		</select>
	                    		<span class="input-group-addon">区：</span>
	                    		<select id="addressTownCityName" class="form-control" style="width: 150px;">
	                    			<option value="0">请选择</option>
	                    		</select>
	                    		<span class="input-group-addon">详细地址：</span>
	                    		<input id="addressDetail" class="form-control" type="text" placeholder="乡镇、街道详细地址"/>
	                		</div>
	                	</div>
	       			</div>
				
				<br><br><br><br>
				<h4 class="page-header" style="width: 1300px;"></h4>
				<div class="col-lg-2" style="padding-left: 1150px;">
            		<button class="btn btn-primary" type="button" onClick="submitPolicyAddress()">确定</button>
            	</div>
	       </div>
	        <!-- /.col-lg-12 -->
	   
    </div>
    <!-- #page-wrapper -->

<@htmlNavFoot />
<@htmlFoot/>