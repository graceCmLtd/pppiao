package com.fullcrum.service.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.ResourceMarketEntity;

public interface ResourceMarketService {

	public ArrayList<ResourceMarketEntity> selectAll();
	

	public ArrayList<ResourceMarketEntity> selectByOrderId( String orderId);
	

	public ArrayList<ResourceMarketEntity> selectByBuyerId( String buyerId);
	
	public List<Map<String, Object>> selectPriorityForResourcePool();
	
	public List<Map<String, Object>> selectByBuyerIDForResourcePool( JSONObject jsonObject);
	
	
	public void insert( ResourceMarketEntity resourceMarketEntity);
	
	public void insertObj(JSONObject jsonObject);
	
	public void deleteByOrderId( String orderId); 
	
	public void updateByOrderId( JSONObject jsonObject);


	public List<Map<String, Object>> selectAllInfo();
}
