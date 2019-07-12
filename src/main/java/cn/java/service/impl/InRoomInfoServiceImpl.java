package cn.java.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.java.entity.InRoomInfo;
import cn.java.entity.JilianMenu;
import cn.java.entity.Vip;
import cn.java.mapper.InRoomInfoMapper;
import cn.java.service.InRoomInfoService;
@Service
@Transactional(readOnly=false)
public class InRoomInfoServiceImpl implements InRoomInfoService {
	
	@Autowired
	private InRoomInfoMapper inRoomInfoMapper;
	
	//一级联菜单
	@Override
	public List<JilianMenu> selectProvinceList() {
		// TODO Auto-generated method stub
		return inRoomInfoMapper.selectProvinceList();
	}
	//二三级联菜单
	@Override
	public List<JilianMenu> getAllListByParentId(String aneald) {
		// TODO Auto-generated method stub
		return inRoomInfoMapper.selectCityAreaList(aneald);
	}
	
	
	/**
	 * 分页查询所有的入住信息
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllInRoomInfos(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return inRoomInfoMapper.getAllInRoomInfos();
	}

	
	/**
	 * 按选择分类和关键字查询(在入住信息查询页面中)
	 * @param type
	 * @param keyWord
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectgetInRoomInfoByCondition(String type, String keyWord) {
		// TODO Auto-generated method stub
		return inRoomInfoMapper.getInRoomInfoByCondition(type, keyWord);
	}


	/**
	 * 删除入住信息
	 * @param id
	 * @return
	 */
	@Override
	public boolean delInfoById(Long id) {
		
		return inRoomInfoMapper.delInfoById(id)>=1?true:false;
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
		int flag = inRoomInfoMapper.batchDel(paramMap);
		return flag>= 1?true:false;
	}

	/**
	 * 获取所有的空闲房间
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getAllKXRoom() {
		// TODO Auto-generated method stub
		return inRoomInfoMapper.getAllKXRoom();
	}


	@Override
	public boolean saveInRoomInfo(InRoomInfo info) {
		//根据roomNum查出房间对应的房间id
		Long roomId = inRoomInfoMapper.getRoomIdByRoomNum(info.getRoomNum());
		//将数据保存到in_room_info表中去
        info.setRoomId(roomId);
		int flag = inRoomInfoMapper.addInRoomInfo(info);
		//修改房间的状态
		if(flag>=1) {
			return inRoomInfoMapper.updateRoomStatus(info.getRoomNum()) > 1?true:false;
		}
		return false;
	}


	@Override
	public List<Map<String, Object>> selectRoomsByStatus() {
		
		return inRoomInfoMapper.getRoomsByStatus();
	}


	@Transactional(readOnly = false)
    @Override
    public Map<String, Object> getInRoomInfoByRoomId(Long roomId) {
        // 查询出入住信息
        Map<String, Object> roomInfoMap = inRoomInfoMapper.getInRoomInfoByRoomId(roomId);
        // 查询出房间对应的单间
        Float price = inRoomInfoMapper.getRoomPriceByRoomId(roomId);
        roomInfoMap.put("price", price);
        // 查询出其他消费金额
//        Float otherMoney = inRoomInfoMapper.getOtherMoney(roomId);
//        roomInfoMap.put("otherMoney", otherMoney);
        return roomInfoMap;
    }


	@Override
	public Map<String, Object> InRoomEdit(Integer id) {
		return inRoomInfoMapper.InRoomEdit(id);
	}


	/*@Override
	public int updateSaveInRoom(InRoomInfo inRoomInfo) {
		return inRoomInfoMapper.updateSaveInRoom(inRoomInfo);
	}*/
	
	@Override
    public boolean updateSaveInRoom(InRoomInfo inRoomInfo) {
        return inRoomInfoMapper.updateSaveInRoom(inRoomInfo) >= 1 ? true : false;
    }





}
