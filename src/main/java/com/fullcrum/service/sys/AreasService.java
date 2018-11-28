package com.fullcrum.service.sys;

import com.fullcrum.model.sys.AreasEntity;

import java.util.List;

public interface AreasService {

    List<AreasEntity> selectProvince();

    List<AreasEntity> selectCityByProvince(Integer pid);
}
