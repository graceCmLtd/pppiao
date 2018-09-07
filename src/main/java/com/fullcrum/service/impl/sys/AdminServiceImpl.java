package com.fullcrum.service.impl.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullcrum.dao.AdminDao;
import com.fullcrum.model.sys.AdminEntity;
import com.fullcrum.service.sys.AdminService;



@Service(value="adminServiceImpl")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDao adminDao;

	@Override
	public AdminEntity adminLogin(AdminEntity adminEntity) {
		AdminEntity adminInfo = adminDao.adminLogin(adminEntity);
		return adminInfo;
	}

}
