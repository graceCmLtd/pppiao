package com.fullcrum.service.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.TransactionEntity;

public interface TransactionService {

	public ArrayList<TransactionEntity> selectTransacByTransacId(String transactionId);
	
	public ArrayList<TransactionEntity> selectTransacByBillNumber(String billNumber);
	
	public ArrayList<TransactionEntity> selectTransacByBuyerId(String buyerId);
	
	public ArrayList<TransactionEntity> selectTransacBySellerId(String sellerId);
	
	public void insertTransaction(TransactionEntity transactionEntity) throws Exception;
	
	public void deleteTransaction(int transactionId);
	
	public void updateTransaction(TransactionEntity transactionEntity);
	
	public void updateTransactionStatus(JSONObject jsonObject);

	public List<Map<String, Object>> selectTransInfo(int transactionId);

	public void updateTransStatus(int transactionId);
}
