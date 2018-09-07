package com.fullcrum.service.impl.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcrum.dao.BillDao;
import com.fullcrum.service.sys.BillAuditingService;

@Service(value="billAuditingServiceImpl")
public class BillAuditingServiceImpl implements BillAuditingService{

	@Autowired
	private BillDao billAuditingDao;
	
	@Override
	public List<Map<String, Object>> getBillInfo(String billNumber) {
		// TODO Auto-generated method stub
		return billAuditingDao.selectBillInfo(billNumber);
	}

	@Override
	public void updateBillStatus(String billNumber) {
		// TODO Auto-generated method stub
		billAuditingDao.updateBillStatus(billNumber);
	}

	@Override
	public List<Map<String, Object>> getBills() {
		// TODO Auto-generated method stub
		return billAuditingDao.selectBills();
	}

}
