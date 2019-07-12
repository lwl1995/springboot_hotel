package cn.java.service;

import java.util.List;

import cn.java.entity.OneMenu;

public interface LoginService {

	/**
	 * 登陆业务方法
	 * @param username
	 * @param pwd
	 * @return
	 * @throws Exception
	 */

	Long isLoginSuccess(String username, String pwd) throws Exception;

	/**
	 * 获取system_authority中的所有菜单
	 * @return
	 */
	List<OneMenu> selectMenusById(Long userId);
	
	

}