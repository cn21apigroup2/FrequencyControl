<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>编辑app</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/application/updateApplication" method="post">
		<table border="1" cellpadding="1" cellspacing="1">
			<input type="hidden" name="app_id" value="${application.app_id }">
			<br> 用户id：
			<input type="text" name="user_id" value="${application.user_id}">
			<br> app_key：
			<input type="text" name="app_key" value="${application.app_key}">
			<br> secret：
			<input type="text" name="secret" value="${application.secret}">
			<br>app信息描述：
			<input type="text" name="app_descrition" value="${application.app_descrition}">
			<br>platform：
			<input type="text" name="platform" value="${application.platform}">
			<br> 是否删除：
			<inputtype ="text" name="deleted" value="${application.deleted}">
			<br>
			<input type="reset" value="重置"> <input type="submit"
				value="提交">
		</table>
	</form>
</body>
</html>