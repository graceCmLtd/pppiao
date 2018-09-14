package com.fullcrum.controller.sys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.BillEntity;
import com.fullcrum.model.sys.BillPicsEntity;
import com.fullcrum.model.sys.TransactionEntity;
import com.fullcrum.service.sys.BillPicsService;
import com.fullcrum.service.sys.BillService;
import com.fullcrum.service.sys.TransactionService;
import com.fullcrum.utils.AipOcrImage;

@RestController
@CrossOrigin
@RequestMapping("/ppp/bills")
public class BillController {

	Long ONE_DAT_MILLIONSECONDS = (long) 86400000;
	
	
	@Resource(name="billServiceImpl")
	private BillService billService;
	
	@Resource(name="billPicsServiceImpl")
	private BillPicsService billPicsService;
	
	@Resource(name="transactionServiceImpl")
	private TransactionService transactionervice;
	
	@RequestMapping("/getbill")
	public ArrayList<BillEntity> getBills( @RequestParam(value="billNumber")  String billNumber){
		
		return billService.selectByBillNumber(billNumber);
		
	}
	@RequestMapping("/getbillr")
	public List<Map<String, Object>> getBillsWithRemainDays(@RequestParam(value="billNumber")  String billNumber){
		return billService.selectByBillNumberWithRemainDays(billNumber, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}
	
	@RequestMapping("/addbill")
	@Transactional
	public JSONObject addBills(@RequestBody  JSONObject jsonObject) {
		JSONObject transobj = new JSONObject();
		JSONObject theBill = jsonObject.getJSONObject("billInfo");
		transobj.put("transactionType", "sellbill");
		transobj.put("billNumber", theBill.get("billNumber"));
		transobj.put("buyerId", null);
		transobj.put("sellerId", jsonObject.getJSONObject("userData").get("uuid"));
		transobj.put("amount", jsonObject.getJSONObject("billInfo").get("amount"));
		transobj.put("transactionStatus", "1");
		transobj.put("transacDate", jsonObject.getJSONObject("billInfo").get("releaseDate"));
		
		
		JSONObject result = new JSONObject();
		System.out.println("output add bill transobj data .................");
		System.out.println(transobj);
		
		try {
			transactionervice.insertTransaction(JSONObject.toJavaObject(transobj, TransactionEntity.class));
			
			billService.insertBill(JSONObject.toJavaObject(jsonObject.getJSONObject("billInfo"), BillEntity.class));
			
			billPicsService.insertBillPics(JSONObject.toJavaObject(jsonObject.getJSONObject("billPics"), BillPicsEntity.class));
			
			result.put("statusCode", "success");
			result.put("errorMsg", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("print excetions ..........");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			if (e.getMessage().toString().contains("Duplicate entry")) {
				result.put("errorMsg", "数据库已存在");
			}else {
				result.put("errorMsg", "unknown error");
			}
			result.put("statusCode", "fail");
			
		}
		return result;
	}
	
	@RequestMapping("/ocrImage")
	public String ocrImage(@RequestBody JSONObject json) {
		String image = json.getString("image");
		String [] str = image.split(",");
		AipOcrImage results = new AipOcrImage();
		org.json.JSONObject orcImage = results.orcImage(str[1]);
		return orcImage.toString();
	}
	
	@RequestMapping("/updatebill")
	public String updateBill(@RequestBody JSONObject jsonObject) {
		
		billService.update(JSONObject.toJavaObject(jsonObject.getJSONObject("billInfo"), BillEntity.class));
		billPicsService.updateBillPics(JSONObject.toJavaObject(jsonObject.getJSONObject("billPics"), BillPicsEntity.class));
		return "success";
	}
	
	@RequestMapping("/deletebill")
	public String deleteBill(@RequestParam(value="billNumber") String billNumber) {
		
		billService.deleteBill(billNumber);
		billPicsService.deleteBillPics(billNumber);
		return "success";
	}
	
	@RequestMapping("/getBillPics")
	public ArrayList<BillPicsEntity> getBillPics(@RequestParam(value="billNumber")  String billNumber){
		
		ArrayList<BillPicsEntity> list = billPicsService.selectByBillNumber(billNumber);
		for (BillPicsEntity be : list) {
			System.out.println(be.getPic1().toString());
		}
		return list;
	}
	
	@RequestMapping("/getAllBills")
	public ArrayList<BillEntity> getAllBills(){
		
		return billService.selectAllBill();
	}
	
	/*java.util.List<Map<String, Object>>  
	 * 票据市场列表
	 * 
	 * */
	@RequestMapping("/filterbill")
	public List<Map<String, Object>>  filterbill(@RequestBody JSONObject jsonObject){
		
		long today = new Date().getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = dateFormat.format(new Date(today+ONE_DAT_MILLIONSECONDS));
		
		System.out.println(date1);
		System.out.println(jsonObject.get("billType"));
		JSONObject conditions = new JSONObject();
		if (jsonObject.get("starter").toString() != null) {
			conditions.put("starter", jsonObject.get("starter").toString());
		}else {
			System.out.println("params starter is null !!!  ");
		}
		
		if (jsonObject.get("number").toString() != null) {
			conditions.put("number", jsonObject.get("number").toString());
		}else {
			System.out.println("params number is null !!!!");
		}
		switch (jsonObject.get("billType").toString()) {
		case "1":
			conditions.put("billType", "纸银");
			break;
		case "2":
			conditions.put("billType", "电银");
			break;
		case "3":
			conditions.put("billType", "纸商");
			break;
		case "4":
			conditions.put("billType", "电商");
			break;
		default:
			//conditions.put("billType", 0);
			break;
		}
		switch (jsonObject.get("amountType").toString()) {
		case "1":
			conditions.put("minAmount", 0);
			conditions.put("maxAmount", 1000000);
			break;
		case "2":
			conditions.put("minAmount", 1000000);
			conditions.put("maxAmount", 5000000);
			break;
		case "3":
			conditions.put("minAmount", 5000000);
			break;
		default:
			//conditions.put("amountType", 0);
			break;
		}
		
		
		
		switch (jsonObject.get("maturityType").toString()) {
		case "1":
			conditions.put("minDate", dateFormat.format(new Date(today)));
			conditions.put("maxDate", dateFormat.format(new Date(today+ONE_DAT_MILLIONSECONDS*30)));
			break;
		case "2":
			conditions.put("minDate", dateFormat.format(new Date(today+ONE_DAT_MILLIONSECONDS*30)));
			conditions.put("maxDate", dateFormat.format(new Date(today+ONE_DAT_MILLIONSECONDS*30*3)));
			break;
		case "3":
			conditions.put("minDate", dateFormat.format(new Date(today+ONE_DAT_MILLIONSECONDS*30*3)));
			conditions.put("maxDate", dateFormat.format(new Date(today+ONE_DAT_MILLIONSECONDS*30*6)));
			break;
		case "4":
			conditions.put("minDate", dateFormat.format(new Date(today + ONE_DAT_MILLIONSECONDS * 30 * 6)));
			break;
		default:
			//conditions.put("maturityType", 0);
			break;
		}
		//return null;
		//为了显示票据市场页面剩余天数这列的数据，获取当前时间
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sf.format(new Date());
		conditions.put("curr_time", date);
		
		return billService.selectByFilter(conditions);
	}
	
	@RequestMapping("/getMyBillsQuoted")
	public List<Map<String, Object>> getMyBills(@RequestBody JSONObject jsonObject){
		jsonObject.put("curr_time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		switch (jsonObject.get("filter").toString()) {
		case "1":
			//获取全部票据的报价信息
			return billService.getBillsInquoting(jsonObject);
		case "2":
			//获取已经报价的票据
			return billService.getBillsReceivedQuote(jsonObject);
		case "3":
			//获取未报价的票据
			return billService.getBillsWaitingQuote(jsonObject);
		case "4":
			//获取正在审核中的票据
			return billService.getBillsAuditing(jsonObject);
		default:
			return null;
		}
	}
	
	@RequestMapping("/getBillsIntentions")
	public List<Map<String, Object>> getBillsIntentions(@RequestBody JSONObject jsonObject){
		
		jsonObject.put("curr_time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		switch (jsonObject.get("IntentionType").toString()) {
		case "1":
			//获取所有意向
			return billService.getALLIntentions(jsonObject);
		case "2":
			//买家已接单
			return billService.getConfirmedIntentions(jsonObject);
		case "3":
			//待买家确认
			return billService.getConfirmingIntentions(jsonObject);
		case "4":
			//买家已拒绝、失效
			return billService.getRefusedIntentions(jsonObject);
		case "5":
			//审核中
			return billService.getBillsAuditing(jsonObject);
		default:
			System.out.println("nothing match the condition intentionType");
			return null;
		}
	}
}
