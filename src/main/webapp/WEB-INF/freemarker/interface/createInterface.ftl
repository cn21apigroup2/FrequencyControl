<!DOCTYPE html>
<html>
	<head>
		<title>欢迎</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0 , user-scalable=no">

	</head>
	<body >
	    <input type="hidden" name="appId" id="appId" value="${appId}"/>
		<form class="createForm"  id="createForm" action="${mediaHost}/interface/save/${userId}/${appId}" method="post">
				<div class="apiNameSet">
					<label>接口名称</label>
					<input class="required" id="apiName" name="apiName" maxlength="30" value="" placeholder="接口名称(30字以内)"/>
				</div>
				<div class="frequencySet">
					<label>接口调用次数</label>
					<input type="number" name="apiFrequency"  value="" placeholder="接口调用次数"/>					
				</div>
				<div class="timeoutSet">
					<label>接口频次控制的时间长度</label>
					<input type="number" name="timeout"  value="" placeholder="接口频次控制的时间长度"/>
				</div>
				<div class="unitSet">
				请选择时间单位
					<select name="unit" id="unit">
                        <option value="s">s</option>
                        <option value="m">m</option>
                        <option value="h">h</option>
                        <option value="d">d</option>
                    </select>
				</div>
				<input type="submit" value="提交" />
			</form>
	</body>
</html>