package com.fullcrum.service.sys;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.CompanyPicsEntity;

public interface CompanyPicsService {
	
	public ArrayList<CompanyPicsEntity> selectCompanyPicsByContactsId(String contactsId);
	
	public void insertCompanyPics(CompanyPicsEntity companyPicsEntity);
	
	public void insertCompanyPicsByJson(JSONObject jsonObject);
	
	public void deleteCompanyPics(CompanyPicsEntity companyPicsEntity);

	public CompanyPicsEntity selectPic(String picId);

	public void updateCompanyPics(CompanyPicsEntity companyPicsEntity);
}
