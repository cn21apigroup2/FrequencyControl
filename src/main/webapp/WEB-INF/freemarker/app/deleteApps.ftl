<!DOCTYPE html>
<html>
	<head>
		<title>欢迎</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0 , user-scalable=no">


	</head>
	<body>
		<input type="hidden" name="userId" id="userId" value="${userId}"/>
		<div>
		<a href="/app/create/1">创建应用</a>
 		</div>
		<table>
 　　	<caption></caption>
 　　	<tr> <th>APP 名称</th><th>APP KEY</th><th>APP Secret</th><th>APP 描述</th><th>运行平台</th><th>审核状态</th><th>操作</th></tr>
 　　	<tbody>
 <#list applications as application>
 　　　　<tr>
 <input type="hidden" name="appId" id="appId" value="${application.app_id}"/>
 <td id="appName">${application.app_name}</td>
 <td id="appKey">${application.app_key}</td>
 <td id="appSecret">${application.secret}</td>
 <td id="appDescription">${application.app_description}</td>
 <td id="appPlatform">${application.platform}</td>
 <td id="appStatus">
 		<#if application.is_reviewed==0>未审核
		<#elseif application.is_reviewed==1>已审核
		<#elseif application.is_reviewed==2>审核不通过
		</#if>
 </td>
 <td>
 <button onclick="editApplication(this)">编辑</button>
 <button onclick="deleteApplication(this)">删除</button>
 </td>
 </tr>
 </#list>
 　　<tbody>
	</table>
	</body>
</html>
