package com.fullcrum.model.sys;

import java.sql.Date;

/*
 * 
 * author:gavin.hou
 * 
 * 
 * 
 * billNumber   varchar（45） 票据号
Pic1   mediumblob  图片1
Pic2  mediumblob   图片2
updateDate date 更新时间
 * 
 * */
public class BillPicsEntity {
	
	
	private String billNumber;
	
	private String pic1;
	
	private String pic2;
	
	private Date  updateDate;

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
