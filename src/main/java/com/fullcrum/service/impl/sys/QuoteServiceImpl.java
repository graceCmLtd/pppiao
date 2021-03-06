package com.fullcrum.service.impl.sys;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.dao.QuoteDao;
import com.fullcrum.model.sys.QuoteEntity;
import com.fullcrum.service.sys.QuoteService;

@Service
public class QuoteServiceImpl implements QuoteService {

	
	@Autowired
	private QuoteDao quoteDao;
	
	
	@Override
	public ArrayList<QuoteEntity> selectQuoteByQuoteId(int quoteId) {
		// TODO Auto-generated method stub
		return quoteDao.selectByQuoteId(quoteId);
	}

	@Override
	public ArrayList<QuoteEntity> selectQuoteByQuoterId(String quoterId) {
		// TODO Auto-generated method stub
		return quoteDao.selectByQuoterId(quoterId);
	}

	@Override
	public ArrayList<QuoteEntity> selectQuoteByBillNumber(String billNumber) {
		// TODO Auto-generated method stub
		return quoteDao.selectByBillNumber(billNumber);
	}

	@Override
	public ArrayList<QuoteEntity> selectQuoteByBillNumberAndQuoterId(String billNumber,String quoterId){
		return quoteDao.selectQuoteByBillNumberAndQuoterId(billNumber,quoterId);
	}

	@Override
	public void insertQuote(QuoteEntity quoteEntity) {
		// TODO Auto-generated method stub
		
		quoteEntity.setQuoteDate(new Date(new java.util.Date().getTime()));
		quoteDao.insertQuote(quoteEntity);
	}

	@Override
	public void deleteQuoteByQuoteId(int quoteId) {
		// TODO Auto-generated method stub
		quoteDao.deleteQuoteByQuoteId(quoteId);
	}

	@Override
	public List<Map<String, Object>> getALLQuote(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return quoteDao.getALLQuote(jsonObject);
	}

	@Override
	public List<Map<String, Object>> getAcceptedQuote(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return quoteDao.getAcceptedQuote(jsonObject);
	}

	@Override
	public List<Map<String, Object>> getUnderQuote(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return quoteDao.getUnderQuote(jsonObject);
	}

	@Override
	public List<Map<String, Object>> getFailQuote(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return quoteDao.getFailQuote(jsonObject);
	}

	@Override
	public void updateQuoteStatus(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		quoteDao.updateQuoteStatus(jsonObject);
	}

	@Override
	public void setInvalidateQuotes(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		quoteDao.setInvalidateQuotes(jsonObject);
	}

	@Override
	public void setValidateQuote(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		quoteDao.setValidateQuote(jsonObject);
	}

	@Override
	public List<Map<String, Object>> selectBillByBillNum(String billNumber) {
		return quoteDao.selectBillByBillNum(billNumber);
	}


	//params : billNumber,quoterId,new_money
	@Override
	public void updateRealMoney(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		quoteDao.updateRealMoney(jsonObject);
	}

	@Override
	public Integer getALLQuoteCount(JSONObject jsonObject) {
		return quoteDao.getAllQuoteCount(jsonObject);
	}

	@Override
	public Integer getUnderQuoteCount(JSONObject jsonObject) {
		return quoteDao.getUnderQuoteCount(jsonObject);
	}

	@Override
	public Integer getFailQuoteCount(JSONObject jsonObject) {
		return quoteDao.getFailQuoteCount(jsonObject);
	}

	@Override
	public void updateStatusByBillNumAndStatus(JSONObject jsonObject) {
		quoteDao.updateStatusByBillNumAndStatus(jsonObject);
	}

	@Override
	public void insertQuote(JSONObject quoteEntity) {
		// TODO Auto-generated method stub
		quoteDao.insertQuoteJson(quoteEntity);
	}

	@Override
	public void updateQuoteStatusByQuoteId(JSONObject jsonObject) {
		quoteDao.updateQuoteStatusByQuoteId(jsonObject);
	}

}
