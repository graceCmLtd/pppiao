package com.fullcrum.dao;



/*
 * author hou
 * */
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fullcrum.model.sys.UserEntity;

@Mapper
public interface UserDao {
	
	public ArrayList<UserEntity> select(@Param("userEntity") UserEntity userEntity);
	
	public void del(@Param("userEntity") UserEntity userEntity);
	
	public void update(@Param("userEntity") UserEntity userEntity);
	
	public void insert(@Param("userEntity") UserEntity userEntity);
	
	/*
	 * 通过用户名获取用户实体信息
	 * 
	 * @return 
	 * 
	 * */
	public UserEntity getUserEntityByLoginName(@Param("login_name") String login_name);
	
	
	/*
	 * 
	 * 
	 */
	public UserEntity getUserEntityByPhone(@Param("user_phone") String user_phone);
	
	/*
	 * 获取用户列表
	 * @param login_name
	 * @param pageSize
	 * @param page start
	 * @return
	 * 
	 * */
	public ArrayList<UserEntity> userList(@Param("login_name") String login_name, @Param("pageSize") int pageSize,@Param("start") int start);
	
	/*
	 * 获取用户列表的总量
	 * @param login_name
	 * @param pageSize
	 * @param start 
	 * @return*/
	
	public int userSize(@Param("login_name") String login_name,@Param("pageSize") int pageSize,@Param("start") int start);
	
	
	/*
	 * 插入用户
	 * @param userEntity
	 * */
	
	public void insertUser(@Param("userEntity") UserEntity userEntity);
	
	/*
	 * 更新用户
	 * @param updateEntity
	 * @return
	 * */
	public void updateUser(@Param("userEntity") UserEntity userEntity);
	
	/*
	 * 删除用户
	 * @param groupId
	 * @return
	 * */
	public void deleteUsers(@Param("groupId") List<String> groupId);
	
	
	
	

}
