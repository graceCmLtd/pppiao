package com.fullcrum.service.impl.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.dao.BillDao;
import com.fullcrum.model.sys.BillEntity;
import com.fullcrum.service.sys.BillService;

@Service(value="billServiceImpl")
public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billDao;
	
	
	
	@Override
	public ArrayList<BillEntity> selectByBillNumber(String billNumber) {
		// TODO Auto-generated method stub
		return billDao.selectByBillNumber(billNumber);
	}


	@Override
	public void deleteBill(String billNumber) {
		// TODO Auto-generated method stub
		billDao.deleteBill(billNumber);
	}

	@Override
	public void update(BillEntity billEntity) {
		// TODO Auto-generated method stub
		billDao.updateBillByBillNumber(billEntity);
	}

	@Override
	public void insertBill(BillEntity billEntity) throws Exception {
		// TODO Auto-generated method stub
		Integer temp = UUID.randomUUID().hashCode();
		while (temp < 0) {
			temp = UUID.randomUUID().hashCode();
		}
		billEntity.setBillId(temp.toString());
		
		
		billEntity.setReleaseDate(new java.sql.Date(new Date().getTime()));
		System.out.println("insert bill <<<<<<");
		billDao.insertBill(billEntity);
		
	}

	/*List<Map<String, Object>>*/
	@Override
	public List<Map<String, Object>> selectByFilter(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
		System.out.println("select by filter imple........................");
		return billDao.selectByFilter(jsonObject);
	}


	@Override
	public ArrayList<BillEntity> selectAllBill() {
		// TODO Auto-generated method stub
		return billDao.selectAllBill();
	}


	@Override
	public List<Map<String, Object>> getBillsInquoting(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getBillsInquoting(jsonObject);
	}


	@Override
	public List<Map<String, Object>> getBillsReceivedQuote(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getBillsReceivedQuote(jsonObject);
	}


	@Override
	public List<Map<String, Object>> getBillsWaitingQuote(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getBillsWaitingQuote(jsonObject);
	}


	@Override
	public List<Map<String, Object>> getBillsAuditing(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getBillsAuditing(jsonObject);
	}



	@Override
	public List<Map<String, Object>>  selectByBillNumberWithRemainDays(String billNumber, String current_date) {
		// TODO Auto-generated method stub
		return billDao.selectByBillNumberAll(billNumber, current_date);
	}


	//获取意向列表 
	@Override
	public List<Map<String, Object>> getSellerALLIntentions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getSellerALLIntentions(jsonObject);
	}


	@Override
	public List<Map<String, Object>> getBuyerALLIntentions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getBuyerALLIntentions(jsonObject);
	}


	@Override
	public List<Map<String, Object>> getSellerIntentions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getSellerIntentions(jsonObject);
	}


	@Override
	public List<Map<String, Object>> getBuyerIntentions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getBuyerIntentions(jsonObject);
	}


	@Override
	public Integer getCount(JSONObject conditions) {
		return billDao.getCount(conditions);
	}


	@Override
	public Integer getSellerALLIntentionsCount(JSONObject jsonObject) {
		return billDao.getSellerALLIntentionsCount(jsonObject);
	}


	@Override
	public Integer getSellerIntentionsCount(JSONObject jsonObject) {
		return billDao.getSellerIntentionsCount(jsonObject);
	}


	@Override
	public Integer getBuyerALLIntentionsCount(JSONObject jsonObject) {
		return billDao.getBuyerALLIntentionsCount(jsonObject);
	}


	@Override
	public Integer getBuyerIntentionsCount(JSONObject jsonObject) {
		return billDao.getBuyerIntentionsCount(jsonObject);
	}


	@Override
	public Integer getBillsReceivedQuoteCount(JSONObject jsonObject) {
		return billDao.getBillsReceivedQuoteCount(jsonObject);
	}


	@Override
	public Integer getBillsWaitingQuoteCount(JSONObject jsonObject) {
		return billDao.getBillsWaitingQuoteCount(jsonObject);
	}


	@Override

	public List<Map<String, Object>> getBillsAuditingPool(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getBillsAuditingPool(jsonObject);
	}
	
	@Override
	public List<Map<String, Object>> getNotAuditIntentions(JSONObject jsonObject) {
		return billDao.getNotAuditIntentions(jsonObject);

	}


	@Override
	public List<Map<String, Object>> getSellerIntentionsAuditing(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getSellerIntentionsAuditing(jsonObject);
	}
	@Override
	public Integer getNotAuditIntentionsCount(JSONObject jsonObject) {
		return billDao.getNotAuditIntentionsCount(jsonObject);

	}


	

	
}
