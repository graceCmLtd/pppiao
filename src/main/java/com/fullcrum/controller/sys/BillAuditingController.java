package com.fullcrum.controller.sys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
import com.fullcrum.service.sys.BillAuditingService;
import com.fullcrum.service.sys.TransactionService;




@RestController
@CrossOrigin
@RequestMapping("/ppp/billauditing")
public class BillAuditingController {
	
	@Resource(name="billAuditingServiceImpl")
	private BillAuditingService billAuditingService;
	@Resource(name="transactionServiceImpl")
	private TransactionService transactionService;
	@Resource(name="msgServiceImpl")
	private MsgService msgService;

	@Autowired
	private GoEasyAPI goEasyAPI;
	
	@RequestMapping("/getBills")
	public List<Map<String,Object>> getBills(@RequestParam("pageSize") Integer pageSize,@RequestParam("currentPage") Integer currentPage){
		System.out.println(pageSize+"========"+currentPage);
		List<Map<String,Object>> list = billAuditingService.getBills(pageSize,(currentPage-1)*pageSize);
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
	public Integer getCount() {
		Integer count = billAuditingService.selectCount();
		return count;
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
		String billReferer = json.getString("billReferer");
		try{
			if(billReferer.equals("资源池")) {//这里判断票据的来源，如果是资源池的票据就更改交易状态为：待接单
				System.out.println(billReferer);
				billAuditingService.updateBillStatus(billNumber,status,failReason);
				transactionService.updateTransStatus(billNumber);
				goEasyAPI.sendMessage(json.getJSONObject("message2").getString("receiverId"), json.getJSONObject("message2").toString());
				JSONObject msg = json.getJSONObject("message2");
				msgService.insertMsg(msg);
				if("审核完成".equals(status)){
					goEasyAPI.sendMessage(json.getJSONObject("message3").getString("receiverId"), json.getJSONObject("message3").toString());
					JSONObject msg2 = json.getJSONObject("message3");
					msgService.insertMsg(msg2);
				}
			}else {
				billAuditingService.updateBillStatus(billNumber,status,failReason);
				goEasyAPI.sendMessage(json.getJSONObject("message1").getString("receiverId"), json.getJSONObject("message1").toString());
				JSONObject msg = json.getJSONObject("message1");
				msgService.insertMsg(msg);
			}
			return "success";
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "failed";
		}
	}
}
