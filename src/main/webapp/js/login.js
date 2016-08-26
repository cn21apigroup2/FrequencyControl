window.onload=function(){
    var keep_login_lable = document.getElementById('keep_login_lable');
    var keep_login=document.getElementById('keep_login');
    keep_login_lable.onclick=function(){
        if(keep_login.checked!=true){
            keep_login.checked=true;
        }else{
            keep_login.checked=false;
        }

    }

    document.getElementById('submit').onclick=function(){
        var request = new XMLHttpRequest();
        request.open("POST","/FrequencyControl/login/")
        var loginDate = "userName="+document.getElementsByName("userName").item(0).value+
            "&password="+document.getElementsByName("password").item(0).value;
        request.send(loginDate);
    }
}