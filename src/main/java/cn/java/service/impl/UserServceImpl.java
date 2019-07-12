package cn.java.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.java.entity.OneMenu;
import cn.java.entity.User;
import cn.java.entity.Vip;
import cn.java.mapper.UserMapper;
import cn.java.service.UserService;
import cn.java.utils.MD5;
@Service
public class UserServceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	/**
	 * 分页显示用户信息
	 */
	@Override
	public List<Map<String, Object>> findAllUserInfo(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.getAllUserInfo();
    }
	
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	@Override
	public boolean userAmountDel(String idAttr) {//“1,2,3,”
		idAttr = idAttr.substring(0, idAttr.length()-1);  //变成“1,2,3”
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("idAttr", idAttr);
		int flag = userMapper.userDel(paramMap);
		return flag>= 1?true:false;
	}


	@Override
	public boolean delUserById(Long id) {
		// TODO Auto-generated method stub
		return userMapper.delUserById(id)>=1?true:false;
	}

	@Override
    public Map<String, Object> findUserInfoById(Long id) {
        return userMapper.getUserInfoById(id);
    }
	
	
	@Override
    public boolean updateUserInfo(User user) {
        return userMapper.updateUserInfo(user) >= 1 ? true : false;
    }
	
@Override
    public boolean updateUseStatus(Long id, String useStatus) {
        if (useStatus == null) {
            return false;
        }
        if (!(useStatus.matches("[01]"))) {
            return false;
        }
        return userMapper.updateUseStatus(id, useStatus) >= 1 ? true : false;
    }
	




@Cacheable(key = "'findAllAuthority'")
@Override
public List<OneMenu> findAllAuthority() {
    return userMapper.getAllAuthority();
}

@Override
public boolean saveUser(String username, String password, String oneIds, String twoIds) throws Exception {
    password = MD5.finalMD5(password);
    // 往system_user表中添加数据
    
    int flag = userMapper.insertSystemUser(username, password);
    // 获取刚添加完用户的id值
    if (flag < 1) {
        return false;
    }
    Long userId = userMapper.getSystemUserId(username, password);
    // 往user_authority表中添加映射关系
    // 再添加用户与一级菜单的映射关系
    String[] oneIdsAttr = oneIds.split("\\,");
    // 取出重复一级id的值
    Set<String> oneIdSet = new HashSet<String>();
    for (String temp : oneIdsAttr) {
        oneIdSet.add(temp);
    }
    // 开始插入一级与用户的映射关系
    for (String temp : oneIdSet) {
        userMapper.insertUserAuthoryRelation(userId, Long.parseLong(temp));
    }
    // 先添加用户与二级菜单的映射关系
    String[] twoIdAtr = twoIds.split("\\,");
    for (String temp : twoIdAtr) {
        userMapper.insertUserAuthoryRelation(userId, Long.parseLong(temp));
    }
    return true;
}




}
