package com.fullcrum.service.impl.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.dao.MsgDao;
import com.fullcrum.service.sys.MsgService;

@Service(value="msgServiceImpl")
public class MsgServiceImpl implements MsgService {

	@Autowired
	private MsgDao msgDao;
	
	
	@Override
	public void insertMsg(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		msgDao.insertMsg(jsonObject);
	}

	@Override
	public List<Map<String, Object>> selectMsgByReceiverId(String receiverId,Integer currentPage,Integer pageSize) {
		// TODO Auto-generated method stub
		return  msgDao.selectMsgByReceiverId(receiverId,currentPage,pageSize);
	}

	@Override
	public void updateReceiverFlag(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		msgDao.updateReceiverFlag(jsonObject);
	}

	@Override
	public Integer selectMsgCount(String receiverId) {
		return msgDao.selectMsgCount(receiverId);
	}

	@Override
	public void updateAllFlag(JSONObject jsonObject) {
		msgDao.updateAllFlag(jsonObject);
	}

}
