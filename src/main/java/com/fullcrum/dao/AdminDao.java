package com.fullcrum.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fullcrum.model.sys.AdminEntity;


@Mapper
public interface AdminDao {

	public AdminEntity adminLogin(@Param("adminEntity")AdminEntity adminEntity);

}
