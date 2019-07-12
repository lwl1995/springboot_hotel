<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath %>/static/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/static/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/editor/kindeditor.js"></script>

<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
	
	$("#name").focus(function(){
		$(this).css("background-color","#0FF")
		var text=$(this).val();
		if(text=="请填写姓名"){
			$(this).val("");
			}
		});
		
		$("#name").blur(function(){
		$(this).css("background-color","#FFF")
		var text=$(this).val();
		if(text==""){
			$(this).val("请填写姓名");
			}
		});

	
});

function checkidentity(){
	var id=$("#identity").val();
	var notice2=$("#notice2");
	var regid=/^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
	if(regid.test(id)==false){
	   $(notice2).html("身份证输入不合法");	
	   return false;
	}else{
		$(notice2).html("");
		return true;
		}
}

function checkphone(){
	var id=$("#phone").val();
	var notice3=$("#notice3");
	var regid=/^[1-9]{8,11}$/;
	if(regid.test(id)==false){
	   $(notice3).html("电话号码输入不合法");	
	   return false;
	}else{
		$(notice3).html("");
		return true;
		}
}
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">会员管理</a></li>
    <li><a href="#">修改会员</a></li>
    </ul>
    </div>
<form action="<%=basePath %>/updateUserInfo.do" method="post">
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">修改密账号和密码</a></li> 
    
  	</ul>
    </div> 
    
  	<div id="tab1" class="tabson">
    
    <div class="formtext">Hi，<b>admin</b>，请填写要修改的用户信息</div>
    
    <ul class="forminfo">
    	<li>
    		<input name="id" type="hidden" class="dfinput" value="${userInfoMap.id }" id="name" />
    	</li>
    	
    	<li><label>用户名<b>*</b></label>
    		<input name="username" type="text" class="dfinput"  value="${userInfoMap.username }" id="name" />
    	</li>
    
    	<li><label>密码<b>*</b></label>
    		<input name="pwd" type="text" class="dfinput" value="${userInfoMap.pwd }" id="name" />
    	</li>
   
    	<li><label>&nbsp;</label>
    		<input name="" type="submit" class="btn" value="修改"/>
    	</li>
    </ul>
    
    </div> 
</form>

</body>

</html>
