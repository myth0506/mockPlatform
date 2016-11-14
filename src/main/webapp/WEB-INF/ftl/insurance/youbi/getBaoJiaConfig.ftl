<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js","/js/date/WdatePicker.js","/js/insurance/youbi/getBaoJiaConfig.js"]
/>


<@htmlNavHead /> 
<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">获取报价</h1>
            </div>
        </div>
        
        <div class="row">
           <div class="col-lg-12">
	                	<div class="Radio" id="bjRadio">
	                	  <label class="control-label" for="formGroupInputLarge">报价开关：</label>
						  <label>
						    <input type="radio" name="bjRadios" id="bjYes" onclick="setNoVisibility()" value="1" checked>成功
						  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  <label>
						    <input type="radio" name="bjRadios" id="bjNo" onclick="setVisibility()" value="0" checked>失败
						  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  <label>
						  <button class="btn btn-primary" type="button" id ="bjkgan" onclick="savebjxx()">确定</button>
						   </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
                </div>
            </div>
            
            <div class="row">
					<div  class="col-lg-6">`
						<div class="form-group input-group">
							<span class="input-group-addon" id = "jqxsbma"  >报价失败message(报价开关失败时设置)</span>
                    		<input type="text" id="sbjqxsbxx"  class="form-control">
                		</div>
                	</div>
		</div>
		
		<div class="row">
					<div  class="col-lg-6">
						<div class="form-group input-group">
							<span class="input-group-addon" id = "jqxsbxx" >报价失败original_message(报价开关失败时设置)</span>
                    		<input type="text" id="sbjqxsbxxma"  class="form-control">
                		</div>
                	</div>
		</div>
                         
        <div class="row">
           <div class="col-lg-12">
	                	<div class="Radio" id="fgRadio">
	                	<label class="control-label" for="formGroupInputLarge">是否费改：</label>
						  <label>
						    <input type="radio" name="optionsRadios" id="fgYes" value="1" checked>费改
						  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  <label>
						    <input type="radio" name="optionsRadios" id="fgNo" value="0" checked>非费改
						  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
                </div>
            </div>

    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">商业险信息</h1>
            </div>
        </div>
        
		<div class="row" style="margin-bottom:15px;">
			<div class="col-lg-2">
			  	<label class="checkbox-inline">
			    	<input type="radio" name="defaultRadio" id="newbao" value="0"> 新保默认方案
				</label>
			</div>
			<div class="col-lg-2">
				<button type="button" class="btn btn-primary" onClick="edit('newDefault')">编辑</button>
			</div>
		</div>
		
		<div class="row" style="margin-bottom:15px;">
			<div class="col-lg-2">
			  	<label class="checkbox-inline">
			    	<input type="radio" name="defaultRadio" id="xubao" value="1"> 续保默认方案
				</label>
			</div>
			<div class="col-lg-2">
				<button type="button" class="btn btn-primary" onClick="edit('xubaoDefault')">编辑</button>
			</div>
		</div>
		
		<div class="row">
        	<div  class="col-lg-4">
				<div class="form-group input-group">
					<span class="input-group-addon">商业险起保日期</span>
                   	<input type="text" id="syxstart" onClick="WdatePicker({readOnly:true})" class="form-control">
                </div>
            </div>
		</div>
		
		<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">交强险信息</h1>
            </div>
        </div>

         <div class="row">
					<div  class="col-lg-5">
						<div class="form-group input-group">
							<span class="input-group-addon">交强险起保日期</span>
                    		<input type="text" id="jqxstart" onClick="WdatePicker({readOnly:true})" class="form-control">
                		</div>
                	</div>
		</div>
		
		<div class="row">
					<div  class="col-lg-5">
						<div class="form-group input-group">
							<span class="input-group-addon">交强险保费</span>
                    		<input type="text" id="jqxbf"  class="form-control">
                		</div>
                	</div>
		</div>
		
		<div class="row">
					<div  class="col-lg-5">
						<div class="form-group input-group">
							<span class="input-group-addon">车船税</span>
                    		<input type="text" id="ccs"  class="form-control">
                		</div>
                	</div>
		</div>
		
		<div class="row">
					<div  class="col-lg-5">
						<div class="form-group input-group">
							<span class="input-group-addon">交强险去年止保日期</span>
                    		<input type="text" id="jqxend" onClick="WdatePicker({readOnly:true})" class="form-control">
                		</div>
                	</div>
		</div>
		
		<div class="row">
					<div  class="col-lg-5">
						<div class="form-group input-group">
							<span class="input-group-addon">交强险到期天数</span>
                    		<input type="text" id="jqxdqts"  class="form-control">
                		</div>
                	</div>
		</div>
		
		<div class="row">
					<div  class="col-lg-5">
						<div class="form-group input-group">
							<span class="input-group-addon">交强险失败message(报价开关成功时设置)</span>
                    		<input type="text" id="jqxsbxx"  class="form-control">
                		</div>
                	</div>
		</div>
		
		<div class="row">
					<div  class="col-lg-5">
						<div class="form-group input-group">
							<span class="input-group-addon">交强险失败信息(报价开关成功时设置)</span>
                    		<input type="text" id="jqxsbxxma"  class="form-control">
                		</div>
                	</div>
		</div>

       <div class="row">
					<div class="col-lg-12">
	                	<button class="btn btn-primary" type="button" onclick="savebjxx()">确定</button>
	                </div>
				</div>


<@htmlNavFoot />
<@htmlFoot/>