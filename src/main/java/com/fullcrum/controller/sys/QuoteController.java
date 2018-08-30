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
import com.fullcrum.model.sys.QuoteEntity;
import com.fullcrum.service.sys.QuoteService;

@RestController
@CrossOrigin
@RequestMapping("/ppp/quote")
public class QuoteController {

	@Resource(name="quoteServiceImpl")
	private QuoteService quoteService;
	
/*	@Resource(name="transactionServiceImpl")
	private TransactionService transactionService;*/
	
	@RequestMapping("/getByQuoteId")
	public ArrayList<QuoteEntity>  getQuoteByQuoteId(@RequestParam(value="quoteId") int quoteId){
		
		return quoteService.selectQuoteByQuoteId(quoteId);
	}
	
	
	@RequestMapping("/getByQuoterId")
	public ArrayList<QuoteEntity> getQuoteByQuoterId( @RequestParam(value="quoterId") String quoterId){
		return quoteService.selectQuoteByQuoterId(quoterId);
	}
	
	@RequestMapping("/getByBillNumber")
	public ArrayList<QuoteEntity> getQuoteByBillNumber(@RequestParam(value="billNumber") String billNumber ){
		return quoteService.selectQuoteByBillNumber(billNumber);
	}
	
	
	@RequestMapping("/addQuote")
	public String addQuote(@RequestBody QuoteEntity quoteEntity) {
		
		quoteService.insertQuote(quoteEntity);
		return "success";
		
	}
	
	@RequestMapping("/deleteQuote")
	public String deleteQuote(@RequestParam(value="quoteId") int quoteId) {
		
		quoteService.deleteQuoteByQuoteId(quoteId);
		return "success";
	}
	
	@RequestMapping("/getMyQuote")
	public List<Map<String, Object>> getMyQuote(@RequestBody JSONObject jsonObject){
		
		switch (jsonObject.get("filter").toString()) {
		case "1":
			return quoteService.getALLQuote(jsonObject);
		case "2":
			return quoteService.getAcceptedQuote(jsonObject);
		case "3":
			return quoteService.getUnderQuote(jsonObject);
		case "4":
			return quoteService.getFailQuote(jsonObject);
		default:
			return null;
		}
	}
	
	@RequestMapping("/submitIntention")
	public JSONObject updateQuoteStatus(@RequestBody JSONObject jsonObject) {
		
		JSONObject result = new JSONObject();
		quoteService.updateQuoteStatus(jsonObject);
		
		result.put("status", "success");
		
		return result;
	}
	
	
}
