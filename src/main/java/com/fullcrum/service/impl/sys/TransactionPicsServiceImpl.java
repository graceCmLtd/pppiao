package com.fullcrum.service.impl.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.dao.TransactionPicsDao;
import com.fullcrum.model.sys.TransactionPicsEntity;
import com.fullcrum.service.sys.TransactionPicsService;

@Service
public class TransactionPicsServiceImpl implements TransactionPicsService {

	@Autowired
	private TransactionPicsDao transactionPicsDao;
	
	@Override
	public List<TransactionPicsEntity> selectByOrderId(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return transactionPicsDao.selectByOrderId(jsonObject);
	}

	@Override
	public void insertPics(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		transactionPicsDao.insertPics(jsonObject);
	}

}
