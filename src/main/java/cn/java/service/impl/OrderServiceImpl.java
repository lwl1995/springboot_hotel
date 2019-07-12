package cn.java.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.java.entity.InRoomInfo;
import cn.java.entity.Order;
import cn.java.mapper.OrderMapper;
import cn.java.service.OrderService;

@Service
@Transactional(readOnly=false)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	/* (non-Javadoc)
	 * @see cn.java.service.impl.OrderService#getRoomsByStatus()
	 */
	@Override
	public List<Map<String,Object>> getRoomsByStatus(){
		
		return orderMapper.getRoomsByStatus();
	}

	@Override
	public boolean saveOrder(Order order) {
		 // 生成订单的编号
        String orderNum = UUID.randomUUID().toString();
        // 生成订单时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDate = sdf.format(new Date());
        order.setCreateDate(createDate);
        order.setOrderNum(orderNum);
        return orderMapper.addOrder(order) >= 1 ? true : false;
	}

	@Override
	public List<Map<String, Object>> findAllOrders(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return orderMapper.getAllOrders(pageNum, pageSize);
	}
	
	/**
	 * 删除
	 */
	@Override
	public boolean delOrderById(Long id) {
		return orderMapper.delOrdersById(id)>=1?true:false;
	}
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	@Override
	public boolean batchOrderDelInfo(String idAttr) {//“1,2,3,”
		idAttr = idAttr.substring(0, idAttr.length()-1);  //变成“1,2,3”
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("idAttr", idAttr);
		int flag = orderMapper.batchOrderDel(paramMap);
		return flag>= 1?true:false;
	}

	/**
	 * 回显页面
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> InOrdersEdit(Long id) {
		// TODO Auto-generated method stub
		return orderMapper.InOrdersEdit(id);
	}

	
	 /**
     * 保存回显数据
     * @param order
     * @return
     */
	@Override
    public boolean updateSaveOrder(Order order) {
		
        return orderMapper.updateSaveOrder(order) >= 1 ? true : false;
    }
	
}
