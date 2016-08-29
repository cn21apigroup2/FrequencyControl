<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="/css/login.css" />
<script type="text/javascript" src="/js/login.js"></script>
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
            	<div class="header">找回密码</div>
				<form action="${mediaHost}/login/sendPass" method="post">
                <div class="text">
                	<input type="text" name="username" placeholder="用户名" /><br />
                    <input type="email" name="email" placeholder="邮箱"/><br />					
                </div>
                <div class="submit">
                	<input type="submit"  value="确定" id="submit" />
                </div>
                </form>
			</div>
    </div>
    <div id="footer">
    	21cn
    </div>

</body>
</html>
            
