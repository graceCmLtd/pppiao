package com.fullcrum.service.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.BillEntity;

public interface BillService {
	
	public ArrayList<BillEntity> selectAllBill();

	public ArrayList<BillEntity> selectByBillNumber(String billNumber);
	
	public List<Map<String, Object>>  selectByBillNumberWithRemainDays(String billNumber,String current_date);
	
	
	public void insertBill(BillEntity  billEntity) throws Exception;
	
	
	public void deleteBill(String billNumber);
	
	public void update(BillEntity billEntity);
	
	public List<Map<String, Object>>  selectByFilter(JSONObject jsonObject);
	/*List<Map<String, Object>>*/
	
	public List<Map<String, Object>> getBillsInquoting(JSONObject jsonObject);
	
	
	public List<Map<String, Object>> getBillsReceivedQuote(JSONObject jsonObject);

	public List<Map<String, Object>> getBillsWaitingQuote(JSONObject jsonObject);

	public List<Map<String, Object>> getBillsAuditing(JSONObject jsonObject);
	
	public List<Map<String, Object>> getBillsAuditingPool(JSONObject jsonObject);
	
	//获取意向列表
	
	public List<Map<String, Object>> getSellerALLIntentions(JSONObject jsonObject);

	public List<Map<String, Object>> getBuyerALLIntentions(JSONObject jsonObject);
	
	public List<Map<String, Object>> getSellerIntentions(JSONObject jsonObject);
	
	public List<Map<String, Object>> getBuyerIntentions(JSONObject jsonObject);

	public List<Map<String, Object>> getSellerIntentionsAuditing(JSONObject jsonObject);
	
	public Integer getCount(JSONObject conditions);

	public Integer getSellerALLIntentionsCount(JSONObject jsonObject);

	public Integer getSellerIntentionsCount(JSONObject jsonObject);

	public Integer getBuyerALLIntentionsCount(JSONObject jsonObject);

	public Integer getBuyerIntentionsCount(JSONObject jsonObject);

	public Integer getBillsReceivedQuoteCount(JSONObject jsonObject);

	public Integer getBillsWaitingQuoteCount(JSONObject jsonObject);
	
	
	
}
