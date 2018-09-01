package com.fullcrum.service.impl.sys;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.dao.TransactionDao;
import com.fullcrum.model.sys.TransactionEntity;
import com.fullcrum.service.sys.TransactionService;

@Service(value="transactionServiceImpl")
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	@Override
	public ArrayList<TransactionEntity> selectTransacByTransacId(String transactionId) {
		// TODO Auto-generated method stub
		return transactionDao.selectTransacByTransacId(transactionId);
	}

	@Override
	public ArrayList<TransactionEntity> selectTransacByBillNumber(String billNumber) {
		// TODO Auto-generated method stub
		return transactionDao.selectTransacByBillNumber(billNumber);
	}

	@Override
	public ArrayList<TransactionEntity> selectTransacByBuyerId(String buyerId) {
		// TODO Auto-generated method stub
		return transactionDao.selectTransacByBuyerId(buyerId);
	}

	@Override
	public ArrayList<TransactionEntity> selectTransacBySellerId(String sellerId) {
		// TODO Auto-generated method stub
		return transactionDao.selectTransacBySellerId(sellerId);
	}

	@Override
	public void insertTransaction(TransactionEntity transactionEntity) throws Exception {
		// TODO Auto-generated method stub
		Integer temp = UUID.randomUUID().hashCode();
		while (temp <= 0) {
			temp = UUID.randomUUID().hashCode();
		}
		transactionEntity.setTransactionId(temp.toString());
		
		transactionEntity.setTransacDate(new Date(new java.util.Date().getTime()));
		transactionDao.insertTransaction(transactionEntity);
	}

	@Override
	public void deleteTransaction(int transactionId) {
		// TODO Auto-generated method stub
		transactionDao.deleteTransaction(transactionId);
	}

	@Override
	public void updateTransaction(TransactionEntity transactionEntity) {
		// TODO Auto-generated method stub
		transactionDao.updateTransaction(transactionEntity);
	}

	@Override
	public void updateTransactionStatus(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		transactionDao.updateTransactionStatus(jsonObject);
	}

	@Override
	public List<Map<String, Object>> selectTransInfo(int transactionId) {
		return transactionDao.selectTransInfo(transactionId);
	}

	@Override
	public void updateTransStatus(int transactionId) {
		transactionDao.updateTransStatus(transactionId);
	}
	
	
}
