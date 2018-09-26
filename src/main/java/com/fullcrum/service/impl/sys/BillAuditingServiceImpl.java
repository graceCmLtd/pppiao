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
	public void updateBillStatus(String billNumber,String status,String failReason) {
		// TODO Auto-generated method stub
		billAuditingDao.updateBillStatus(billNumber,status,failReason);
	}

	@Override
	public List<Map<String, Object>> getBills(Integer pageSize,Integer currentPage) {
		// TODO Auto-generated method stub
		return billAuditingDao.selectBills(pageSize,currentPage);
	}

	@Override
	public Integer selectCount() {
		// TODO Auto-generated method stub
		return billAuditingDao.selectCount();
	}


}
