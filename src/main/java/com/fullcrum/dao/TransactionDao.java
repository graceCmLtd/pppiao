package com.fullcrum.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.TransactionEntity;

@Mapper
public interface TransactionDao {

	String TABLE_NAME = "ppp_transaction";
	
	String INSER_FIELDS = "transacId,transacType,billNumber,buyerId,sellerId,amount,transacStatus,transacDate";
	
	@Select({"select * from ",TABLE_NAME,"where transacId = #{transactionId} order by transacDate desc"})
	@ResultMap(value="transactionMap")
	public ArrayList<TransactionEntity> selectTransacByTransacId( @Param("transactionId") String transactionId);
	
	@Select({"select * from ",TABLE_NAME,"where billNumber = #{billNumber} order by transacDate desc "})
	@ResultMap(value="transactionMap")
	public ArrayList<TransactionEntity> selectTransacByBillNumber(@Param("billNumber")  String billNumber);
	
	@Select({"select * from " ,TABLE_NAME,"where buyerId = #{buyerId} order by transacDate desc"})
	@ResultMap(value="transactionMap")
	public ArrayList<TransactionEntity> selectTransacByBuyerId(@Param("buyerId") String buyerId);
	
	@Select({"select * from ",TABLE_NAME,"where sellerId = #{sellerId} order by transacDate desc"})
	@ResultMap(value="transactionMap")
	public ArrayList<TransactionEntity> selectTransacBySellerId(@Param("sellerId") String sellerId);
	
	@Insert({"insert " ,TABLE_NAME,"(",INSER_FIELDS,") values (#{transactionEntity.transactionId},#{transactionEntity.transactionType},#{transactionEntity.billNumber},#{transactionEntity.buyerId},"
			+ "#{transactionEntity.sellerId},#{transactionEntity.amount},#{transactionEntity.transactionStatus},#{transactionEntity.transacDate})"})
	public void insertTransaction(@Param("transactionEntity") TransactionEntity transactionEntity);
	
	@Delete({"delete from ",TABLE_NAME,"where transacId = #{transactionId}"})
	public void deleteTransaction(@Param("transactionId") int transactionId);
	
	@Update({"update ",TABLE_NAME,"set transacType = #{transactionEntity.transactionType},billNumber = #{transactionEntity.billNumber},buyerId = #{transactionEntity.buyerId},"
			+ "sellerId = #{transactionEntity.sellerId},amount = #{transactionEntity.amount},transacStatus = #{transactionEntity.transactionStatus},transacDate = #{transactionEntity.transacDate} where transacId = #{transactionEntity.transactionId}"})
	public void updateTransaction(@Param("transactionEntity") TransactionEntity transactionEntity);
	
	@Update({"update ",TABLE_NAME," set transacStatus = #{jsonObject.transacStatus} where billNumber = #{jsonObject.billNumber}"})
	public void updateTransactionStatus(@Param("jsonObject") JSONObject jsonObject);
}
