package com.fullcrum.controller.sys;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.AdminEntity;
import com.fullcrum.service.sys.AdminService;

/**
 * 
 * @author ermao
 * 管理员登录接口
 *
 */


@RestController
@CrossOrigin
@RequestMapping("/ppp/admin")
public class AdminController {
	
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	
	//管理员登录
	@RequestMapping("/login")
	public JSONObject adminLogin(@RequestBody AdminEntity adminEntity) {
		
		JSONObject json = new JSONObject();
		
		System.out.println(adminEntity.getLoginName());
		
		if(adminEntity.getLoginName() != null && adminEntity.getPasswd() != null){
			AdminEntity adminInfo = adminService.adminLogin(adminEntity);
			if(adminInfo != null) {
				System.out.println(adminInfo.getLoginName());
				json.put("status", "success");
			}else {
				json.put("status", "failed");
			}
		}
		return json;
	}
}
