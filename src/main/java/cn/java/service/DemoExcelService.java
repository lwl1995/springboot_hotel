package cn.java.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.google.common.collect.Lists;


import cn.java.entity.DemoExcel;
import cn.java.entity.InRoomInfo;
 
/**
 * <pre>
 * &#64;author cao_wencao
 * &#64;date 2018年12月13日 下午5:37:17
 * </pre>
 */

public interface DemoExcelService {
	
	
	//数据导出
	public List<DemoExcel> findAll();
	
	//单条数据导出
	List<InRoomInfo> InRoomSelect(Integer id);
	
	
	/**
	 * 批量导出入住信息
	 * @param idAttr
	 * @return
	 */
	public List<InRoomInfo> roomInfo(String idAttr);
	
}