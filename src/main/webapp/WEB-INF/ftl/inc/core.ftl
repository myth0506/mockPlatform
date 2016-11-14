<#compress>
<#--
	简化登录判断
	仅仅用于服务器端ftl输出页面使用
	简化cdn版本控制
-->
<#assign cdnFileVersion = versionId!"0"/>
<#--欲定义-->
<#setting locale="zh_CN">
<#setting url_escaping_charset="UTF-8">
<#--
输出js文件 / css文件，含版本号
-->
<#macro jsFile file=[] file2=[]>
<#list file2 as x><script src="${cdnBaseUrl}${x}"></script>
</#list>
<#--<#list file as x><script src="${cdnBaseUrl}${x}?v=${cdnFileVersion}"></script>
</#list>-->
</#macro>
<#macro cssFile file=[] file2=[]>
<#list file2 as x><link rel="stylesheet" href="${cdnBaseUrl}${x}"/>
</#list>
<#--<#list file as x><link rel="stylesheet" href="${cdnBaseUrl}${x}?v=${cdnFileVersion}"/>
</#list>-->
</#macro>

<#--
文档声明/head
支持对head内容项进行修改
-->
<#macro htmHead title="" css=[] otherCss=[] js=[] otherJs=[]>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<title>${title}</title>
	
	<link rel="icon" type="image/x-icon" href="${cdnBaseUrl}/img/favicon.ico">
	<!-- Bootstrap Core CSS -->
    <link href="${cdnBaseUrl}/css/common/bootstrap.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="${cdnBaseUrl}/css/common/metisMenu.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${cdnBaseUrl}/css/common/sb-admin-meteor.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="${cdnBaseUrl}/css/common/font-awesome.css" rel="stylesheet">
    
    <!-- jQuery -->
    <script src="${cdnBaseUrl}/js/common/jquery-1.11.3.js"></script>
    <!-- jQuery cookie -->
    <script src="${cdnBaseUrl}/js/common/jquery.cookie.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="${cdnBaseUrl}/js/common/bootstrap.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="${cdnBaseUrl}/js/common/metisMenu.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="${cdnBaseUrl}/js/common/sb-admin-2.js"></script>
	<@cssFile file=css file2=otherCss/>
	<@jsFile file=js file2=otherJs/>
<#nested>
</head>
<body>
    <!-- navbar -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-brand"  id="logo">
         	<img style="display:inline;" src="/img/logo.png">
        		保险Mock平台
      	</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                    </li>
                    <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->
    </nav>
    <!-- /.navbar-static-side -->
</#macro>

<#macro htmlNavHead isShow=1 activeName="1">
	<#if isShow == 1>
		<!-- /.navbar-static-side -->
	    <div class="navbar-default sidebar" role="navigation">
	        <div class="sidebar-nav navbar-collapse">
	            <ul class="nav" id="side-menu">
	            	<#if activeName == "1">
	            	<li class="active">
	            	<#else>
	                <li>
	                </#if>
	                <li>
	                    <a href="${cdnBaseUrl}/index.html"><i class="glyphicon glyphicon-home"></i> &nbsp;&nbsp;首  页</a>
	                </li>
	                
	                <#if activeName == "2">
	            	<li class="active">
	            	<#else>
	                	<li>
	                </#if>
	                    <a href="${cdnBaseUrl}/insurance/wzcx.html"><i class="glyphicon glyphicon-search"></i> 违章查询</a>
	                </li>
	                <#if activeName == "3">
	            	<li class="active">
	            	<#else>
	                <li>
	                </#if>
	                    <a href="#"><i class="fa fa-edit fa-fw"></i> 平安车险 <span class="fa arrow"></span> </a>
	                     <ul class="nav nav-second-level">
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/pingan/xbsr.html"><i class="fa fa-camera"></i> 续保和生日校验</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/pingan/searchCarInfo.html"><i class="fa fa-image"></i> 车辆信息</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/pingan/baojia.html"><i class="fa fa-shopping-cart"></i> 报价接口</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/pingan/shybxpz.html"><i class="glyphicon glyphicon-phone-alt"></i> 商业险报价</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/pingan/jqxbj.html"><i class="glyphicon glyphicon-phone-alt"></i> 交强险报价</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/pingan/sendPolicyAddress.html"><i class="glyphicon glyphicon-tag"></i> 获取配送地址</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/pingan/getSpecialPromise.html"><i class="glyphicon glyphicon-file"></i> 获取特别约定</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/pingan/toAudit.html"><i class="glyphicon glyphicon-check"></i> 核保与手机验证</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/pingan/policyAndOrder.html"><i class="glyphicon glyphicon-stats"></i> 支付检查和出单</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/pingan/zaiBei.html"><i class="glyphicon glyphicon-stats"></i> 灾备测试</a>
	                        </li>
	                    </ul>
	                </li>
	                <#if activeName == "4">
	            	<li class="active">
	            	<#else>
	                <li>
	                </#if>
	                    <a href="#"><i class="fa fa-wrench fa-fw"></i> 阳光车险<span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/yangguang/xubao.html"><i class="fa fa-camera"></i> 阳光续保</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/yangguang/searchCarInfo.html"><i class="fa fa-image"></i> 车辆信息</a>
	                        </li>
	                         <li>
	                            <a href="${cdnBaseUrl}/insurance/yangguang/shybxpz.html"><i class="glyphicon glyphicon-phone-alt"></i> 商业险报价</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/yangguang/jqxbj.html"><i class="glyphicon glyphicon-phone-alt"></i> 交强险报价</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/yangguang/baojia.html"><i class="fa fa-shopping-cart"></i> 报价接口</a>
	                        </li>
	                         <li>
	                            <a href="${cdnBaseUrl}/insurance/yangguang/toAudit.html"><i class="glyphicon glyphicon-check"></i> 核保与手机验证</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/yangguang/policyAndOrder.html"><i class="glyphicon glyphicon-stats"></i> 支付检查和出单</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/yangguang/zaiBei.html"><i class="glyphicon glyphicon-stats"></i> 灾备测试</a>
	                        </li>
	                    </ul>
	                    <!-- /.nav-second-level -->
	                </li>
	                
	                <#if activeName == "5">
	            	<li class="active">
	            	<#else>
	                <li>
	                </#if>
	                    <a href="#"><i class="fa fa-wrench fa-fw"></i> 优比车险<span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/youbi/cityConfig.html"><i class="fa fa-camera"></i> 获取城市配置</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/youbi/searchCarModel.html"><i class="glyphicon glyphicon-search"></i> 查询车型</a>
	                        </li>
	                         <li>
	                            <a href="${cdnBaseUrl}/insurance/youbi/inputCarModel.html"><i class="glyphicon glyphicon-phone-alt"></i> 输入车型</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/youbi/cjbjqq.html"><i class="glyphicon glyphicon-check"></i> 创建报价请求</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/youbi/getBaoJiaConfig.html"><i class="fa fa-shopping-cart"></i> 获取报价</a>
	                        </li>
	                        <li>
	                            <a href="${cdnBaseUrl}/insurance/youbi/syxbj.html"><i class="glyphicon glyphicon-phone-alt"></i> 商业险报价</a>
	                        </li>
	                         <li>
	                            <a href="${cdnBaseUrl}/insurance/youbi/zaiBeiYouBi.html"><i class="glyphicon glyphicon-stats"></i> 灾备测试</a>
	                        </li>
	                    </ul>
	                    <!-- /.nav-second-level -->
	                </li>
	                 
	                <#if activeName == "charge">
	            		<li class="active">
	            	<#else>
	                	<li>
	                </#if>
	                    <a href="${cdnBaseUrl}/insurance/charge.html"><i class="glyphicon glyphicon-book"></i> &nbsp;&nbsp;充值功能</a>
	                </li>
	                    
	                <#if activeName == "pay">
	            		<li class="active">
	            	<#else>
	                	<li>
	                </#if>
	                    <a href="${cdnBaseUrl}/insurance/pay.html"><i class="glyphicon glyphicon-shopping-cart"></i> &nbsp;&nbsp;支付功能</a>
	                </li>
	                
	                </li>
	            </ul>
	        </div>
	        <!-- /.sidebar-collapse -->
	    </div>
            
	</#if>
</#macro>

<#macro htmlNavFoot isShow=1>
	<#if isShow == 1>
        <footer class="footer">
			<div class="container">
				<p class="text-muted">Powered by Netease QA© 2015- Netease QA 如有问题请及时联系我们<br>
				</p>
			</div>
	    </footer>
    </#if>
</#macro>

<#macro htmlFoot>


    </body>
</html>
</#macro>
</#compress>