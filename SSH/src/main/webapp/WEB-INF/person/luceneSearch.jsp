<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/public/head.jspf"%>
</head>
<body>
依据：Person表中的数据
<form id="ff" method="post">
	<div>
		<label for="content">搜索:</label> 
		<input type="text" name="content" />
		<a id="submit" href="#" class="easyui-linkbutton">搜索</a>
	</div>
</form>
<div id ="result"></div>
<script type="text/javascript">
$(function(){

	// form指定的无刷新提交
	$("#submit").click(function(){
		$("#ff").form("enableValidation");
		// 默认是ajax提交,提交之前会自动进行表达验证
		$('#ff').form('submit', {
		    url:'${basePath}/person_luceneSearch.action', 
		    success:function(result){
		    	console.info(result);
		    	$("#result").html(result);
		       // 如果成功 则重置表单数据
		       $("#ff").form("disableValidation");
// 		       $("#ff").form("reset");
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