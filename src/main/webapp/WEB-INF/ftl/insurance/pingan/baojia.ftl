<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/date/WdatePicker.js", "/js/insurance/pingan/baojia.js"]
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
	        	<div class="row" >
	        		<h4 class="page-header">默认套餐</h4>
       				<label class="radio-inline">
					  <input type="radio" name="default" id="defaultRecommand" value="defaultRecommand"> 推荐型套餐
					</label>
       				<label class="radio-inline">
					  <input type="radio" name="default" id="defaultBasic" value="defaultBasic"> 基本型套餐
					</label>
					<label class="radio-inline">
					  <input type="radio" name="default" id="defaultAll" value="defaultAll"> 全包型套餐
					</label>		        	
        			<label class="radio-inline">
					  <input type="radio" name="default" id="defaultRenew" value="defaultRenew"> 续保套餐
					</label>
	        	</div>
	        	
				<div class="row" >
					<h4 class="page-header">返回套餐</h4>
					<div class="form-group" >
						<div class="col-sm-2">
							<label class="checkbox-inline">
							  <input type="checkbox" id="returnCustom"> 自己来定制
							</label>
						</div>
					</div>
					<div class="form-group" >
						<div class="col-sm-2">
				        	<label class="checkbox-inline">
							  <input type="checkbox" id="returnRecommand"> 推荐型套餐
							</label>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" onClick="edit('defaultRecommand')">编辑</button>
						</div>
					</div>
					<div class="form-group" >
						<div class="col-sm-2">
							<label class="checkbox-inline">
							  <input type="checkbox" id="returnBasic"> 基本型套餐
							</label>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" onClick="edit('defaultBasic')">编辑</button>
						</div>
					</div>
					<div class="form-group" >
						<div class="col-sm-2">
							<label class="checkbox-inline">
							  <input type="checkbox" id="returnAll"> 全包型套餐
							</label>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" onClick="edit('defaultAll')">编辑</button>
						</div>
					</div>
					<div class="form-group" >
						<div class="col-sm-2">
							<label class="checkbox-inline">
							  <input type="checkbox" id="returnRenew"> 续保套餐
							</label>
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary" onClick="edit('defaultRenew')">编辑</button>
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