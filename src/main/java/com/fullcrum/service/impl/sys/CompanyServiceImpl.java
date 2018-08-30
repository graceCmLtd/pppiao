package com.fullcrum.service.impl.sys;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcrum.dao.CompanyDao;
import com.fullcrum.model.sys.CompanyEntity;
import com.fullcrum.service.sys.CompanyService;

@Service(value="companyServiceImpl")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;
	

	@Override
	public void insertCompany(CompanyEntity companyEntity) {
		// TODO Auto-generated method stub
		companyDao.insertCompany(companyEntity);
	}

	@Override
	public void deleteCompany(CompanyEntity companyEntity) {
		// TODO Auto-generated method stub
		companyDao.deleteCompany(companyEntity);
		
	}


	@Override
	public ArrayList<CompanyEntity> selectAll() {
		// TODO Auto-generated method stub
		return companyDao.selectAll();
	}

	@Override
	public ArrayList<CompanyEntity> selectByContactsId(String contactsId) {
		// TODO Auto-generated method stub
		return companyDao.selectByContactsId(contactsId);
	}

}
