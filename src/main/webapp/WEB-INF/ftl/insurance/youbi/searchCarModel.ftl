<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/date/WdatePicker.js", "/js/insurance/youbi/searchCarModel.js"]
/>
<@htmlNavHead />
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">查询车型</h1>
            </div>
        </div>
        <br><br>
        <div class="row" style="margin-left: 10px;">
	        <div class="col-lg-12">
			    <label class="control-label" for="formGroupInputLarge">mock开关：</label>
				<label style="padding-left: 100px;">
					<input type="radio" name="mockSwitch" id="mockYes" value="1" checked>打开
				</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label style="padding-left: 100px;">
					<input type="radio" name="mockSwitch" id="mockNo" value="0" checked>关闭
				</label>
				<button style="margin-left:100px;" class="btn btn-info" onclick="submitMockSwitch()">确定</button>
	        </div>
	        <br></br>
	        <div class="col-lg-12">
			    <label class="control-label" for="formGroupInputLarge">&nbsp;&nbsp;查询结果：</label>
				<label style="padding-left: 100px;">
					<input type="radio" name="searchSwitch" id="searchYes" value="1" checked>查询到车辆信息
				</label>
				<label style="padding-left: 60px;">
					<input type="radio" name="searchSwitch" id="searchNo" value="0" checked>查询不到车辆信息
				</label>
				<button style="margin-left:16px;" class="btn btn-info" onclick="submitSearchSwitch()">确定</button>
	        </div>
        </div>
    </div>

<@htmlNavFoot />
<@htmlFoot/>