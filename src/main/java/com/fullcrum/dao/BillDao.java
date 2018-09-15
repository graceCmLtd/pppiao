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
 * lll
 * ll
 * billId varchar(45) PK 
billNumber varchar(45) 
billType varchar(45) 
acceptor varchar(45) 
amount varchar(45) 
maturity date 
status varchar(45) 
releaseDate date 
releaserId varchar(45) 
billPicsId int(11) 
transferable tinyint(4) 
billReferer varchar(45) 
timeStamp timestamp
 * */

@Mapper
public interface BillDao {

		String TABLE_NAME = "ppp_bill";
		
		String SELECT_FIELDS = "billId,billNumber,billType,acceptor,amount,maturity,status,releaseDate,releaserId,billPicsId ,transferable,billReferer,failReason,timeStamp";
		String INSERT_FIELDS = "billId, billNumber, billType,acceptor,amount,maturity,status,releaseDate,releaserId,billPicsId ,transferable,billReferer";
		

		
		@Select({"select * from ppp_bill where  billNumber = #{billNumber}"})
		@ResultMap(value="billMap")
		public ArrayList<BillEntity> selectByBillNumber(@Param("billNumber") String billNumber);
		
		@Select({"select "+SELECT_FIELDS+",TIMESTAMPDIFF(day,#{current_date},maturity) as remain_days  from ppp_bill where  billNumber = #{billNumber}"})
		@ResultMap(value="billMapAll")
		public List<Map<String, Object>>  selectByBillNumberAll(@Param("billNumber") String billNumber,@Param("current_date") String current_date);
		
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
		
		
		//获取当前用户发布票据的报价情况
		@Select({"SELECT * from (SELECT billNumber,billType,acceptor,amount,maturity,TIMESTAMPDIFF(day,#{jsonObject.curr_time},maturity) as remain_days,`status`,releaseDate,releaserId,billReferer,failReason from ppp_bill WHERE releaserId = #{jsonObject.uuid} and status='审核完成'  ) a "
				+ " LEFT JOIN(SELECT billNumber,COUNT(*) AS countNum from ppp_quote GROUP BY billNumber) b on a.billNumber = b.billNumber  " })
		@ResultMap(value="billAboutQuote")
		public List<Map<String, Object>> getBillsInquoting(@Param("jsonObject") JSONObject jsonObject );
		
		//根据订单号获取当前用户发布票据的已报价的报价情况
		@Select({"SELECT a.billNumber,a.billType,a.acceptor,a.amount,a.maturity,a.`status` AS billstatus,a.releaseDate,a.releaserId ,a.billReferer,a.failReason,"
				+ "TIMESTAMPDIFF(day,#{jsonObject.curr_time},a.maturity) as remain_days,b.quoterId,b.interest,b.xPerLakh,b.`status` as quotesattus,b.quoteReferer "
				+ "from (SELECT billNumber,billType,acceptor,amount,maturity,`status`,releaseDate,releaserId,billReferer,failReason "
				+ "from ppp_bill WHERE releaserId = #{jsonObject.uuid} AND  billNumber = #{jsonObject.billNumber}) a  "
				+ "LEFT JOIN(SELECT billNumber,quoterId,interest,xPerLakh,`status`,quoteDate,quoteReferer from ppp_quote ) b  on a.billNumber = b.billNumber ;"})
		@ResultMap(value="billAboutQuote")
		public List<Map<String, Object>> getBillsReceivedQuote(@Param("jsonObject") JSONObject jsonObject);
		
		//根据订单号获取当前用户发布票据的未报价的报价情况
		@Select({"SELECT a.billNumber,a.billType,a.acceptor,a.amount,a.maturity,a.`status` AS billstatus,a.releaseDate,a.releaserId ,a.billReferer,a.failReason,"
				+ "TIMESTAMPDIFF(day,#{jsonObject.curr_time},a.maturity) as remain_days,b.quoterId,b.interest,b.xPerLakh,b.`status` as quotesattus,b.quoteReferer "
				+ "from (SELECT billNumber,billType,acceptor,amount,maturity,`status`,releaseDate,releaserId,billReferer,failReason "
				+ "from ppp_bill WHERE releaserId = #{jsonObject.uuid} AND  billNumber = #{jsonObject.billNumber}) a  "
				+ "LEFT JOIN(SELECT billNumber,quoterId,interest,xPerLakh,`status`,quoteDate,quoteReferer from ppp_quote ) b  on a.billNumber = b.billNumber ;"})
		@ResultMap(value="billAboutQuote")
		public List<Map<String, Object>> getBillsWaitingQuote(@Param("jsonObject") JSONObject jsonObject);
		
		
		//获取用户发布的正在审核中的票据
		@Select({"SELECT * from (SELECT billNumber,billType,acceptor,amount,maturity,TIMESTAMPDIFF(day,#{jsonObject.curr_time},maturity) as remain_days,`status`,releaseDate,releaserId,billReferer,failReason from ppp_bill WHERE releaserId = #{jsonObject.uuid} and status='审核中'  ) a "
				+ " LEFT JOIN(SELECT billNumber,COUNT(*) AS countNum from ppp_quote GROUP BY billNumber) b on a.billNumber = b.billNumber  " })
		@ResultMap(value="billAboutQuote")
		public List<Map<String, Object>> getBillsAuditing(@Param("jsonObject") JSONObject jsonObject);
		
		
		
		//卖家  获取所有的意向
		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,a.intentionStatus,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,TIMESTAMPDIFF(day,#{jsonObject.curr_time},c.maturity)as remain_days,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable ,c.billReferer,"
				+ "d.contactsId,d.companyName,d.contactsName,d.contactsPhone,contactsQQ "
				+ "from (select * from pengpengpiao.ppp_transaction where sellerId = #{jsonObject.uuid} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber  LEFT JOIN(select * from pengpengpiao.ppp_company ) d ON a.buyerId =  d.contactsId ;"})
		@ResultMap(value="QuoteIntention")
		public List<Map<String, Object>> getSellerALLIntentions(@Param("jsonObject") JSONObject jsonObject);
		
		//买家调用 获取所有的意向
				@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,a.intentionStatus,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
						+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,TIMESTAMPDIFF(day,#{jsonObject.curr_time},c.maturity)as remain_days,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable ,c.billReferer,"
						+ "d.contactsId,d.companyName,d.contactsName,d.contactsPhone,contactsQQ "
						+ "from (select * from pengpengpiao.ppp_transaction where buyerId = #{jsonObject.uuid} ) a " + 
						"left join (select * from pengpengpiao.ppp_quote ) b " + 
						"on a.billNumber = b.billNumber " + 
						"left join (select * from pengpengpiao.ppp_bill ) c " + 
						"on a.billNumber = c.billNumber LEFT JOIN(select * from pengpengpiao.ppp_company ) d ON a.sellerId =  d.contactsId  ;"})
				@ResultMap(value="QuoteIntention")
				public List<Map<String, Object>> getBuyerALLIntentions(@Param("jsonObject") JSONObject jsonObject);
				
		
		//卖家调用，获取意向信息列表
		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,a.intentionStatus,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,TIMESTAMPDIFF(day,#{jsonObject.curr_time},c.maturity)as remain_days,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable ,c.billReferer,"
				+ "d.contactsId,d.companyName,d.contactsName,d.contactsPhone,contactsQQ "
				+ "from (select * from pengpengpiao.ppp_transaction where sellerId = #{jsonObject.uuid} and intentionStatus= #{jsonObject.filter_str} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber  LEFT JOIN(select * from pengpengpiao.ppp_company ) d ON a.buyerId =  d.contactsId  ;"})
		@ResultMap(value="QuoteIntention")
		public List<Map<String, Object>> getSellerIntentions(@Param("jsonObject") JSONObject jsonObject);
		
		//买家调用，获取意向信息列表
		@Select({"select a.transacId,a.transacType,a.billNumber,a.buyerId,a.sellerId,a.amount,a.transacStatus,a.transacDate,a.intentionStatus,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,"
				+ "b.quoteDate,c.billType,c.amount,c.billId,c.acceptor,c.maturity,TIMESTAMPDIFF(day,#{jsonObject.curr_time},c.maturity)as remain_days,c.status,c.releaseDate,c.releaserId,c.billPicsId,c.transferable ,c.billReferer,"
				+ "d.contactsId,d.companyName,d.contactsName,d.contactsPhone,contactsQQ "
				+ "from (select * from pengpengpiao.ppp_transaction where buyerId = #{jsonObject.uuid} and intentionStatus= #{jsonObject.filter_str} ) a " + 
				"left join (select * from pengpengpiao.ppp_quote ) b " + 
				"on a.billNumber = b.billNumber " + 
				"left join (select * from pengpengpiao.ppp_bill ) c " + 
				"on a.billNumber = c.billNumber  "
				+ "LEFT JOIN(select * from pengpengpiao.ppp_company ) d\r\n" + 
				"ON a.sellerId =  d.contactsId  ;"})
		@ResultMap(value="QuoteIntention")
		public List<Map<String, Object>> getBuyerIntentions(@Param("jsonObject") JSONObject jsonObject);
		
		
		
		/*@Select({"select distinct a.*, b.pic1 as pic1,b.pic2 as pic2 from ppp_bill a " + 
				"left join ppp_bill_pics b on a.billNumber = b.billNumber" + 
				" where  a.billNumber = #{billNumber}"})*/
		@Select({"select * from ppp_bill_pics where billNumber = #{billNumber}"})
		@ResultMap(value="getBillInfo")
		public List<Map<String, Object>> selectBillInfo(@Param("billNumber")String billNumber);
		
		@Select({"update ppp_bill set status = #{status},failReason = #{failReason}  where billNumber = #{billNumber}"})
		public void updateBillStatus(@Param("billNumber")String billNumber, @Param("status")String status, @Param("failReason")String failReason);
		
		@Select("select * from ppp_bill order by timeStamp desc")
		@ResultMap(value="allBills")
		public List<Map<String, Object>> selectBills();
		
		
}
