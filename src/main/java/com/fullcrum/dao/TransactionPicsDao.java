package com.fullcrum.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.TransactionPicsEntity;

@Mapper
public interface TransactionPicsDao {
	
	String TABLE_NAME = "ppp_transaction_pics";
	String INSERT_FIELDS = "orderId,pic1,pic2";
	
	@Select({"select * from ",TABLE_NAME," where orderId = #{jsonObject.orderId}"})
	@ResultMap(value="transactionPicsMap")
	List<TransactionPicsEntity> selectByOrderId(@Param("jsonObject") JSONObject jsonObject);
	
	@Insert({"insert " ,TABLE_NAME ," ( ",INSERT_FIELDS," ) values ( #{jsonObject.orderId}, #{jsonObject.pic1},#{jsonObject.pic2} )"})
	void insertPics(@Param("jsonObject") JSONObject jsonObject);
}
