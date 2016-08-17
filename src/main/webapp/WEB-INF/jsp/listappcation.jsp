<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
		<tr>
			<th>app_id</th>
			<th>user_id</th>
			<th>app_key</th>
			<th>secret</th>
			<th>app_descrition</th>
			<th>platform</th>
			<th>deleted</th>
			<th>操作</th>
		</tr>
		<c:if test="${!empty applications }">
				<c:forEach items="${applications }" var="application">
					<tr>
						<td>${application.app_id }</td>
						<td>${application.user_id }</td>
						<td>${application.app_key }</td>
						<td>${application.secret }</td>
						<td>${application.app_descrition }</td>
						<td>${application.platform }</td>
						<td>${application.deleted }</td>
						<td><a href="<%=request.getContextPath()%>/application/getApplication?app_id=${application.app_id }">编辑</a>
							<a href="<%=request.getContextPath()%>/application/delApplication?app_id=${application.app_id }">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
	</table>
	<h3><a href="<%=request.getContextPath()%>/application/toaddApplication">添加app</a></h3>
</body>
</html>