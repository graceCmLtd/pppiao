package com.fullcrum.service.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.TransactionEntity;

public interface TransactionService {
	
	
	
	//select -------------------------------

	ArrayList<Map<String,Object>> selectTransacByTransacId(String transactionId);
	
	String getIntentionStatusByTransacType(String orderId);
	
	ArrayList<Map<String,Object>> selectTransacByBillNumber(String billNumber);
	
	ArrayList<Map<String,Object>> selectTransacByBuyerId(String buyerId);
	
	ArrayList<Map<String,Object>> selectTransacBySellerId(String sellerId);
	
	List<Map<String, Object>> selectTransInfo(int transactionId);
	
	List<Map<String,Object>> selectAllTrans(Integer pageSize,Integer currentPage);

	Integer getCount();
	
	List<Map<String, Object>> selectOrderIdByBillNum(String billNumber);

	

	List<Map<String, Object>> selectCountByIntentionStatus(JSONObject jsonObject);
	
	//insert -------------------------------
	
	void insertTransaction(TransactionEntity transactionEntity) throws Exception;
	
	void insertTransaction( JSONObject transactionEntity);
	
	
	//delete -------------------------
	void deleteTransaction(int transactionId);
	
	
	
	//update -------------------------------
	void updateTransaction(TransactionEntity transactionEntity);
	
	void updateTransactionIntentionStatus(JSONObject jsonObject);
	
	void updateTransactionStatus(JSONObject jsonObject);
	
	void updateTransStatus(String billNumber);
	
	void updateTransStatus(int transactionId, String transStatus);
	
	void updateIntentionStatusByBillNum(JSONObject setTransacInvalid);
	
	void updateTransactionRealMoney(JSONObject jsonObject);
	
	//set ----------------------
	void setTransactionIntentionStatusByOrderId(JSONObject jsonObject);
	
	void setTransactionIntentionStatus(JSONObject jsonObject);


}
