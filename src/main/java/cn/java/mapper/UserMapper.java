package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.entity.OneMenu;
import cn.java.entity.User;
import cn.java.entity.Vip;

public interface UserMapper {

	/**
	 * 查询所有的用户信息
	 * @param keyWord
	 * @return
	 */
	@Select("select * from system_user where use_status='1'")
	List<Map<String, Object>> getAllUserInfo();
	
	
	/**
	 * 删除vip信息
	 * @param id
	 * @return
	 */
	@Update("UPDATE system_user SET use_status='0' WHERE id=#{arg0}")
	int delUserById(Long id);
	
	/**
	 * 批量删除
	 * @param paramMap
	 * @return
	 */
	@Update("UPDATE system_user SET use_status='0' WHERE id IN(${idAttr})")
	int userDel(Map<String,Object> paramMap);
	
	/**
	 * 回显信息
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM `system_user` WHERE id=#{arg0}")
    Map<String, Object> getUserInfoById(Long id);
	
	 //保存修改
	 @Insert("UPDATE system_user SET username=#{username},pwd=#{pwd} WHERE id=#{id}")
	 int updateUserInfo(User user);
	
	 /**
	  * 禁用 启用
	  * @param id
	  * @param useStatus
	  * @return
	  */
	@Update("UPDATE SYSTEM_USER SET use_status=#{arg1} WHERE id=#{arg0}")
    int updateUseStatus(Long id, String useStatus);

	 /**
     * 
     * Description: 获取数据库中所有的权限<br/>
     *
     * @author 丁鹏
     * @return
     */
    List<OneMenu> getAllAuthority();

    /**
     * 
     * Description: 插入数据<br/>
     * @param username
     * @param pwd
     * @param createDate
     * @return
     */
    @Insert("INSERT INTO SYSTEM_USER VALUES(NULL,#{arg0},#{arg1},'1')")
    int insertSystemUser(String username, String pwd);

    /**
     * 
     * Description: 获取用户的主键<br/>
     * @param username
     * @param pwd
     * @return
     */
    @Select("SELECT id FROM SYSTEM_USER WHERE username=#{arg0} AND pwd=#{arg1}")
    Long getSystemUserId(String username, String pwd);

    /**
     * 
     * Description: 添加用户主键与权限的关联关系<br/>
     * @param userId
     * @param authorityId
     * @return
     */
    @Insert("INSERT INTO user_authority VALUES(#{arg0},#{arg1})")
    int insertUserAuthoryRelation(Long userId, Long authorityId);

}
