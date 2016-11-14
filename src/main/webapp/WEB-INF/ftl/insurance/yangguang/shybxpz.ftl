<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/date/WdatePicker.js", "/js/insurance/yangguang/shybxpz.js"]
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
					<h4>车辆损失险
						<button class="btn btn-primary" type="button" onclick="newCov_200()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="cov_200Table">
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
					<h4>商业第三者责任险
						<button class="btn btn-primary" type="button" onclick="newCov_600()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="cov_600Table">
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
					<h4>司机座位责任险
						<button class="btn btn-primary" type="button" onclick="newCov_701()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="cov_701Table">
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
					<h4>乘客座位责任险
						<button class="btn btn-primary" type="button" onclick="newCov_702()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="cov_702Table">
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
						<button class="btn btn-primary" type="button" onclick="newCov_210()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="cov_210Table">
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
					<h4>交通事故精神损害赔偿责任险
						<button class="btn btn-primary" type="button" onclick="newCov_640()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="cov_640Table">
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
               	
               	<hr>
               	<div class="form-group" >
					<label class="col-sm-2 control-label">全车盗抢险</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_500" value="${cov_500!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-2 control-label">指定专修厂</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_321" value="${cov_321!'' }" class="form-control">
						</div>
					</div>
				</div>
				<div class="form-group" >
					<label class="col-sm-2 control-label">自燃损失险</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_310" value="${cov_310!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-2 control-label">玻璃单独破碎险</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">国产玻璃</span>
							<input type="text" id="gcCov_231" value="${gcCov_231!'' }" class="form-control">
				        	<span class="input-group-addon">进口玻璃</span>
							<input type="text" id="jkCov_231" value="${jkCov_231!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	<div class="form-group" >
					<label class="col-sm-2 control-label">高速高价救援险</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_390" value="${cov_390!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-2 control-label">发动机特别损失险</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_291" value="${cov_291!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	<div class="form-group" >
					<label class="col-sm-2 control-label">不计免赔险（机动车盗抢险）</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_921" value="${cov_921!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-2 control-label">不计免赔险（车身划痕损失险）</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_922" value="${cov_922!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	<div class="form-group" >
					<label class="col-sm-2 control-label">不计免赔险(车损险)</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_911" value="${cov_911!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-2 control-label">不计免赔险(三者险)</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_912" value="${cov_912!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	<div class="form-group" >
					<label class="col-sm-2 control-label">不计免赔险(司机险)</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_928" value="${cov_928!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-2 control-label">不计免赔险(乘客险)</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_929" value="${cov_929!'' }" class="form-control">
						</div>
					</div>
               	</div>
               	
               	<div class="form-group afterFeiGai">
               		<h4>费改后新增的险种</h4>
					<hr>
					<label class="col-sm-2 control-label">修理期间费用补偿险(cov_734)</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_734" value="${cov_734!'' }" class="form-control">
						</div>
					</div>
					<label class="col-sm-2 control-label">修理期间费用补偿险(cov_731)</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">保额</span>
							<input type="text" id="cov_731" value="${cov_731!'' }" class="form-control" placeholder="50-500元内正整数">
						</div>
					</div>
               	</div>
               	<div class="form-group afterFeiGai">
					<label class="col-sm-2 control-label">修理期间费用补偿险(cov_732)</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">天数</span>
							<input type="text" id="cov_732" value="${cov_732!'' }" class="form-control" placeholder="1-90内正整数">
						</div>
					</div>
					<label class="col-sm-2 control-label">机动车损失保险无法找到第三方特约险</label>
					<div class="col-sm-3">
						<div class="form-group input-group">
				        	<span class="input-group-addon">投保</span>
							<input type="text" id="cov_733" value="${cov_733!'' }" class="form-control">
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