package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.entity.DemoExcel;
import cn.java.entity.InRoomInfo;

public interface DemoExcelMapper {
	
	
 //数据导出
 List<DemoExcel> getExcelInfo();
 
//单条数据导出
 @Select("SELECT * FROM in_room_info iri where iri.id=#{arg0}")
 List<InRoomInfo> InRoomSelect(Integer id);

 
 	/**
	 * 批量导出
	 * @param paramMap
	 * @return
	 */
	@Select("SELECT * FROM in_room_info iri where iri.id IN(${idAttr})")
	List<InRoomInfo> batchExport(Map<String,Object> paramMap);
}
