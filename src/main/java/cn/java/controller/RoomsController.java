package cn.java.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.java.entity.Rooms;
import cn.java.entity.Vip;
import cn.java.mapper.RoomsMapper;
import cn.java.service.RoomsService;

@Controller
public class RoomsController {
	
	@Autowired
	private RoomsService roomsService;
	
	/**
	 * 分页显示房间信息
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAllRooms.do")
	public String getAllVip(@RequestParam(name="pageNum",defaultValue="1") Integer pageNum,
			@RequestParam(name="pageSize",defaultValue="5") Integer pageSize,Model model) {
		//分页之后显示的数据
		List<Map<String, Object>> infoList = roomsService.getAllRooms(pageNum, pageSize);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(infoList);	
		model.addAttribute("pageInfo",pageInfo);
		return "admin/room/rooms.jsp";
	}
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	@RequestMapping("/delRoomsInfo.do")
	@ResponseBody
	public boolean batchDVipel(String idAttr) {	
		return roomsService.batchDel(idAttr);
	}
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/delRooms.do")
	@ResponseBody
	public boolean delVipInfo(Long id) {
		 boolean delRoomsById = roomsService.delRoomsById(id);
		
		return delRoomsById;
	}
	
	/**
	 * 房间修改
	 * @return
	 */
	@RequestMapping("/getRoomsInfoById.do")
    public String getVipInfoById(Long id, Model model) {
		System.out.println("输出的id为------------"+id);
        // 调用业务层
        Map<String, Object> roomsInfoMap = roomsService.findRoomsInfoById(id);
        // 跳转到修改页面，并且带数据过去
        model.addAttribute("roomsInfoMap", roomsInfoMap);
        return "admin/room/roomsEdit.jsp";
    }
	
	/**
	 * 修改保存页面
	 * @return
	 */
	   @RequestMapping("/roomsEditSave.do")
	    public String updateVipInfo(Rooms rooms,Long id) {
		   System.out.println("rooms修改信息为----------------------"+rooms);
	        int saveRoomsInfo = roomsService.saveRoomsInfo(rooms);
	        /*if (flag) {// 修改成功
	            return "redirect:/getAllRooms.do";
	        }
	        return "redirect:/getRoomsInfoById.do";*/
	        return "redirect:/getAllRooms.do";
	    }
	   
	   
		/**
		 * 跳转到增加页面
		 * @return
		 */
		@RequestMapping("/addRooms.do")
		public String addVip() {
			return "admin/room/addRoom.jsp";
		}
		
		
		/**
		 * 增加信息
		 * @return
		 */
		@RequestMapping("/addRoomsInfo.do")
		public String addRoomsInfo(Rooms room) {
			System.out.println("room增加信息为-------------"+room);
			roomsService.addRommsInfo(room);
			return "redirect:/getAllRooms.do";
		}
		
}
