package com.fullcrum.service.impl.sys;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcrum.dao.BillPicsDao;
import com.fullcrum.model.sys.BillPicsEntity;
import com.fullcrum.service.sys.BillPicsService;

@Service(value="billPicsServiceImpl")
public class BillPicsServiceImpl implements BillPicsService {

	@Autowired
	private BillPicsDao billPicsDao;
	
	@Override
	public ArrayList<BillPicsEntity> selectByBillNumber(String billNumber) {
		// TODO Auto-generated method stub
		return billPicsDao.selectByBillNumber(billNumber);
	}

	@Override
	public void insertBillPics(BillPicsEntity billPicsEntity) throws Exception {
		// TODO Auto-generated method stub
		billPicsDao.insertPics(billPicsEntity);
	}

	@Override
	public void updateBillPics(BillPicsEntity billPicsEntity) {
		// TODO Auto-generated method stub
		billPicsDao.updateByBillNumber(billPicsEntity);
	}

	@Override
	public void deleteBillPics(String billNumber) {
		// TODO Auto-generated method stub
		billPicsDao.deletePics(billNumber);
	}

	
}
