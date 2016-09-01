<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${cssRoot}/login.css" />
<script type="text/javascript" src="${jsRoot}/login.js"></script>
<title>Login</title>
</head>
<body>
	<div id="header">
    	<div class="top">
        	欢迎使用API接口频次控制系统
        </div>
        <div class="bottom"></div>
    </div>
    <div id="body">
        	<div id=login_windows>
            	<div class="header">登录</div>
				<form action="${mediaHost}/login/validate" method="post">
                <div class="text">
                	<input type="text" name="username" placeholder="用户名" /><br />
                    <input type="password" name="password" placeholder="密码"/><br />                    
                </div>
                <div class="checkbox">
            		 <input id="keep_login" type="checkbox" value="true" /><label id="keep_login_lable">&nbsp;&nbsp;保持登录状态</label> <br />
                </div>
                <div class="submit">
                	<input type="submit"  value="登录" id="submit" />
                </div>
                <div class="create_propt" >
                	<a href="${mediaHost}/login/toRegister">没有账号?</a>
                </div>
                <div class="forget_password">
                	<a href="${mediaHost}/login/forgetPassword">忘记密码</a>
                </div>
			</form>
            </div>
    </div>
    <div id="footer">
    	21cn
    </div>

</body>
</html>
