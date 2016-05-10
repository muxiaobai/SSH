<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/public/head.jspf"%>
<style type="text/css">
 	/* ul块级元素,因此首先要设置外面div的宽度, */
 	#menu{
 		width: 200px;
 	}
 	/* 设置ul基本属性 */
 	#menu ul{
 		list-style-type: none;
 		margin: 0px;
 		padding: 0px;
 	}
 	/* 设置li的基本属性 */
 	#menu ul li{
 		border-bottom: 1px solid red;
 	}
 	/* 设置li a 标签的样式 */
 	#menu ul li a{
 		display: block;
 		padding: 5px;
 		text-decoration: none;
 		border-left: solid 10px red;
 	}
 	#menu ul li a:hover{
 		background-color: #c11136;
		color: #ffffff;
 	}
 </style>
<script type="text/javascript">
	$(function(){
		$("a[title]").click(function(){
			// 1:获取 a 标签相应的信息  
			var href=$(this).attr("title");
		    var text=$(this).text(); //val html text
			// 2:如果右边已经存在相应tab则直接切换,否则新创建
			if($("#tt").tabs("exists",text)){
				// 让当前tab选中
				$("#tt").tabs("select",text);
			}else{
				// 重创建tab,并且加载"类别管理页面"
				$("#tt").tabs("add",{
					title:text,
					// href:href,  /* 采用href:仅仅只能加载 body中的内容*/
					// content 支持html标签
					content:"<iframe src='" + href + "' width='100%' height='100%' frameborder='0' />",
					closable:true
				});
			}
		});
	});
</script>

</head>

<body class="easyui-layout">
	<!-- top -->
	<div data-options="region:'north',title:'欢迎来到后台系统',split:true" style="height: 40px;"></div>
	<!--  -->
	<div data-options="region:'south',title:'South Title',split:false" style="height: 10px;"></div>
	<!-- left -->
	<div data-options="region:'west',title:'系统操作',split:true" style="width:150px;"
		<!-- 菜单 begin -->
    	<div id="menu" data-options="fit:true" class="easyui-accordion" style="width:300px;height:200px;">  
				<!-- 单击后,会动态生成tab,显示在页面的右边区域 --> 
			 <div title="基本操作"> 
				<ul>
					<li><a title="send_content_ServletSuccess.action" href="#">成功管理</a></li>	
					<li><a title="send_content_dataGrid.action" href="#">用户管理</a></li>	
			   </ul>
			    <ul>
					<li><a title="send_util_upload.action" href="#">上传文件</a></li>	
					<li><a title="send_util_sendMessage.action" href="#">发送邮件</a></li>	
			   </ul>
		    </div> 
		    
			 <div title="Util操作">  
			 </div>   
			  <div title="管理员操作">  
			 </div>   
		</div>  
		<!-- 菜单 end-->	
	</div>
	<!-- main begin-->
	 <div data-options="region:'center',title:'操作管理页面'" style="padding:1px;background:#fff;">
		<!-- 系统初始化tab begin-->
    	<div id="tt" class="easyui-tabs" data-options="fit:true">   
		    <div title="系统初始化页面" style="padding:20px;display:none;">   
				此处后面会动态加载网页, 网页中显示的是系统的信息    <s:debug/>
		    </div>
		</div>
		<!-- 系统初始化tab end -->
	</div>
	<!-- main end-->
</body>

</html>
