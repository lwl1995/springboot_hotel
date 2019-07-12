package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.entity.InRoomInfo;
import cn.java.entity.Vip;

public interface VipMapping {

	/**
	 * 查询出所有的会员信息
	 * @return
	 */
	@Select("select * from vip where status='1'")
	List<Map<String,Object>> getAllVip();
	
	/**
	 * 删除vip信息
	 * @param id
	 * @return
	 */
	@Update("UPDATE vip SET STATUS='0' WHERE id=#{arg0}")
	int delVipById(Long id);
	
	/**
	 * 批量删除
	 * @param paramMap
	 * @return
	 */
	@Update("UPDATE vip SET STATUS='0' WHERE id IN(${idAttr})")
	int batchDel(Map<String,Object> paramMap);
	
	/**
	 * 添加会员信息
	 * @param info
	 * @return
	 */
	@Insert("INSERT INTO `vip` VALUES(NULL,#{vipNum},#{customerName},#{idcard},#{phone},#{gender},'1')")
	int addVipInfo(Vip vip);
	
	
	/**
	 * 回显所有的会员信息
	 * @return
	 */
	@Select("SELECT * FROM `vip` WHERE id=#{arg0}")
    Map<String, Object> getVipInfoById(Long id);
	
	 /*保存修改*/
	@Insert("UPDATE vip SET vip_num=#{vipNum},customer_name=#{customerName},gender=#{gender},idcard=#{idcard},phone=#{phone} WHERE id=#{id}")
	int updateVipInfo(Vip vip);
}
