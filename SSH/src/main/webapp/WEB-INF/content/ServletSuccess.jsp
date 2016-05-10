<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${username}登录成功！
Application参数：${applicationScope.user}<br/>
Session参数：${sessionScope.token}<br/>
Cookie参数：ServletActionContext方法：${cookie.ServletActionCookie.value}<br/>
Request参数 :${requestScope.request} <br/>
ActionContext方法：${username}<br/>
ServletRequestAware方法：${aware}<br/>
ServletActionContext方法：${requestScope.ServletActionRequest}
</body>
</html>