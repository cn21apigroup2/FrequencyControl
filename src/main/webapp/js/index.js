var storage;
var imageRoot;
var Host;

window.onload=function(){
	storage=window.sessionStorage;
	imageRoot=document.getElementById('imageRoot').value;
	Host=document.getElementById('mediaHost').value;
	/**
	 * 导航栏事件监听
	 */
	barClickEventListener();

	
	/**
	 * 加载会话参数
	 */
	loadSessionParam()
	
	/**
	 * 读取APP列表
	 */
	readAppList();
	
	/**
	 * 黑名单搜索
	 */
	searchBlacklist();

	/**
	 *APP修改弹窗事件监听 
	 */
	appPropmtEventListener();
	
	/**
	 * API修改窗口事件监听
	 */
	apiPropmtEventListener();
	
	/**
	 * APP添加窗口事件监听
	 */
	appAddPropmtEventListener();
	
	
	/**
	 * API添加窗口事件监听
	 */
	apiAddPropmtEventListener();

	/**
	 * API参数添加事件监听
	 */
	apiParamAddEventListener();
	
	apiParamButtonEventListener();
	/**
	 * icon 式样切换事件
	 */
	document.getElementById('dropdown_icon').onmouseover=function(){
		this.src=document.getElementById('imageRoot').value+"/dropdown_click_icon.png";
	}
	document.getElementById('dropdown_icon').onmouseout=function(){
		this.src=document.getElementById('imageRoot').value+"/dropdown_icon.png";
	}

	/**
	 * 读取登录状态
	 */
	if(getCookie('username')){
		document.getElementById('loginStatus').removeAttribute('href');
		document.getElementById('loginStatus').innerHTML='欢迎您! '+getCookie('username')+"<a style=\'margin-left: 20px;\' id=\"logout\">登出</a>";
		document.getElementById('loginStatus').style.color='black';
		document.getElementById('logout').style.cursor="pointer";
		document.getElementById('logout').onclick=function(){
			DelCookie('username');
			DelCookie('userId');
			storage.clear();
			window.location.href=Host+"/login/index";

		}
	}


}


/*************************************************************************************************************/
	/**
	 * AJAX异步加载APP列表
	 */
	 function readAppList(){
		var url = Host+"/app/list/"+getCookie('userId');
		interactiveByGet(url,function(responseText){
			var data = JSON.parse(responseText);
			createAppTable(data)
		});
	 }
/***************************************************************************************************************/
	/**
	 *AJAX异步加载API控制列表
	 */
	function readApiList(){
		var url=Host+"/interface/list/"+storage.APP_ID;
		interactiveByGet(url,function(responseText){
			var data = JSON.parse(responseText);
			createApiTable(data);
			createApiEvent();
		});
	}
/*****************************************************************************************************************/
	/**
	 * AJAX异步查询黑名单列表
	 */	
	function readBlacklistList(){
		var url="";
		if(document.getElementById('username_search').value==""){
			if(document.getElementById('ip_search').value==""){
				//没有输入查询条件，查询所有结果
				url=Host+"/blacklist/show?appKey="+storage.APP_KEY;
			}else{
				//通过IP地址查询结果
				url=Host+"/blacklist/showByIp?appKey="+storage.APP_KEY+
				"&ip="+document.getElementById('ip_search').value;
			}
		}else{
			//通过用户名查询结果
			url=Host+"/blacklist/showByUsername?appKey="+storage.APP_KEY+
			"&username="+document.getElementById('username_search').value;
		}
		interactiveByGet(url,function(responseText){
			var date = JSON.parse(responseText);
			var str = "";
			var colunm="";
			var num = 1;
			if(date[0][0]==null){
				alert('没有结果');
			}else {
				for (var i = 0; i < date[0].length; i++) {

					if (i % 2 != 0) {
						colunm = "odd";
					} else {
						colunm = "even";
					}
					if (!date[0][i].customerId)
						date[0][i].customerId = "";
					if (!date[0][i].limitedIp)
						date[0][i].limitedIp = "";
					if (!date[0][i].firDate)
						date[0][i].firDate = "";
					if (!date[0][i].secDate)
						date[0][i].secDate = "";
					if (!date[0][i].thrDate)
						date[0][i].thrDate = "";
					if (!date[0][i].absoluateDate)
						date[0][i].absoluateDate = "";
					str = str + "<tr class=\"" + colunm + "\">" +
					"<td>" + num + "</td>" +
					"<td>" + date[0][i].appKey + "</td>" +
					"<td>" + date[0][i].customerId + "</td>" +
					"<td>" + date[0][i].limitedIp + "</td>" +
					"<td>" + (new Date(date[0][i].firDate)).Format("yyyy-MM-dd hh:mm:ss") + "</td>" +
					"<td>" + (new Date(date[0][i].secDate)).Format("yyyy-MM-dd hh:mm:ss") + "</td>" +
					"<td>" + (new Date(date[0][i].thrDate)).Format("yyyy-MM-dd hh:mm:ss")+ "</td>" +
					"<td>" + (new Date(date[0][i].absoluteDate)).Format("yyyy-MM-dd hh:mm:ss")+ "</td>" +
					"<td><img title=\'解除黑名单冻结\' class=\"blacklist_reset\" src=\"" + document.getElementById('imageRoot').value + "/reset_icon.png\" /></td>";
					num++;
				}
				document.getElementById("blacklist_table_content").innerHTML = "<th style=\"width:50px;\">序号</th>" +
				"<th>APP序列号</th> " +
				"<th>用户名</th>" +
				"<th>IP地址</th>" +
				"<th>第一次封号时间</th>" +
				"<th>第二次封号时间</th>" +
				"<th>第三次封号时间</th>" +
				"<th>冻结时间</th>" +
				"<th>操作</th>" + str;
			}
			//重置记录事件监听
			var blacklist_reset = document.getElementsByClassName("blacklist_reset");
			for(var k=0;k<blacklist_reset.length;k++){
				(function(k){
					blacklist_reset.item(k).onclick=function(){
						var url=Host+"/blacklist/reset";
						var data = "appKey="+storage.APP_KEY+
							"&username="+this.parentNode.parentNode.getElementsByTagName('td').item(2).innerHTML+
							"&ip="+this.parentNode.parentNode.getElementsByTagName('td').item(3).innerHTML;
						var row = this.parentNode.parentNode;
						interactiveByPost(url,data, function () {
							row.getElementsByTagName('td').item(4).innerHTML="";
							row.getElementsByTagName('td').item(5).innerHTML="";
							row.getElementsByTagName('td').item(6).innerHTML="";
							row.getElementsByTagName('td').item(7).innerHTML="";
							alert("操作成功");
						});
					}
				})(k);
			}

		});
	}
/******************************************************************************************************************/
	/**
	 * 搜索黑名单事件
	 */
	function searchBlacklist(){
		document.getElementById('search_botton').onclick=function(){
			if(document.getElementById('username_search').value==""&&document.getElementById('ip_search').value==""){
				alert("请输入搜索条件");
			}else{
				readBlacklistList();
			}
		}
	}
/*****************************************************************************************************************/
	/**
	 *导航栏点击事件监听
	 */
	function barClickEventListener(){
		var storage=window.sessionStorage;
		var bar_buttons = document.getElementsByClassName('bar_button');
		for(var i=0;i<bar_buttons.length;i++){
			if(i===0){
				bar_buttons.item(i).onclick=function(){
					if(!storage.APP_NAME){
						alert("请先设置APP");
					}else{
						document.getElementsByClassName('apiController').item(0).style.display="block";
						document.getElementsByClassName('blacklist').item(0).style.display="none";
						document.getElementsByClassName('app').item(0).style.display="none";
						//读取API控制表
						readApiList();
					}
				}
			}
			if(i===1){
				bar_buttons.item(i).onclick=function(){
					if(!storage.APP_NAME){
						alert("请先设置APP");
					}else{
						document.getElementsByClassName('apiController').item(0).style.display="none";
						document.getElementsByClassName('blacklist').item(0).style.display="block";
						document.getElementsByClassName('app').item(0).style.display="none";
						readBlacklistList();
					}
				}
			}
			if(i===2){
				bar_buttons.item(i).onclick=function(){
					document.getElementsByClassName('apiController').item(0).style.display="none";
					document.getElementsByClassName('blacklist').item(0).style.display="none";
					document.getElementsByClassName('app').item(0).style.display="block";
					readAppList();
				}
			}
		}
	}
/*****************************************************************************************************************/
	/**
	 * APP修改窗口事件监听
	 */
	function appPropmtEventListener(){
		document.getElementById('app_edit_confirm').onclick=function(){
			var appId =document.getElementById('app_edit_appId').value;
			var url=Host+"/app/saveModify/"+getCookie('userId')+"/"+appId;
			var data=createTransformData(document.getElementById('app_edit_table'));
			interactiveByPost(url,data,function(responseText){
				var responseDate = JSON.parse(responseText);
				var table = document.getElementById('app_table_content');
				for(var i=1;i<table.rows.length;i++){
					if(table.rows[i].getElementsByTagName('td').item(0).getElementsByTagName('input').item(0).value!=
						appId){
						continue;
					}else{
						table.rows[i].getElementsByTagName('td').item(2).innerHTML=responseDate[0].appName;
						table.rows[i].getElementsByTagName('td').item(4).innerHTML=responseDate[0].appDescription;
						table.rows[i].getElementsByTagName('td').item(5).innerHTML=responseDate[0].appPlatform;
					}
				}
				alert("修改成功");
			});
			document.getElementsByClassName('app_edit_propmt').item(0).style.display="none";
		}
		
		document.getElementById('app_edit_cancle').onclick=function(){
			document.getElementsByClassName('app_edit_propmt').item(0).style.display="none";			
		}
	}
/********************************************************************************************************************************/
	/**
	 * API修改窗口事件监听
	 */
	function apiPropmtEventListener(){
		  document.getElementById('api_edit_confirm').onclick=function(){
			  var interfceId=this.parentNode.parentNode.getElementsByTagName('input').item(0).value;
			  var url=Host+"/interface/modifySave/"+storage.APP_ID+"/"+interfceId;
			  var data=createTransformData(document.getElementById('api_edit_table'));
			  interactiveByPost(url,data,function(responseText){
				  var responseDate = JSON.parse(responseText);
				  var table = document.getElementById('api_table_content');
				  for(var i=1;i<table.rows.length;i++){
					  if(table.rows[i].getElementsByTagName('td').item(0).getElementsByTagName('input').item(0).value!=
						  responseDate[0].interface_id){
						  continue;
					  }else{
						  if(i===1){
							  table.rows[i].getElementsByTagName('td').item(2).innerHTML='全局控制';
						  }else {
							  table.rows[i].getElementsByTagName('td').item(2).innerHTML = responseDate[0].api_name;
						  }
						  table.rows[i].getElementsByTagName('td').item(3).innerHTML=responseDate[0].frequency;
						  table.rows[i].getElementsByTagName('td').item(4).innerHTML=responseDate[0].timeout;
						  table.rows[i].getElementsByTagName('td').item(5).innerHTML=responseDate[0].unit;
					  }
				  }
				  alert("修改成功");
			  })
			document.getElementsByClassName('api_edit_propmt').item(0).style.display="none";
		}
		
		document.getElementById('api_edit_cancle').onclick=function(){
			document.getElementsByClassName('api_edit_propmt').item(0).style.display="none";	
		}
	}

/********************************************************************************************************************************/
	/**
	 * 生成传递参数
	 */
	function createTransformData(table){
		var str="";
		for(var i=0;i<table.rows.length;i++){
			if(i==0){
				str=str+table.rows[i].getElementsByTagName("td").item(1).getElementsByTagName('input').item(0).name
				+"="+table.rows[i].getElementsByTagName("td").item(1).getElementsByTagName('input').item(0).value;
			}else{
				str=str+"&"+table.rows[i].getElementsByTagName("td").item(1).getElementsByTagName('input').item(0).name
				+"="+table.rows[i].getElementsByTagName("td").item(1).getElementsByTagName('input').item(0).value;
			}
		}
		return str;
	}
/*******************************************************************************************************************************/
	/**
	 * 加载会话参数
	 */
	function loadSessionParam(){
		document.getElementById('APP_ID').value=storage.APP_ID;
		document.getElementById('APP_KEY').value=storage.APP_KEY;
		if(storage.APP_NAME){
			document.getElementById('APP_NAME').innerHTML=storage.APP_NAME;
		}else{
			document.getElementById('APP_NAME').innerHTML="";
		}
			
	}
/*******************************************************************************************************************************/
	/**
	 * APP添加事件监听
	 */
	function appAddPropmtEventListener(){
		document.getElementById('addApp').onclick=function(){
			document.getElementsByClassName('app_add_propmt').item(0).style.display="block";
		}
		
		document.getElementById('app_add_confirm').onclick=function(){
			var table=document.getElementById('app_add_table');
			if(table.rows[0].getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value==""||
					table.rows[2].getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value==""){
				alert("应用名或者平台不能为空");
			}else{
				var url=Host+"/app/save/"+getCookie('userId');
				var data=createTransformData(document.getElementById('app_add_table'));
				interactiveByPost(url,data,function(){
					var bar_buttons = document.getElementsByClassName('bar_button');
					bar_buttons.item(2).click();
				});


				//清空文本框内容
				for(var i =0;i<table.rows.length;i++){
					table.rows[i].getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value="";
				}
				document.getElementsByClassName('app_add_propmt').item(0).style.display="none";
			}
		}
		
		
		document.getElementById('app_add_cancle').onclick=function(){
			var table=document.getElementById('app_add_table');
			//清空文本框内容
			for(var i =0;i<table.rows.length;i++){
				table.rows[i].getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value="";
			}
			document.getElementsByClassName('app_add_propmt').item(0).style.display="none";

		}

	}
/*******************************************************************************************************************************/
	/**
	 * API添加事件监听
	 */
	function apiAddPropmtEventListener(){
		document.getElementById('addApi').onclick=function(){
			document.getElementsByClassName('api_add_propmt').item(0).style.display="block";
		}
		
		document.getElementById('api_add_confirm').onclick=function(){
			var table=document.getElementById('api_add_table');
			var transform=0;
			for(var k=0;k<table.rows.length;k++){
				if(table.rows[k].getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value==""){
					transform=1;	
				}
			}
			if(transform==1){
				alert("输入不能为空");
			}else{
				var url=Host+"/interface/save/"+storage.APP_ID;
				var data=createTransformData(document.getElementById('api_add_table'));
				interactiveByPost(url,data,function(){
					var bar_buttons = document.getElementsByClassName('bar_button');
					bar_buttons.item(0).click();
				});
				//清空文本框内容
				for(var i =0;i<table.rows.length;i++){
					table.rows[i].getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value="";
				}
				document.getElementsByClassName('api_add_propmt').item(0).style.display="none";
			}
		}
			
		document.getElementById('api_add_cancle').onclick=function(){
			var table=document.getElementById('api_add_table');
			//清空文本框内容
			for(var i =0;i<table.rows.length;i++){
				table.rows[i].getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value="";
			}
			document.getElementsByClassName('api_add_propmt').item(0).style.display="none";
			
		}
	}
/*******************************************************************************************************************************/
	/**
	 * API参数读取
	 */
	function readApiParam(interfaceId){
		var request = new XMLHttpRequest();
		var storage = window.sessionStorage;
		request.open("GET",document.getElementById('mediaHost').value+"/parameter/list/"+interfaceId);
		request.send();
		request.onreadystatechange=function(){
			if(request.readyState===4){
				if(request.status===200){
					var resultDate = JSON.parse(request.responseText);
					var str = "";
					var colunm="";
					var num = 1;
					for(var i=0;i<resultDate[0].length;i++){
						if(i%2!=0){
							colunm="odd";
						}else{
							colunm="even";
						}
						str=str+"<tr class=\""+colunm+"\">" +
							"<td style=\"display: none;\"><input type=\'hidden\' value=\""+resultDate[0][i].parameter_id+"\"></td>"+
							"<td>"+num+"</td>" +
							"<td><input type=\"text\" value=\""+resultDate[0][i].parameter_key+"\"</td>" +
							"<td><input type=\"text\" value=\""+resultDate[0][i].parameter_value+"\"</td>" +
							"<td><img class=\"api_param_edit\" src=\""+document.getElementById('imageRoot').value+"/edit_icon.png\" />" +
							"<img class=\"api_param_delete\" src=\""+document.getElementById('imageRoot').value+"/delete_icon.png\" /></td>";
						num++;
					}
					
					document.getElementById("api_param_table").innerHTML="<input type=\"hidden\" value=\'"+interfaceId+"\'><th style=\"width:50px;\">序号</th>" +
					"<th>参数名</th> " +
					"<th>参数值</th>" +
					"<th>操作</th>"+str;
					
					var api_param_edit=document.getElementsByClassName('api_param_edit');
					var api_param_delete=document.getElementsByClassName('api_param_delete');
					var table = document.getElementById('api_param_table');
					for(var k=0;k<table.rows.length;k++){
						(function(k){
							api_param_edit.item(k).onclick=function(){
								var node = this.parentNode.parentNode;
								var parameterId = node.getElementsByTagName('input').item(0).value;
								var data = "parameterKey="+node.getElementsByTagName('input').item(1).value+
									"&parameterValue="+node.getElementsByTagName('input').item(2).value;
								var request =  new XMLHttpRequest();
								request.open("POST",document.getElementById('mediaHost').value+"/parameter/modifySave/"+storage.APP_ID+"/"+parameterId);
								request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
								request.send(data);
								request.onreadystatechange=function() {
									if (request.readyState === 4) {
										if (request.status === 200) {
											alert("修改成功");
										}
									}
								}
							}

							api_param_delete.item(k).onclick=function(){
								if(confirm("确认删除？")){
									var request =  new XMLHttpRequest();
									var node = this.parentNode.parentNode;
									var parameterId = node.getElementsByTagName('input').item(0).value;
									request.open("GET",document.getElementById('mediaHost').value+"/parameter/delete/"+storage.APP_ID+"/"+parameterId);
									request.send();
									request.onreadystatechange=function() {
										if (request.readyState === 4) {
											if (request.status === 200) {
												var table = document.getElementById('api_param_table');
												table.deleteRow(node.rowIndex);
												for(var l=0;l<table.rows.length;l++){
													var SZ_col = table.rows[l+1].getElementsByTagName("td");
													SZ_col[1].innerHTML=l+1;
													if((l+1)%2==0){
														table.rows[l+1].className="odd";
													}else{
														table.rows[l+1].className="even";
													}
												}
											}
										}
									}
								}

							}
						})(k);
					}
				}
			}
		}
	}
	
	function apiParamButtonEventListener(){
		document.getElementById('api_param_close').onclick=function(){
			document.getElementsByClassName('api_param_propmt').item(0).style.display="none";
		}
	}
/*********************************************************************************************************************/
		/**
	     *API删除
	     */
	function apiDelete(node){
		var table = document.getElementById('api_table_content');
		var request =  new XMLHttpRequest();
		var storage = window.sessionStorage;
		request.open("GET",document.getElementById('mediaHost').value+"/interface/delete/"+storage.APP_ID+"/"+
		node.getElementsByTagName('input').item(0).value);
		request.send();	
		request.onreadystatechange=function(){
			if(request.readyState===4){
				if(request.status===200){
					table.deleteRow(node.rowIndex);
					//编号重排序
					for(var l=0;l<table.rows.length;l++){
						var SZ_col = table.rows[l+1].getElementsByTagName("td");
						SZ_col[1].innerHTML=l+1;
						if((l+1)%2==0){
							table.rows[l+1].className="odd";
						}else{
							table.rows[l+1].className="even";
						}
					}	
				}
			}
		}
	}
/*****************************************************************************************************************************/
/**
 * 日期格式化
 */
Date.prototype.Format = function (fmt) { //author: meizz
	if(this!='Invalid Date') {
		var o = {
			"M+": this.getMonth() + 1, //月份
			"d+": this.getDate(), //日
			"h+": this.getHours(), //小时
			"m+": this.getMinutes(), //分
			"s+": this.getSeconds(), //秒
			"q+": Math.floor((this.getMonth() + 3) / 3), //季度
			"S": this.getMilliseconds() //毫秒
		};
		if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (var k in o)
			if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	return "";
}
/*****************************************************************************************************************************/
	/**
	 * API参数添加事件监听
	 */
	function apiParamAddEventListener(){
		document.getElementById('api_param_add').onclick=function(){
			document.getElementsByClassName('api_param_add_propmt').item(0).style.display='block';
		}

		document.getElementById('api_param_add_cancle').onclick=function(){
			var table=document.getElementById('api_param_add_table');
			//清空文本框内容
			for(var i =0;i<table.rows.length;i++){
				table.rows[i].getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value="";
			}
			document.getElementsByClassName('api_param_add_propmt').item(0).style.display='none';
		}

		document.getElementById('api_param_add_confirm').onclick=function(){
			var request =  new XMLHttpRequest();
			
			var interfaceId=document.getElementById('api_param_table').getElementsByTagName('input').item(0).value;
			request.open("POST",document.getElementById('mediaHost').value+"/parameter/save/"+storage.APP_ID+"/"+interfaceId);
			request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			var table = document.getElementById('api_param_add_table');
			var date=createTransformData(table);
			request.send(date);
			request.onreadystatechange=function(){
				if(request.readyState===4){
					if(request.status===200){
						var table=document.getElementById('api_param_add_table');
						//清空文本框内容
						for(var i =0;i<table.rows.length;i++){
							table.rows[i].getElementsByTagName('td').item(1).getElementsByTagName('input').item(0).value="";
						}
						document.getElementsByClassName('api_param_add_propmt').item(0).style.display='none';
						var paramTable = document.getElementById('api_table_content');
						for(var i=1;i<paramTable.rows.length;i++){
							if(paramTable.rows[i].getElementsByTagName('input').item(0).value===request.responseText){
								document.getElementsByClassName('api_param').item(i-2).click();
							}
						}
					}
				}
			}
		}
	}

function getCookie(c_name)
{
	if (document.cookie.length>0)
	{
		c_start=document.cookie.indexOf(c_name + "=")
		if (c_start!=-1)
		{
			c_start=c_start + c_name.length+1
			c_end=document.cookie.indexOf(";",c_start)
			if (c_end==-1) c_end=document.cookie.length
			return unescape(document.cookie.substring(c_start,c_end))
		}
	}
	return ""
}

	//ajax异步通信方法封装
	function interactiveByGet(url,responseBody){
		var request = new XMLHttpRequest();
		request.open("GET",url);
		request.send();
		request.onreadystatechange=function (){
			if(request.readyState===4){
				if(request.status===200){
					responseBody(request.responseText);
				}
			}
		}
	}
	function interactiveByPost(url,data,responseBody){
		var request = new XMLHttpRequest();
		request.open('POST',url);
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		request.send(data);
		request.onreadystatechange=function (){
			if(request.readyState===4){
				if(request.status===200){
					responseBody(request.responseText);
				}
			}
		}
	}




	//生APP成表
	function createAppTable(date){
		var tr="<tr class=\"\">" +
			"<td class=\"app_id\" style=\"display: none;\"><input id=\"appId\" type=\"hidden\" />"+
			"<input id=\"appPage\" type=\"hidden\" value=\"1\" />" +
			"<input id=\"appPageSize\" type=\"hidden\" value=\"10\" /></td>"+
			"<td class=\"index\"></td>" +
			"<td class=\"app_name\"></td>" +
			"<td class=\"app_key\"></td>" +
			"<td class=\"app_description\"></td>" +
			"<td class=\"platform\"></td>" +
			"<td class=\"secret\"></td>" +
			"<td class=\"is_reviewed\"></td>" +
			"<td><img title=\'切换当前APP\' class=\"app_switch\" src=\""+imageRoot+"/switch_icon.png\" />" +
			"<img title=\'修改\' class=\"app_edit\" src=\""+imageRoot+"/edit_icon.png\" />" +
			"<img title=\'删除\' class=\"app_delete\" src=\""+imageRoot+"/delete_icon.png\" /></td>";
		var th="<th style=\"width:50px;\">序号</th>" +
			"<th>APP名称</th> " +
			"<th>APP序列号</th>" +
			"<th>应用描述</th>" +
			"<th>平台</th>" +
			"<th>密匙</th>" +
			"<th>状态</th>" +
			"<th>操作</th>";
		createTableContent(date[0],"",tr,th,'app_table_content');
		createAppEvent();
	}

	function createAppEvent(){
		var app_edit = document.getElementsByClassName('app_edit');
		var app_switch = document.getElementsByClassName('app_switch');
		var app_delete = document.getElementsByClassName('app_delete');
		var table = document.getElementById('app_table_content');
		for(var k=0;k<app_switch.length;k++){
			(function(k){
				//切换APP
				app_switch.item(k).onclick=function(){
					var child_row = table.getElementsByTagName("tr")[k+1];
					var SZ_col = child_row.getElementsByTagName("td");
					var storage=window.sessionStorage;
					storage.APP_ID=SZ_col[0].getElementsByTagName('input').item(0).value;
					storage.APP_NAME=SZ_col[2].innerHTML;
					storage.APP_KEY=SZ_col[3].innerHTML;
					document.getElementById('APP_ID').value=storage.APP_ID;
					document.getElementById('APP_KEY').value=storage.APP_KEY;
					document.getElementById('APP_NAME').innerHTML=storage.APP_NAME;
					alert("设置成功");
				}
				app_delete.item(k).onclick=function(){
					if(confirm('确认删除？')){
						var node=this.parentNode.parentNode;
						var url=Host+"/app/delete/"+getCookie('userId')+"/"+node.getElementsByTagName('input').item(0).value;
						alert(node.getElementsByTagName('input').item(0).value);
						interactiveByGet(url,null);
						table.deleteRow(this.parentNode.parentNode.rowIndex);
						//编号重排序
						indexSort(table);
					}
				}
				app_edit.item(k).onclick=function(){
					var app_edit_propmt=document.getElementsByClassName('app_edit_propmt').item(0);
					app_edit_propmt.style.display="block";
					document.getElementById('app_edit_appId').value=
						this.parentNode.parentNode.getElementsByTagName('td').item(0).getElementsByTagName('input').item(0).value;
					app_edit_propmt.getElementsByTagName('input').item(1).value=
						this.parentNode.parentNode.getElementsByTagName('td')[2].innerHTML;
					app_edit_propmt.getElementsByTagName('input').item(2).value=
						this.parentNode.parentNode.getElementsByTagName('td')[4].innerHTML;
					app_edit_propmt.getElementsByTagName('input').item(3).value=
						this.parentNode.parentNode.getElementsByTagName('td')[5].innerHTML;
				}
			})(k);
		}
	}

	function createTableContent(data,ftr,tr,th,tableId){
		var str = "";
		str+=ftr;
		for(var i=0;i<data.length;i++){
			str+=tr;
		}
		str=th+str;
		var table = document.getElementById(tableId);
		table.innerHTML=str;
		var rows=table.rows;
		for(var i=1;i<rows.length;i++){
			if(i%2!=0){
				rows.item(i).className='even';
			}else{
				rows.item(i).className='odd';
			}
			var tds=rows.item(i).getElementsByTagName('td');
			rows.item(i).getElementsByClassName('index').item(0).innerHTML=i;
			for(var key in data[i-1]){
				for(var j=0;j<tds.length;j++){
					if(key==tds.item(j).className){
						if(j===0){
							tds.item(j).getElementsByTagName('input').item(0).value=data[i-1][key];
						}else{
							tds.item(j).innerHTML=data[i-1][key];
						}
					}
				}
			}
		}
	}

//生成API列表
function createApiTable(data){
	var str="";
	var ftr="<tr>" +
		"<td class=\'interface_id\' style=\"display: none;\"><input type=\"hidden\" /></td>"+
		"<td class=\'index\'></td>" +
		"<td class=\'api_name\'></td>" +
		"<td class=\'frequency\'></td>" +
		"<td class=\'timeout\'></td>" +
		"<td class=\'unit\'></td>" +
		"<td class=\'\'></td>" +
		"<td><img title=\'编辑\' class=\"api_edit\" src=\""+imageRoot+"/edit_icon.png\" /></td>";
	var tr="<tr>" +
		"<td class=\'interface_id\' style=\"display: none;\"><input type=\"hidden\" /></td>"+
		"<td class=\'index\'></td>" +
		"<td class=\'api_name\'></td>" +
		"<td class=\'frequency\'></td>" +
		"<td class=\'timeout\'></td>" +
		"<td class=\'unit\'></td>" +
		"<td><a class=\'api_param\' style=\'cursor: pointer;\'>配置参数</a></td>" +
		"<td><img title=\'编辑\' class=\"api_edit\" src=\""+imageRoot+"/edit_icon.png\" />"+
		"<img title=\'删除\' class=\"api_delete\" src=\""+imageRoot+"/delete_icon.png\"  /></td>";
	for(var i=0;i<data[1].length;i++){
		str+=tr;
	}
	var th="<th style=\"width:50px;\">序号</th>" +
		"<th>API名称</th> " +
		"<th>控制频次</th>" +
		"<th>时间长度</th>" +
		"<th>时间单位</th>" +
		"<th>参数列表</th>" +
		"<th>操作</th>";
	var table = document.getElementById('api_table_content');
	table.innerHTML=th+ftr+str;
	var rows=table.rows;
	var ftds=rows.item(1).getElementsByTagName('td');
	rows.item(1).getElementsByClassName('index').item(0).innerHTML=1;
	rows.item(1).className='even';
	for(var key in data[0]){
		for(var j=0;j<ftds.length;j++){
			if(key==ftds.item(j).className){
				if(j===0){
					ftds.item(j).getElementsByTagName('input').item(0).value=data[0][key];
				}else if(j===2){
					ftds.item(j).innerHTML='全局控制';
				}else{
					ftds.item(j).innerHTML=data[0][key];
				}
			}
		}
	}
	for(var i=2;i<rows.length;i++){
		if(i%2!=0){
			rows.item(i).className='even';
		}else{
			rows.item(i).className='odd';
		}
		var tds=rows.item(i).getElementsByTagName('td');
		rows.item(i).getElementsByClassName('index').item(0).innerHTML=i;
		for(var key in data[1][i-2]){
			for(var j=0;j<tds.length;j++){
				if(key==tds.item(j).className){
					if(j===0){
						tds.item(j).getElementsByTagName('input').item(0).value=data[1][i-2][key];
					}else{
						tds.item(j).innerHTML=data[1][i-2][key];
					}
				}
			}
		}
	}

	document
}

	function createApiEvent(){
		var api_delete = document.getElementsByClassName('api_delete');
		var api_edit = document.getElementsByClassName('api_edit');
		var api_param = document.getElementsByClassName('api_param');
		var table = document.getElementById("api_table_content");
		for(var k=0;k<table.rows.length-1;k++){
			(function(k){
				if(k<table.rows.length-2){
					api_delete.item(k).onclick=function(){
						if(confirm('确认删除？')){
							var node=this.parentNode.parentNode;
							apiDelete(node);
						}
					}
				}

				api_edit.item(k).onclick=function(){
					var api_edit_propmt=document.getElementsByClassName('api_edit_propmt').item(0);
					api_edit_propmt.style.display="block";
					if(k===0){
						api_edit_propmt.getElementsByTagName('input').item(1).style.display='none';
						api_edit_propmt.getElementsByTagName('input').item(1).value='#overall#';
					}else{
						api_edit_propmt.getElementsByTagName('input').item(1).value=
							this.parentNode.parentNode.getElementsByTagName('td')[2].innerHTML;
					}
					api_edit_propmt.getElementsByTagName('input').item(0).value=
						this.parentNode.parentNode.getElementsByTagName('input').item(0).value;
					api_edit_propmt.getElementsByTagName('input').item(2).value=
						this.parentNode.parentNode.getElementsByTagName('td')[3].innerHTML;
					api_edit_propmt.getElementsByTagName('input').item(3).value=
						this.parentNode.parentNode.getElementsByTagName('td')[4].innerHTML;
					api_edit_propmt.getElementsByTagName('input').item(4).value=
						this.parentNode.parentNode.getElementsByTagName('td')[5].innerHTML;
				}

				if(k<table.rows.length-2){
					api_param.item(k).onclick=function(){
						document.getElementsByClassName('api_param_propmt').item(0).style.display="block";
						var interfaceId=this.parentNode.parentNode.getElementsByTagName('input').item(0).value;
						readApiParam(interfaceId);
					}
				}
			})(k);
		}
	}

function indexSort(table){
	for(var l=0;l<table.rows.length;l++){
		var SZ_col = table.rows[l+1].getElementsByTagName("td");
		SZ_col[1].innerHTML=l+1;
		if((l+1)%2==0){
			table.rows[l+1].className="odd";
		}else{
			table.rows[l+1].className="even";
		}
	}
}
function DelCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() + (-1 * 24 * 60 * 60 * 1000));
	var cval =getCookie(name)
	document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
}