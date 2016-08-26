<!DOCTYPE html>
<html>
	<head>
		<title>欢迎</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0 , user-scalable=no">

	</head>
	<body >
	    <input type="hidden" name="interId" id="interId" value="${interId}"/>
		<form class="createForm"  id="createForm" action="${mediaHost}/parameter/save/${userId}/${interId}" method="post">
				<div class="parameterKeySet">
					<label>参数名</label>
					<input class="required" id="parameterKey" name="parameterKey"  value="" placeholder="参数(30字以内)"/>
				</div>
				<div class="parameterValueSet">
					<label>参数值</label>
					<input type="number" name="parameterValue"  value="" placeholder="参数值"/>					
				</div>				
				<input type="submit" value="提交" />
			</form>
	</body>
</html>