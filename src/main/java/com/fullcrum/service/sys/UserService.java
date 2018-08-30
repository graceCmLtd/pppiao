package com.fullcrum.service.sys;

import java.util.ArrayList;
import java.util.Map;

import com.fullcrum.model.sys.UserEntity;

public interface UserService {

	/*
	 * 
	 * 
	 * 
	 * */
	public void insert(UserEntity userEntity);
	
	
	/**
	 * 通过登录名得到用户信息
	 * @param loginName
	 * @return
	 */
	public UserEntity getUserEntityByLoginName(String login_name);
	
	public UserEntity getUserEntityByPhone(String user_phone);
	public ArrayList<UserEntity> userList( String login_name, int pageSize, int start);
	
	public Map<String, String> register( String login_name,String user_phone,String passwd);
	public String addLoginTicket(String userId);
	
	public Map<String, String> login(String user_phone,String passwd);
	

}
