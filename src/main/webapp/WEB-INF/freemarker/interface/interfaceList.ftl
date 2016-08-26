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
	
	<input type="hidden" name="appId" id="appId" value="${appId}"/>
	
	    <div align="center">
		    <a href="${mediaHost}/interface/list/${userId}/${appId}"><span>接口列表</span></a>
		    <a href="${mediaHost}/interface/listDel/${userId}/${appId}"><span>已删除接口列表</span></a>
		    <a href="${mediaHost}/interface/create/${userId}/${appId}"></span>创建接口</span></a>
 		</div>
 		
		<tr >
		    <th>接口名称</th>
			<th>接口调用次数</th>
			<th>接口频次控制的时间长度</th>
			<th>时间单位</th>			
			<th>操作</th>
		</tr>
		<tbody>
		<#if overallControl??>
				<tr>
					<input type="hidden" name="appId" id="appId" value="${overallControl.app_id}">
					    <td id="apiName">全局频次控制 </td>
						<td id="frequency">${overallControl.frequency }</td>
						<td id="timeout">${overallControl.timeout }</td>
						<td id="unit">${overallControl.unit }</td>												
						<td>
						     <a href="${mediaHost}/interface/modify/${userId}/${appId}/${overallControl.interface_id}">编辑</a>
						</td>
						
					</tr>
					</#if>
				<#if interfacs??>
		   		 <#list interfacs as interfac>
					<tr>
					<input type="hidden" name="appId" id="appId" value="${interfac.app_id}">
					    <td id="apiName">${interfac.api_name }</td>
						<td id="frequency">${interfac.frequency }</td>
						<td id="timeout">${interfac.timeout }</td>
						<td id="unit">${interfac.unit }</td>												
						<td>
						     <#if !deleted??>
						     <a href="${mediaHost}/interface/modify/${userId}/${appId}/${interfac.interface_id}">编辑</a>
						     <a href="${mediaHost}/interface/delete/${userId}/${appId}/${interfac.interface_id}">删除</a>
						     <a href="${mediaHost}/parameter/list/${userId}/${interfac.interface_id}">参数控制</a>
						     <#elseif deleted??&&deleted==1>
						     <a href="${mediaHost}/interface/resume/${userId}/${appId}/${interfac.interface_id}">恢复</a>
						     </#if>
						</td>
						
					</tr>
		        </#list>
		        </#if>
		        <tbody>
	</table>
	
	</body>
</html>