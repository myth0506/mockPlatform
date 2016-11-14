<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/insurance/pingan/xbsr.js", "/js/common/bootbox.js", "/js/date/WdatePicker.js"]
/>

<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">续保用户</h1>
            </div>
        </div>
            <br><br>
  
            <div class="row">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">车牌号码</span>
                    		<input type="text" id="vehicleNo" class="form-control" placeholder="请输入车牌号码">
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="saveVehiclNo()">确定</button>
				</div>
			</div>
			<br/>
            <div class="row">
            	<div class="col-lg-12">
	                <div class="col-lg-2">
	                 	<h4><label class="control-label" for="formGroupInputLarge">是否是续保用户：</label></h4>
	                </div>
	                <div class="col-lg-6">
	                	<div class="Radio" id="xbyhRadio">
						  <label>
						    <input type="radio" name="optionsRadios" id="xbyhYes" value="1" checked>是
						  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  <label>
						    <input type="radio" name="optionsRadios" id="xbyhNo" value="0" checked>否
						  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  <button class="btn btn-primary" type="button" onClick="submitXbyh()">确定</button>
						</div>
	                </div>
                </div>
            </div>
            
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">生日校验</h1>
            </div>
        </div>
            <div class="row">
	            <div class="col-lg-8">
	            	<div class="col-lg-6">
	        			<div class="form-group input-group">
							<span class="input-group-addon">校验生日：</span>
                    		<input id="birthday" class="form-control" type="text" placeholder="输入格式：日月 XXXX(如 1月12日写成 0112)"/>
                		</div>
                	</div>
            		<div class="col-lg-2">
            			<button class="btn btn-primary" type="button" onClick="submitBirthday()">确定</button>
            		</div>
                </div>
            </div>
            
        </div>
    </div>

<@htmlNavFoot />
<@htmlFoot/>