package com.fullcrum.controller.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.service.sys.BillAuditingService;




@RestController
@CrossOrigin
@RequestMapping("/ppp/billauditing")
public class BillAuditingController {
	
	@Resource(name="billAuditingServiceImpl")
	private BillAuditingService billAuditingService;
	
	@RequestMapping("/getBills")
	public List<Map<String,Object>> getBills(){
		return billAuditingService.getBills();
	}
	
	@RequestMapping("/getBillInfo")
	public List<Map<String,Object>> getBillInfo(@RequestParam(value="billNumber")  String billNumber){
		System.out.println("picc result .ddddddddddddddddddddd");
		System.out.println(billAuditingService.getBillInfo(billNumber).toString());
		return billAuditingService.getBillInfo(billNumber);
	}
	@RequestMapping("/updateBillStatus")
	public String updateBillStatus(@RequestBody JSONObject json) {
		String billNumber = json.getString("billNumber");
		String status = json.getString("status");
		String failReason = json.getString("failReason");
		billAuditingService.updateBillStatus(billNumber,status,failReason);
		return "success";
	}
}
