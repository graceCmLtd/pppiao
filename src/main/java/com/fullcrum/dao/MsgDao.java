package com.fullcrum.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alibaba.fastjson.JSONObject;

@Mapper
public interface MsgDao {
	
	String TABLE_NAME = "ppp_msg";
	String INSERT_FEILD = "msgType,senderId,receiverId,msgContent,flag";
	
	@Insert({"insert ",TABLE_NAME,"(",INSERT_FEILD," ) values(#{jsonObject.msgType},#{jsonObject.senderId},#{jsonObject.receiverId},#{jsonObject.msgContent},#{jsonObject.flag})"})
	void insertMsg(@Param("jsonObject") JSONObject jsonObject);
	
	@Select({"select * from ",TABLE_NAME," where receiverId = #{receiverId} order by updateTimeStamp desc"})
	@ResultMap(value="userMsg")
	List<Map<String, Object>> selectMsgByReceiverId(@Param("receiverId") String receiverId);
	
	@Update({"update ",TABLE_NAME," set flag = #{jsonObject.flag where receiverId = #{receiverId}}"})
	void updateReceiverFlag(@Param("jsonObject") JSONObject jsonObject);
	

}

