package com.fullcrum.model.sys;

import java.sql.Date;

/*
 * author:gavin.hou
 * 
 * 
 * picId   int   图片id
picContent    Mediumblob 图片内容 
UpateDate  date  更新时间

*/


public class CompanyPicsEntity {
	
	private int picId;
	
	
	private String picContent;
	
	
	private Date  updateDate;

	private String contactsId;
	
	
	public int getPicId() {
		return picId;
	}


	public void setPicId(int picId) {
		this.picId = picId;
	}


	public String getPicContent() {
		return picContent;
	}


	public void setPicContent(String picContent) {
		this.picContent = picContent;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public String getContactsId() {
		return contactsId;
	}


	public void setContactsId(String contactsId) {
		this.contactsId = contactsId;
	}


	
}
