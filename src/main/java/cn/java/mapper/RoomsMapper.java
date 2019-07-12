package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.entity.Rooms;
import cn.java.entity.Vip;

public interface RoomsMapper {

	/**
	 * 查询出所有的会员信息
	 * @return
	 */
	@Select("select * from rooms where room_status='1'")
	List<Map<String,Object>> getAllRooms();
	
	
	/**
	 * 批量删除
	 * @param paramMap
	 * @return
	 */
	@Update("UPDATE rooms SET ROOM_STATUS='0' WHERE id IN(${idAttr})")
	int batchDel(Map<String,Object> paramMap);
	
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@Update("UPDATE rooms SET ROOM_STATUS='0' WHERE id=#{arg0}")
	int delRoomsById(Long id);
	
	
	/**
	 * 回显所有的房间信息
	 * @return
	 */
	@Select("SELECT * FROM `rooms` WHERE id=#{arg0}")
    Map<String, Object> getRoomsInfoById(Long id);
	
	 /**
	  * 修改保存房间信息
	  * @param room
	  * @return
	  */
	
	/*@Insert("INSERT INTO rooms(id,room_num,room_status,create_time,room_type_id) VALUES(NULL,#{room_num},#{room_status},#{createTime},#{room_type_id}")
	int saveRoomsInfo(Rooms rooms);*/
	
	
	@Insert("update rooms set room_num=#{roomNum}, create_time=#{createTime} where id=#{id}")
	int saveRoomsInfo(Rooms rooms);
	
	
	/**
	 * 添加房间信息
	 * @param info
	 * @return
	 */
	@Insert({ "insert into rooms values(NULL, #{roomNum},#{createTime},'1','1')" })
	int addRoomsInfo(Rooms room);
	
	
	
}
