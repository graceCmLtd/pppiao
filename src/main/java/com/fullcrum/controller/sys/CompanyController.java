package com.fullcrum.controller.sys;

import java.sql.Date;
import java.util.ArrayList;

import javax.annotation.Resource;

import com.fullcrum.service.sys.MsgService;
import com.fullcrum.utils.GoEasyAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.CompanyEntity;
import com.fullcrum.model.sys.CompanyPicsEntity;
import com.fullcrum.service.sys.CompanyPicsService;
import com.fullcrum.service.sys.CompanyService;

@RestController
@CrossOrigin
@RequestMapping("/ppp")
public class CompanyController {
	
	@Resource(name="companyServiceImpl")
	private CompanyService companyService;
	
	@Resource(name="companyPicsServiceImpl")
	private CompanyPicsService companyPicsService;

	@Resource(name="msgServiceImpl")
	private MsgService msgService;

	@Autowired
	private GoEasyAPI goEasyAPI;
	
	@RequestMapping("/getAllCompanys")
	public ArrayList<CompanyEntity> getAllCompanys(@RequestParam Integer currentPage,@RequestParam Integer pageSize){
		return companyService.selectAll(pageSize,(currentPage-1)*pageSize);
	}

	@RequestMapping("/getCompanyCount")
	public Integer getCompanyCount(){
		return companyService.getCount();
	}

	@RequestMapping("/getCompanyInfo")
	public ArrayList<CompanyEntity> getCompanyInfo(@RequestParam(value="companyId") String companyId){
		return companyService.selectByCompanyId(companyId);
	}
	
	@RequestMapping("/getCompany")
	public ArrayList<CompanyEntity> getCompanyById(@RequestParam(value="contactsId") String contactsId){
		return companyService.selectByContactsId(contactsId);
	}
	
	@RequestMapping("/addCompany")
	public JSONObject addCompany(@RequestBody  JSONObject jsonObject) {
		
		
		//CompanyPicsEntity companyPicsEntity = JSONObject.toJavaObject(jsonObject.getJSONObject("companyPics"), CompanyPicsEntity.class);
		CompanyEntity companyEntity = JSONObject.toJavaObject(jsonObject.getJSONObject("companyInfo"), CompanyEntity.class);
		companyEntity.setUpdateDate(new Date(new java.util.Date().getTime()));
		System.out.println("print   add company jsonobject .xxxxxxxxxxxxxxxxxxx");
		companyService.insertCompany(companyEntity);
		companyPicsService.insertCompanyPicsByJson(jsonObject.getJSONObject("companyPics"));
		
		companyEntity.setUpdateDate(new Date(new java.util.Date().getTime()));
		
		
		System.out.println("print   add company jsonobject .xxxxxxxxxxxxxxxxxxx");
		System.out.println(companyEntity);
		System.out.println(jsonObject);
		JSONObject result = new JSONObject();
		result.put("CompanyAuthentication", true);
		result.put("status", "success");
		result.put("errorMsg", null);
		return result;
	}
	
	@RequestMapping("/deleteCompany")
	public String deleteCompany(@RequestBody CompanyEntity companyEntity) {
		companyService.deleteCompany(companyEntity);
		
		return "success";
	}
	
	//公司审核(分为普通用户和包装户和未审核)
	@RequestMapping("/auditCompany")
	public String auditCompany(@RequestBody JSONObject json) {
		String companyId = json.getString("companyId");
		String role = json.getString("role");
		try{
			companyService.updateCompanyStatus(companyId,role);
			goEasyAPI.sendMessage(json.getJSONObject("message").getString("receiverId"),json.getJSONObject("message").toString());
			msgService.insertMsg(json.getJSONObject("message"));
			return "success";
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "failed";
		}

	}
	
	@RequestMapping("/updateCompany")
	public String updateBill(@RequestBody JSONObject jsonObject) {
		CompanyEntity companyEntity = JSONObject.toJavaObject(jsonObject.getJSONObject("companyInfo"), CompanyEntity.class);
		companyEntity.setUpdateDate(new Date(new java.util.Date().getTime()));
		CompanyPicsEntity companyPicsEntity = JSONObject.toJavaObject(jsonObject.getJSONObject("companyPics"), CompanyPicsEntity.class);
		companyPicsEntity.setUpdateDate(new Date(new java.util.Date().getTime()));
        companyEntity.setRole("未审核");
		companyService.update(companyEntity);
		companyPicsService.updateCompanyPics(companyPicsEntity);
		return "success";
	}
	
}
