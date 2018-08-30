package com.fullcrum.model.sys;


/*
 * 
 * author:gavin.hou
 * 
 * 
 * 
*CompanyId    int  公司id
ContactsId  int  联系人id
contactsPhone  int  联系人手机号
email    string   邮箱    
*
*
*
*/

public class CompanyContactsEntity {
	
	private int companyId;
	
	private int contactsId;
	
	private int contactsPhone;
	
	
	private String email;


	public int getCompanyId() {
		return companyId;
	}


	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


	public int getContactsId() {
		return contactsId;
	}


	public void setContactsId(int contactsId) {
		this.contactsId = contactsId;
	}


	public int getContactsPhone() {
		return contactsPhone;
	}


	public void setContactsPhone(int contactsPhone) {
		this.contactsPhone = contactsPhone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
}
