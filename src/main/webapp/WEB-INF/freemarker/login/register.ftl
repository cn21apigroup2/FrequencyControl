<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link type="text/css" rel="stylesheet" href="${cssRoot}/register.css">
<title>Register</title>

</head>
<body>
	<div id="header">
    	<div class="top">
        	欢迎使用API接口频次控制系统
        </div>
    </div>
    <div id="body">
    	<div class="wrap">
            <div class="create_logo">
                创建账户
            </div>
            <div class="create_text_disc">
                使用新的电子邮箱创建你的账户，我们将通过电子邮件认证你的身证，请务必准确填写。电子邮箱可作为登录的用户名。如果你已有账户，请使用该账户<a style="color:#0078D7;" href="${mediaHost}/login/index">登录</a>
            </div>
            <form action="/login/registersuccess" method="post">
            <div class="create_text_input">
                <div class="input_username">
                    用户名<br>
                    <input type="text" name="username"><br>
                </div>
                <div class="input_email">
                    电子邮箱<br>
                    <input type="text" name="email"><br>
                    <a style="font-size:10px; font-weight:400; line-height:24px;">最少输入8个字符，区分大小写</a>
                </div>
                <div class="input_password">
                    密码<br>
                    <input type="password" name="password"><br>
                </div>
                <div class="input_confirm_password">
                    重新输入密码<br>
                    <input type="password" name="confirm"><br>
                </div>
       
                <div class="confirm_checkbox">
            		 <input id="confirm_checkbox" type="checkbox" value="true" /><label id="confirm_checkbox_lable" style="vertical-align:middle;">&nbsp;&nbsp;请阅读并同意<a style="color:#0078DF;">相关协议</a></label> <br />
                </div>
                <div class="submit">
                	<input type="submit" value="创建账户"><br>
                </div>
            </div>
            </form>
        </div>
    </div>
    <div id="footer"></div>
</body>
</html>
