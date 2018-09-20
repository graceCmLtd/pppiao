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
import com.fullcrum.model.sys.ResourceMarketEntity;

@Mapper
public interface ResourceMarket {

	String TABLE_NAME = "ppp_resource_market";
	String INSERT_FEILDS = "buyerId,amountRange,timeLimit,type1,type2,type3,type4,billType,priority,updateDate,note";
	
	@Select({"select * from ",TABLE_NAME," order by updateDate desc"})
	@ResultMap(value="resourceMarket")
	public ArrayList<ResourceMarketEntity> selectAll();
	
	@Select({"select * from ",TABLE_NAME," where orderId = #{orderId} "})
	@ResultMap(value="resourceMarket")
	public ArrayList<ResourceMarketEntity> selectByOrderId(@Param("orderId") String orderId);
	
	@Select({"select * from ",TABLE_NAME," where buyerId = #{buyerId} order by updateDate desc"})
	@ResultMap(value="resourceMarket")
	public ArrayList<ResourceMarketEntity> selectByBuyerId(@Param("buyerId") String buyerId);
	
	
	@Select({"select * from (select orderId,buyerId,amountRange,timeLimit,type1 as interest,  '国票+国股' as acceptor , billType, '1' as priority,updateDate,note from pengpengpiao.ppp_resource_market where type1 is not null and priority > 1  " + 
			" union all " + 
			"	select orderId,buyerId,amountRange,timeLimit,type2 as interest,'大商' as acceptor ,billType,priority,updateDate,note from pengpengpiao.ppp_resource_market where type2 is not null and priority > 1 " + 
			"	union all " + 
			"	select orderId,buyerId,amountRange,timeLimit,type3 as interest,'授信城商' as acceptor ,billType,priority,updateDate,note from pengpengpiao.ppp_resource_market where type3 is not null and  priority > 1 " + 
			"	union all " + 
			"	select orderId,buyerId,amountRange,timeLimit,type4 as interest,'村镇银行' as acceptor ,billType,priority,updateDate,note from pengpengpiao.ppp_resource_market where type4 is not null and priority > 1 ) a where a.priority ='1'   "})
	@ResultMap(value="resourcePool")
	public List<Map<String, Object>> selectPriorityForResourcePool();
	
	
	@Select({" select * from (select orderId,buyerId,amountRange,timeLimit,type1 as interest,  '国票+国股' as acceptor , billType, '1' as priority,updateDate,note from pengpengpiao.ppp_resource_market where type1 is not null and buyerId= #{jsonObject.buyerId} " +
			" union all " + 
			" select orderId,buyerId,amountRange,timeLimit,type2 as interest,'大商' as acceptor ,billType,priority,updateDate,note from pengpengpiao.ppp_resource_market where type2 is not null and buyerId= #{jsonObject.buyerId} " + 
			" union all " + 
			" select orderId,buyerId,amountRange,timeLimit,type3 as interest,'授信城商' as acceptor ,billType,priority,updateDate,note from pengpengpiao.ppp_resource_market where type3 is not null  and buyerId= #{jsonObject.buyerId} " + 
			" union all " + 
			" select orderId,buyerId,amountRange,timeLimit,type4 as interest,'村镇银行' as acceptor ,billType,priority,updateDate,note from pengpengpiao.ppp_resource_market where type4 is not null  and buyerId= #{jsonObject.buyerId} ) a  limit #{jsonObject.start}, #{jsonObject.page} "})
	@ResultMap(value="resourcePool")
	public List<Map<String, Object>> selectByBuyerIDForResourcePool(@Param("jsonObject") JSONObject jsonObject);
	
	@Insert({"insert ",TABLE_NAME,"( ",INSERT_FEILDS," ) values ( #{resourceMarketEntity.buyerId},#{resourceMarketEntity.amountRange},#{resourceMarketEntity.timeLimit},"
			+ "#{resourceMarketEntity.type1},#{resourceMarketEntity.type2},#{resourceMarketEntity.type3},#{resourceMarketEntity.type4},#{resourceMarketEntity.billType},#{resourceMarketEntity.priority},#{resourceMarketEntity.updateDate},#{resourceMarketEntity.note} )"})
	public void insert(@Param("resourceMarketEntity") ResourceMarketEntity resourceMarketEntity);
	
	@Insert({"insert ",TABLE_NAME,"( ",INSERT_FEILDS," ) values ( #{jsonObject.buyerId},#{jsonObject.amountRange},#{jsonObject.timeLimit},"
			+ "#{jsonObject.type1},#{jsonObject.type2},#{jsonObject.type3},#{jsonObject.type4},#{jsonObject.billType},#{jsonObject.priority},#{jsonObject.updateDate},#{jsonObject.note} )"})
	public void insertObj(@Param("jsonObject") JSONObject jsonObject);
	
	
	@Delete({"delete from ",TABLE_NAME, " where orderId = #{orderId}"})
	public void deleteByOrderId(@Param("orderId") String orderId);
	
	public void updateByOrderId(@Param("jsonObject") JSONObject jsonObject);
	
	@Select({"select distinct a.*,b.contactsName from (" + 
			"select orderId,buyerId,amountRange,timeLimit,type1 as interest,  '国票+国股' as acceptor,billType,updateDate,note from pengpengpiao.ppp_resource_market where type1 is not null " + 
			"union all select orderId,buyerId,amountRange,timeLimit,type2 as interest,'大商' as acceptor ,billType,updateDate,note from pengpengpiao.ppp_resource_market where type2 is not null " + 
			"union all select orderId,buyerId,amountRange,timeLimit,type3 as interest,'授信城商' as acceptor ,billType,updateDate,note from pengpengpiao.ppp_resource_market where type3 is not null " + 
			"union all select orderId,buyerId,amountRange,timeLimit,type4 as interest,'村镇银行' as acceptor ,billType,updateDate,note from pengpengpiao.ppp_resource_market where type4 is not null) a " +
			"left join ppp_company b on a.buyerId=b.contactsId group by a.orderId order by a.updateDate desc"})
	@ResultMap(value="AllInfo")
	public List<Map<String, Object>> selectAllInfo();
	
	
}
