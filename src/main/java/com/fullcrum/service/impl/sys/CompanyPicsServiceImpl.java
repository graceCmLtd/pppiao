package com.fullcrum.service.impl.sys;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.dao.CompanyPicsDao;
import com.fullcrum.model.sys.CompanyPicsEntity;
import com.fullcrum.service.sys.CompanyPicsService;

@Service(value="companyPicsServiceImpl")
public class CompanyPicsServiceImpl implements CompanyPicsService {

	@Autowired
	private CompanyPicsDao companyPicsDao;
	
	
	@Override
	public void insertCompanyPics(CompanyPicsEntity companyPicsEntity) {
		companyPicsDao.insertCompanyPics(companyPicsEntity);
	}
	
	@Override
	public void deleteCompanyPics(CompanyPicsEntity companyPicsEntity) {
		companyPicsDao.deleteCompanyPics(companyPicsEntity);
	}

	@Override
	public void insertCompanyPicsByJson(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		companyPicsDao.insertCompanyPicsByJson(jsonObject);
		
	}

	@Override
	public ArrayList<CompanyPicsEntity> selectCompanyPicsByContactsId(String contactsId) {
		// TODO Auto-generated method stub
		return companyPicsDao.selectCompanyPicsByContactsId(contactsId);
	}

	@Override
	public CompanyPicsEntity selectPic(String picId) {
		// TODO Auto-generated method stub
		return companyPicsDao.selectPic(picId);
	}

	@Override
	public void updateCompanyPics(CompanyPicsEntity companyPicsEntity) {
		companyPicsDao.updatePic(companyPicsEntity);
	}
}
