package cn.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.java.entity.OneMenu;
import cn.java.mapper.LoginMapper;
import cn.java.service.LoginService;
import cn.java.utils.MD5;
@Service
@Transactional(readOnly = false)
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	/**
	 * 登录和MD5加密
	 */
	@Transactional(readOnly = true)
	@Override
	public Long isLoginSuccess(String username,String pwd) throws Exception {
		
		//判断username、pwd是否等于null(如果人家在控制台上把name=username和pwd=123456删除后，账号密码就为nulll ,那么这个就起作用了)
		if(username == null || pwd == null) {
			return null;   //null代表失败
		}
		//首先验证用户名和密码是否满足格式要求（MD5后台验证）（如果人家在控制台上把pattern="\w{3,12}" required="required"和
															//pattern="\w{6,12}" required="required"删除，这个就起作用了）
		String regex1 = "\\w{3,12}";
		String regex2 = "\\w{6,12}";
		boolean flag1 = username.matches(regex1);
		boolean flag2 = pwd.matches(regex2);
		if(flag1 && flag2) {
			String miwenPwd = MD5.finalMD5(pwd);
			Long flag = loginMapper.login(username, miwenPwd);
			return flag;
		}
			return null;				
	} 

	/**
	 * 获取system_authority中的所有菜单
	 */	  
	@Override
	public List<OneMenu> selectMenusById(Long userId){
		
		return loginMapper.getMenusByUserId(userId);
	}

	
	
	
	
	
}
