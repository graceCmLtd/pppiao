package com.fullcrum.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.CompanyPicsEntity;

@Mapper
public interface CompanyPicsDao {

	String TABLE_NAME_1 = "ppp_company_pics";
	String INSERT_FIELDS = " pic1Content, updateDate,contactsId,pic2Content,pic1IDCard,pic2IDCard";
	
	@Select({"select * from ", TABLE_NAME_1,"where contactsId = #{contactsId} order by updateDate desc"})
	@ResultMap(value="companyPicsMap")
	public ArrayList<CompanyPicsEntity> selectCompanyPicsByContactsId(@Param("contactsId") String contactsId);
	
	@Insert({"insert ",TABLE_NAME_1,"(",INSERT_FIELDS,") values(#{companyPicsEntity.pic1Content},#{companyPicsEntity.updateDate},#{companyPicsEntity.contactsId}," +
			"#{companyPicsEntity.pic2Content},#{companyPicsEntity.pic1IDCard},#{companyPicsEntity.pic2IDCard} )"})
	public void insertCompanyPics(@Param("companyPicsEntity") CompanyPicsEntity companyPicsEntity);

	@Insert({"insert " ,TABLE_NAME_1,"(",INSERT_FIELDS," ) values (#{jsonObject.picContent},#{jsonObject.updateDate},#{jsonObject.contactsId},#{jsonObject.pic2Content},#{jsonObject.pic1IDCard},#{jsonObject.pic2IDCard})"})
	public void insertCompanyPicsByJson(@Param("jsonObject") JSONObject jsonObject );
	
	
	
	@Delete({"delete from ",TABLE_NAME_1,"where companyId = #{companyPicsEntity.contactsId}"})
	public void deleteCompanyPics(@Param("companyPicsEntity") CompanyPicsEntity companyPicsEntity);
	
	@Select({"select * from ",TABLE_NAME_1,"where picId = #{picId}"})
	public CompanyPicsEntity selectPic(String picId);

	public void updatePic(@Param("companyPicsEntity")CompanyPicsEntity companyPicsEntity);
	

}
