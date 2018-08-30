package com.fullcrum.service.sys;

import java.util.ArrayList;

import com.fullcrum.model.sys.CompanyEntity;

public interface CompanyService {

		public ArrayList<CompanyEntity> selectAll();
		
		public ArrayList<CompanyEntity> selectByContactsId(String contactsId);
		
		public void insertCompany(CompanyEntity companyEntity);
		
		public void deleteCompany(CompanyEntity companyEntity);
		
}
