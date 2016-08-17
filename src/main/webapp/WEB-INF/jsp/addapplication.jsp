<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加app</title>
</head>
<body>
    <form action="<%=request.getContextPath()%>/application/addApplication"
		method="post">
		app_id： <input type="text" name="app_id" ><br>
		user_id： <input type="text" name="user_id" ><br>
		app_key： <input type="text" name="app_key" >
		<br>secret： <input type="text" name="secret">
		<br>app_descrition： <input type="text" name="app_descrition"><br>
		platform： <input type="text" name="platform"><br>
		deleted： <input type="text" name="deleted"><br>
		<input type="reset" value="重置"> <input type="submit"
			value="添加">
	</form>
</body>
</html>