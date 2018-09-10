package com.fullcrum.model.sys;

import java.sql.Date;

/*
 * author:gavin.hou
 * 
 * 
 * billNumber   varchar(45)  票号
billType   varchar(45)  票据类型
acceptor   varchar(45)   兑换方
Amount  int   金额
Maturity   date  到期日
Status    varchar   状态
releaseDate   date 发布时间
ReleaserId    int 发布人id
billPicsId    int  票据图片id
 * 
 * 
 * */


public class BillEntity {
	
	private String billId;
	
	private String billNumber;
	
	private String billType;
	
	private String acceptor;
	
	private int amount;
	
	private  Date maturity;
	
	private String status;
	
	private Date releaseDate;
	
	private String releaserId;
	
	private int billPicsId;
	
	private boolean transferable;
	
	private String billReferer;
	
	private String failReason;
	

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getAcceptor() {
		return acceptor;
	}

	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getMaturity() {
		return maturity;
	}

	public void setMaturity(Date maturity) {
		this.maturity = maturity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaserId() {
		return releaserId;
	}

	public void setReleaserId(String releaserId) {
		this.releaserId = releaserId;
	}

	public int getBillPicsId() {
		return billPicsId;
	}

	public void setBillPicsId(int billPicsId) {
		this.billPicsId = billPicsId;
	}

	public boolean isTransferable() {
		return transferable;
	}

	public void setTransferable(boolean transferable) {
		this.transferable = transferable;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getBillReferer() {
		return billReferer;
	}

	public void setBillReferer(String billReferer) {
		this.billReferer = billReferer;
	}

	
	
	
	 
}
