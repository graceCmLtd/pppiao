package com.fullcrum.service.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.ResourceMarketEntity;

public interface ResourceMarketService {

	public ArrayList<ResourceMarketEntity> selectAll();
	

	public ArrayList<ResourceMarketEntity> selectByOrderId( String orderId);
	

	public ArrayList<ResourceMarketEntity> selectByBuyerId( String buyerId, Integer pageSize, Integer currentPage);
	
	public List<Map<String, Object>> selectPriorityForResourcePool();
	
	public List<Map<String, Object>> selectByBuyerIDForResourcePool( JSONObject jsonObject);
	
	public ArrayList<ResourceMarketEntity> selectByConditions(JSONObject jsonObject);
	
	public void insert( ResourceMarketEntity resourceMarketEntity);
	
	public void insertObj(JSONObject jsonObject);
	
	public void deleteByOrderId( String orderId); 
	
	public void updateByOrderId( JSONObject jsonObject);
	
	public void updateNoteByUserId(JSONObject jsonObject);

	public List<Map<String, Object>> selectAllInfo(Integer pageSize, Integer i);


	public Integer getCount();

	public Integer checkRepetition(JSONObject jsonObject );

	public Integer getCountByBuyerId(String buyerId);
}
