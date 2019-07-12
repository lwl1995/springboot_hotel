package cn.java.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.entity.InRoomInfo;
import cn.java.entity.JilianMenu;
import cn.java.entity.Vip;

public interface InRoomInfoService {
	
	
	//一级联菜单
	List<JilianMenu> selectProvinceList();
	

	/**
	 * 分页查询所有的入住信息
	 * @return
	 */
	List<Map<String,Object>> selectAllInRoomInfos(Integer pageNum,Integer pageSize);
	
	/**
	 * 按选择分类和关键字查询(在入住信息查询页面中)
	 * @param type
	 * @param keyWord
	 * @return
	 */
	List<Map<String,Object>> selectgetInRoomInfoByCondition(String type,String keyWord);
	
	/**
	 * 删除入住信息
	 * @param id
	 * @return
	 */
	boolean delInfoById(Long id);

	
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	boolean batchDel(String idAttr);
	
	/**
	 * 获取所有的空闲房间
	 * @return
	 */
	List<Map<String,Object>> getAllKXRoom();
	
	/**
	 * 入住信息添加
	 * @param info
	 * @return
	 */
	boolean saveInRoomInfo(InRoomInfo info);
	
	/**
	 * 获取数据表中所有的已经入住的房间号
	 * @return
	 */
	List<Map<String,Object>> selectRoomsByStatus();
	
	/**
	 * 根据房间主键来获取入住人的详细信息
	 * @param roomId
	 * @return
	 */
	Map<String,Object> getInRoomInfoByRoomId(Long roomId);
	
	/**
	 * 编辑页面回显
	 * @param id
	 * @return
	 */
    Map<String,Object> InRoomEdit(Integer id);
    
    /**
     * 保存编辑
     * @param inRoomInfo
     * @return
     */
    //int updateSaveInRoom(InRoomInfo inRoomInfo);
    boolean updateSaveInRoom(InRoomInfo inRoomInfo);

    //二三级
    List<JilianMenu> getAllListByParentId(String aneald);
    
}
