package com.fullcrum.service.impl.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.dao.ResourceMarket;
import com.fullcrum.model.sys.ResourceMarketEntity;
import com.fullcrum.service.sys.ResourceMarketService;

@Service(value="resourceMarketImpl")
public class ResourceMarketImpl implements ResourceMarketService {

	
	@Autowired
	private ResourceMarket resourceMarket;
	
	@Override
	public ArrayList<ResourceMarketEntity> selectAll() {
		// TODO Auto-generated method stub
		return resourceMarket.selectAll();
	}

	@Override
	public ArrayList<ResourceMarketEntity> selectByOrderId(String orderId) {
		// TODO Auto-generated method stub
		return resourceMarket.selectByOrderId(orderId);
	}

	
	@Override
	public ArrayList<ResourceMarketEntity> selectByBuyerId(String buyerId,Integer pageSize,Integer currentPage) {
		// TODO Auto-generated method stub
		return resourceMarket.selectByBuyerId(buyerId,pageSize,currentPage);
	}

	@Override
	public void insert(ResourceMarketEntity resourceMarketEntity) {
		// TODO Auto-generated method stub
		resourceMarket.insert(resourceMarketEntity);
	}

	
	
	@Override
	public void deleteByOrderId(String orderId) {
		// TODO Auto-generated method stub
		resourceMarket.deleteByOrderId(orderId);
	}

	@Override
	public void updateByOrderId(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		resourceMarket.updateByOrderId(jsonObject);
	}

	@Override
	public List<Map<String, Object>> selectPriorityForResourcePool() {
		// TODO Auto-generated method stub
		return resourceMarket.selectPriorityForResourcePool();
	}

	@Override
	public List<Map<String, Object>> selectByBuyerIDForResourcePool(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return resourceMarket.selectByBuyerIDForResourcePool(jsonObject);
	}

	@Override
	public List<Map<String, Object>> selectAllInfo(Integer pageSize,Integer currentPage) {
		return resourceMarket.selectAllInfo(pageSize,currentPage);
	}

	@Override
	public Integer getCount() {
		return resourceMarket.getCount();
	}

	@Override
	public void insertObj(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		resourceMarket.insertObj(jsonObject);
	}

	@Override
	public void updateNoteByUserId(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		resourceMarket.updateNoteByUserId(jsonObject);
	}
	
	
	
	@Override
	public Integer getCountByBuyerId(String buyerId) {
		return resourceMarket.getCountByBuyerId(buyerId);

	}

	@Override
	public ArrayList<ResourceMarketEntity> selectByConditions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return resourceMarket.selectByConditions(jsonObject);
	}

	/*有值时返回条数，返回行为空时为false*/
	@Override
	public Integer checkRepetition(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		ArrayList<ResourceMarketEntity> result = resourceMarket.selectByConditions(jsonObject);
		if (result.isEmpty()) {
			return 0;
		}else {
			return result.size();
		}
	}

}
