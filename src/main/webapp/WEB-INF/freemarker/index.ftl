<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="${cssRoot}/index.css" />
<script type="text/javascript" src="${jsRoot}/index.js"></script>
<title>欢迎使用API接口频次控制平台</title>
</head>
<body>
<input type="hidden" value="${imageRoot}" id="imageRoot"/>
<input type="hidden" value="${mediaHost}" id="mediaHost"/>
	<div id="header">
    	<div class="top">
        	欢迎使用API接口频次控制系统
        </div>
        <div class="bottom">
        	<img src="${imageRoot}/21cn_icon.png"  width="40px;" style="padding-top:18px; padding-left:120px;"/>
        	<a href="${mediaHost}/login/index" id="loginStatus">登录 </a>
        </div>
    </div>
    <div id="body">
    	<div class="bar_content">
        	<ul>
            	<li class="bar_button">API控制</li>
                <li class="bar_button">黑名单</li>
                <li class="bar_button">APP</li>
                <div id="APP_NAME" style="height:70px; line-height: 4; margin-left:350px; float: left;color: #0078DF;"></div>
                <input type="hidden" id ="APP_ID" />
                <input type="hidden" id = "APP_KEY" />
               	<div id="bar_dropdown_icon">
               		<img id="dropdown_icon" src="${imageRoot}/dropdown_icon.png" width="13px;" style="margin-top: 24px; margin-left: 25px;" />
               	</div>
            </ul>
        </div>
        <div class="shadow"></div>
        <div class="content">
        	<div class="blacklist">
            	<div class="blacklist_search_bar">
                	<div class="username_search">
                    	用户名 <br /><input type="text" name="username" id="username_search" value=""/>
                    </div>
                    <div class="ip_search">
                    	IP <br /><input type="text" name="ipAddr" id="ip_search" />
                    </div>
                    <div class="icon_search" id="search_botton">
                    	<img src="${imageRoot}/icon_search.png" width="25px;" style="margin-top:25px; margin-left:40px; cursor:pointer;"/>
                    </div>
                </div>
            	<div class="blacklist_table">
                	<table 	cellspacing="0";cellpadding="0" id = "blacklist_table_content">
                    </table>
                </div>
            </div>
         	<div class="apiController" >
         		<div class="addApi"><img src="${imageRoot}/icon_add.png" width="20px;" style="margin-left:800px;"/></div>  
         		<div class="addApi" style="margin-left: 5px; cursor:pointer" id="addApi">添加API</div>       		         			
	         	<table cellspacing="0";cellpadding="0" id="api_table_content">
	         	</table>
         	</div>
         	<div class="app">
         		<div class="addApp"><img src="${imageRoot}/icon_add.png" width="20px;" style="margin-left:800px;"/></div>
         		<div class="addApp" style="margin-left: 5px;cursor:pointer" id="addApp">添加APP</div>
         		<table cellspacing="0";cellpadding="0" id="app_table_content">
         		</table>
         	</div>
        </div>
        <div class ="edit">
        	<div class="app_edit_propmt">
         		<div class="app_edit_tittle">APP信息修改</div>
         		<div>
         			<table id = "app_edit_table">
       					<tr style="display: none;">
       						<td></td><td><input id="app_edit_appId" type="hidden" name="appId" ></input></td>
       					<tr>
       						<td>APP名称</td><td><input type="text"; name="appName"/></td>
       					<tr>
       						<td>应用描述</td><td><input type="text"; name="appDescription"/></td>
       					<tr>
         					<td>平台</td><td><input type="text"; name="appPlatform"/></td>
         				</table>
         				<input id="app_edit_confirm"type="button" value="确认"/> 
         				<input id="app_edit_cancle" type="button" value="取消"/> 
         		</div>
         	</div>
         	<div class="api_edit_propmt">
         		<div class="api_edit_tittle">API控制信息修改</div>
         		<div>
         			<table id = "api_edit_table">
       					<tr style="display: none;">
       						<td></td><td><input id="api_edit_interfaceId" type="hidden" name="interfaceId" ></input></td>
       					<tr>
       						<td>API名称</td><td><input type="text"; name="apiName"/></td>
       					<tr>
       						<td>控制频次</td><td><input type="text"; name="apiFrequency"/></td>
       					<tr>
         					<td>时间长度</td><td><input type="text"; name="timeout"/></td>
         				<tr>
         					<td>时间单位</td><td><input type="text"; name="unit"/></td>
         				</table>
         				<input id="api_edit_confirm" type="button" value="确认"/> 
         				<input id="api_edit_cancle" type="button" value="取消"/> 
         		</div>
         	</div>
         	<div class="app_add_propmt">
         		<div class="app_add_tittle" style="margin-top: 20px;color:#0078DF;">添加APP</div>
         		<div>
         			<table id = "app_add_table">
       					<tr>
       						<td>APP名称</td><td><input type="text"; name="appName"/></td>
       					<tr>
       						<td>应用描述</td><td><input type="text"; name="appDescription"/></td>
       					<tr>
         					<td>平台</td><td><input type="text"; name="appPlatform"/></td>
         				</table>
         				<input id="app_add_confirm" type="button" value="确认"/> 
         				<input id="app_add_cancle" type="button" value="取消"/> 
         		</div>
         	</div>
         	<div class="api_add_propmt">
         		<div class="api_add_tittle" style="margin-top: 20px; color:#0078DF;">新增API控制信息</div>
         		<div>
         			<table id = "api_add_table">
       					<tr>
       						<td>API名称</td><td><input type="text"; name="apiName"/></td>
       					<tr>
       						<td>控制频次</td><td><input type="text"; name="apiFrequency"/></td>
       					<tr>
         					<td>时间长度</td><td><input type="text"; name="timeout"/></td>
         				<tr>
         					<td>时间单位</td><td><input type="text"; name="unit"/></td>
         				</table>
         				<input id="api_add_confirm" type="button" value="确认"/> 
         				<input id="api_add_cancle" type="button" value="取消"/> 
         		</div>
         	</div>
         	<div class="api_param_propmt" >
         			<div class="api_param_tittle" style="margin-top: 20px; color:#0078DF;">参数列表</div>
         			<div class="api_param_table">
	         			<table id = "api_param_table" cellspacing="0";cellpadding="0">
	         			</table>
         			</div>
                	<input id="api_param_add" type="button" value="添加"/>
         			<input id="api_param_close" type="button" value="关闭"/>
         	</div>
            <div class="api_param_add_propmt" >
                <div class="api_param_add_tittle" style="margin-top: 20px; color:#0078DF;">添加参数</div>
                <div class="api_param_add_table">
                    <table id = "api_param_add_table" cellspacing="0";cellpadding="0" style="margin-top: 40px;">
						<tr style="display: none">
							<td></td><td><input style="hidden" id="interfaceId" /></td>
                        <tr >
                            <td>参数名</td><td><input type="text"; name="parameterKey"/></td>
						<tr >
                            <td>参数值</td><td><input type="text"; name="parameterValue"/></td>
                    </table>
                </div>
                <input id="api_param_add_confirm" type="button" value="确认" style="margin-bottom: 40px;"/>
                <input id="api_param_add_cancle" type="button" value="取消" style="margin-bottom: 40px;"/>
            </div>
        </div>
    </div>
    <div id="footer"></div>
</body>
</html>
