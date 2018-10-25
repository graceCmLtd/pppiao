package com.fullcrum.service.sys;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface MsgService {

	void insertMsg(JSONObject jsonObject);
	
	List<Map<String, Object>> selectMsgByReceiverId( String receiverId);
	
	void updateReceiverFlag( JSONObject jsonObject);
}
