<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<body>
<h3>用户注册</h3>
<form action="login" method="post">
<input type="text" name="name"/>
<input type="password" name="password"/>
<input type="submit" value="提交"/>
<a href="send_content_login.action">跳转</a>
</body>
</html>
