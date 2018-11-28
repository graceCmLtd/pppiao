package com.fullcrum.service.impl.sys;

import com.fullcrum.dao.AreasDao;
import com.fullcrum.model.sys.AreasEntity;
import com.fullcrum.service.sys.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="areasServiceImpl")
public class AreasServiceImpl implements AreasService {

    @Autowired
    private AreasDao areasDao;

    @Override
    public List<AreasEntity> selectProvince() {
        return areasDao.selectProvince();
    }

    @Override
    public List<AreasEntity> selectCityByProvince(Integer pid) {
        return areasDao.selectCityByProvince(pid);
    }
}
