package com.fullcrum.model.sys;

/*
 * 
 * @author hou
 * 
 * 
 * */

public class UserEntity {
	
/*用户id	
*/	
	private String user_id;

/*用户登录名
*/
	private String login_name;

/*登录密码
 * 
*/	private String user_passwd;
	
	
	private String user_phone;
	
	private String user_name;
	
	private String nick_name;
	
	private String user_address;
	
	private String user_age;
	
	private String user_email;

	
	
	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getLogin_name() {
		return login_name;
	}


	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}


	public String getUser_passwd() {
		return user_passwd;
	}


	public void setUser_passwd(String user_passwd) {
		this.user_passwd = user_passwd;
	}


	public String getUser_phone() {
		return user_phone;
	}


	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getNick_name() {
		return nick_name;
	}


	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}


	public String getUser_address() {
		return user_address;
	}


	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}


	public String getUser_age() {
		return user_age;
	}


	public void setUser_age(String user_age) {
		this.user_age = user_age;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public String toString() {
		return "UserEntity [id=" + user_id + ", loginName=" + login_name + ", password=" + user_passwd
				+ "phone = " +user_phone +", name="+ user_name + "nick_name =" + nick_name +"address= "+user_address + "age = "+user_age + ", email=" + user_email + "]";
	}
}
