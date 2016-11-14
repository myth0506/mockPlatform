<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js"]
/>

<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">创建报价请求</h1>
            </div>
        </div>
        <br><br>
            
         <div class="row" style="margin-left: 10px;" >
        	<div class="col-lg-12">
             	  <label class="control-label" for="formGroupInputLarge">mock开关：</label>
				  <label style="padding-left: 100px;">
				    <input type="radio" name="mockSwitch" id="mockYes" value="1" checked="checked">打开
				  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  <label style="padding-left: 100px;">
				    <input type="radio" name="mockSwitch" id="mockNo" value="0">关闭
				  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
        </div>
  
    </div>

<@htmlNavFoot />
<@htmlFoot/>