package com.fullcrum.service.sys;

import java.util.ArrayList;

import com.fullcrum.model.sys.BillEntity;
import com.fullcrum.model.sys.CompanyEntity;

public interface CompanyService {

		public ArrayList<CompanyEntity> selectAll(Integer pageSize,Integer currentPage);
		
		public ArrayList<CompanyEntity> selectByContactsId(String contactsId);
		
		public void insertCompany(CompanyEntity companyEntity);
		
		public void deleteCompany(CompanyEntity companyEntity);

		public ArrayList<CompanyEntity> selectByCompanyId(String companyId);

		public void updateCompanyStatus(String companyId, String role);

		public void update(CompanyEntity companyEntity);

		public Integer getCount();


		
		
}
