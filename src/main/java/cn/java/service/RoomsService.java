package cn.java.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import cn.java.entity.Rooms;
import cn.java.entity.Vip;

public interface RoomsService {

	/**
	 * 查询出所有的房间信息
	 * @return
	 */
	List<Map<String,Object>> getAllRooms(Integer pageNum,Integer pageSize);
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	boolean batchDel(String idAttr);
	
	/**
	 * 删除
	 * @param idAttr
	 * @return
	 */
	boolean delRoomsById(Long id);
	
	/**
	 * 回显出所有的信息
	 * @return
	 */
	 Map<String, Object> findRoomsInfoById(Long id);
	 
	 /**
	  * 保存修改信息
	  * @param vip
	  * @return
	  */
	 int saveRoomsInfo(Rooms rooms);
	  
	  /**
	   * 添加信息
	   * @param vip
	   * @return
	   */
	  int addRommsInfo(Rooms room);
	
}
