package com.fullcrum.service.sys;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.TransactionPicsEntity;

public interface TransactionPicsService {

	
	List<TransactionPicsEntity> selectByOrderId(JSONObject jsonObject);
	
	void insertPics(JSONObject jsonObject);
}
