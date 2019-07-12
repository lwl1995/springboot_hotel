package cn.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.entity.Permission;
import cn.java.service.PermissionService;


@Controller
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;

	//跳转到tree页面
	@RequestMapping("/permission.do")
	public String permission() {
		
		return "/admin/permission/zTree.jsp";
			
	}

	@RequestMapping("/loadData")
	@ResponseBody
	public Object loadData() {
		/*List<Permission> permission= new ArrayList<Permission>();
		System.out.println("permssion---"+permission);
		//得到父节点
		Permission root = permissionService.queryRootPermission();
		permission.add(root);
		//得到子节点
		List<Permission> childPermssions = permissionService.queryChildPermission(root.getId());
		//得到子节点的子节点
		for(Permission childPermssion : childPermssions) {
			
			List<Permission> childChildPermssions = permissionService.queryChildPermission(childPermssion.getId());
			//子节点和子节点下的子节点两者的关系
			childPermssion.setChildren(childChildPermssions);
		}
		//顶级节点和子节点两者的关系
		root.setChildren(childPermssions);*/
		
		
		Permission parent = new Permission();
		//查询顶级父节点
		parent.setId(0);
		queryChildPermission(parent);
		return parent.getChildren();
	}
	/**
	 * 递归查询法：
	 * 1.方法自己拥有自己
	 * 2.方法一定要存在跳出逻辑
	 * 3.方法调用时，参数之间应该有规律
	 * @param parent
	 */
	public void queryChildPermission(Permission parent) {
		System.out.println("///////////////"+parent.getId());
		List<Permission> childPermssions = permissionService.queryChildPermission(parent.getId());
		for(Permission permission : childPermssions) {
			queryChildPermission(permission);
		}
		
		parent.setChildren(childPermssions);
	}
	
}
