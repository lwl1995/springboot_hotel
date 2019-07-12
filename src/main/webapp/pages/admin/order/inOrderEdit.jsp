<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath %>/static/css/style.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath %>/static/css/select.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=basePath %>/static/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/static/js/jquery.idTabs.min.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/static/js/select-ui.min.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/static/js/editor/kindeditor.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/static/js/laydate/laydate.js"></script>
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
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">订单管理</a></li>
			<li><a href="#">添加订单</a></li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>添加订单</span>
		</div>
		<div id="usual1" class="usual">
			<div id="tab1" class="tabson">
			<form action="<%=basePath%>/saveOrder.do" type="POST">
				<ul class="forminfo">
					<input name="id" type="hidden" class="dfinput" style="width: 344px;" value="${orderEdit.id }"/>
					<br />
					<li><label for="name">订单编号<b>*</b></label>
						<div class="vocation">
							<input name="order_num" type="text" class="dfinput" style="width: 344px;" value="${orderEdit.order_num }"/>
						</div>
					</li>
					<br />
					<li><label for="name">房间号<b>*</b></label>
						<div class="vocation">
							<input name="room_num" type="text" class="dfinput" style="width: 344px;" value="${orderEdit.room_num }"/>
						</div>
					</li>
					<br />
					<li><label for="customer_name">客人姓名<b>*</b></label>
						<div class="vocation">
							<input name="customer_name" type="text" class="dfinput" style="width: 344px;" value="${orderEdit.customer_name }"/>
						</div>
					</li>
					<br />
					<li><label for="name">订单金额<b>*</b></label>
						<div class="vocation">
							<input name="order_money" type="text" class="dfinput" style="width: 344px;" value="${orderEdit.order_money }"/>
						</div>
					</li>
					<br />
						<li><label>结算状态<b>*</b></label>
								<div style="padding-top: 7px">
									<c:choose>
										<c:when test="${orderEdit.order_status=='1' }">
											<input type="radio" name="orderStatus" checked="checked" value="0"/>未结算 
											<input type="radio" name="orderStatus" value="0"/>已结算
										</c:when>
										<c:otherwise>
											<input type="radio" name="orderStatus"  value="0"/>未结算 
											<input type="radio" name="orderStatus" checked="checked" value="1"/>已结算
										</c:otherwise>
									</c:choose>
								</div>
						</li>
					<br />
					<li><label for="date">订单时间<b>*</b></label>
						<div class="vocation">
							<input name="createDate" type="text" value="${orderEdit.createDate }" class="laydate-icon span1-1" id="Calendar" style="width: 324px; height: 30px; line-height: 28px; text-indent: 10px;" />
						</div>
					</li>
					<br />
					<li>
						<label>&nbsp;</label> 
						<input type="submit"class="btn" value="修改" />
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
	
<script type="text/javascript">
	$(function(){
		//给消费金额添加一个获取焦点的事件，将错误信息抹除
		$("input[name=money]").focus(function(){
			$("#moneyErrorMsg").html("");
		});
		
		//根据房间号事件来获取当前已经入住人的详细信息
		$("select[name=roomId]").change(function(){
			var v = $("select[name=roomId]>option:selected").val();
			$.ajax({
				url:"<%=basePath %>/getInRoomInfoByRoomId.do",
				type:"POST",
				dataType:"JSON",
				data:{roomId:v},
				success:function(result){
					var rs = JSON.parse(result);
					//回显给文本输入框设置值
					$("input[name=customerName]").val(rs.customer_name);
					
					//入住时间
					$("input[name=createDate]").val(rs.create_date);
					$("input[name=createDate]").attr("disabled","disabled");
					
					//身份证号
					$("input[name=idcard]").val(rs.idcard);
					
					//手机号码
					$("input[name=phone]").val(rs.phone);
					
				}
			});
		});
	});
</script>
	</div>
</body>
</html>
