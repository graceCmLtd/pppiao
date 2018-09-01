package com.fullcrum.controller.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.TransactionEntity;
import com.fullcrum.service.sys.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/ppp/transaction")
public class TransactionController {

	@Resource(name="transactionServiceImpl")
	private TransactionService transactionService;
	
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
	
	//交易处理时需要的信息
	@RequestMapping("/getTransInfo")
	public List<Map<String,Object>> selecttransInfo(@RequestParam(value="transactionId") int transactionId) {
		return transactionService.selectTransInfo(transactionId);
	}
	
	//更改交易处理状态
	//交易状态 1为交易中  0为交易完成
	@RequestMapping("/updateTransStatus")
	public String updateTransStatus(@RequestParam(value="transactionId") int transactionId) {
		transactionService.updateTransStatus(transactionId);
		return "success";
	}
}
