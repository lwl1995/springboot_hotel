package cn.java.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.java.entity.Rooms;
import cn.java.entity.Vip;
import cn.java.mapper.RoomsMapper;
import cn.java.service.RoomsService;
@Service
public class RoomsServiceImpl implements RoomsService {

	@Autowired
	private RoomsMapper roomsMapper;

	@Override
	public List<Map<String, Object>> getAllRooms(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return roomsMapper.getAllRooms();
	}
	
	/**
	 * 批量删除
	 * @param idAttr
	 * @return
	 */
	@Override
	public boolean batchDel(String idAttr) {//“1,2,3,”
		idAttr = idAttr.substring(0, idAttr.length()-1);  //变成“1,2,3”
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("idAttr", idAttr);
		int flag = roomsMapper.batchDel(paramMap);
		return flag>= 1?true:false;
	}

	/**
	 * 删除
	 */
	@Override
	public boolean delRoomsById(Long id) {
		return roomsMapper.delRoomsById(id)>=1?true:false;
	}

	@Override
	public Map<String, Object> findRoomsInfoById(Long id) {
		// TODO Auto-generated method stub
		return roomsMapper.getRoomsInfoById(id);
	}
	
	/**
	 * 保存修改信息
	 */
	@Override
    public int saveRoomsInfo(Rooms rooms) {
	
       
        return roomsMapper.saveRoomsInfo(rooms);
    }

	/**
	 * 添加房间信息
	 */
	@Override
	public int addRommsInfo(Rooms room) {
		// TODO Auto-generated method stub
		return roomsMapper.addRoomsInfo(room);
	}

	

	
}
