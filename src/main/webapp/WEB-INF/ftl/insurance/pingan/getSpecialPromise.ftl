<#include "../../inc/core.ftl">
<@htmHead title="保险Mock平台" 
otherJs=["/js/common/bootbox.js", "/js/insurance/pingan/getSpecialPromise.js"]
/>
<@htmlNavHead /> 
	<div id="page-wrapper">
    	<div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">获取特别约定</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        
	        <div  class="form-horizontal">
	        	<div class="row" >
	        		<h4 class="page-header">商业险特别约定</h4>
	        		<div class="col-lg-6">
       				<textarea class="form-control" type="text" id="bizSpecialPromise" rows="8" placeholder="输入商业险特别约定"></textarea>
       				</div>
	        	</div>
	        	
	        	<div class="row" >
	        		<h4 class="page-header">交强险特别约定</h4>
       				<div class="col-lg-6">
       				<textarea class="form-control" type="text" id="jqxSpecialPromise" rows="8" placeholder="输入交强险特别约定"/></textarea>
       				</div>
	        	</div>
				
				<div class="row">
				<h4 class="page-header" style="width: 800px;"></h4>
					<div class="col-lg-2" style="padding-left: 750px;">
            			<button class="btn btn-primary" type="button" onClick="submitSpecialPromise()">确定</button>
            		</div>
				</div>
	       </div>
	        <!-- /.col-lg-12 -->
	   
    </div>
    <!-- #page-wrapper -->

<@htmlNavFoot />
<@htmlFoot/>