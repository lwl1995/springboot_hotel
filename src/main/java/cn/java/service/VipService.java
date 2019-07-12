package cn.java.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import cn.java.entity.InRoomInfo;
import cn.java.entity.Vip;

public interface VipService {

	/**
	 * 分页查询所有的会员信息
	 * @return
	 */
	List<Map<String,Object>> selectAllVip(Integer pageNum,Integer pageSize);
	
	
	/**
	 * 删除vip信息
	 * @param id
	 * @return
	 */
	boolean delVipById(Long id);
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	boolean batchDel(String idAttr);
	
	
	/**
	 * 添加会员信息
	 * @param info
	 * @return
	 */
	//@Insert("INSERT INTO `vip` VALUES(NULL,#{customerName},#{vipRate},#{idcard},#{phone},#{gender},#{createDate})")
	int selectVipInfo(Vip vip);
	
	/**
	 * 回显出所有的信息
	 * @return
	 */
	 Map<String, Object> findVipInfoById(Long id);
	 
	 /**
	  * 保存会员信息
	  * @param vip
	  * @return
	  */
	  boolean updateVipInfo(Vip vip);
	  
	  
	  
}
