<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/date/WdatePicker.js", "/js/insurance/youbi/syxbj.js"]
/>
<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">商业险配置</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
	        <div  class="form-horizontal">
	        	
               	<h4>&nbsp
					<button class="btn btn-primary" type="button" onclick="newBusi()">新增险种</button>
				</h4>
				<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="car01Table">
								<thead>
									<tr>
										<th>名称</th>
										<th>code</th>
										<th width=30%>操作</th>									
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
					<br></br><br></br>
				<div class="col-sm-6">
				</div>
				
				
				
				
	        	<div class="form-group" >
					<h4>&nbsp&nbsp&nbsp车辆损失险						
					</h4>
					<h4>
			&nbsp&nbsp&nbsp<button class="btn btn-primary" type="button" onclick="newCar01()">新增</button>
					</h4>
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="car02Table">
								<thead>
									<tr>
										<th>文案</th>
										<th>保额</th>
										<th>保费</th>
										<th>是否有效</th>
										<th>是否可投</th>
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
					<h4>&nbsp&nbsp&nbsp车身划痕险
					</h4>
					<h4>
			&nbsp&nbsp&nbsp<button class="btn btn-primary" type="button" onclick="newCar02()">新增</button>
					</h4>
					
					
					<div class="col-sm-6">
						<div>
							<table class="table table-striped table-bordered"
								id="car03Table">
								<thead>
									<tr>
										<th>文案</th>
										<th>保额</th>
										<th>保费</th>
										<th>是否有效</th>
										<th>是否可投</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
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