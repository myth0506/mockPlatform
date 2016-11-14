<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js"]
/>

<@htmlNavHead /> 
<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">输入车型</h1>
            </div>
        </div>
            <br><br>
  
            <div class="row">
            	<div class="col-lg-12">
	                <div class="col-lg-6">   
	                	<label class="control-label" for="formGroupInputLarge">mock开关：</label>
						  <label>
						    <input type="radio" name="optionsRadios" id="srcxMockYes" value="1" checked>打开
						  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  <label>
						    <input type="radio" name="optionsRadios" id="srcxMockNo" value="0" checked>关闭
						  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;					
	                </div>
                </div>
            </div>


<@htmlNavFoot />
<@htmlFoot/>