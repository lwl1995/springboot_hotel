package cn.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.entity.Permission;
import cn.java.mapper.PermissionMapper;
import cn.java.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public Permission queryRootPermission() {
		
		return permissionMapper.queryRootPermission();
	}

	@Override
	public List<Permission> queryChildPermission(Integer id) {
		
		return permissionMapper.queryChildPermission(id);
	}
	
}
