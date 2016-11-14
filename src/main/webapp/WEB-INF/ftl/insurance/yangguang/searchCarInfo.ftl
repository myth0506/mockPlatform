<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/insurance/yangguang/searchCarInfo.js", "/js/common/bootbox.js", "/js/date/WdatePicker.js"]
>
<script type="text/javascript">
   $(function () { $('#collapseFour').collapse({
      toggle: false
   })});
   $(function () { $('#collapseTwo').collapse('hide')});
   $(function () { $('#collapseThree').collapse('hide')});
   $(function () { $('#collapseOne').collapse('show')});
</script> 
</@htmHead>

<@htmlNavHead />
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">查询车辆信息</h1>
            </div>
        </div>
            
            <div class="row">
            <div class="col-lg-12">
               
						<div class="panel-group" id="accordion">
						
						   <div class="panel panel-success">
						      <div class="panel-heading">
						         <h4 class="panel-title">
						            <a data-toggle="collapse" data-parent="#accordion" 
						               href="#collapseOne">
						               车主信息 <span class="caret"></span><span class="fa arrow"></span>
						            </a>
						         </h4>
						      </div>
						      <div id="collapseOne" class="panel-collapse collapse in">
						         <div class="panel-body"><br>
						         
						         		<div class="row">
								         	<div class="col-lg-12">
									            <div class="col-lg-4 col-md-offset-2">
								        			<div class="form-group input-group">
														<span class="input-group-addon">车主姓名：</span>
							                    		<input id="registerName" class="form-control" type="text" placeholder="姓名"/>
							                		</div>
							                	</div>
							                	<div class="col-lg-4 col-md-offset-1">
								        			<div class="form-group input-group">
														<span class="input-group-addon">身份证号：</span>
							                    		<input id="registerIdNo" class="form-control" type="text" placeholder="身份证号码"/>
							                		</div>
							                	</div>
						                	</div>
						              </div>
						              
						              <br><br>
						              <div class="row">
								         	<div class="col-lg-12">
									            <div class="col-lg-4 col-md-offset-2">
								        			<div class="form-group input-group">
														<span class="input-group-addon">手机号码：</span>
							                    		<input id="registerMobile" class="form-control" type="text" placeholder="手机号码"/>
							                		</div>
							                	</div>
							                	<div class="col-lg-4 col-md-offset-1">
								        			<div class="form-group input-group">
														<span class="input-group-addon">邮箱地址：</span>
							                    		<input id="registerEmail" class="form-control" type="text" placeholder="邮箱地址"/>
							                		</div>
							                	</div>
						                	</div>
						              </div>
						              
						         </div>
						      </div>
						   </div>
						   
						   <div class="panel panel-info">
						      <div class="panel-heading">
						         <h4 class="panel-title">
						            <a data-toggle="collapse" data-parent="#accordion" 
						               href="#collapseTwo">
						               投保人信息 <span class="caret"></span><span class="fa arrow"></span>
						            </a>
						         </h4>
						      </div>
						      <div id="collapseTwo" class="panel-collapse collapse in">
						         <div class="panel-body"><br>
						         
						         		<div class="row">
								         	<div class="col-lg-12">
								         		<div class="checkbox">
											      <label>
											      	<input type="checkbox" id="applicantCheckBox" onclick="applicantCheckBox()"> 同车主信息
											      </label>
											  	</div>
									            <div class="col-lg-4 col-md-offset-2">
								        			<div class="form-group input-group">
														<span class="input-group-addon">投保人姓名：</span>
							                    		<input id="applicantName" class="form-control" type="text" placeholder="姓名"/>
							                		</div>
							                	</div>
							                	<div class="col-lg-4 col-md-offset-1">
								        			<div class="form-group input-group">
														<span class="input-group-addon">身份证号：</span>
							                    		<input id="applicantIdNo" class="form-control" type="text" placeholder="身份证号码"/>
							                		</div>
							                	</div>
						                	</div>
						              </div>
						              
						              <br><br>
						              <div class="row">
								         	<div class="col-lg-12">
									            <div class="col-lg-4 col-md-offset-2">
								        			<div class="form-group input-group">
														<span class="input-group-addon">手机号码：</span>
							                    		<input id="applicantMobile" class="form-control" type="text" placeholder="手机号码"/>
							                		</div>
							                	</div>
							                	<div class="col-lg-4 col-md-offset-1">
								        			<div class="form-group input-group">
														<span class="input-group-addon">邮箱地址：</span>
							                    		<input id="applicantEmail" class="form-control" type="text" placeholder="邮箱地址"/>
							                		</div>
							                	</div>
						                	</div>
						              </div>
						              
						         </div>
						      </div>
						   </div>
						   
						    <div class="panel panel-danger">
						      <div class="panel-heading">
						         <h4 class="panel-title">
						            <a data-toggle="collapse" data-parent="#accordion" 
						               href="#collapseThree">
						               被投保人信息 <span class="caret"></span><span class="fa arrow"></span>
						            </a>
						         </h4>
						      </div>
						      <div id="collapseThree" class="panel-collapse collapse in">
						         <div class="panel-body"><br>
						         
						         		<div class="row">
								         	<div class="col-lg-12">
								         		<div class="checkbox">
											      <label>
											     	 <input type="checkbox" id="insuredCheckBox" onclick="insuredCheckBox()"> 同车主信息
											      </label>
											  	</div>
									            <div class="col-lg-4 col-md-offset-2">
								        			<div class="form-group input-group">
														<span class="input-group-addon">被投保人姓名：</span>
							                    		<input id="insuredName" class="form-control" type="text" placeholder="姓名"/>
							                		</div>
							                	</div>
							                	<div class="col-lg-4 col-md-offset-1">
								        			<div class="form-group input-group">
														<span class="input-group-addon">身份证号：</span>
							                    		<input id="insuredIdNo" class="form-control" type="text" placeholder="身份证号码"/>
							                		</div>
							                	</div>
						                	</div>
						              </div>
						              
						              <br><br>
						              <div class="row">
								         	<div class="col-lg-12">
									            <div class="col-lg-4 col-md-offset-2">
								        			<div class="form-group input-group">
														<span class="input-group-addon">手机号码：</span>
							                    		<input id="insuredMobile" class="form-control" type="text" placeholder="手机号码"/>
							                		</div>
							                	</div>
							                	<div class="col-lg-4 col-md-offset-1">
								        			<div class="form-group input-group">
														<span class="input-group-addon">邮箱地址：</span>
							                    		<input id="insuredEmail" class="form-control" type="text" placeholder="邮箱地址"/>
							                		</div>
							                	</div>
						                	</div>
						              </div>
						              
						         </div>
						      </div>
						   </div>
						   
						   <div class="panel panel-warning">
						      <div class="panel-heading">
						         <h4 class="panel-title">
						            <a data-toggle="collapse" data-parent="#accordion" 
						               href="#collapsFour">
						               车辆信息 <span class="caret"></span> <span class="fa arrow"></span>
						            </a>
						         </h4>
						      </div>
						      <div id="collapsFour" class="panel-collapse collapse">
						         <div class="panel-body"><br>
						            
						            <div class="row">
								         	<div class="col-lg-12">
									            <div class="col-lg-4 col-md-offset-2">
								        			<div class="form-group input-group">
														<span class="input-group-addon">车架号码：</span>
							                    		<input id="vehicleFrameNo" class="form-control" type="text" placeholder="车架号"/>
							                		</div>
							                	</div>
							                	<div class="col-lg-4 col-md-offset-1">
								        			<div class="form-group input-group">
														<span class="input-group-addon">发动机号：</span>
							                    		<input id="vehicleEngineNo" class="form-control" type="text" placeholder="发动机号"/>
							                		</div>
							                	</div>
						                	</div>
						              </div><br>
						              <div class="row">
								         	<div class="col-lg-12">
									            <div class="col-lg-4 col-md-offset-2">
								        			<div class="form-group input-group">
														<span class="input-group-addon">注册日期：</span>
							                    		<input id="vehicleRegisterDate" class="form-control" type="text" onClick="WdatePicker()" placeholder="注册日期"/>
							                		</div>
							                	</div>
							                	<div class="col-lg-4 col-md-offset-1">
								        			<div class="form-group input-group">
														<span class="input-group-addon">车型号码：</span>
							                    		<input id="vehicleVehicleId" class="form-control" type="text" placeholder="车型号"/>
							                		</div>
							                	</div>
						                	</div>
						              </div><br>
						              <div class="row">
								         	<div class="col-lg-12">
									            <div class="col-lg-4 col-md-offset-2">
								        			<div class="form-group input-group">
														<span class="input-group-addon">是否过户：</span>
							                    			<select id="vehicleSpecialCarFlag" class="form-control" onChange="statusChanged()">
								                    			<option value="1">是</ortion>
								                    			<option value="0">否</ortion>
							                    			</select>
							                		</div>
							                	</div>
							                	<div class="col-lg-4 col-md-offset-1">
								        			<div class="form-group input-group">
														<span class="input-group-addon">过户日期：</span>
							                    		<input id="vehicleSpecialCarDate" class="form-control" type="text" onClick="WdatePicker()" placeholder="过户日期"/>
							                		</div>
							                	</div>
						                	</div>
						              </div><br>
						              <div class="row">
								         	<div class="col-lg-12">
									            <div class="col-lg-4 col-md-offset-2">
								        			<div class="form-group input-group">
														<span class="input-group-addon">品牌型号：</span>
							                    		<input id="vehicleModelName" class="form-control" type="text" placeholder="品牌型号"/>
							                		</div>
							                	</div>
						                	</div>
						              </div>
						            
						         </div>
						      </div>
						   </div>
						</div>
						<br>
						<div class="row">
							<div  class="col-lg-3">
								<div class="form-group input-group">
									<span class="input-group-addon">是否是费改区域</span>
		                    	    <select id="isNpsFlow" class="form-control" style="width: 150px;">
		                    			<option value="1">是</option>
		                    			<option value="0">否</option>
		                    		</select>
		                		</div>
		                	</div>
						</div>
						<div class="col-lg-2">
            				<button class="btn btn-primary" type="button" onClick="submitInfo()">确定</button>
            			</div>
                </div>  <!--  end  class="col-lg-12"-->
            </div>
        
        </div>
    </div>

<@htmlNavFoot />
<@htmlFoot/>