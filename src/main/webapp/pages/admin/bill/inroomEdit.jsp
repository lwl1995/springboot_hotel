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
    <li><a href="#">入住信息管理</a></li>
    <li><a href="#">添加入住信息</a></li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>入住信息</span></div>
  <div id="usual1" class="usual">
    <div id="tab1" class="tabson">
    <form action="<%=basePath %>/inroomSave.do" method="post">
      <ul class="forminfo">
      <li>
          <label name="roomNum">编号<b>*</b></label>
          <div class="vocation"> 
           <input name="id" type="text" required="required" readonly="true" class="dfinput" value="${roomEdit.id }" style="width:344px;"/>  
          </div>
        </li>
        <br />
        <li>
          <label name="roomNum">房间号<b>*</b></label>
          <div class="vocation"> 
           <input name="roomNum" type="text" required="required" class="dfinput" value="${roomEdit.room_num }" style="width:344px;"/>  
          </div>
        </li>
        <br />
        <li>
          <label for="roomtypeName" >房间类型<b>*</b></label>
          <div class="vocation">
            <input name="room_type_name" required="required" type="text" class="dfinput" value="${roomEdit.room_type_name }" style="width:344px;"/>
          </div>
        </li>
        <br />
        <li>
          <label for="name" >客人姓名<b>*</b></label>
          <div class="vocation">
            <input name="customerName" required="required" type="text" class="dfinput" value="${roomEdit.customer_name }" style="width:344px;"/>
          </div>
        </li>
        <br />
        <%-- <li>
          <label for="gender">性别<b>*</b></label>
          <div class="vocation">
          <input name="gender" required="required" type="text" class="dfinput" value="${roomEdit.gender==1?'男':'女' }" style="width:344px;"/>  <!-- 男为1  女为0 -->
          </div>
        </li>
        <br /> --%>
        <li>
          <label for="sex">性别<b>*</b></label>
          <input name="gender" type="radio" value="1" checked="checked" />  <!-- 男为1  女为0 -->
          男&nbsp;&nbsp;&nbsp;&nbsp;
          <input name="gender" type="radio" value="0" />
          女
        </li>
        <br />
        
        <li>
          <label for="idcard" >身份证号码<b>*</b></label>
          <div class="vocation">
            <input name="idcard" type="text" required="required" pattern="\d{17}[0-9X]" class="dfinput" value="${roomEdit.idcard }" style="width:344px;"/>
          </div>
        </li>
        <br />
        <li>
          <label for="phone" >手机号码<b>*</b></label>
          <div class="vocation">
            <input name="phone" required="required" pattern="1[35789]\d{9}" type="text" class="dfinput" value="${roomEdit.phone }" style="width:344px;"/>
          </div>
        </li>
        <br />
       <%--  <li>
          <label for="money" >押金<b>*</b></label>
          <div class="vocation">
            <input name="money" required="required" pattern="[1-9]\d*|0" type="text" class="dfinput" value="${roomEdit.money }" style="width:344px;"/>
          </div>
        </li>
        <br /> --%>
        <li>
          <label for="createDate" >入住时间<b>*</b></label>
          <div class="vocation">
								<input name="create_date" type="text" value="${roomEdit.create_date }"
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
