package cn.java.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import cn.java.entity.InRoomInfo;
import cn.java.entity.Order;

public interface OrderService {

	/**
	 * 获取数据表中所有已经入住的房间号(订单添加)
	 * @return
	 */
	List<Map<String, Object>> getRoomsByStatus();

	/**
	 * 添加订单
	 * @param order
	 * @return
	 */
	boolean saveOrder(Order order);
	
	/**
     * 
     * Description: 查询所有订单信息<br/>
     * @return
     */
    List<Map<String, Object>> findAllOrders(Integer pageNum,Integer pageSize);
    
    /**
	 * 删除vip信息
	 * @param id
	 * @return
	 */
	boolean delOrderById(Long id);
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	boolean batchOrderDelInfo(String idAttr);
	
	/* 编辑页面回显*/
    Map<String,Object> InOrdersEdit(Long id);
    
    /*保存编辑*/
    boolean updateSaveOrder(Order order);
}