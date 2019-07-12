<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath %>/static/css/style.css" rel="stylesheet" type="text/css" />
<!-- 引入bootstrap分页 -->
<link rel="stylesheet" href="<%=basePath%>/static/js/bootstrap/bootstrap.css" />
<script src="<%=basePath%>/static/js/bootstrap/jquery.min.js"></script>
<script src="<%=basePath%>/static/js/bootstrap/bootstrap.min.js"></script>
<script src="<%=basePath%>/static/js/bootstrap/bootstrap-paginator.js"></script>
<script>
$(function() {
	$('#pagination').bootstrapPaginator({
		bootstrapMajorVersion: 3,
		currentPage: ${requestScope.pageInfo.pageNum },
		totalPages: ${requestScope.pageInfo.pages },
		pageUrl: function(type, page, current) {
			return 'getAllOrders.do?pageNum=' + page;
		},
		itemTexts: function(type, page, current) {
			switch(type) {
				case "first":
					return "首页";
				case "prev":
					return "上一页";
				case "next":
					return "下一页";
				case "last":
					return "末页";
				case "page":
					return page;
			}
		}
	});
});
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>
</head>

<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html">首页</a></li>
			<li><a href="#">订单管理</a></li>
			<li><a href="#">订单信息查询</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li id ="add" class="click"><span><img
						src="<%=basePath %>/static/images/t01.png" /></span>查看</li>
				<%-- <li class="click"><span><img
						src="<%=basePath %>/static/images/t02.png" /></span>修改</li> --%>
				<li id="batchDel"><span><img src="<%=basePath %>/static/images/t03.png" /></span>删除</li>
			</ul>

		

		</div>
		<table class="tablelist">
			<thead>
				<tr>
					<th><input id="selectAll" name="" type="checkbox" value="" /></th>
					<th>序号</th>
					<th>订单编号</th>
					<th>房间号</th>
					<th>客人姓名</th>
					<th>订单金额</th>
					<th>结算状态</th>
					<th>订单时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageInfo.list }" var="map" varStatus="num">
					<tr>
						<td><input name="ck" type="checkbox" value="${map.id }"/></td>
						<td>${map.id }</td>
						<td>${map.order_num }</td>
						<td>${map.room_num }</td>
						<td>${map.customer_name }</td>
						<td>${map.order_money }</td>
						<td>${map.order_status=='1'?'已结算':'未结算' }</td>
						<td>${map.create_date }</td>
						<td>
							<a iri="${map.id }" id="delBtn" href="javascript:void(0)" class="tablelink delbtnclass"> 删除</a>
							<a href="<%=basePath %>/getOrderInfoById.do?id=${map.id }" class="tablelink"> 修改</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
 <!-- bootstrap的分页 -->
 <!-- 把分页搞出来 -->
 <nav class="text-center">
 	<ul id="pagination"></ul>
 </nav>

		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>
			<div class="tipinfo">
				<span><img src="images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>
			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" /> &nbsp; <input
					name="" type="button" class="cancel" value="取消" />
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	$(function(){
		/* 删除操作 */
		$(".delbtnclass").click(function(){
			var flag = window.confirm("您确认删除本条记录吗？");
			if(flag){
				var iri = $(this).attr("iri");
				$.ajax({
					url:"<%=basePath %>/delOrderInfo.do",
					type:"POST",
					data:{id:iri},
					dataType:"JSON",
					success:function(result){
						if(result){//代表删除成功
							window.location.reload();
						}else{
							window.alert("删除失败，请您重新试一次!");
						}
					}
				});
			}
		});
		/* 全选复选框按钮 */
		$("#selectAll").change(function(){
			var flag =  $(this).prop("checked");
			if(flag){//全选
				$("input[name=ck]").prop("checked",true);
			}else{//全部取消
				$("input[name=ck]").prop("checked",false);
			}
		});
	
	/* 批量删除 */
	$("#batchDel").click(function(){
		//先判断有没有被勾选
		var $ckChecked = $("input[name=ck]:checked");
		var len = $ckChecked.size();
		if(len>=1){
			var ids = "";
			$ckChecked.each(function(index,dom){
				var id = $(dom).val();
				ids+=id+",";
			});
			//将多个id传递给后台的controller方法
			$.ajax({
				url:"<%=basePath %>/batchOrder.do",
				type:"POST",
				dataType:"JSON",
				data:{idAttr:ids},
				success:function(result){
					if(result){
						window.location.reload();
					}else{
						alert("批量删除失败");
					}
				}
			});
		}else{
			alert("亲，请选择要删除的记录!");
		}
	});
});

	$("#add").click(function(){
		window.location.href="<%=basePath %>/addOrder.do";
	});


	
	
	
	</script>
</body>
</html>
