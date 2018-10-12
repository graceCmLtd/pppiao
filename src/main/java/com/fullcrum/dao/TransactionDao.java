package com.fullcrum.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	String INSER_FIELDS = "transacType,billNumber,buyerId,sellerId,amount,transacStatus,transacDate";
	
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
	
	@Insert({"insert " ,TABLE_NAME,"(",INSER_FIELDS,") values (#{transactionEntity.transactionType},#{transactionEntity.billNumber},#{transactionEntity.buyerId},"
			+ "#{transactionEntity.sellerId},#{transactionEntity.amount},#{transactionEntity.transactionStatus},#{transactionEntity.transacDate})"})
	public void insertTransaction(@Param("transactionEntity") TransactionEntity transactionEntity);
	
	@Insert({"insert " ,TABLE_NAME,"(",INSER_FIELDS,") values (#{transactionEntity.transactionType},#{transactionEntity.billNumber},#{transactionEntity.buyerId},"
			+ "#{transactionEntity.sellerId},#{transactionEntity.amount},#{transactionEntity.transactionStatus},#{transactionEntity.transacDate})"})
	public void insertTransactionJson(@Param("transactionEntity") JSONObject transactionEntity);
	
	
	@Delete({"delete from ",TABLE_NAME,"where transacId = #{transactionId}"})
	public void deleteTransaction(@Param("transactionId") int transactionId);
	
	/*@param 更新
	 * */
	@Update({"update ",TABLE_NAME,"set transacType = #{transactionEntity.transactionType},billNumber = #{transactionEntity.billNumber},buyerId = #{transactionEntity.buyerId},"
			+ "sellerId = #{transactionEntity.sellerId},amount = #{transactionEntity.amount},transacStatus = #{transactionEntity.transactionStatus},transacDate = #{transactionEntity.transacDate} where transacId = #{transactionEntity.transactionId}"})
	public void updateTransaction(@Param("transactionEntity") TransactionEntity transactionEntity);
	
	//只更新设置transaction表中的transacStatus状态，线下交易状态
	@Update({"update ",TABLE_NAME," set transacStatus = #{jsonObject.transacStatus} where billNumber = #{jsonObject.billNumber}"})
	public void updateTransactionStatus(@Param("jsonObject") JSONObject jsonObject);
	
	//只设置transaction表中的intentionStatus 状态
	@Update({"update ",TABLE_NAME," set intentionStatus = #{jsonObject.intentionStatus} where billNumber = #{jsonObject.billNumber}"})
	public void setTransactionIntentionStatus(@Param("jsonObject") JSONObject jsonObject);
	
	
	//更新设置transaction表中intentionStatus 和 买家Id
	@Update({"update ",TABLE_NAME," set intentionStatus = #{jsonObject.intentionStatus} ,buyerId = #{jsonObject.quoterId} where billNumber = #{jsonObject.billNumber}"})
	public void updateTransactionIntentionStatus(@Param("jsonObject") JSONObject jsonObject);
	
	@Select({"select DISTINCT a.transacId,a.billNumber,a.amount,a.transacStatus,a.transacDate," + 
			"b.pic1,b.pic2,c1.user_phone as buyer_phone,c2.user_phone as seller_phone " + 
			"from ppp_transaction a " + 
			"left join ppp_bill_pics b on a.billNumber=b.billNumber " + 
			"LEFT JOIN ppp_user c1 on a.buyerId=c1.user_id " + 
			"LEFT JOIN ppp_user c2 on a.sellerId=c2.user_id " +
			"where a.transacId=#{transactionId}"})
	@ResultMap(value="getTransInfo")
	public List<Map<String, Object>> selectTransInfo(@Param("transactionId")int transactionId);
	
	@Select({"update ppp_transaction set transacStatus=#{transStatus} where transacId=#{transactionId} and sellerId is not null and buyerId is not null"})
	public void updateTransStatus(@Param("transactionId")int transactionId,@Param("transStatus") String transStatus);
	
	@Select({"select * from ppp_transaction"})
	@ResultMap(value="transactionMap")
	public ArrayList<TransactionEntity> slectAllTrans();
	
	@Select({"update ppp_transaction set intentionStatus = '待接单'  where billNumber = #{billNumber}"})
	public void updateIntentionStatus(String billNumber);
}
