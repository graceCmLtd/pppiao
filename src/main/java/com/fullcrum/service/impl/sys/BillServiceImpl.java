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
	public List<Map<String, Object>> getALLIntentions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getALLIntentions(jsonObject);
	}


	@Override
	public List<Map<String, Object>> getConfirmedIntentions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getConfirmedIntentions(jsonObject);
	}


	@Override
	public List<Map<String, Object>> getConfirmingIntentions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getConfirmingIntentions(jsonObject);
	}


	@Override
	public List<Map<String, Object>> getRefusedIntentions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getRefusedIntentions(jsonObject);
	}


	@Override
	public List<Map<String, Object>>  selectByBillNumberWithRemainDays(String billNumber, String current_date) {
		// TODO Auto-generated method stub
		return billDao.selectByBillNumberAll(billNumber, current_date);
	}


	@Override
	public List<Map<String, Object>> getBuyerConfirmingIntentions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getBuyerConfirmingIntentions(jsonObject);
	}


	@Override
	public List<Map<String, Object>> getBuyerConfirmedIntentions(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return billDao.getBuyerConfirmedIntentions(jsonObject);
	}

	
}
