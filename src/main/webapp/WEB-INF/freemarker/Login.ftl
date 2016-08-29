<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
<title>登录界面</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
</head>
<style type="text/css">
html,body {
	height: 100%;
}
.box {
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#6699FF', endColorstr='#6699FF'); /*  IE */
	background-image:linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
	background-image:-o-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
	background-image:-moz-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
	background-image:-webkit-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
	background-image:-ms-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
	
	margin: 0 auto;
	position: relative;
	width: 100%;
	height: 100%;
}
.login-box {
	width: 100%;
	max-width:500px;
	height: 400px;
	position: absolute;
	top: 50%;

	margin-top: -200px;
	/*设置负值，为要定位子盒子的一半高度*/
	
}
@media screen and (min-width:500px){
	.login-box {
		left: 50%;
		/*设置负值，为要定位子盒子的一半宽度*/
		margin-left: -250px;
	}
}	

.form {
	width: 100%;
	max-width:500px;
	height: 275px;
	margin: 25px auto 0px auto;
	padding-top: 25px;
}	
.login-content {
	height: 300px;
	width: 100%;
	max-width:500px;
	background-color: rgba(255, 250, 2550, .6);
	float: left;
}		
	
	
.input-group {
	margin: 0px 0px 30px 0px !important;
}
.form-control,
.input-group {
	height: 40px;
}

.form-group {
	margin-bottom: 0px !important;
}
.login-title {
	padding: 20px 10px;
	background-color: rgba(0, 0, 0, .6);
}
.login-title h1 {
	margin-top: 10px !important;
}
.login-title small {
	color: #fff;
}

.link p {
	line-height: 20px;
	margin-top: 30px;
}
.btn-sm {
	padding: 8px 24px !important;
	font-size: 16px !important;
}
</style>
</head>
<body>
<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h1><small>登录</small></h1>
			</div>
			<div class="login-content ">
			<div class="form">
			<form action="${mediaHost}/login/validate" method="post">
				<div class="form-group">
					<div class="col-xs-12">
						<div class="input-group">
							<span class="input-group-addon">用户名</span>
							<input type="text" id="userName" name="userName" placeholder="请输入用户名" class="form-control" value="${error}">
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;&nbsp;码</span>
							<input type="text" id="password" name="password" placeholder="请输入密码" class="form-control">
						</div>
					</div>
				</div>
				<div  class="inputSect checkCodeSect">
					<span class="input-group-addon">检验码</span>
					<input type="input"  class="input checkCode" name="code" id="code" maxlength="4" placeholder="请输入检验码"> 
					<img class="checkCodeImg" src="${mediaHost}/login/getCheckCode" alt="验证码" onclick="this.src='${mediaHost}/login/getCheckCode?rand='+Math.random()">
				</div>	

				<div class="form-group form-actions">
					<div class="col-xs-4 col-xs-offset-4 ">
						<button type="submit" class="btn btn-sm btn-info">登录</button>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12 link">
						<p class="text-center remove-margin"><small>还没注册?</small> <a href="/login/toRegister" ><small>注册</small></a>
						</p>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12 link">
						<p class="text-center remove-margin">
						<a href="${mediaHost}/login/forgetPassword" ><small>找回密码</small>
						<a href="${mediaHost}/login/chPwd" ><small>更改密码</small></a>
						</p>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
</div>

</body>
</html>