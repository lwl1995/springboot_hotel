package cn.java.service;

import java.util.List;
import java.util.Map;

import cn.java.entity.OneMenu;
import cn.java.entity.User;
import cn.java.entity.Vip;

public interface UserService {

	/**
	 * 分页查询用户信息
	 * @param pageNum
	 * @param pageSize
	 * @param keyWord
	 * @return
	 */
	 List<Map<String, Object>> findAllUserInfo(Integer pageNum, Integer pageSize);
	 
	 /**
		 * 删除user信息
		 * @param id
		 * @return
		 */
		boolean delUserById(Long id);
	 
	 /**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	boolean userAmountDel(String idAttr);
	
	
	/**
	 * 回显出所有的信息
	 * @return
	 */
	 Map<String, Object> findUserInfoById(Long id);
	 
	/* *//**
	  * 保存用户信息
	  * @param vip
	  * @return
	  *//**/
	  boolean updateUserInfo(User user);
	 boolean updateUseStatus(Long id, String useStaus);
	 
	 
	 
	 List<OneMenu> findAllAuthority();

	 boolean saveUser(String username, String password, String oneIds, String twoIds) throws Exception;
}
