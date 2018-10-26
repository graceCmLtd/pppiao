package com.fullcrum.service.sys;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface MsgService {

	void insertMsg(JSONObject jsonObject);
	
	List<Map<String, Object>> selectMsgByReceiverId( String receiverId,Integer currentPage,Integer pageSize);
	
	void updateReceiverFlag( JSONObject jsonObject);

	Integer selectMsgCount(String receiverId);

	void updateAllFlag(JSONObject jsonObject);
}
