package com.fullcrum.service.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.TransactionEntity;

public interface TransactionService {

	public ArrayList<Map<String,Object>> selectTransacByTransacId(String transactionId);
	
	public ArrayList<Map<String,Object>> selectTransacByBillNumber(String billNumber);
	
	public ArrayList<Map<String,Object>> selectTransacByBuyerId(String buyerId);
	
	public ArrayList<Map<String,Object>> selectTransacBySellerId(String sellerId);
	
	public void insertTransaction(TransactionEntity transactionEntity) throws Exception;
	
	public void insertTransaction( JSONObject transactionEntity);
	
	public void deleteTransaction(int transactionId);
	
	public void updateTransaction(TransactionEntity transactionEntity);
	
	public void updateTransactionIntentionStatus(JSONObject jsonObject);
	
	public void updateTransactionStatus(JSONObject jsonObject);
	
	public void setTransactionIntentionStatusByOrderId(JSONObject jsonObject);
	
	public List<Map<String, Object>> selectTransInfo(int transactionId);

	public void updateTransStatus(int transactionId, String transStatus);
	
	public void setTransactionIntentionStatus(JSONObject jsonObject);

	public List<Map<String,Object>> selectAllTrans(Integer pageSize,Integer currentPage);

	public void updateTransStatus(String billNumber);

	public Integer getCount();

	List<Map<String, Object>> selectOrderIdByBillNum(String billNumber);

	void updateIntentionStatusByBillNum(JSONObject setTransacInvalid);
}
