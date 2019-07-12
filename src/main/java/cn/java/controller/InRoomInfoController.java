package cn.java.controller;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.java.entity.InRoomInfo;
import cn.java.entity.JilianMenu;
import cn.java.entity.Vip;
import cn.java.service.InRoomInfoService;

/**
 * 入住管理模块
 * @author LWL
 *
 */

@Controller
public class InRoomInfoController {

	
	
	@Autowired
	private InRoomInfoService inRoomInfoService;
	
	


	/**
	 * 分页查询所有的入住信息
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/getInRoomInfo.do")
	public String getInfoRoomInfo(@RequestParam(name="pageNum",defaultValue="2") Integer pageNum,
			@RequestParam(name="pageSize",defaultValue="5") Integer pageSize,Model model) {
		//分页之后显示的数据
		List<Map<String, Object>> infoList = inRoomInfoService.selectAllInRoomInfos(pageNum, pageSize);
		//将infoList封装到PageInfo工具类中,PageInfo<Map<String, Object>>中的泛型跟上面一样
		//点击PageInfo会看到private List<T> list;,这是把上面的结果infoList封装到了这个list中了，前台取值一定要注意
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(infoList);
		
		//一级菜单
		List<JilianMenu> selectProvinceList = inRoomInfoService.selectProvinceList();
    	System.out.println("selectProvinceList-----------"+selectProvinceList);
    	model.addAttribute("province", selectProvinceList);
		
		model.addAttribute("pageInfo",pageInfo);
		return "admin/bill/inroominfo.jsp";
	}
	 /***
	    * @Author lc
	    * 说明：动态查询省市区--二三级数据查询
	    * @Date 14:52 2019/4/25
	    * @Param [aneald]
	    * @return java.util.List
	    */
	    @PostMapping("/get_anea")
	    @ResponseBody
	    public List<JilianMenu> getArea(@RequestParam String aneald){
	        List<JilianMenu> allListByParentId = inRoomInfoService.getAllListByParentId(aneald);
	        return allListByParentId;
	    }
	
	/**
	 * 按选择分类和关键字查询(在入住信息查询页面中)
	 * @param type
	 * @param keyWord
	 * @return
	 */
	@RequestMapping("/getInRoomInfoByCondition.do")
	public String getInRoomInfoByCondition(String type,String keyWord,Model model) {
		List<Map<String, Object>> infoList = inRoomInfoService.selectgetInRoomInfoByCondition(type, keyWord);
		model.addAttribute("infoList", infoList);
		return "admin/bill/inroominfo_condition.jsp";
	}
		
	/**
	 * 删除入住信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/delInRoomInfo.do")
	@ResponseBody
	public boolean delInRoomInfo(Long id) {
		boolean delInfoById = inRoomInfoService.delInfoById(id);
		return delInfoById;
	}
	
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	@RequestMapping("/batchDel.do")
	@ResponseBody
	public boolean batchDel(String idAttr) {
	
		return inRoomInfoService.batchDel(idAttr);
	}
	
	/**
	 * 跳转到入住信息添加
	 * @return
	 */
	@RequestMapping("/checkIn.do")
	public String checkIn(Model model) {
		List<Map<String, Object>> roomList = inRoomInfoService.getAllKXRoom();
		model.addAttribute("roomList", roomList);
		return "admin/bill/checkin.jsp";
	}
	
	/**
	 * 添加入住信息
	 * @param inRoomInfo
	 * @param br
	 * @param model
	 * @return
	 */
	@RequestMapping("/addInRoomInfo.do")
	public String addInRoomInfo(@Valid InRoomInfo inRoomInfo,BindingResult br,Model model) {
		System.out.println("----------------------------------"+inRoomInfo);
		boolean flag = br.hasErrors();
		if(flag) { //数据格式错误
			//将错误格式封装
			Map<String,Object> errorMap = new HashMap<String,Object>();
			List<FieldError> fieldErrorList = br.getFieldErrors();
			for (FieldError fieldError : fieldErrorList) {
				String field = fieldError.getField(); //发生错误的字段名
				String errorMessage = fieldError.getDefaultMessage(); //错误信息
				errorMap.put("errorMap", errorMessage);				
			}
			model.addAttribute("errorMap", errorMap);
			model.addAttribute("inRoomInfo", inRoomInfo);
			return "redirect:/checkIn.do";
		}else { //所有数据格式完全正确
			//调用业务层代码，将数据保存到数据库中
			boolean result = inRoomInfoService.saveInRoomInfo(inRoomInfo);
			System.out.println("result-------------------为"+result);
			if(result) { //入住信息添加成功
				return "redirect:/checkIn.do";
			}else { //入住信息添加失败
				return "redirect:/getInRoomInfo.do";
			}
		}
		
	}
	
	/**
	 *  跳转到编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/inroomEdit")
	public String inroomEdit(Integer id,Model model) {
		/*System.out.println("id为-----------------"+id);*/
		Map<String, Object> roomEdit = inRoomInfoService.InRoomEdit(id);
		System.out.println("输出的roomEdit为-----------------------"+roomEdit);
		model.addAttribute("roomEdit", roomEdit);
		return "admin/bill/inroomEdit.jsp";
	}
	
	/*保存编辑*/
	/*@RequestMapping("/inroomSave.do")
	public String inroomSave(InRoomInfo inRoomInfo) {
		System.out.println("inRoomInfo为------------------"+inRoomInfo);
		inRoomInfoService.saveInRoomInfo(inRoomInfo);
		return "redirect:/getInRoomInfo.do";
	}*/
	
	
	/**
	 * 修改保存页面
	 * @return
	 */
	   @RequestMapping("/inroomSave.do")
	    public String inroomSave(InRoomInfo inRoomInfo) {
		   System.out.println("inRoomInfo为----------"+inRoomInfo);
	        boolean flag = inRoomInfoService.updateSaveInRoom(inRoomInfo);
	        if (flag) {// 修改成功
	            return "redirect:/getInRoomInfo.do";
	        }
	        return "redirect:/inroomEdit";
	    }
	
		
	/**
	 * 结账退房
	 * @return
	 */
	@RequestMapping("/jiezhang.do")
	public String jieZhang(Model model) {
		//调用业务层获取所有已经入住的房间号
		List<Map<String, Object>> rmList = inRoomInfoService.selectRoomsByStatus();
		model.addAttribute("rmList", rmList);
		return "admin/bill/out.jsp";
	}
	
	/**
	 * 
	 */
	@RequestMapping("/getInRoomInfoByRoomId.do")
	@ResponseBody
	public Map<String,Object> getInRoomInfoByRoomId(Long roomId) {
		
		return inRoomInfoService.getInRoomInfoByRoomId(roomId);
	}
	
	
	
	
	
}
