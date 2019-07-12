package cn.java.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.entity.DemoExcel;
import cn.java.entity.InRoomInfo;
import cn.java.mapper.DemoExcelMapper;
import cn.java.service.DemoExcelService;
@Service
public class DemoExcelServiceImpl implements DemoExcelService{

	@Autowired
	DemoExcelMapper demoExcelMapper;
	
	//数据导出
	@Override
	public List<DemoExcel> findAll() {
		
		return demoExcelMapper.getExcelInfo();
	}

	
	//批量入住信息数据导出
	@Override
	public List<InRoomInfo> roomInfo(String idAttr) {
		idAttr = idAttr.substring(0, idAttr.length()-1);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("idAttr", idAttr);
		
		return demoExcelMapper.batchExport(paramMap);
	}

	
	//单条数据导出
	@Override
	public List<InRoomInfo> InRoomSelect(Integer id) {
		
		return demoExcelMapper.InRoomSelect(id);
	}

	

	
	
	
	
	
	
	
}
