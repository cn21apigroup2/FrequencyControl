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
		<a href="${mediaHost}/app/list/${userId}">应用列表</a>
		<a href="${mediaHost}/app/listDel/${userId}">已删除应用列表</a>
		<a href="${mediaHost}/app/create/${userId}">创建应用</a>
 		</div>
		<table>
 　　	<caption></caption>
 　　	<tr> <th>APP 名称</th><th>APP KEY</th><th>APP Secret</th><th>APP 描述</th><th>运行平台</th><th>审核状态</th><th>操作</th></tr>
 　　	<tbody>
 <#list page.records as application>
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
 	<#if !deleted??>
 	<a href="${mediaHost}/app/modify/${userId}/${application.app_id}">编辑</a>
	<a href="${mediaHost}/app/delete/${userId}/${application.app_id}">删除</a>
	<#elseif deleted?? && deleted==1>
	<a href="${mediaHost}/app/resume/${userId}/${application.app_id}">恢复</a>
	</#if>
 </td>
 </tr>
 </#list>
 　　<tbody>
	</table>
	<div id="pager">
          <#import "/page.ftl" as p>
              <@p.pager pageNo=page.pageNo pageSize=page.pageSize recordCount=page.totalSize pageCount=page.maxPage toURL="${mediaHost}/app/page/${userId}" method="post"/>
    </div>
	</body>
</html>
