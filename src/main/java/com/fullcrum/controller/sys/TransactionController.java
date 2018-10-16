package com.fullcrum.controller.sys;

import java.util.ArrayList;
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
import com.fullcrum.model.sys.TransactionEntity;
import com.fullcrum.model.sys.TransactionPicsEntity;
import com.fullcrum.service.sys.QuoteService;
import com.fullcrum.service.sys.TransactionPicsService;
import com.fullcrum.service.sys.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/ppp/transaction")
public class TransactionController {

	@Resource(name="transactionServiceImpl")
	private TransactionService transactionService;
	
	@Resource(name="quoteServiceImpl")
	private QuoteService quoteService;
	
	@Resource(name="transactionPicsServiceImpl")
	private TransactionPicsService transactionPicsService;
	
	
	
	@RequestMapping("/getAllTrans")
	public List<Map<String,Object>> getAllTrans(@RequestParam Integer currentPage,@RequestParam Integer pageSize){
		return transactionService.selectAllTrans(pageSize,(currentPage-1)*pageSize);
	}

	@RequestMapping("/getCount")
	public Integer getCount(){
		return transactionService.getCount();
	}
	
	@RequestMapping("/getByTransacId")
	public ArrayList<TransactionEntity> getByTransacId(@RequestParam(value="transactionId") String transactionId){
		
		return transactionService.selectTransacByTransacId(transactionId);
	}
	
	@RequestMapping("/getByBillNumber")
	public ArrayList<TransactionEntity> getByBillNumber(@RequestParam(value="billNumber") String billNumber){
		return transactionService.selectTransacByBillNumber(billNumber);
	}
	
	@RequestMapping("/getByBuyerId")
	public ArrayList<TransactionEntity> getByBuyerId(@RequestParam(value="buyerId") String buyerId){
		return transactionService.selectTransacByBuyerId(buyerId);
	}
	
	@RequestMapping("/getBySellerId")
	public ArrayList<TransactionEntity> getBySellerId(@RequestParam(value="sellerId") String sellerId){
		return transactionService.selectTransacBySellerId(sellerId);
	}
	
	
	@RequestMapping("/addTransaction")
	public JSONObject addTransaction(@RequestBody TransactionEntity transactionEntity) {
		
		JSONObject result = new JSONObject();
		try {
			transactionService.insertTransaction(transactionEntity);
			result.put("statusCode", true);
			result.put("errorMsg", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("statusCode", false);
			if (e.getMessage().toString().contains("Duplicate entry")) {
				result.put("errorMsg", "数据库已经存在");
			}else {
				result.put("errorMsg", "unknown error");
			}
			
		}
		return result;
	}
	
	@RequestMapping("/deleteTransaction")
	public String deleteTransaction(@RequestParam(value="transactionId") int transactionId) {
		transactionService.deleteTransaction(transactionId);
		return "success";
	}
	
	@RequestMapping("/updateTransaction")
	public String updateTransaction(@RequestBody TransactionEntity transactionEntity) {
		
		transactionService.updateTransaction(transactionEntity);
		return "success";
	}
	
	@RequestMapping("/payViaPlatform")
	public JSONObject updateTransactionStatus(@RequestBody JSONObject jsonObject) {
		
		JSONObject result = new JSONObject();
		transactionService.updateTransactionStatus(jsonObject);
		result.put("status", "success");
		return result;
	}
	
	//交易处理时需要的交易信息
	@RequestMapping("/getTransInfo")
	public List<Map<String,Object>> selecttransInfo(@RequestParam(value="transactionId") int transactionId) {
		return transactionService.selectTransInfo(transactionId);
	}
	
	/*
	 * @description 单独修改transaction 表中的intentionStatus 状态值
	 * 
	 * 
	 * */
	@RequestMapping("/updateTransacIntentionStatus")
	@Transactional
	public JSONObject updateTransacIntentionStatus(@RequestBody JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		
		try {
			transactionService.setTransactionIntentionStatus(jsonObject);
			result.put("errorMsg", null);
			result.put("status", "success");
		} catch (Exception e) {
			// TODO: handle exception
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.put("errorMsg", e);
			result.put("status", "fail");
		}
		
		return result;
	}
	
	
	
	
	
	//更新交易意向状态
	@RequestMapping("/updateIntentionStatus")
	@Transactional
	public JSONObject updateIntentionStatus(@RequestBody JSONObject jsonObject) {
		
		/*必要参数 ：
		 * invalidateQuoteParam ： billNumber , quoterId , quoteStatus (报价失效)
		 * validateQuoteParam : billNumber ,quoterId , quoteStatus  (ok，转入意向)
		 * transactionParam :  billNumber ,transacStatus  (101 : 买家待接单   102 :买家已接单  103:买家拒绝  104:失效   202:卖家已确认)
		 * 
		 * */
		
		
		JSONObject result =  new JSONObject();
		JSONObject invalidateQuoteParam = jsonObject.getJSONObject("InvalidateBody");
		JSONObject validateQuoteParam = jsonObject.getJSONObject("validateBody");
		JSONObject transactionParam = jsonObject.getJSONObject("transactionBody");
		
		 String operate = jsonObject.getString("operate");
		 System.out.println("print the operator :   ");
		 System.out.println(operate);
		 switch (operate) {
		 // seller operator 1 sign up intention
		case "sop1":
			try {
				System.out.println("seller oprator 1 : ");
				quoteService.setInvalidateQuotes(invalidateQuoteParam);
				quoteService.setValidateQuote(validateQuoteParam);
				transactionService.updateTransactionIntentionStatus(transactionParam);
				
				result.put("status", "success");
				result.put("errorMsg", null);
				System.out.println("finish sop1 ");
			} catch (Exception e) {
				// TODO: handle exception
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				result.put("errorMsg", e);
				result.put("status", "fail");
			}
			
			break;
		case "sop2":
			try {
				transactionService.setTransactionIntentionStatus(transactionParam);
				
				result.put("status", "success");
				result.put("errorMsg", null);
			} catch (Exception e) {
				// TODO: handle exception
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				result.put("errorMsg", e);
				result.put("status", "fail");
			}
			
			break;
		default:
			result.put("errorMsg", "nothing done");
			result.put("status", "fail");
			break;
		}
		
		
		return result;
	}
	
	//更改交易处理状态
	@RequestMapping("/updateTransStatus")
	public String updateTransStatus(@RequestBody JSONObject json) {
		
		Integer transactionId =  json.getIntValue("transactionId");
		String transStatus = json.getString("transStatus");
		
		String msg = null;
		
		if(transactionId != null && !"".equals(transStatus) && transStatus != null) {
			switch(transStatus) {
			case "0":
				transStatus = "0";
				transactionService.updateTransStatus(transactionId,transStatus);
				msg = "交易失败";
				break;
			case "1":
				transStatus = "1";
				transactionService.updateTransStatus(transactionId,transStatus);
				msg = "买家正在付款";
				break;
			case "2":
				transStatus = "2";
				transactionService.updateTransStatus(transactionId,transStatus);
				msg = "买家已付款，请卖家背书";
				break;
			case "3":
				transStatus = "3";
				transactionService.updateTransStatus(transactionId,transStatus);
				msg = "买家确认卖家背书";
				break;
			case "4":
				transStatus = "4";
				transactionService.updateTransStatus(transactionId,transStatus);
				msg = "买家确认背书，卖家已收款";
				break;
			case "5":
				transStatus = "5";
				transactionService.updateTransStatus(transactionId,transStatus);
				msg = "交易结束";
				break;
			}
		}
		
		return msg;       
	}
	
	@RequestMapping("/getPicsByOrderId")
	public List<TransactionPicsEntity> getPicsByOrderId(@RequestBody JSONObject jsonObject){
		return transactionPicsService.selectByOrderId(jsonObject);
		
	}
	
	@RequestMapping("/addBackEndPics")
	public void insertPics(@RequestBody JSONObject jsonObject) {
		transactionPicsService.insertPics(jsonObject);
	} 
}
