package com.fullcrum.dao;

import com.fullcrum.model.sys.AreasEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreasDao {

    List<AreasEntity> selectProvince();

    List<AreasEntity> selectCityByProvince(Integer pid);
}
