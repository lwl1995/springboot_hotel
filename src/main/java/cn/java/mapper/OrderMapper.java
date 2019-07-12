package cn.java.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.java.entity.InRoomInfo;
import cn.java.entity.Order;

public interface OrderMapper {

	/**
	 * 获取数据表中所有已经入住的房间号(订单添加)
	 * @return
	 */
	@Select("SELECT room_num,id FROM rooms where room_status='0'")
	List<Map<String,Object>> getRoomsByStatus();
	
	/**
	 * 添加订单
	 * @param order
	 * @return
	 */
	@Insert("INSERT INTO `orders`  VALUES(NULL,#{orderNum},#{money},#{orderStatus},#{roomId},#{createDate})")
    int addOrder(Order order);
	
	
	/**
     * 
     * Description: 查询所有订单信息<br/>
     * @return
     */
    /*@Select(value = "SELECT o.*,iri.customer_name,rs.room_num FROM `orders` o INNER JOIN in_room_info iri ON o.room_id=iri.room_id INNER JOIN rooms rs ON rs.id=o.room_id\r\n"
            + "")*/
	@Select(value = "SELECT o.*,iri.customer_name,rs.room_num FROM `orders` o INNER JOIN in_room_info iri ON o.room_id=iri.room_id INNER JOIN rooms rs ON rs.id=o.room_id where order_status='1'")
    List<Map<String, Object>> getAllOrders(Integer pageNum,Integer pageSize);
    
    /**
	 * 删除订单信息
	 * @param id
	 * @return
	 */
	@Update("UPDATE orders SET order_status='0' WHERE id=#{arg0}")
	int delOrdersById(Long id);
	
	
	/**
	 * 批量删除
	 * @param paramMap
	 * @return
	 */
	@Update("UPDATE orders SET ORDER_STATUS='0' WHERE id IN(${idAttr})")
	int batchOrderDel(Map<String,Object> paramMap);
	
	/**
	 * 回显页面
	 * @param id
	 * @return
	 */
    @Select("SELECT o.*,iri.customer_name,rs.room_num FROM `orders` o INNER JOIN in_room_info iri ON o.room_id=iri.room_id INNER JOIN rooms rs ON rs.id=o.room_id where o.id=#{id}")
    Map<String,Object> InOrdersEdit(Long id);
    
    /**
     * 保存回显数据
     * @param order
     * @return
     */
    @Update("update orders o inner join in_room_info iri set iri.customer_name='张飞飞',o.order_status='1',iri.gender=#{gender},iri.idcard=#{idcard},iri.phone=#{phone},o.create_date=#{createDate} where iri.id=#{id}")
    int updateSaveOrder(Order order);
   
	
}
