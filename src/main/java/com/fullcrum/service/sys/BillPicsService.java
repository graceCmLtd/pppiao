package com.fullcrum.service.sys;

import java.util.ArrayList;

import com.fullcrum.model.sys.BillPicsEntity;

public interface BillPicsService {

	public ArrayList<BillPicsEntity> selectByBillNumber(String billNumber);
	
	public void insertBillPics(BillPicsEntity billPicsEntity) throws Exception;
	
	public void updateBillPics(BillPicsEntity billPicsEntity);
	
	public void deleteBillPics(String billNumber);
}
