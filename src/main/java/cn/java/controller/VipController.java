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
import cn.java.service.VipService;
@Controller
public class VipController {
	
	@Autowired
	private VipService vipService;

	
	/**
	 * 分页查询所有的会员信息
	 * @return
	 */	
	@RequestMapping("/getAllVip.do")
	public String getAllVip(@RequestParam(name="pageNum",defaultValue="1") Integer pageNum,
			@RequestParam(name="pageSize",defaultValue="5") Integer pageSize,Model model) {
		//分页之后显示的数据
		List<Map<String, Object>> infoList = vipService.selectAllVip(pageNum, pageSize);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(infoList);	
		model.addAttribute("pageInfo",pageInfo);
		return "admin/vip/vip.jsp";
	}
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/delVipInfo.do")
	@ResponseBody
	public boolean delVipInfo(Long id) {
		boolean delVipById = vipService.delVipById(id);
		
		return delVipById;
	}
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	@RequestMapping("/batchDVipel.do")
	@ResponseBody
	public boolean batchDVipel(String idAttr) {	
		return vipService.batchDel(idAttr);
	}
	
	
	/**
	 * 跳转到增加页面
	 * @return
	 */
	@RequestMapping("/addVip.do")
	public String addVip() {
		
		return "admin/vip/addVip.jsp";
	}
	

	   /**
	    * 添加会员
	    */
	   @RequestMapping("/saveVip.do")
	   public String addRooms(Vip vip) {
		   System.out.println("vip为-----------"+vip);
		   vipService.selectVipInfo(vip);
		   return "redirect:/getAllVip.do";
	   }
	

	/**
	 * 会员修改
	 * @return
	 */
	@RequestMapping("/getVipInfoById.do")
    public String getVipInfoById(Long id, Model model) {
		System.out.println("输出的id为------------"+id);
        // 调用业务层
        Map<String, Object> vipInfoMap = vipService.findVipInfoById(id);
        // 跳转到修改页面，并且带数据过去
        model.addAttribute("vipInfoMap", vipInfoMap);
        return "admin/vip/vipEdit.jsp";
    }
	
	
	/**
	 * 修改保存页面
	 * @return
	 */
	   @RequestMapping("/updateVipInfo.do")
	    public String updateVipInfo(Vip vip) {
	        boolean flag = vipService.updateVipInfo(vip);
	        if (flag) {// 修改成功
	            return "redirect:/getAllVip.do";
	        }
	        return "redirect:/getVipInfoById.do";
	    }
	
	
}
