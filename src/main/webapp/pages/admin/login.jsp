﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="<%=basePath %>/static/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath %>/static/js/jquery-2.1.4.min.js"></script>
<script src="<%=basePath %>/static/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#df7611; background-image:url(<%=basePath %>/static/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录天下酒店客房管理界面平台</span>       
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <form action="<%=basePath %>/login.do" method="post">
	   <ul>
	   		<!-- 用正则表达式(前台验证)：\w:代表用户名只能为数字、字母、下划线的组合，括号中的3,12代表：长度为3~12位 required:代表不能为空-->
		   <li>
		   		<input name="username" value="bigbird" type="text" class="loginuser" placeholder="长度为6~12的数字、字母、下划线"
		    	onclick="JavaScript:this.value=''" pattern="\w{3,12}" required="required"/>
	    	</li>
		   <li>
		   		<input name="pwd" value="123456" type="password" class="loginpwd" placeholder="长度为6~20的数字、字母、下划线"
		   		onclick="JavaScript:this.value=''" pattern="\w{6,12}" required="required"/>
   		   </li>
		   <li>
		   		<input type="submit" class="loginbtn" value="登录" />
   		   </li>
	   </ul>
    </form>
   
    </div>
    
    </div>
    
    
    
    <!--<div class="loginbm">版权所有  2014  <a href="http://www.uimaker.com">uimaker.com</a>  仅供学习交流，勿用于任何商业用途</div>-->
	
    
</body>

</html>
