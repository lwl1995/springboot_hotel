package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.entity.InRoomInfo;
import cn.java.entity.JilianMenu;
import cn.java.entity.Vip;



public interface InRoomInfoMapper {
	
	
	//一级联菜单
	@Select("select id,code, codeName, parentId from place_menu where id='1'") 
	List<JilianMenu> selectProvinceList();
	
	//二三级
	@Select("select id,code, codeName, parentId from place_menu where parentId = #{aneald}")
	List<JilianMenu> selectCityAreaList(String aneald);
	
	/**
	 * 分页查询所有的入住信息
	 * @return
	 */
	List<Map<String,Object>> getAllInRoomInfos();
	
	
	/**
	 * 按选择分类和关键字查询(在入住信息查询页面中)
	 * @param type
	 * @param keyWord
	 * @return
	 */
	List<Map<String,Object>> getInRoomInfoByCondition(String type,String keyWord);
	
	
	/**
	 * 删除入住信息
	 *增删改返回的结果都为int
	 * @param id
	 * @return
	 */
	@Update("UPDATE in_room_info SET STATUS='0' WHERE id=#{arg0}")
	int delInfoById(Long id);
	
	/**
	 * 批量删除
	 * @param paramMap
	 * @return
	 */
	@Update("UPDATE in_room_info SET STATUS='0' WHERE id IN(${idAttr})")
	int batchDel(Map<String,Object> paramMap);

	/**
	 * 获取所有的空闲房间
	 * @return
	 */
	@Select("select * from rooms where room_status = 0")
	List<Map<String,Object>> getAllKXRoom();
	
	
	/**
	 * 根据房间号获取房间的id
	 * @param roomNum
	 * @return
	 */
	@Select(value="select id from rooms where room_num=#{arg0}")
	Long getRoomIdByRoomNum(String roomNum);
	
	/**
	 * 添加入住信息
	 * @param info
	 * @return
	 */
	@Insert("INSERT INTO `in_room_info` VALUES(NULL,#{customerName},#{gender},#{isVip},#{idcard},#{phone},#{createDate},#{roomId},'1')")
	int addInRoomInfo(InRoomInfo info);
	
	/**
	 * 修改房间的状态
	 * @param roomNum
	 * @return
	 */
	@Update("update rooms set room_status=1 where room_num=#{arg0}")
	int updateRoomStatus(String roomNum);
	
	/**
	 * 获取数据表中所有已经入住的房间号
	 * @return
	 */
	@Select("SELECT room_num,id FROM rooms where room_status='1'")
	List<Map<String,Object>> getRoomsByStatus();
	
	/**
	 * 根据房间主键来获取入住人的详细信息
	 * @param roomId
	 * @return
	 */
	//@Select("select * from in_room_info where room_id =#{arg0} limit 1")
	@Select("SELECT customer_name,gender,idcard,phone,DATE_FORMAT(create_date,'%Y-%m-%d') AS create_date FROM `in_room_info` WHERE room_id=#{arg0} LIMIT 1\r\n"
            + "")
	Map<String,Object> getInRoomInfoByRoomId(Long roomId);
	
	 /**
     * 
     * Description: 根据房间主键查询出房间对应的单价<br/>
     * @param id
     * @return
     */
    @Select("SELECT room_price FROM room_type WHERE id=(SELECT room_type_id FROM `rooms` WHERE id=#{arg0})")
    Float getRoomPriceByRoomId(Long id);
    
   /* 编辑页面回显*/
    @Select("SELECT iri.*,r.room_num,rt.room_type_name FROM in_room_info iri INNER JOIN rooms r on iri.room_id = r.id INNER join room_type rt on rt.id=r.id where iri.id=#{arg0}")
    Map<String,Object> InRoomEdit(Integer id);
    
    /**
     * 保存编辑（修改）
     * @param inRoomInfo
     * @return
     */
   /* @Update("update in_room_info set customer_name=#{customerName},gender=#{gender},idcard=#{idcard},phone=#{phone},money=#{money},create_date=#{create_date} where id=#{arg0}") 
    int updateSaveInRoom(InRoomInfo inRoomInfo);*/
    /*保存修改*/
   	@Insert("update in_room_info set customer_name=#{customerName},gender=#{gender},idcard=#{idcard},phone=#{phone},create_date=#{create_date} where id=#{id}")
   	int updateSaveInRoom(InRoomInfo inRoomInfo);



	
    
}
	
