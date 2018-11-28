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

	private String pic1Content;

	private Date  updateDate;

	private String contactsId;

	private String pic2Content;

	private String pic1IDCard;

	private String pic2IDCard;
	
	
	public int getPicId() {
		return picId;
	}


	public void setPicId(int picId) {
		this.picId = picId;
	}

	public String getPic1Content() {
		return pic1Content;
	}

	public void setPic1Content(String pic1Content) {
		this.pic1Content = pic1Content;
	}

	public String getPic2Content() {
		return pic2Content;
	}

	public void setPic2Content(String pic2Content) {
		this.pic2Content = pic2Content;
	}

	public String getPic1IDCard() {
		return pic1IDCard;
	}

	public void setPic1IDCard(String pic1IDCard) {
		this.pic1IDCard = pic1IDCard;
	}

	public String getPic2IDCard() {
		return pic2IDCard;
	}

	public void setPic2IDCard(String pic2IDCard) {
		this.pic2IDCard = pic2IDCard;
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
