package com.fullcrum.controller.sys;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.AdminEntity;
import com.fullcrum.service.sys.AdminService;

import java.util.concurrent.TimeUnit;

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

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

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
				json.put("ticket",adminInfo.getLoginName());
				json.put("role",adminInfo.getRole());
				json.put("uuid",adminInfo.getLoginName());
				stringRedisTemplate.opsForValue().set(adminInfo.getLoginName(),json.toString(),900, TimeUnit.SECONDS);
			}else {
				json.put("status", "failed");
			}
		}
		return json;
	}
}
