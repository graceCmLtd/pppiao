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

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.BillEntity;

/*
 * 
 * autor:gavin.hou
 * 
 * <!-- billNumber varchar(45) PK 
billType varchar(45) 
acceptor varchar(45) 
amount varchar(45) 
maturity date 
status varchar(45) 
releaseDate date 
releaserId int(11) 
billPicsId int(11) -->
 * 
 * 
 * */

@Mapper
public interface BillDao {

		String TABLE_NAME = "ppp_bill";
		String INSERT_FIELDS = "billId, billNumber, billType,acceptor,amount,maturity,status,releaseDate,releaserId,billPicsId ,transferable,billReferer";
		
		
		@Select({"select * from ppp_bill where  billNumber = #{billNumber}"})
		@ResultMap(value="billMap")
		public ArrayList<BillEntity> selectByBillNumber(@Param("billNumber") String billNumber);
		
		@Select({"select * from ppp_bill "})
		@ResultMap(value="billMap")
		public ArrayList<BillEntity> selectAllBill();
		
		public List<Map<String, Object>>  selectByFilter(@Param("jsonObject") JSONObject jsonObject);
		
		 /*List<Map<String, Object>>*/
		@Insert({"insert ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{billEntity.billId},#{billEntity.billNumber}, #{billEntity.billType}, #{billEntity.acceptor}, #{billEntity.amount},#{billEntity.maturity},#{billEntity.status}, "
				+ "#{billEntity.releaseDate}, #{billEntity.releaserId},#{billEntity.billPicsId}, #{billEntity.transferable}, #{billEntity.billReferer})"})
		public void insertBill( @Param("billEntity") BillEntity billEntity);
		
		
		@Delete({"delete from ",TABLE_NAME,"where billNumber = #{billNumber}"})
		public void deleteBill(@Param("billNumber") String billNumber);
		
		
		public void updateBillByBillNumber(@Param("billEntity") BillEntity billEntity);
		
		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable,c.billReferer "
				+ "from (select * from pengpengpiao.ppp_transaction where sellerId = #{jsonObject.uuid} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber   ;"})
		@ResultMap(value="billAboutQuote")
		public List<Map<String, Object>> getBillsInquoting(@Param("jsonObject") JSONObject jsonObject );
		
		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable ,c.billReferer "
				+ "from (select * from pengpengpiao.ppp_transaction where sellerId = #{jsonObject.uuid} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber where b.quoteId is not null ;"})
		@ResultMap(value="billAboutQuote")
		public List<Map<String, Object>> getBillsReceivedQuote(@Param("jsonObject") JSONObject jsonObject);
		
		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable, c.billReferer "
				+ "from (select * from pengpengpiao.ppp_transaction where sellerId = #{jsonObject.uuid} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber  where b.quoteId is null and c.status != '审查中' ;"})
		@ResultMap(value="billAboutQuote")
		public List<Map<String, Object>> getBillsWaitingQuote(@Param("jsonObject") JSONObject jsonObject);
		
		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable ,c.billReferer "
				+ "from (select * from pengpengpiao.ppp_transaction where sellerId = #{jsonObject.uuid} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber  where c.status = '审核中'  ;"})
		@ResultMap(value="billAboutQuote")
		public List<Map<String, Object>> getBillsAuditing(@Param("jsonObject") JSONObject jsonObject);
		
		
		

		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable ,c.billReferer "
				+ "from (select * from pengpengpiao.ppp_transaction where sellerId = #{jsonObject.uuid} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber  where b.quoteId is not null ;"})
		@ResultMap(value="QuoteIntention")
		public List<Map<String, Object>> getALLIntentions(@Param("jsonObject") JSONObject jsonObject);
		
		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable ,c.billReferer "
				+ "from (select * from pengpengpiao.ppp_transaction where sellerId = #{jsonObject.uuid} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber  where b.quoteId is not null  ;"})
		@ResultMap(value="QuoteIntention")
		public List<Map<String, Object>> getConfirmedIntentions(@Param("jsonObject") JSONObject jsonObject);
		
		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable,c.billReferer "
				+ "from (select * from pengpengpiao.ppp_transaction where sellerId = #{jsonObject.uuid} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber  where b.quoteId is not null ;"})
		@ResultMap(value="QuoteIntention")
		public List<Map<String, Object>> getConfirmingIntentions(@Param("jsonObject") JSONObject jsonObject);
		
		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable ,c.billReferer"
				+ "from (select * from pengpengpiao.ppp_transaction where sellerId = #{jsonObject.uuid} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber  where b.quoteId is not null  ;"})
		@ResultMap(value="QuoteIntention")
		public List<Map<String, Object>> getRefusedIntentions(@Param("jsonObject") JSONObject jsonObject);
		
		
		@Select({"select distinct a.*, b.pic1 as pic1,b.pic2 as pic2 from ppp_bill a " + 
				"left join ppp_bill_pics b on a.billNumber = b.billNumber" + 
				" where  a.billNumber = #{billNumber}"})
		@ResultMap(value="getBillInfo")
		public List<Map<String, Object>> selectBillInfo(@Param("billNumber")String billNumber);
		
		@Select({"update ppp_bill set status = #{status},failReason = #{failReason}  where billNumber = #{billNumber}"})
		public void updateBillStatus(@Param("billNumber")String billNumber, @Param("status")String status, @Param("failReason")String failReason);
		
		@Select("select * from ppp_bill")
		@ResultMap(value="allBills")
		public List<Map<String, Object>> selectBills();
		
		
}
