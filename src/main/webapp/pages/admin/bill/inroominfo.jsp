<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!-- forEach循环的时间格式 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>">
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
			return '/getInRoomInfo.do?pageNum=' + page;
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
<%-- <script type="text/javascript" src="<%=basePath %>/static/js/jquery.js"></script> --%>
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
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="main.html">首页</a></li>
    <li><a href="#">入住管理</a></li>
    <li><a href="#">入住信息查询</a></li>
  </ul>
</div>
<div class="rightinfo">
  <div class="tools">
    <ul class="toolbar">
      <li id ="add" class="click"><span><img src="<%=basePath %>/static/images/t01.png" /></span>添加</li>
      <li id="batchDel"><span><img src="<%=basePath %>/static/images/t03.png" /></span>删除</li>
      <li><input type="button" value="批量导出" onclick="exportCheck();"/></li>
    </ul>
    
    <div class="toolbar1">
      <table>
        <form method="post" name="serch" action="<%=basePath%>/getInRoomInfoByCondition.do">
          <tr style="width: 155px;height:120px;">
            <td class="zi"><span>选择分类：</span></td>
            <td>
            	<select name="type">
	                <option value="1">房间号</option>
	                <option value="2">客人姓名</option>
	                <option value="3">手机号码</option>
	                <option value="4">身份证号码</option>
               </select>
            </td>
            <td class="zi"><span>关键字：</span></td>
            <td><input name="keyWord" type="text" placeholder="与分类关联"/></td>
            <td class="zi"><span>选择地区：</span></td>
            <td>
                <select class="form-control" id="province" onchange="getCitys(this,'city_code');">
                	<option value="">--选择省--</option>
                    <c:forEach items="${province }" var="jilian" varStatus="num">
                    	<option value="${jilian.id }">${jilian.codeName }</option>
                    </c:forEach>
                </select>
                <select class="form-control" id="select_city_code" onchange="getCitys(this,'area_code');">
                	<option value="">--选择市--</option>
            	</select>
            	<select class="form-control" id="select_area_code">
            		<option value="">--选择区--</option>
            	</select>
            </td> 
           
        	
            <td><input type="submit" value="查询" class="button"/></td>
          </tr>
        </form>
      </table>
    </div>
    
  </div>
  <table class="tablelist">
    <thead>
      <tr>
        <th><input id="selectAll" name="" type="checkbox" value=""/></th>
        <th>编号</th>
        <th>房间号</th>
        <th>房间类型</th>
        <th>客人姓名</th>
        <th>性别</th>
        <th>身份证号码</th>
        <th>手机号码</th>
        <th>入住时间</th>
        <th>操作</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${requestScope.pageInfo.list }" var="map" varStatus="num">
      	<tr>
	        <td><input name="ck" type="checkbox" value="${map.id }" /></td>
	        <td>${map.id }</td>
	        <td>${map.room_num }</td>
	        <td>${map.room_type_name }</td>
	        <td>${map.customer_name }</td>
	        <td>${map.gender == 1?'男':'女' }</td>
	        <td>${map.idcard }</td>
	        <td>${map.phone }</td>
	        <td><fmt:formatDate value='${map.create_date }' pattern='yyyy-MM-dd'/></td>
	       <td>
	       <!-- <a href="out.html" class="tablelink">退房</a>  -->
	     		<!--<a iri="${map.id }" id="delBtn" href="javascript:void(0)" class="tablelink"> 删除</a>	  href="javascript:void(0)":不做任何的跳转 -->    		
	     		<a iri="${map.id }" id="delBtn" href="javascript:void(0)" class="tablelink delbtnclass"> 删除</a>
	     		<a class="tablelink" href="<%=basePath %>/inroomEdit?id=${map.id }">修改</a>
	     		<a class="tablelink" href="<%=basePath %>/excel/exportRoomExcel?id=${map.id }">导出</a>
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
  
 /* <div class="tip">
    <div class="tiptop"><span>提示信息</span><a></a></div>
    <div class="tipinfo"> <span><img src="images/ticon.png" /></span>
      <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
    </div>
    <div class="tipbtn">
      <input name="" type="button"  class="sure" value="确定" />
      &nbsp;
      <input name="" type="button"  class="cancel" value="取消" />
    </div>
  </div>
</div>*/
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	
	//级联代码
	function getCitys(obj,selName) {
		alert($(obj).val());
		alert(selName);  //city_code
		if ($(obj).val() == '') {//如果为空 则清空后面的数据
			
			if ('city_code' == selName) {

				$("#select_" + selName).html('');
				$("#select_" + selName).append("<option value=''>--选择市--</option>");
				$("#select_" + selName).val('');
			}
			$("#select_area_code").html('');
			$("#select_area_code").append("<option value=''>--选择区--</option>");
			$("#select_area_code").val('');
		} else {//否则调后台查询二级 三级 市区数据
			alert($(obj).val()+'0000');
			$.post("/get_anea", {"aneald": $(obj).val()}, function (data) {			
				if (data) {
					$("#select_" + selName).html('');
					$("#select_" + selName).append("<option value=''>--选择市--</option>");
					$("#select_" + selName).val('');
					$.each(data, function (i, v) {
						$("#select_" + selName).append("<option value='" + v.id + "'>" + v.codeName + "</option>");
					});
					if (selName == 'city_code') {
						$("#select_area_code").html('');
						$("#select_area_code").append("<option value=''>--选择区--</option>");
						$("#select_area_code").val('');
					}
				}
			}, "json");
		}
	};

	
	
	
	
	$(function(){
		/* 删除操作 */
		$(".delbtnclass").click(function(){
			var flag = window.confirm("您确认删除本条记录吗？");
			if(flag){
				var iri = $(this).attr("iri");
				$.ajax({
					url:"<%=basePath %>/delInRoomInfo.do",
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
					url:"<%=basePath %>/batchDel.do",
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
	
	//增加
	$("#add").click(function(){
		window.location.href="<%=basePath %>/checkIn.do";
	});
	
	
	//模拟数据导出功能
	/*$("#excelBtn").click(function(){
		window.location.href="<%=basePath %>/excel/exportExcel";
	});*/
	
	//批量导出功能
    function exportCheck(){    
        var chckBox = document.getElementsByName("ck");    
        var num = chckBox.length;    
        var ids = "";    
        for(var index =0 ; index<num;index++)  
        {  
            if(chckBox[index].checked){    
                ids += chckBox[index].value + ",";                  
            }    
        }    
        if(ids!="")  
        {    
            if(window.confirm("确定导出所选记录记录？"))  
            {    
                //ids为数组里面存有选中的记录的id  
                location="<%=basePath %>/excel/roomExcel?idAttr="+ids;  
            }    
        }  
       else  
       {    
            alert("请选择要导出的记录");    
       }    
    }    
	
	
	
</script>
</body>
</html>
