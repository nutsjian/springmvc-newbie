<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Kisso - SpringMvc</title>
    </head>
	<body style="text-align: center;margin-top: 168px;">
	<h1>Kisso - SpringMvc</h1>
	<div class="table_box">
		<p>${userName} 欢迎来到 SpringMvc-SSO 极速开发世界！</p>
		<br>
		<a href="${pageContext.request.contextPath}/logout">退出登录</a>
	</div>
	</body>
</html>