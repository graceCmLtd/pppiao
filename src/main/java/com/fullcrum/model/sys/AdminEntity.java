package com.fullcrum.model.sys;


/*
 * 管理员用户实体类
 * 
 * id 主键
 * loginName 登录名
 * passwd 登录密码
 * nickName 昵称
 * role  管理员角色
 * 
 */
public class AdminEntity {
	
	private int id;
	private String loginName;
	private String passwd;
	private String nickName;
	private String role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
