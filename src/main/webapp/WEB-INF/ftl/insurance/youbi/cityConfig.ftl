<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/insurance/youbi/cityConfig.js", "/js/common/bootbox.js"]
/>

<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">获取城市配置</h1>
            </div>
        </div>
            <br><br>
            
             <div class="row" style="margin-left: 10px;" >
            	<div class="col-lg-12">
	             	  <label class="control-label" for="formGroupInputLarge">mock开关：</label>
					  <label style="padding-left: 100px;">
					    <input type="radio" name="mockSwitch" id="mockYes" value="1">打开
					  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  <label style="padding-left: 100px;">
					    <input type="radio" name="mockSwitch" id="mockNo" value="0">关闭
					  </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </div>
  
            <div class="row" style="margin-top: 30px;">
            	<div class="col-lg-12">
					<div  class="col-lg-3">
						<div class="form-group input-group">
							<span class="input-group-addon">城市代码</span>
                    		<input type="text" id="cityCode" class="form-control" placeholder="请输入城市代码">
                		</div>
                	</div>
                	<button class="btn btn-primary" type="button" onClick="saveCityCode()">确定</button>
				</div>
			</div>
			<div class="row">
        		<h4 class="page-header">基本信息：</h4>
        		<br>
        		<div class="col-lg-5" style="padding-left: 30px;">
        			<div class="form-group input-group">
						<span class="input-group-addon">省份代码：</span>
						<input type="text" id="province_code" class="form-control">
						<span class="input-group-addon">车牌前缀：</span>
						<input type="text" id="license_prefix" class="form-control">
            		</div>
        		</div>
        	</div>
        	
        	<div class="row">
        		<h4 class="page-header">车型输入所需参数：</h4>
        		<br>
        		<div class="col-lg-7" style="padding-left: 30px;margin-top:-20px;">
        			<h5>本地车：</h5> 
        			<div class="form-group input-group">
    					<label class="checkbox-inline">
						  <input type="checkbox" id="local_1" name="carInfo"> 车辆品牌型号
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="local_2" name="carInfo"> 车架号
						</label>
    					<label class="checkbox-inline">
						  <input type="checkbox" id="local_3" name="carInfo"> 发动机号
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="local_4" name="carInfo"> 初登日期
						</label>
    					<label class="checkbox-inline">
						  <input type="checkbox" id="local_5"  name="carInfo"> 核定载客
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="local_6"  name="carInfo"> 整备质量
						</label>
            		</div>
        			<h5>外地车：</h5> 
        			<div class="form-group input-group">
    					<label class="checkbox-inline">
						  <input type="checkbox" id="out_1" name="carInfo"> 车辆品牌型号
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="out_2" name="carInfo"> 车架号
						</label>
    					<label class="checkbox-inline">
						  <input type="checkbox" id="out_3" name="carInfo"> 发动机号
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="out_4" name="carInfo"> 初登日期
						</label>
    					<label class="checkbox-inline">
						  <input type="checkbox" id="out_5" name="carInfo"> 核定载客
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="out_6" name="carInfo"> 整备质量
						</label>
            		</div>
        		</div>
        	</div>
        	
        	<div class="row">
        		<h4 class="page-header">投保需要的照片列表：</h4>
        		<br>
        		<div class="col-lg-5" style="padding-left: 30px;margin-top:-20px;">
        			<div class="form-group input-group">
    					<label class="checkbox-inline">
						  <input type="checkbox" id="pic_1" name="needPic" checked="checked"> 行驶证主页图
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="pic_2" name="needPic" checked="checked"> 行驶证附页
						</label>
    					<label class="checkbox-inline">
						  <input type="checkbox" id="pic_3" name="needPic" > 年检页
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" id="pic_4" name="needPic"> 身份证首页
						</label>
    					<label class="checkbox-inline">
						  <input type="checkbox" id="pic_5" name="needPic"> 身份证背面
						</label>
            		</div>
        		</div>
        	</div>
        	
        	
        	<div class="row">
        		<h4 class="page-header">城市配置：</h4>
        		<br>
        		<div class="col-lg-12" style="padding-left: 30px;margin-top:-20px;">
						<span>交强险标志位：</span>
						<label class="checkbox-inline" style="padding-left: 100px;">
						   <input type="radio"  class="policyStatus" name="jqxbzw" value="0"> 不可投保
						</label>
						<label class="checkbox-inline" style="padding-left: 30px;">
						   <input type="radio" class="policyStatus" name="jqxbzw" value="1"> 交强险/车船税
						</label>
						<label class="checkbox-inline" style="padding-left: 30px;">
						   <input type="radio"  class="policyStatus" name="jqxbzw" value="2"> 交强险
						</label>
						<label class="checkbox-inline" style="padding-left: 30px;">
						   <input type="radio"  class="policyStatus" name="jqxbzw" value="3"> 车船税
						</label><br><br>
						<span>商业险提前投保天数：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="biz_days" style="width:100px;" value="90">
						</span><br><br>
						<span>指定驾驶区域：</span>
						<label class="checkbox-inline" style="padding-left: 100px;">
						   <input type="radio" name="zdjsqy" class="policyStatus" value="0"> 不支持
						</label>
						<label class="checkbox-inline" style="padding-left: 100px;">
						   <input type="radio" name="zdjsqy" class="policyStatus" value="1"> 支持
						</label><br><br>
						<span>指定驾驶人&nbsp;&nbsp;&nbsp;：</span>
						<label class="checkbox-inline" style="padding-left: 100px;">
						   <input type="radio" name="zdjsr" class="policyStatus" value="0"> 不支持
						</label>
						<label class="checkbox-inline" style="padding-left: 100px;">
						   <input type="radio" name="zdjsr" class="policyStatus" value="1"> 支持
						</label><br><br>
        		</div>
        	</div>
        	
        	<div class="row">
        		<div class="col-lg-12" style="padding-left: 600px;padding-bottom: 50px;">
        			<button class="btn btn-primary" type="button" onClick="save()">确定</button>
                </div>
        	</div>
        	
        	
        </div>
    </div>

<@htmlNavFoot />
<@htmlFoot/>