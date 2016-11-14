<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/date/WdatePicker.js", "/js/insurance/yangguang/baojia.js"]
/>
<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">报价接口配置</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        
	        <div  class="form-horizontal">
	        	<div class="row  hidden" >
	        		<h4 class="page-header">默认套餐</h4>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="default" id="defaultLuxury" value="defaultLuxury"> 全险方案
					</label>
       				<label class="radio-inline col-sm-1">
					  <input type="radio" name="default" id="defaultAffordable" value="defaultAffordable"> 经济方案
					</label>
					<label class="radio-inline col-sm-1">
					  <input type="radio" name="default" id="defaultRenewal" value="defaultRenewal"> 续保方案
					</label>		        	
	        	</div>
				<div class="row" >
					<h4 class="page-header">返回套餐</h4>
					<div class="form-group" >
						<div class="col-sm-2">
							<label class="checkbox-inline">
							  <input type="checkbox" id="returnOptional"> 自定义方案
							</label>
						</div>
					</div>
					<div class="form-group" >
						<div class="col-sm-2">
				        	<label class="checkbox-inline">
							  <input type="checkbox" id="returnLuxury"> 全险方案
							</label>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" onClick="edit('returnLuxury')">编辑</button>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2">
							<label class="checkbox-inline">
							  <input type="checkbox" id="returnAffordable"> 经济方案
							</label>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" onClick="edit('returnAffordable')">编辑</button>
						</div>
					</div>
					<div class="form-group" >
						<div class="col-sm-2">
							<label class="checkbox-inline">
							  <input type="checkbox" id="returnRenewal"> 续保方案
							</label>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" onClick="edit('returnRenewal')">编辑</button>
						</div>
					</div>
				</div>
				<div class="row">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">商业险起保日期</span>
                    		<input type="text" id="shyxstart" onClick="WdatePicker({readOnly:true})" class="form-control">
                		</div>
                	</div>
				</div>
				<div class="row">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">交强险起保日期</span>
                    		<input type="text" id="jqxstart" onClick="WdatePicker({readOnly:true})" class="form-control">
                		</div>
                	</div>
				</div>
				<div class="row">
					<div class="col-lg-6">
	                	<button class="btn btn-primary" type="button" onclick="saveBjjk()">提交</button>
	                </div>
				</div>
	       </div>
	        <!-- /.col-lg-12 -->
	   
    </div>
    <!-- #page-wrapper -->

<@htmlNavFoot />
<@htmlFoot/>