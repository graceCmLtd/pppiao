package com.fullcrum.model.sys;

import java.sql.Date;

/*
 * 
 * author:gavin.hou
 * 
 * 
 * TransacId   int   交易id
billNumber  varchar（45） 票号
buyerId    int  买家id
SellerId  int   卖家id
Amount  int  交易金额
transacStatus  varchar 交易状态
 * 
 * 
 * */
public class TransactionEntity {
	
	
	
	private String transactionId;
	
	private String  transactionType;
	
	private String billNumber;
	
	
	private String buyerId ;
	
	
	private String sellerId;
	
	
	private int amount;
	
	
	private String transactionStatus;
	
	private Date transacDate;
	
	private int timeStamp;

	
	
	
	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public String getBillNumber() {
		return billNumber;
	}


	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}


	

	public String getBuyerId() {
		return buyerId;
	}


	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}


	public String getSellerId() {
		return sellerId;
	}


	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getTransactionStatus() {
		return transactionStatus;
	}


	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public Date getTransacDate() {
		return transacDate;
	}


	public void setTransacDate(Date transacDate) {
		this.transacDate = transacDate;
	}


	public int getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(int timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}
