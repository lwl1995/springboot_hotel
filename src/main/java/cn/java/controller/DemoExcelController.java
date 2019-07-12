package cn.java.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import cn.java.entity.DemoExcel;
import cn.java.entity.InRoomInfo;
import cn.java.service.DemoExcelService;
import cn.java.utils.ExcelUtiles;
 
/**
 * <pre>
 * &#64;author cao_wencao
 * &#64;date 2018年12月13日 下午6:16:59
 * </pre>
 */
@RestController
@RequestMapping("/excel")
public class DemoExcelController {
	
	
	@Autowired
	private DemoExcelService demoExcelService;
 
	
	//数据导出
	@GetMapping("/exportExcel")
	public void export(HttpServletResponse response) {
	
		List<DemoExcel> personList = demoExcelService.findAll();
		
		// 导出操作
		ExcelUtiles.exportExcel(personList, "easypoi导出功能", "导出sheet1", DemoExcel.class, "student.xlsx", response);
 
	}
	
	//单条数据导出
	@RequestMapping("/exportRoomExcel")
	public List<InRoomInfo> inroomEdit(Integer id,HttpServletResponse response) {
		
		List<InRoomInfo> inRoomSelect = demoExcelService.InRoomSelect(id);
		
		// 导出操作
		ExcelUtiles.exportExcel(inRoomSelect, "easypoi导出功能", "导出sheet1", InRoomInfo.class, "student.xlsx", response);
		return inRoomSelect;
	}
	
	
	
	//入住信息批量导出
	@RequestMapping("/roomExcel")
	@ResponseBody
	public List<InRoomInfo> batchDel(HttpServletResponse response, String idAttr) {
		
		List<InRoomInfo> roomInfo = demoExcelService.roomInfo(idAttr);
		ExcelUtiles.exportExcel(roomInfo, "easypoi导出功能", "导出sheet1", InRoomInfo.class, "student.xlsx", response);
		return roomInfo;
	}
	
	
}