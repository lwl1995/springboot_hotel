package cn.java.service;

import java.util.List;


import cn.java.entity.Permission;

public interface PermissionService {

	//查询顶级节点
	Permission queryRootPermission();
	//查询子节点

	List<Permission> queryChildPermission(Integer id);
	
}
