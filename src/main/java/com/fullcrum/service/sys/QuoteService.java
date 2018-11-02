package com.fullcrum.service.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.QuoteEntity;

public interface QuoteService {

	public ArrayList<QuoteEntity> selectQuoteByQuoteId( int quoteId);
	
	public ArrayList<QuoteEntity> selectQuoteByQuoterId(String quoterId);
	
	public ArrayList<QuoteEntity> selectQuoteByBillNumber(String billNumber);

	ArrayList<QuoteEntity> selectQuoteByBillNumberAndQuoterId(String billNumber,String QuoterId);
	
	public void insertQuote(QuoteEntity quoteEntity);
	
	public void insertQuote(JSONObject quoteEntity);
	
	public void deleteQuoteByQuoteId( int quoteId);
	
	public void updateQuoteStatus(JSONObject jsonObject);
	
	public void updateQuoteStatusByQuoteId(JSONObject jsonObject);
	
	public void updateRealMoney(JSONObject jsonObject);
	
	public void setInvalidateQuotes(JSONObject jsonObject);
	
	public void setValidateQuote(JSONObject jsonObject);
	
	public List<Map<String, Object>> getALLQuote(JSONObject jsonObject);
	
	public List<Map<String, Object>> getAcceptedQuote(JSONObject jsonObject);
	
	public List<Map<String, Object>> getUnderQuote(JSONObject jsonObject);
	
	public List<Map<String, Object>> getFailQuote(JSONObject jsonObject);

	public List<Map<String, Object>> selectBillByBillNum(String billNumber);

	public Integer getALLQuoteCount(JSONObject jsonObject);

	public Integer getUnderQuoteCount(JSONObject jsonObject);

	public Integer getFailQuoteCount(JSONObject jsonObject);


	void updateStatusByBillNumAndStatus(JSONObject jsonObject);
}
