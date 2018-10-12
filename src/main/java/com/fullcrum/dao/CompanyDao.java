package com.fullcrum.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.fullcrum.model.sys.CompanyEntity;

@Mapper
public interface CompanyDao {
	
	String TABLE_NAME = "ppp_company";
	String INSERT_FIELDS = "companyName,contactsId ,contactsName, contactsPhone ,contactsEmail ,contactsQQ ,bankAccountName ,bankAccount,bankName,picId ,signUpAddr ,updateDate,role ";
	
	@Select({"select * from ",TABLE_NAME,"order by updateTimeStamp desc limit #{currentPage},#{pageSize}"  })
	@ResultMap("companyMap")
	public  ArrayList<CompanyEntity>  selectAll(@Param("pageSize") Integer pageSzie,@Param("currentPage") Integer currentPage);
	
	@Select({"select * from ",TABLE_NAME,"where contactsId = #{contactsId}"})
	@ResultMap("companyMap")
	public ArrayList<CompanyEntity> selectByContactsId(@Param("contactsId") String contactsId);
	
	
	@Insert({"insert  ",TABLE_NAME,"(",INSERT_FIELDS,") values ( #{companyEntity.companyName},#{companyEntity.contactsId},#{companyEntity.contactsName},#{companyEntity.contactsPhone},#{companyEntity.contactsEmail},"
			+ "#{companyEntity.contactsQQ},#{companyEntity.bankAccountName},#{companyEntity.bankAccount},#{companyEntity.bankName},#{companyEntity.picId},#{companyEntity.signUpAddr},"
			+ "#{companyEntity.updateDate},'未审核')"}  )
	public void  insertCompany(@Param("companyEntity") CompanyEntity companyEntity);
	
	@Delete({"delete from " , TABLE_NAME,"where companyName = #{companyEntity.companyName}"})
	public void  deleteCompany(@Param("companyEntity") CompanyEntity companyEntity);
	
	@Select({"select * from ",TABLE_NAME,"where companyId = #{companyId}"})
	@ResultMap("companyMap")
	public ArrayList<CompanyEntity> selectByCompanyId(@Param("companyId")String companyId);
	
	@Select({"update ppp_company set role = #{role}  where companyId = #{companyId}"})
	public void updateCompanyStatus(@Param("companyId")String companyId, @Param("role")String role);

	public void update(@Param("companyEntity")CompanyEntity companyEntity);

	public Integer getCount();
}
