<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/insurance/yangguang/jqxbj.js", "/js/common/bootbox.js"]
/>

<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">交强险报价</h1>
            </div>
        </div>
            <br><br>
            <div class="row">
	            <div class="col-lg-12">
					<div class="form-group input-group col-lg-3">
			        	<span class="input-group-addon">失败原因</span>
						<input type="text" id="jqxFail" class="form-control">
					</div>
				</div>
			</div>
										
			<div class="row">
				<div class="col-lg-12">
					<table class="table table-striped table-bordered"
						id="table_search_task" style="width:400px; table-layout:fixed;">
						<thead>
							<tr>
								<th>险种</th>
								<th>价格</th>
							</tr>
							<tr>
								<td>交强险</td>
								<td>
								    <div class="form-inline form-group">
	                    			<input class="form-control" id="jqxjg" type="text" value="950" style="width:120px"/>
	                    			</div>
								</td>
							</tr>
							<tr>
								<td>车船税</td>
								<td style="width:270px">
									<div class="form-inline form-group">
	                    			<input class="form-control" id="ccsjg" type="text" value="200" style="width:120px"/>
	                    			</div>
								</td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		<br>
		<input class="btn btn-primary" type="button"  onClick="submitjqxbj()" value="确定"/>
    </div>

<@htmlNavFoot />
<@htmlFoot/>