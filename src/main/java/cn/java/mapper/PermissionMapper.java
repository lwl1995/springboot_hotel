package cn.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.java.entity.Permission;

public interface PermissionMapper {

	//查询顶级节点
	@Select("select * from t_permission where pid is null")
	Permission queryRootPermission();
	
	//查询子节点
	@Select("select * from t_permission where pid=#{id}")
	List<Permission> queryChildPermission(Integer id);
}
