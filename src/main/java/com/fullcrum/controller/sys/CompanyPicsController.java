package com.fullcrum.controller.sys;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullcrum.model.sys.CompanyPicsEntity;
import com.fullcrum.service.sys.CompanyPicsService;

@RestController
@CrossOrigin
@RequestMapping("/ppp")
public class CompanyPicsController {

	@Resource(name="companyPicsServiceImpl")
	private CompanyPicsService companyPicsService;
	
	@RequestMapping("/getPicsOfCom")
	public ArrayList<CompanyPicsEntity> getCompanyPics(@RequestParam(value="contactsId") String contactsId){
		
		System.out.println("pics of Company id ...........................");
		System.out.println(contactsId);
		return companyPicsService.selectCompanyPicsByContactsId(contactsId);
	}
	
}
