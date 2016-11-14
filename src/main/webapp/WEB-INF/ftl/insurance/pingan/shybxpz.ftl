<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/date/WdatePicker.js", "/js/insurance/pingan/shybxpz.js"]
/>
<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">商业险报价配置</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
	        <div  class="form-horizontal">
	        	<div class="form-group">
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">商业险原价</span>
							<input type="text" id="originalPremium" value="${originalPremium!'' }" class="form-control">
						</div>
					</div>
	        	</div>
	        	<div class="form-group" >
					<h4>车辆损失险</h4>
					<div class="col-sm-6">
						<div class="form-group input-group">
				        	<span class="input-group-addon">默认保额</span>
							<input type="text" id="defaultAmount01" value="${defaultAmount01!'' }" class="form-control">
				        	<span class="input-group-addon">保额可变区间下限</span>
							<input type="text" id="lowAmount01" value="${lowAmount01!'' }" class="form-control">
				        	<span class="input-group-addon">保额可变区间上限</span>
							<input type="text" id="heighAmount01" value="${heighAmount01!'' }" class="form-control">
						</div>
					</div>
               		<div class="col-sm-4">
	               		<div class="form-group input-group">
	               			<span class="input-group-addon">投保</span>
	                   		<input type="text" id="amount01" value="${amount01!'' }" class="form-control">
							<span class="input-group-addon">自定义</span>
	                   		<input type="text" id="zidingyiAmount01" value="${zidingyiAmount01!'' }" class="form-control">
	               		</div>
               		</div>
               	</div>
	        	<div class="form-group" >
					<h4>第三者责任险
						<button class="btn btn-primary" type="button" onclick="newAmount02()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="amount02Table">
								<thead>
									<tr>
										<th>文案</th>
										<th>保额</th>
										<th>保费</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
               	</div>
                <div class="form-group" >
					<h4>车上人员责任险（司机）
						<button class="btn btn-primary" type="button" onclick="newAmount04()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="amount04Table">
								<thead>
									<tr>
										<th>文案</th>
										<th>保额</th>
										<th>保费</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
               	</div>
                <div class="form-group" >
					<h4>车上人员责任险（乘客）
						<button class="btn btn-primary" type="button" onclick="newAmount05()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="amount05Table">
								<thead>
									<tr>
										<th>文案</th>
										<th>保额</th>
										<th>保费</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
               	</div>
               	 <div class="form-group" >
					<h4>车辆划痕责任险
						<button class="btn btn-primary" type="button" onclick="newAmount17()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="amount17Table">
								<thead>
									<tr>
										<th>文案</th>
										<th>保额</th>
										<th>保费</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
               	</div>
               	<div class="form-group" >
					<label class="col-sm-1 control-label">全车盗抢险</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount03" value="${amount03!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-1 control-label">玻璃单独破碎险</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">国产玻璃</span>
							<input type="text" id="gcAmount08" value="${gcAmount08!'' }" class="form-control">
				        	<span class="input-group-addon">进口玻璃</span>
							<input type="text" id="jkAmount08" value="${jkAmount08!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	<div class="form-group" >
					<label class="col-sm-1 control-label">自然损失险</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount18" value="${amount18!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-1 control-label">不计免赔险（车损）</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount27" value="${amount27!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	<div class="form-group" >
					<label class="col-sm-1 control-label">不计免赔险（三者）</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount28" value="${amount28!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-1 control-label">不计免赔险（盗抢）</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount48" value="${amount48!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	<div class="form-group" >
					<label class="col-sm-1 control-label">不计免赔险（车上人员）</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount49" value="${amount49!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-1 control-label">不计免赔险（附加险）</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount50" value="${amount50!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	<div class="form-group" >
					<label class="col-sm-1 control-label">涉水驾驶损失险</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount41" value="${amount41!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-1 control-label">指定专修厂特约条款</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount57" value="${amount57!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	<div class="form-group" >
					<label class="col-sm-1 control-label">倒车镜、车灯单独损坏险</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount59" value="${amount59!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-1 control-label">机动车损失保险无法找到第三方特约险</label>
					<div class="col-sm-4">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="amount63" value="${amount63!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	<button class="btn btn-primary" type="button" onClick="saveOtherInsr()">确定</button>
	        </div>
	        <!-- /.col-lg-12 -->
    </div>
    <!-- #page-wrapper -->

<@htmlNavFoot />
<@htmlFoot/>