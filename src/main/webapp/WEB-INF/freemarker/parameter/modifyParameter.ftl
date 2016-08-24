<!DOCTYPE html>
<html>
	<head>
		<title>欢迎</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0 , user-scalable=no">

	</head>
	<body>
	    <input type="hidden" name="interId" id="interId" value="${interId}"/>
	    <form action="/FrequencyControl/parameter/modifySave/${interId}/${parameter.parameter_id}" type="text" method="post">
		<table border="1" cellpadding="1" cellspacing="1">
			 参数名称：
			<input class="required" type="text" name="parameterKey" value="${parameter.parameter_key}">
			<br> 参数值：
			<input  class="required" type="text" name="parameterValue" value="${parameter.parameter_value}">
			<br> 
			<input type="reset" value="重置"> <input type="submit" value="提交">
		</table>
	</form>
	</body>
</html>
