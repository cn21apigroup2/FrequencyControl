<!DOCTYPE html>
<html>
	<head>
		<title>欢迎</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0 , user-scalable=no">


	</head>
	<body>
		<input type="hidden" name="userId" id="userId" value="${userId}"/>
		<form class="createForm"  id="createForm" action="/app/saveModify/${userId}/${application.app_id}" method="post">
				<div class="appNameSet">
					<label>应用名称</label>
					<input class="required" id="appName" name="appName" maxlength="30" value="${application.app_name}" placeholder="应用名称(30字以内)"/>
				</div>
				<div class="descriptionSet">
					<label>应用描述</label>
					<textarea class="required" id="appDescription" name="appDescription" rows="4" cols="30">
					${application.app_description}
					</textarea>
				</div>
				<div class="platformSet">
					<label>应用平台</label>
					<input class="" id="appPlatform" name="appPlatform" maxlength="10" value="${application.platform}" placeholder="应用运行平台"/>
				</div>
				<input type="submit" value="保存" />
			</form>
	</body>
</html>
