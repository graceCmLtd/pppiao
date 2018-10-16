package com.fullcrum.model.sys;

import java.sql.Date;

public class TransactionPicsEntity {
	
	private Integer pic_id ;
	
	private String orderId;
	
	private String pic1;
	
	private String pic2;
	
	private Date timeStamp;
	
	private Date updateTimeStamp;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public Integer getPic_Id() {
		return pic_id;
	}

	public void setPic_Id(Integer pic_Id) {
		this.pic_id = pic_Id;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}
	
	
}
