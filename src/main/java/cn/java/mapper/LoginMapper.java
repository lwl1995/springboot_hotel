package cn.java.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.java.entity.OneMenu;

public interface LoginMapper {

	/**
	 * 用户登录
	 * @param username 用户名
	 * @param pwd    密文
	 * @return
	 * 
	 * arg0：代表第一个参数username，arg1代表第二个参数pwd
	 */
	@Select("select id from system_user where username=#{arg0} and pwd=#{arg1}")
	Long login(String username,String pwd);
	
	/**
	 * 根据用户主键，获取用户拥有的权限 
	 * @return
	 */
	List<OneMenu> getMenusByUserId(Long userId);
}
