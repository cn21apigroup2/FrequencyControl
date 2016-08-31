			var accountAvailable = false;
			var emailAvailable = false;
			var passwordAvailable = false;
			
			function checkAccountAvailable(){
				var accountAvailableIcon = document.getElementById("accountAvailable");
				var accountUnavailableIcon = document.getElementById("accountUnavailable");
				var accountInput = document.getElementById("account");	
//				alert("checkAccountAvailable0");
				if(accountInput.value != ""){
					console.log("checkAccountAvailable");
//					alert("checkAccountAvailable1");
					var requestURI = "/login/isUsed?username=" + accountInput.value ;
					var checkAccountRequest = new XMLHttpRequest();
					checkAccountRequest.open("GET", requestURI , true);
					checkAccountRequest.onreadystatechange = function(){
						if( checkAccountRequest.readyState === 4 && checkAccountRequest.status === 200 ){
							var response = JSON.parse(checkAccountRequest.responseText);
						//    alert(response.isUsed);
							if(response.isUsed=='0'){
								accountUnavailableIcon.style.display = "none";
								accountAvailableIcon.style.display = "inline-block";
								accountAvailable = true;
								return true;
							}else{
								showPrompt("用户名已经被注册");
								accountAvailableIcon.style.display = "none";
								accountUnavailableIcon.style.display = "inline-block";
								accountAvailable = false;
								return false;
							}
						}
					}
					checkAccountRequest.send();
				}
			}
			
			function checkEmailAvailable(){
				var emailAvailableIcon = document.getElementById("emailAvailable");
				var emailUnavailableIcon = document.getElementById("emailUnavailable");
				var emailInput = document.getElementById("email");
				
				var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
				if(reg.test(emailInput.value)){
					emailAvailableIcon.style.display = "inline-block";
					emailUnavailableIcon.style.display = "none";
					emailAvailable = true;
					return true;
				}else{
					emailUnavailableIcon.style.display = "inline-block";
					emailAvailableIcon.style.display = "none";
					showPrompt("请输入正确的邮箱地址，以用于找回密码");
					emailAvailable = false;
					return false;
				}
			}
			
			function checkPasswordAvailable(){
				var passwordAvailableIcon = document.getElementById("passwordAvailable");
				var passwordUnavailableIcon = document.getElementById("passwordUnavailable");
				var passwordInput = document.getElementById("password");
				if(passwordInput.value.length >= 6){
					passwordAvailableIcon.style.display = "inline-block";
					passwordUnavailableIcon.style.display = "none";
					passwordAvailable = true;
					return true;
				}else{
					passwordUnavailableIcon.style.display = "inline-block";
					passwordAvailableIcon.style.display = "none";
					passwordAvailable = false;
					showPrompt("密码不符合要求，请填写6~15位数字或字母");
					return false;
				}
			}
			
			function verifyPassword(){
				var passwordConfirmIcon = document.getElementById("passwordConfirmed");
				var passwordUnconfirmIcon = document.getElementById("passwordUnconfirmed");
				if(passwordAvailable){
					var password = document.getElementById("password");
					var confirmPassword = document.getElementById("confirmPassword");
					if( confirmPassword.value == password.value ){
						passwordConfirmIcon.style.display = "inline-block";
						passwordUnconfirmIcon.style.display = "none";	
						return true;
					}else{
						passwordUnconfirmIcon.style.display = "inline-block";
						passwordConfirmIcon.style.display = "none";
						showPrompt("两次输入的密码不一致");
						return false;
					}
				}else{
					passwordUnconfirmIcon.style.display = "inline-block";
					passwordConfirmIcon.style.display = "none";		
					return false;
				}
			}
			
	/*		function verifycheckcode(){
				var checkcodeAvailableIcon = document.getElementById("checkcodedAvailable");
				var checkcodeUnavailableIcon = document.getElementById("checkcodeUnavailable");
				var checkcodeInput = document.getElementById("checkcode");
				var code = $("#checkcode_").val();
				if(checkcodeInput.value == code.value){
					checkcodedAvailableIcon.style.display = "inline-block";
					checkcodeUnavailableIcon.style.display = "none";
					checkcodeAvailable = true;
					return true;
				}else{
					checkcodedUnavailableIcon.style.display = "inline-block";
					checkcodeAvailableIcon.style.display = "none";
					checkcodeAvailable = false;
					showPrompt("验证码错误");
					return false;
				}
			} */
			function submitForm(){
				
					var code = $("#checkCode_").val();
					$.ajax({
						method: "post",
						url: "/login/validateCheckCode?checkCode="+code,
						success: function(returnedData){
							var returnedData = JSON.parse(returnedData);
							
							if(returnedData.isRight)
							{
							document.getElementById("registerFrom").submit();
							}
							else
							{
							alert("校验码错误");
							}			
						}
					});
				
			}

			function checkAgreement(){
				var confirm_checkbox =	document.getElementById("confirm_checkbox");
					console.log(confirm_checkbox.checked);
					if(confirm_checkbox.checked){
						return true;
					}else{
						showPrompt("请同意用户使用协议");
						return false;
					}
			}
			
			
			function checkAll(){
				if( accountAvailable && checkEmailAvailable() && checkPasswordAvailable() && verifyPassword() && checkAgreement()){
					return true;
				}else{
					return false;
				}
			}
			
			function showAgreement(){
				document.getElementById("agreementContainer").style.display = "block";
			}
			
			function hideAgreement(dom){
				dom.style.display = "none";
			}
			
			function showPrompt(text){
				var promptBg = document.createElement("div");
				promptBg.style.cssText = "background-color: rgba(0,0,0,.7); position: fixed; width: 100%; height: 100%; top: 0; text-align: center;";
				var prompt = document.createElement("div");
				prompt.style.cssText = "width: 40%; margin: auto; background-color: #fff; padding: 8%; border-radius: 5px; position: absolute; top: 30%; left: 22%; font-size: .9em;"
				prompt.innerHTML = text;
				promptBg.appendChild(prompt);
				document.getElementsByClassName("wrap")[0].appendChild(promptBg);
				promptBg.addEventListener("click",function(){promptBg.style.display ="none"});
				
			}