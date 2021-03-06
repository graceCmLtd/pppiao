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
	
	@Select({"select a.*,date_format(updateTimeStamp,'%m月%d日  %H:%i') as msgTime from ",TABLE_NAME," a where receiverId = #{receiverId} order by flag,updateTimeStamp desc limit #{currentPage},#{pageSize}"})
	@ResultMap(value="userMsg")
	List<Map<String, Object>> selectMsgByReceiverId(@Param("receiverId") String receiverId,@Param("currentPage")Integer currentPage,@Param("pageSize") Integer pageSize);
	
	@Update({"update ",TABLE_NAME," set flag = #{jsonObject.flag} where receiverId = #{jsonObject.receiverId}"})
	void updateReceiverFlag(@Param("jsonObject") JSONObject jsonObject);


	Integer selectMsgCount(@Param("receiverId") String receiverId);

	void updateAllFlag(@Param("jsonObject") JSONObject jsonObject);

	@Update({"update ",TABLE_NAME," set flag = #{jsonObject.flag} where receiverId = #{jsonObject.receiverId} and msgId=#{jsonObject.msgId}"})
    void updateFlagByReceiverIdAndMsgId(@Param("jsonObject") JSONObject jsonObject);
}

