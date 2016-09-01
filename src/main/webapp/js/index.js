window.onload=function(){

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
		this.src="/FrequencyControl/image/dropdown_click_icon.png";
	}
	document.getElementById('dropdown_icon').onmouseout=function(){
		this.src="/FrequencyControl/image/dropdown_icon.png";
	}


	/**
	 * 读取登录状态
	 */
	if(getCookie('username')){
		document.getElementById('loginStatus').innerHTML='欢迎您! '+getCookie('username');
		document.getElementById('loginStatus').href="";
	}
}




/*************************************************************************************************************/
	/**
	 * AJAX异步加载APP列表
	 */
	 function readAppList(){
		var request = new XMLHttpRequest();
		request.open("GET","/FrequencyControl/app/list/"+getCookie('userId'));
		request.send();
		request.onreadystatechange=function(){
			if(request.readyState===4){
				if(request.status===200){
					var date = JSON.parse(request.responseText);
					var str = "";
					var colunm="";
					var num = 1;

					for(var j=0;j<date[0].length;j++){
						if(j%2!=0){
							colunm="odd";
						}else{
							colunm="even";
						}
						str=str+"<tr class=\""+colunm+"\">" +
							"<td style=\"display: none;\"><input id=\"appId\" type=\"hidden\" value=\""+date[0][j].app_id+"\"/>" +
								"<input id=\"appPage\" type=\"hidden\" value=\"1\" />" +
								"<input id=\"appPageSize\" type=\"hidden\" value=\"10\" /></td>"+
							"<td>"+num+"</td>" +
							"<td>"+date[0][j].app_name+"</td>" +
							"<td>"+date[0][j].app_key+"</td>" +
							"<td>"+date[0][j].app_description+"</td>" +
							"<td>"+date[0][j].platform+"</td>" +
							"<td>"+date[0][j].secret+"</td>" +
							"<td>"+date[0][j].is_reviewed+"</td>" +
							"<td><img title=\'切换当前APP\' class=\"app_switch\" src=\""+document.getElementById('imageRoot').value+"/switch_icon.png\" />" +
							"<img title=\'修改\' class=\"app_edit\" src=\""+document.getElementById('imageRoot').value+"/edit_icon.png\" />" +
							"<img title=\'删除\' class=\"app_delete\" src=\""+document.getElementById('imageRoot').value+"/delete_icon.png\" /></td>";
						num++;
						
					}

					document.getElementById("app_table_content").innerHTML="<th style=\"width:50px;\">序号</th>" +
							"<th>APP名称</th> " +
							"<th>APP序列号</th>" +
							"<th>应用描述</th>" +
							"<th>平台</th>" +
							"<th>密匙</th>" +
							"<th>状态</th>" +
							"<th>操作</th>"+str;
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
									var request = new XMLHttpRequest();
									request.open("GET","/FrequencyControl/app/delete/"+getCookie('userId')+"/"+document.getElementById('appId').value);
									request.send();
									table.deleteRow(this.parentNode.parentNode.rowIndex);
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
			}
		}
	 }
/***************************************************************************************************************/
	/**
	 *AJAX异步加载API控制列表
	 */
	function readApiList(){
		var request = new XMLHttpRequest();
		var stroage = window.sessionStorage;
		request.open("GET","/FrequencyControl/interface/list/"+stroage.APP_ID);
		request.send();
		request.onreadystatechange=function(){
			if(request.readyState===4){
				if(request.status===200){
					var date = JSON.parse(request.responseText);
					var str = "";
					var colunm="";
					var num = 1;
					for(var j=0;j<date.length-1;j++){
					
						if(j%2!=0){
							colunm="odd";
						}else{
							colunm="even";
						}
						
						str=str+"<tr class=\""+colunm+"\">" +
							"<td style=\"display: none;\"><input type=\"hidden\" value=\""+date[j].interface_id+"\"/></td>"+
							"<td>"+num+"</td>" +
							"<td>"+date[j].api_name+"</td>" +
							"<td>"+date[j].frequency+"</td>" +
							"<td>"+date[j].timeout+"</td>" +
							"<td>"+date[j].unit+"</td>" +
							"<td></td>" +
							"<td><img title=\'编辑\' class=\"api_edit\" src=\""+document.getElementById('imageRoot').value+"/edit_icon.png\" /></td>";
						num++;
						
					}
					for(var j=0;j<date[1].length;j++){
						if(j%2!=0){
							colunm="even";
						}else{
							colunm="odd";
						}
						str=str+"<tr class=\""+colunm+"\">" +
						"<td style=\"display: none;\"><input type=\"hidden\" value=\""+date[1][j].interface_id+"\"/></td>"+
						"<td>"+num+"</td>" +
						"<td>"+date[1][j].api_name+"</td>" +
						"<td>"+date[1][j].frequency+"</td>" +
						"<td>"+date[1][j].timeout+"</td>" +
						"<td>"+date[1][j].unit+"</td>" +
						"<td class=\"api_param\" style=\"cursor: pointer;text-decoration: underline;\">"+"控制参数"+"</td>" +
						"<td><img title=\'编辑\' class=\"api_edit\" src=\""+document.getElementById('imageRoot').value+"/edit_icon.png\" />"+
						"<img title=\'删除\' class=\"api_delete\" src=\""+document.getElementById('imageRoot').value+"/delete_icon.png\" /></td>";
						num++;

					}
					document.getElementById("api_table_content").innerHTML="<th style=\"width:50px;\">序号</th>" +
							"<th>API名称</th> " +
							"<th>控制频次</th>" +
							"<th>时间长度</th>" +
							"<th>时间单位</th>" +
							"<th>参数列表</th>" +
							"<th>操作</th>"+str;
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
								api_edit_propmt.getElementsByTagName('input').item(0).value=
									this.parentNode.parentNode.getElementsByTagName('input').item(0).value;
								api_edit_propmt.getElementsByTagName('input').item(1).value=
									this.parentNode.parentNode.getElementsByTagName('td')[2].innerHTML;
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
									var interfaceId=this.parentNode.getElementsByTagName('input').item(0).value;
									readApiParam(interfaceId);
								}
							}
		 				})(k);
					}	
				}
			}				 
		}
	}
/*****************************************************************************************************************/
	/**
	 * AJAX异步查询黑名单列表
	 */	
	function readBlacklistList(){
		var request = new XMLHttpRequest();
		var storage=window.sessionStorage;
		if(document.getElementById('username_search').value==""){
			if(document.getElementById('ip_search').value==""){
				//没有输入查询条件，查询所有结果
				request.open("GET","/FrequencyControl/blacklist/show?appKey="+storage.APP_KEY);
			}else{
				//通过IP地址查询结果
				request.open("GET","/FrequencyControl/blacklist/showByIp?appKey="+storage.APP_KEY+
						"&ip="+document.getElementById('ip_search').value);
			}
		}else{
			//通过用户名查询结果
			request.open("GET","/FrequencyControl/blacklist/showByUsername?appKey="+storage.APP_KEY+
					"&username="+document.getElementById('username_search').value);
		}
		request.send();		
		request.onreadystatechange=function(){
			if(request.readyState===4){
				if(request.status===200){
					var date = JSON.parse(request.responseText);
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
								var request = new XMLHttpRequest();
								request.open("POST","/FrequencyControl/blacklist/reset");
								var date = "appKey="+storage.APP_KEY+
									"&username="+this.parentNode.parentNode.getElementsByTagName('td').item(2).innerHTML+
									"&ip="+this.parentNode.parentNode.getElementsByTagName('td').item(3).innerHTML;
								request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
								request.send(date);
								var row = this.parentNode.parentNode;
								request.onreadystatechange=function(){
									if(request.readyState===4){
										if(request.status===200){
											row.getElementsByTagName('td').item(4).innerHTML="";
											row.getElementsByTagName('td').item(5).innerHTML="";
											row.getElementsByTagName('td').item(6).innerHTML="";
											row.getElementsByTagName('td').item(7).innerHTML="";
											alert("操作成功");
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
			var request = new XMLHttpRequest();
			var appId =document.getElementById('app_edit_appId').value;
			request.open("POST","/FrequencyControl/app/saveModify/1/"+appId);
			var date=createTransformData(document.getElementById('app_edit_table'));
			request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			request.send(date);
			request.onreadystatechange=function(){
				if(request.readyState===4){
					if(request.status===200){
						var responseDate = JSON.parse(request.responseText);
						
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
						alert("success");
					}
				}
			}
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
			var stroage = window.sessionStorage;
			  var interfceId=this.parentNode.parentNode.getElementsByTagName('input').item(0).value;
			var request = new XMLHttpRequest();
			request.open("POST","/FrequencyControl/interface/modifySave/"+stroage.APP_ID+"/"+interfceId);
			var date=createTransformData(document.getElementById('api_edit_table'));
			request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			request.send(date);
			request.onreadystatechange=function(){
				if(request.readyState===4){
					if(request.status===200){
						var responseDate = JSON.parse(request.responseText);
						var table = document.getElementById('api_table_content');
						for(var i=1;i<table.rows.length;i++){
							if(table.rows[i].getElementsByTagName('td').item(0).getElementsByTagName('input').item(0).value!=
								responseDate[0].interface_id){
								continue;
							}else{
								table.rows[i].getElementsByTagName('td').item(2).innerHTML=responseDate[0].api_name;
								table.rows[i].getElementsByTagName('td').item(3).innerHTML=responseDate[0].frequency;
								table.rows[i].getElementsByTagName('td').item(4).innerHTML=responseDate[0].timeout;
								table.rows[i].getElementsByTagName('td').item(5).innerHTML=responseDate[0].unit;
							}
						}
						alert("success");
					}
				}
			}
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
	 * 设置Cookies
	 */
	function setCookie(cname, cvalue, exdays) {
	    var d = new Date();
	    d.setTime(d.getTime() + (exdays*24*60*60*1000));
	    var expires = "expires="+d.toUTCString();
	    document.cookie = cname + "=" + cvalue + "; " + expires;
	}
/*******************************************************************************************************************************/
	/**
	 * 加载会话参数
	 */
	function loadSessionParam(){
		var storage=window.sessionStorage;
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
				var request = new XMLHttpRequest();
				request.open("POST","/FrequencyControl/app/save/1");
				var date=createTransformData(document.getElementById('app_add_table'));
				request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				request.send(date);
				request.onreadystatechange=function(){
					if(request.readyState===4){
						if(request.status===200){
							var bar_buttons = document.getElementsByClassName('bar_button');
							bar_buttons.item(2).click();
						}
					}
				}
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
				var storage = window.sessionStorage;
				var request = new XMLHttpRequest();
				request.open("POST","/FrequencyControl/interface/save/"+storage.APP_ID);
				var date=createTransformData(document.getElementById('api_add_table'));
				request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				alert(date);
				request.send(date);
				request.onreadystatechange=function(){
					if(request.readyState===4){
						if(request.status===200){
							if(confirm('是否进行参数配置')){
								
							}
							var bar_buttons = document.getElementsByClassName('bar_button');
							bar_buttons.item(0).click();
						}
					}
				}
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
		request.open("GET","/FrequencyControl/parameter/list/"+interfaceId);
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
								request.open("POST","/FrequencyControl/parameter/modifySave/"+parameterId);
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
									request.open("GET","/FrequencyControl/parameter/delete/"+parameterId);
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
		request.open("GET","/FrequencyControl/interface/delete/"+storage.APP_ID+"/"+
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
			request.open("POST","/FrequencyControl/parameter/save/"+interfaceId);
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
						alert(paramTable.rows.length);
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
	