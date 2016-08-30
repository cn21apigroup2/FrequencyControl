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
	        <a href="${mediaHost}/interface/list/${userId}/${appId}"><span>接口列表</span></a>
		    <a href="${mediaHost}/parameter/create/${userId}/${appId}/${interId}"></span>添加参数</span></a>
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
						     
<<<<<<< HEAD
						     <a href="${mediaHost}/parameter/modify/${userId}/${appId}/${interId}/${parameter.parameter_id}">编辑</a>
						     <a href="${mediaHost}/parameter/delete/${userId}/${appId}/${interId}/${parameter.parameter_id}">删除</a>						     
=======
						     <a href="${mediaHost}/parameter/modify/${userId}/${interId}/${parameter.parameter_id}">编辑</a>
						     <a href="${mediaHost}/parameter/delete/${userId}/${interId}/${parameter.parameter_id}">删除</a>						     
>>>>>>> branch 'master' of https://github.com/cn21apigroup2/FrequencyControl.git
						</td>
						
					</tr>
		        </#list>
		        <tbody>
	</table>
	
	</body>
</html>