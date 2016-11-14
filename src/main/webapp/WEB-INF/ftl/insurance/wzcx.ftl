<#include "../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/insurance/wzcx/wzcx.js", "/js/common/bootbox.js", "/js/date/WdatePicker.js"]
/>
<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">违章查询</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        
	        <div  class="col-lg-12">
	        	<div class="row" >
	       	 		<div  class="col-lg-6">
	        			<div class="form-group input-group">
							<span class="input-group-addon">车牌号码</span>
                    		<input id="carNumber" class="form-control" type="text"/>
                		</div>
                	</div>
                	<div class="col-lg-6">
                		<button class="btn btn-primary" type="button" onClick="searchWzcx()">查询</button>
                	</div>
             	</div>
             	   
             	<br>
             	<div class="row" >
             		<div  class="col-lg-6">
						<div class="form-group input-group">
							<span class="input-group-addon">设置查询状态</span>
                    		<select id="setWzSelectStatus" class="form-control"></select>
                		</div>
                	</div>
                </div>
                
                <br>
             	<div class="row" >
             		<div  class="col-lg-6">
						<div class="form-group input-group">
							<span class="input-group-addon">是否有违章</span>
                    		<select id="setWzStatus" class="form-control">
                    		</select>
                		</div>
                	</div>
                </div>
                
                <br>
                <div class="row">
	                <div class="col-lg-6">
	                	<button class="btn btn-primary" type="button" onClick="submitWzcx()">保存设置信息</button>
	                	<button class="btn btn-success" type="button" onClick="addWzcxRecord()">新增违章记录</button>
	                </div>
                </div>
                
                <br>
                <div class="row">
					<div class="col-lg-12">
						<div>
							<table class="table table-striped table-bordered"
								id="table_search_task">
								<thead>
									<tr>
										<th>行号</th>
										<th>违章时间</th>
										<th>违章地点</th>
										<th>违章原因</th>
										<th>扣分</th>
										<th>罚款</th>
										<th>违章状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>    <!--end col-lg-12-->
				</div>
                
	       </div>
	        <!-- /.col-lg-12 -->
	   
    </div>
    <!-- #page-wrapper -->

<@htmlNavFoot />
<@htmlFoot/>