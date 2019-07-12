<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath %>/static/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>/static/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/static/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/select-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/editor/kindeditor.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/laydate/laydate.js"></script>
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
	
	
	
});


</script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">房间信息管理</a></li>
    <li><a href="#">添加放间信息</a></li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>房间信息</span></div>
  <div id="usual1" class="usual">
    <div id="tab1" class="tabson">
    <form action="<%=basePath %>/roomsEditSave.do" method="post">
      <ul class="forminfo">
      <li>
          <div class="vocation"> 
           <input name="id" type="hidden" required="required" class="dfinput" value="${roomsInfoMap.id }" style="width:344px;"/>  
          </div>
        </li>
        <br />
        <li>
          <label name="roomNum">房间号<b>*</b></label>
          <div class="vocation"> 
           <input name="roomNum" type="text" required="required" class="dfinput" value="${roomsInfoMap.room_num }" style="width:344px;"/>  
          </div>
        </li>
        <br />
        <li>
          <label for="createDate" >入住时间<b>*</b></label>
          <div class="vocation">
								<input name="createTime" type="text" value="${roomsInfoMap.createTime }"
									class="laydate-icon span1-1" id="Calendar"
									style="width: 324px; height: 30px; line-height: 28px; text-indent: 10px;" />
							</div>
        </li>
        <br />
        <li>
          <label>&nbsp;</label>
          <input type="submit" class="btn" value="修改"/>
        </li>
      </ul>
      </form>
    </div>
  </div>
  <script type="text/javascript"> 
  
      $("#usual1 ul").idTabs(); 
    </script> 
  <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	!function(){
laydate.skin('qianhuang');
laydate({elem: '#Calendar'});
laydate.skin('qianhuang');
laydate({elem: '#Calendar2'});
}();
$(function dd(){
		var d=new Date(),str="";
		str+=(d.getFullYear()+"-");
		str+="0";
		str+=(d.getMonth()+1+"-");
		str+=d.getDate();
		$("#Calendar").attr("value",str);
		$("#Calendar2").attr("value",str);
	});

	</script> 
</div>
</body>
</html>
