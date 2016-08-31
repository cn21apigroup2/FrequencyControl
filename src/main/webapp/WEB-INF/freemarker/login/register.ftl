<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link type="text/css" rel="stylesheet" href="${cssRoot}/register.css">
<script type="text/javascript" src="${jsRoot}/register.js"></script>
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
            <form action="${mediaHost}/login/register" method="post">
            <div class="create_text_input">
                <div class="input_username">
                    用户名<br>
                    <input type="text" id="account" name="username" maxlength="15"  value="" onblur="checkAccountAvailable(this)"><br>
                	<i class="icon-ok-circle" id="accountAvailable" ></i>
					<i class="icon-remove-circle" id="accountUnavailable"></i>
                </div>
                <div class="input_email">
                    电子邮箱<br>
                    <input type="email" id="email" name="email" maxlength="30"  value="" onblur="checkEmailAvailable(this)"><br>
                    <i class="icon-ok-circle" id="emailAvailable" ></i>
					<i class="icon-remove-circle" id="emailUnavailable"></i>
                    <a style="font-size:10px; font-weight:400; line-height:24px;"></a>
                </div>
                <div class="input_password">
                    密码<br>
                    <input type="password" class="input password" id="password" name="password" maxlength="15" value="" onblur="checkPasswordAvailable(this)"><br>
                	<i class="icon-ok-circle" id="passwordAvailable" ></i>
					<i class="icon-remove-circle" id="passwordUnavailable"></i>
                </div>
                <div class="input_confirm_password">
                    重新输入密码<br>
                    <input type="password" class="input password" name="confirm"  maxlength="15" id="confirmPassword" value="" onblur="verifyPassword(this)"><br><br>
                	<i class="icon-ok-circle" id="passwordConfirmed" ></i>
					<i class="icon-remove-circle" id="passwordUnconfirmed"></i>
                </div>
                <div class="inputSect checkCodeSect">
                    检验码 <br>
                    <input type="input"  class="input checkCode" name="checkCode_" id="checkCode_" maxlength="4" > 
					<img class="checkCodeImg" src="${mediaHost}/login/getCheckCode" alt="验证码" onclick="this.src='${mediaHost}/login/getCheckCode?rand='+ Math.random()"><br>
                </div>    
                <div class="confirm_checkbox">
            		 <input id="confirm_checkbox" type="checkbox" value="true" /><label id="confirm_checkbox_lable" style="vertical-align:middle;">&nbsp;&nbsp;请阅读并同意<a style="color:#0078DF;">相关协议</a></label> <br>
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
