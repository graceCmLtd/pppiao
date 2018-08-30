package com.fullcrum.model.sys;

import java.sql.Date;

/*
 * 
 * author:gavin.hou
 * 
 * 
 * 
billNumber    varchar（45） 票号
quoterId   int  报价人id
quoteAmount  int 报价金额
Interest   double 报价利率
xPerLakh   int  每十万加X元 
Status    varchar  状态
quoteDate  date 报价时间
 * 
 * 
 * 
 * */
public class QuoteEntity {

	
	private int quoteId;
	
	private String billNumber;
	
	
	private String quoterId;
	
	private int quoteAmount;
	
	private double interest;
	
	private int xPerLakh;
	
	private  String  status;
	
	private  Date  quoteDate;

	

	public int getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(int quoteId) {
		this.quoteId = quoteId;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	

	public String getQuoterId() {
		return quoterId;
	}

	public void setQuoterId(String quoterId) {
		this.quoterId = quoterId;
	}

	public int getQuoteAmount() {
		return quoteAmount;
	}

	public void setQuoteAmount(int quoteAmount) {
		this.quoteAmount = quoteAmount;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public int getxPerLakh() {
		return xPerLakh;
	}

	public void setxPerLakh(int xPerLakh) {
		this.xPerLakh = xPerLakh;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getQuoteDate() {
		return quoteDate;
	}

	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}
	
	
}
