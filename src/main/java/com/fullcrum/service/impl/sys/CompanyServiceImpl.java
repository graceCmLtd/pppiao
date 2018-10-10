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
	public ArrayList<CompanyEntity> selectAll(Integer pageSize,Integer currentPage) {
		// TODO Auto-generated method stub
		return companyDao.selectAll(pageSize,currentPage);
	}

	@Override
	public ArrayList<CompanyEntity> selectByContactsId(String contactsId) {
		// TODO Auto-generated method stub
		return companyDao.selectByContactsId(contactsId);
	}

	@Override
	public ArrayList<CompanyEntity> selectByCompanyId(String companyId) {
		// TODO Auto-generated method stub
		return companyDao.selectByCompanyId(companyId);
	}

	@Override
	public void updateCompanyStatus(String companyId, String role) {
		companyDao.updateCompanyStatus(companyId,role);
	}

	@Override
	public void update(CompanyEntity companyEntity) {
		companyDao.update(companyEntity);
	}

	@Override
	public Integer getCount() {return companyDao.getCount();}
	
}
