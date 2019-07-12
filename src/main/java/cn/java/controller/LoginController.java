package cn.java.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.java.entity.OneMenu;
import cn.java.service.LoginService;
@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "admin/login.jsp";
	}
	
	/**
	 * 登陆
	 * @param username 从jsp页面接收
	 * @param pwd    从jsp页面接收
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login.do")
	public String login(String username,String pwd,HttpSession session) throws Exception {
		
		System.out.println(username+"-----------------------------------"+pwd);
		
		Long flag = loginService.isLoginSuccess(username, pwd);
		if(flag != null || flag != 0) {	//flag=true
			session.setAttribute("username", username);
			//获取所有的菜单
			List<OneMenu> OneMenuList = loginService.selectMenusById(flag);
			session.setAttribute("OneMenuList", OneMenuList);
			return "redirect:/pages/admin/index.jsp";   //在application.properties中配置了试图解析器了，但是对重定向无效，所以还得写pages
		}else {		//flag=false
			return "redirect:/pages/admin/login.jsp";
		}	
	}

	 
	
}
