package com.fullcrum.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fullcrum.model.sys.BillPicsEntity;

@Mapper
public interface BillPicsDao {
	
	String TABLE_NAME = "ppp_bill_pics";
	String INSERT_FIELDS = " billNumber , pic1, pic2 , updateDate";
	
	@Select({"select * from ",TABLE_NAME,"where billNumber = #{billNumber}"})
	@ResultMap(value="billPicsMap")
	public ArrayList<BillPicsEntity> selectByBillNumber(@Param("billNumber") String billNumber);
	
	@Insert({"insert ",TABLE_NAME,"(",INSERT_FIELDS,") values(#{billPicsEntity.billNumber} ,#{billPicsEntity.pic1},#{billPicsEntity.pic2},#{billPicsEntity.updateDate} )"})
	public void insertPics( @Param("billPicsEntity")  BillPicsEntity billPicsEntity);
	
	@Delete({"delete from ",TABLE_NAME,"where billNumber = #{billNumber}"})
	public void deletePics(@Param("billNumber") String billNumber);
	
	@Update({"update ",TABLE_NAME,"set pic1 = #{billPicsEntity.pic1},pic2 = #{billPicsEntity.pic2},updateDate = #{billPicsEntity.updateDate} where billNumber = #{billPicsEntity.billNumber}"})
	public void updateByBillNumber(@Param("billPicsEntity") BillPicsEntity billPicsEntity);
	
	
}
