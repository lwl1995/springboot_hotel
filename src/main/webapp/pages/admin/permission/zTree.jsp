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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="<%=basePath%>/static/ztree/zTreeStyle.css"/>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/ztree/jquery.ztree.all-3.5.min.js"></script>

<script src="<%=basePath%>/static/layer/layer.js"></script>

</head>
<body>
<div class="place">
	<span>树：</span>	
</div>

<div>
	
		  <div>
              <ul id="permissionTree" class="ztree"></ul>
		  </div>
   
  </div>

<script type="text/javascript">

$(function () {
var setting = {
		async: {
			enable: true,
			url:"<%=basePath%>/loadData",
			autoParam:["id", "name=n", "level=lv"]
				
		}
		
    };
  
/*var zNodes =[
		{ name:"父节点1 - 展开11111", open:true,
			children: [
				{ name:"父节点11 - 折叠",
					children: [
						{ name:"叶子节点111"},
						{ name:"叶子节点112"},
						{ name:"叶子节点113"},
						{ name:"叶子节点114"}
					]},
				{ name:"父节点12 - 折叠",
					children: [
						{ name:"叶子节点121"},
						{ name:"叶子节点122"},
						{ name:"叶子节点123"},
						{ name:"叶子节点124"}
					]},
				{ name:"父节点13 - 没有子节点", isParent:true}
			]},
		{ name:"父节点2 - 折叠",
			children: [
				{ name:"父节点21 - 展开", open:true,
					children: [
						{ name:"叶子节点211"},
						{ name:"叶子节点212"},
						{ name:"叶子节点213"},
						{ name:"叶子节点214"}
					]},
				{ name:"父节点22 - 折叠",
					children: [
						{ name:"叶子节点221"},
						{ name:"叶子节点222"},
						{ name:"叶子节点223"},
						{ name:"叶子节点224"}
					]},
				{ name:"父节点23 - 折叠",
					children: [
						{ name:"叶子节点231"},
						{ name:"叶子节点232"},
						{ name:"叶子节点233"},
						{ name:"叶子节点234"}
					]}
			]},
		{ name:"父节点3 - 没有子节点", isParent:true}

	];*/
 
    // 异步获取树形结构数据
	$.fn.zTree.init($("#permissionTree"), setting);
});

</script>
</body>

</html>
