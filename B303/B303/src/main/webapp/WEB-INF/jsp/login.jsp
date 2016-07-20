<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sj" uri="/struts-json-tags"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh-cn" class="no-js">
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>主页</title>
<!-- CSS -->
<link rel="stylesheet" href="assets/assets/css/reset.css">
<link rel="stylesheet" href="assets/assets/css/supersized.css">
<link rel="stylesheet" href="assets/assets/css/style.css">
<%-- <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script> --%>
<!--[if lt IE 9]>
<script src="http://apps.bdimg.com/libs/html5shiv/r29/html5.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="assets/assets/js/html5.js"></script>
        <![endif]-->
</head>
<body>

	<div class="page-container">
		<h1>登录(Login)</h1>
		<form action="user_login" method="post">
			<input type="text" name="username" class="username"
				placeholder="请输入您的用户名！"> 
			<input type="password" name="password" class="password" placeholder="请输入您的用户密码！"> 
			<button type="submit" class="submit_button">登录</button>
			<s:fielderror/>
			<div class="error">
				<span>+</span>
			</div>
		</form>
	
	</div>

	<!-- Javascript -->
	<script src="assets/assets/js/jquery-1.8.2.min.js"></script>
	<script src="assets/assets/js/supersized.3.2.7.min.js"></script>
	<script src="assets/assets/js/supersized-init.js"></script>
	<script src="assets/assets/js/scripts.js"></script>
<script>
$(document).ready(function(){
	$(".password").on('focus',function(){
		var username=$(".username").val();
		console.log(username);
		
		$.ajax({
			   url:'user_check_checkUsername',// 跳转到 action  
			   data:{  
		    		username : username,  
			    }, 
			    type:'post',  
			    cache:false,  
			    dataType:'json',  
			    success:function(data) { 
			    	console.log(data);
			    	if(data.flag==false){
			    		 alert("用户不存在");  
			    	}
			    
// 			        if(data.flag =="true" ){  
// 			            // view("修改成功！");  
// 			            alert(data.flag);  
// 			            window.location.reload();  
// 			        }else{  
// 			            view(data.msg);  
// 			        }  
			     },  
			     error : function() {  
			          alert("服务器异常！");  
			     }
		});
	});


});
</script>
</body>

</html>

