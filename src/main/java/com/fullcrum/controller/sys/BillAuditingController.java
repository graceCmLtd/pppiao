package com.fullcrum.controller.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullcrum.service.sys.BillAuditingService;




@RestController
@CrossOrigin
@RequestMapping("/ppp/billauditing")
public class BillAuditingController {
	
	@Resource(name="billAuditingServiceImpl")
	private BillAuditingService billAuditingService;
	
	@RequestMapping("/getBillInfo")
	public List<Map<String,Object>> getBillInfo(@RequestParam(value="billNumber")  String billNumber){
		return billAuditingService.getBillInfo(billNumber);
	}
	@RequestMapping("/updateBillStatus")
	public String updateBillStatus(@RequestParam(value="billNumber")  String billNumber) {
		
		billAuditingService.updateBillStatus(billNumber);
		return "success";
	}
}
