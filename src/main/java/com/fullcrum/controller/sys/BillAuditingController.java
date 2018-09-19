package com.fullcrum.controller.sys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	public List<Map<String,Object>> getBills(@RequestParam("pageSize") Integer pageSize,@RequestParam("currentPage") Integer currentPage){
		System.out.println(pageSize+"========"+currentPage);
		List<Map<String,Object>> list = billAuditingService.getBills(pageSize,currentPage-1);
		//将时间戳转为正常的日期格式
		for(int i = 0;i<list.size();i++) {
			Object timeStamp = list.get(i).get("timeStamp");
			String str = "";
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str = sdf.format(timeStamp);
			list.get(i).put("timeStamp", str);
		}
		
		return list;
	}
	@RequestMapping("/getCount")
	public JSONObject getCount() {
		Integer count = billAuditingService.selectCount();
		JSONObject json = new JSONObject();
		json.put("count", count);
		return json;
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
