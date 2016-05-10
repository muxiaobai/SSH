<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/public/head.jspf"%>
</head>
<body>
<form id="ff" method="post" >
	<div>
		<label for="name">姓名:</label> 
		<input type="text" name="name" />
	</div>
	<div>
		<label for="passwword">密码:</label>
		<input type="text" name="password" />
	</div>
	
	<div>
		<a id="submit" href="#" class="easyui-linkbutton">添 加</a>
		<a id="reset" href="#" class="easyui-linkbutton">重 置</a>
	</div>
</form>

<script type="text/javascript">
$(function(){
	$("[name=name]").validatebox({
		required:true,
		invalidMessage:'对不起此用户名已存在'
	});
	// form指定的无刷新提交
	$("#submit").click(function(){
		$("#ff").form("enableValidation");
		// 默认是ajax提交,提交之前会自动进行表达验证
		$('#ff').form('submit', {    
		    url:'${basePath}/person_save.action',    
		    success:function(result){
		       // 如果成功 则重置表单数据
		       $("#ff").form("disableValidation");
		       $("#ff").form("reset");
		    }    
		});  
	});
	//重置按钮
	$("#reset").click(function(){
		$("#ff").form("reset");
	});
	$("#ff").form("disableValidation");
});
</script>

</body>
</html>