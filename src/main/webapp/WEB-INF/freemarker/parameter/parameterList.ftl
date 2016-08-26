<!DOCTYPE html>
<html>
	<head>
		<title>欢迎</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0 , user-scalable=no">

<style type="text/css">
body {margin-top: 20px}
</style>
	</head>
	<body>
	<table border="1" align="center">
	
	<input type="hidden" name="interId" id="interId" value="${interId}"/>
	
	    <div align="center">		 
		    <a href="${mediaHost}/parameter/create/${userId}/${interId}"></span>添加参数</span></a>
 		</div>
 		
		<tr >
		    <th>参数名称</th>
			<th>参数值</th>			
			<th>操作</th>
		</tr>
		<tbody>
		   		 <#list parameters as parameter>
					<tr>
					<input type="hidden" name="interId" id="interId" value="${parameter.interface_id}">
					    <td id="parameterKey">${parameter.parameter_key }</td>
						<td id="parameterValue">${parameter.parameter_value }</td>												
						<td>
						     
						     <a href="/FrequencyControl/parameter/modify/${userId}/${interId}/${parameter.parameter_id}">编辑</a>
						     <a href="/FrequencyControl/parameter/delete/${userId}/${interId}/${parameter.parameter_id}">删除</a>						     
						</td>
						
					</tr>
		        </#list>
		        <tbody>
	</table>
	
	</body>
</html>