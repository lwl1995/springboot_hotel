package cn.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.java.entity.InRoomInfo;
import cn.java.entity.Order;
import cn.java.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	/**
	 * 跳转到添加订单
	 * @return
	 */
	@RequestMapping("/toAddOrder.do")
	public String toAddOrder(Model model) {
		List<Map<String, Object>> roomList = orderService.getRoomsByStatus();
		model.addAttribute("roomList", roomList);
		return "admin/order/addOrder.jsp";
	}
	
	/**
	 * 添加订单，将数据保存到数据库的表中去
	 * @return
	 */
	 @RequestMapping("/addOrder.do")
	    public String addOrder(@Valid Order order, BindingResult br, HttpSession session) {
	        System.out.println(order);
	        boolean flag = br.hasErrors();
	        if (flag) {// 数据格式有错误
	            // 构建一个Map集合用来封装的错误信息
	            Map<String, Object> errorMap = new HashMap<String, Object>();
	            List<FieldError> fieldErrorList = br.getFieldErrors();
	            for (FieldError fieldError : fieldErrorList) {
	                String fieldName = fieldError.getField();
	                String errorMessage = fieldError.getDefaultMessage();
	                errorMap.put(fieldName, errorMessage);
	            }
	            session.setAttribute("errorMap", errorMap);
	            session.setAttribute("order", order);
	            return "redirect:/toAddOrder.do";
	        } else {// 数据格式全部正确
	            // 调用业务层，将数据保存到数据库的表中去
	            boolean result = orderService.saveOrder(order);
	            if (result) {
	                return "redirect:/getAllOrders.do";
	            }
	            return "redirect:/toAddOrder.do";
	        }
	    }
	 
	 /**
	  * 跳转到orderInfo.jsp页面，并且带数据过去
	  * @return
	  */
	    @RequestMapping("/getAllOrders.do")
	    public String getAllOrders(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
	            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize, Model model) {
	        // 调用业务层获取所有的订单信息
	        List<Map<String, Object>> orderList = orderService.findAllOrders(pageNum, pageSize);
	        // 将查询的结果传递给PageHelper后台分页插件的PageInfo工具类中去
	        PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(orderList);
	        model.addAttribute("pageInfo", pageInfo);
	        return "admin/order/orderInfo.jsp";
	    }
	    
	    /**
	     * 删除信息
	     * @param id
	     * @return
	     */
	    @RequestMapping("/delOrderInfo.do")
		@ResponseBody
		public boolean delVipInfo(Long id) {
			boolean delOrderById = orderService.delOrderById(id);
			return delOrderById;
		}
		
		/**
		 * 批量删除
		 * @param idAttr
		 * @return
		 */
		@RequestMapping("/batchOrder.do")
		@ResponseBody
		public boolean batchDel(String idAttr) {	
			return orderService.batchOrderDelInfo(idAttr);
		}
	    
		/**
		 *  跳转到编辑页面
		 * @param id
		 * @param model
		 * @return
		 */
		@RequestMapping("/getOrderInfoById.do")
		public String inorderEdit(Long id,Model model) {
			System.out.println("输出的id为-----------------"+id);
			Map<String, Object> orderEdit = orderService.InOrdersEdit(id);
			System.out.println("输出的orderEdit为-----------------------"+orderEdit);
			model.addAttribute("orderEdit", orderEdit);
			return "admin/order/inOrderEdit.jsp";
		}
		
		/**
		 * 修改保存页面
		 * @return
		 */
		   @RequestMapping("/saveOrder.do")
		    public String inroomSave(Order order) {
		        boolean flag = orderService.updateSaveOrder(order);
		        if (flag) {// 修改成功
		            return "redirect:/getAllOrders.do";
		        }
		        return "redirect:/getOrderInfoById.do";
		    }
		
}
