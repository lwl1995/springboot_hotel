package cn.java.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.java.entity.InRoomInfo;
import cn.java.entity.Vip;
import cn.java.mapper.VipMapping;
import cn.java.service.VipService;
@Service
public class VipServiceImpl implements VipService{

	@Autowired
	private VipMapping vipMapping;
	
	
	/**
	 * 分页查询所有的会员信息
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectAllVip(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return vipMapping.getAllVip();
	}


	/**
	 * 删除
	 */
	@Override
	public boolean delVipById(Long id) {
		return vipMapping.delVipById(id)>=1?true:false;
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
		int flag = vipMapping.batchDel(paramMap);
		return flag>= 1?true:false;
	}


	/**
	 * 添加
	 */
	@Override
	public int selectVipInfo(Vip vip) {
		return vipMapping.addVipInfo(vip);
	}


	@Override
    public Map<String, Object> findVipInfoById(Long id) {
        return vipMapping.getVipInfoById(id);
    }


	@Override
    public boolean updateVipInfo(Vip vip) {
        return vipMapping.updateVipInfo(vip) >= 1 ? true : false;
    }


}
