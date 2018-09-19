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
import com.fullcrum.model.sys.QuoteEntity;

@Mapper
public interface QuoteDao {

	String TABLE_NAME = "ppp_quote";
	String INSERT_FIELDS = " billNumber,quoterId,quoteAmount,interest,xPerLakh,status,quoteDate";
	
	@Select({"select  * from ",TABLE_NAME," where quoteId = #{quoteId}"})
	@ResultMap(value="quoteMap")
	public ArrayList<QuoteEntity> selectByQuoteId( @Param("quoteId") int quoteId);
	
	@Select({"select * from ",TABLE_NAME,"where quoterId = #{quoterId}"})
	@ResultMap(value="quoteMap")
	public ArrayList<QuoteEntity> selectByQuoterId(@Param("quoterId") String quoterId);
	
	@Select({"select * from ",TABLE_NAME,"where billNumber = #{billNumber} " })
	@ResultMap(value="quoteMap")
	public ArrayList<QuoteEntity> selectByBillNumber(@Param("billNumber") String billNumber);
	
	@Insert({"insert " ,TABLE_NAME,"(",INSERT_FIELDS," ) values(#{quoteEntity.billNumber} ,#{quoteEntity.quoterId},#{quoteEntity.quoteAmount},#{quoteEntity.interest},#{quoteEntity.xPerLakh},"
			+ "#{quoteEntity.status},#{quoteEntity.quoteDate} )"})
	public void insertQuote(@Param("quoteEntity") QuoteEntity quoteEntity);
	
	@Delete({"delete from ",TABLE_NAME,"where quoteId = #{quoteId}"})
	public void deleteQuoteByQuoteId(@Param("quoteId") int quoteId);
	
	@Update({"update ppp_quote set status = #{jsonObject.quoteStatus} where billNumber = #{jsonObject.billNumber} "})
	public void updateQuoteStatus(@Param("jsonObject") JSONObject jsonObject);
	
	@Update({"update ppp_quote set status = #{jsonObject.quoteStatus} where billNumber = #{jsonObject.billNumber} and quoterId != #{jsonObject.quoterId}"})
	public void setInvalidateQuotes(@Param("jsonObject") JSONObject jsonObject);
	
	@Update({"update ppp_quote set status = #{jsonObject.quoteStatus} where billNumber = #{jsonObject.billNumber} and quoterId = #{jsonObject.quoterId}"})
	public void setValidateQuote(@Param("jsonObject") JSONObject jsonObject);
	
	//买获取所有的报价根据报价者的id  获取票据发布者的联系方式   买家 
	@Select({"select b.billNumber,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,b.quoteDate,b.status as quoteStatus," + 
			"c.billType,c.amount,c.billId,c.acceptor,c.maturity,TIMESTAMPDIFF(day,#{jsonObject.curr_time},c.maturity)as remain_days,c.status,c.releaseDate,c.releaserId,c.billPicsId, c.transferable, " + 
			" a.companyName,a.contactsPhone,a.contactsQQ,a.bankAccountName,a.bankName,a.picId,a.contactsId from   (select * from pengpengpiao.ppp_quote where quoterId = #{jsonObject.uuid} ) b " + 
			"left join (select * from pengpengpiao.ppp_bill ) c on b.billNumber = c.billNumber"
			+ " left JOIN ( select * from pengpengpiao.ppp_company ) a on c.releaserId = a.contactsId;"})
	@ResultMap(value="myQuote")
	public List<Map<String, Object>> getALLQuote(@Param("jsonObject") JSONObject jsonObject);
	
	//
	@Select({"select b.billNumber,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,b.quoteDate,b.status as quoteStatus," + 
			"c.billType,c.amount,c.billId,c.acceptor,c.maturity,TIMESTAMPDIFF(day,#{jsonObject.curr_time},c.maturity)as remain_days,c.status,c.releaseDate,c.releaserId,c.billPicsId," + 
			"c.transferable" + 
			" from   (select * from pengpengpiao.ppp_quote where quoterId = #{jsonObject.uuid} ) b " + 
			"left join (select * from pengpengpiao.ppp_bill ) c on b.billNumber = c.billNumber ;"})
	@ResultMap(value="myQuote")
	public List<Map<String, Object>> getAcceptedQuote(@Param("jsonObject") JSONObject jsonObject);
	
	@Select({"select b.billNumber,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,b.quoteDate,b.status as quoteStatus," + 
			"c.billType,c.amount,c.billId,c.acceptor,c.maturity,TIMESTAMPDIFF(day,#{jsonObject.curr_time},c.maturity)as remain_days,c.status,c.releaseDate,c.releaserId,c.billPicsId," + 
			"c.transferable" + 
			" from   (select * from pengpengpiao.ppp_quote where quoterId = #{jsonObject.uuid} and status='报价中' ) b " + 
			"left join (select * from pengpengpiao.ppp_bill ) c on b.billNumber = c.billNumber ;"})
	@ResultMap(value="myQuote")
	public List<Map<String, Object>> getUnderQuote(@Param("jsonObject") JSONObject jsonObject);
	
	@Select({"select b.billNumber,b.quoteId,b.quoteAmount,b.quoterId,b.interest,b.xPerLakh,b.quoteDate,b.status as quoteStatus," + 
			"c.billType,c.amount,c.billId,c.acceptor,c.maturity,TIMESTAMPDIFF(day,#{jsonObject.curr_time},c.maturity)as remain_days,c.status,c.releaseDate,c.releaserId,c.billPicsId," + 
			"c.transferable" + 
			" from   (select * from pengpengpiao.ppp_quote where quoterId = #{jsonObject.uuid} and status='报价失效' ) b " + 
			"left join (select * from pengpengpiao.ppp_bill ) c on b.billNumber = c.billNumber ;"})
	@ResultMap(value="myQuote")
	public List<Map<String, Object>> getFailQuote(@Param("jsonObject") JSONObject jsonObject);
	
	@Update({"update ",TABLE_NAME," set status='报价失效'   where billNumber =#{jsonObject.billNumber} and quoterId != #{jsonObject.quoterId}"})
	public void confirmBuyer(@Param("jsonObject") JSONObject jsonObject);
	
	
	
}
