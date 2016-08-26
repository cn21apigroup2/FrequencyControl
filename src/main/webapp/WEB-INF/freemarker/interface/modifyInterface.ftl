<!DOCTYPE html>
<html>
	<head>
		<title>欢迎</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0 , user-scalable=no">

	</head>
	<body>
	    <input type="hidden" name="appId" id="appId" value="${appId}"/>
	    <form action="${mediaHost}/interface/modifySave/${userId}/${appId}/${interfac.interface_id}" type="text" method="post">
		<table border="1" cellpadding="1" cellspacing="1">
			 接口名称：
			<input class="required" type="text" name="apiName" value="${interfac.api_name}">
			<br> 接口调用次数：
			<input  class="required" type="text" name="apiFrequency" value="${interfac.frequency}">
			<br> 控制的时间长度：
			<input class="required" type="text" name="timeout" value="${interfac.timeout}">
			<br>请选择时间单位
					<select name="unit" id="unit">
                        <option value="s">${interfac.unit}</option>
                        <option value="s">s</option>
                        <option value="m">m</option>
                        <option value="h">h</option>
                        <option value="d">d</option>
                    </select>
			<input type="reset" value="重置"> <input type="submit" value="提交">
		</table>
	</form>
	</body>
</html>
