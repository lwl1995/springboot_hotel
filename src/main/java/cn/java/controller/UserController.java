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

import cn.java.entity.OneMenu;
import cn.java.entity.User;
import cn.java.entity.Vip;
import cn.java.mapper.UserMapper;
import cn.java.service.UserService;
import cn.java.service.impl.UserServceImpl;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	

	/**
	 * 分页显示用户信息
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping("/getUserInfo.do")
	public String getInfoRoomInfo(@RequestParam(name="pageNum",defaultValue="1") Integer pageNum
			,@RequestParam(name="pageSize",defaultValue="1") Integer pageSize,Model model) {
		//分页之后显示的数据
		List<Map<String, Object>> infoList = userService.findAllUserInfo(pageNum, pageSize);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(infoList);
		model.addAttribute("pageInfo",pageInfo);
		return "admin/user/userInfo.jsp";
	}
	
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/delUserInfo.do")
	@ResponseBody
	public boolean delVipInfo(Long id) {
		boolean delVipById = userService.delUserById(id);
		
		return delVipById;
	}
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	@RequestMapping("/batchUserDel.do")
	@ResponseBody
	public boolean batchDVipel(String idAttr) {	
		System.out.println("选中的id为----------------"+idAttr);
		return userService.userAmountDel(idAttr);
	}
	
	/**
	 * 用户回显
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/getUserInfoById.do")
    public String getVipInfoById(Long id, Model model) {
		System.out.println("输出的id为------------"+id);
        // 调用业务层
        Map<String, Object> userInfoMap = userService.findUserInfoById(id);
        System.out.println("userInfoMap为----------------"+userInfoMap);
        // 跳转到修改页面，并且带数据过去
        model.addAttribute("userInfoMap", userInfoMap);
        return "admin/user/userEdit.jsp";
    }
	
	
/*	*//**
	 * 修改保存页面
	 * @return
	 *//*
	   @RequestMapping("/updateUserInfo.do")
	    public String updateVipInfo(User user) {
	        boolean flag = userService.updateUserInfo(user);
	        if (flag) {// 修改成功
	            return "redirect:/getUserInfo.do";
	        }
	        return "redirect:/getUserInfoById.do";
	    }*/
	
	
	   /**
	     * 
	     * Description: 修改禁用、启用的状态<br/>
	     *
	     * @author 丁鹏(大胆开车，幽默讲课)
	     * @param id
	     * @param useStaus
	     * @return
	     */
	    @RequestMapping("/updateUseStatus.do")
	    public @ResponseBody boolean updateUseStatus(Long id, String useStatus) {
	    	
	        System.out.println("-----------useStatus为------------------"+useStatus);
	        return userService.updateUseStatus(id, useStatus);
	    }

	    /**
	     * 
	     * Description: 跳转到添加用户页面<br/>
	     *
	     * @return
	     */
	  /*  @RequestMapping("/toAddUser.do")
	    public String toAddUser(Model model) {
	        return "admin/user/addUser.jsp";
	    }
	    @RequestMapping("/addUser.do")
	    public @ResponseBody boolean addUser(String username, String password)
	            throws Exception {
	        return userService.saveUser(username, password);
	    }*/
	    /**
	     * 
	     * Description: 跳转到添加用户页面<br/>
	     *
	     * @author 丁鹏(大胆开车，幽默讲课)
	     * @return
	     */
	    @RequestMapping("/toAddUser.do")
	    public String toAddUser(Model model) {
	        List<OneMenu> oneMenuList = userService.findAllAuthority();
	        System.out.println("oneMenuList为------------"+oneMenuList);
	        model.addAttribute("oneMenuList", oneMenuList);
	        return "admin/user/addUser.jsp";
	    }

	    @RequestMapping("/addUser.do")
	    public @ResponseBody boolean addUser(String username, String password, String oneIds, String twoIds)
	            throws Exception {
	        return userService.saveUser(username, password, oneIds, twoIds);
	    }
}
