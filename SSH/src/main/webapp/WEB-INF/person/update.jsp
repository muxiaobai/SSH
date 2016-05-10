<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/public/head.jspf"%>
</head>
<body>
<form id="ff" method="post">
	<input type="hidden" name="id" />
	<div>
		<label for="name">姓名:</label> 
		<input type="text" name="name" />
	</div>
	<div>
	<label for="passwword">密码:</label>
	<input type="text" name="password" />
	</div>
	<div>
		<a id="submit" href="#" class="easyui-linkbutton">更新</a>
		<a id="reset" href="#" class="easyui-linkbutton">重 置</a>
	</div>
</form>
<script type="text/javascript">
$(function(){	
	// iframe中获取框架外面的值需要parent
	var rows=parent.$("#dg").datagrid("getSelections");
	$("#ff").form("load",{
		id:rows[0].id,
		name:rows[0].name,
		password:rows[0].password,
	});
	
	// form指定的无刷新提交
	$("#submit").click(function(){
		$("#ff").form("submit", {    
		    url:'${basePath}/person_update.action',
		    success:function(data){    
		       // 关闭更新的窗体
		       parent.$("#win").window("close");
		       // 让数据重新检索一次
		       parent.$("#dg").datagrid("reload");
		    }    
		});
	});
	//重置按钮
	$("#reset").click(function(){
		$("#ff").form("reset");
	});
	
});
</script>
</body>
</html>