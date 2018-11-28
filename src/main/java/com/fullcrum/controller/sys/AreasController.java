package com.fullcrum.controller.sys;

import com.fullcrum.model.sys.AreasEntity;
import com.fullcrum.service.sys.AreasService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ppp/areas")
public class AreasController {

    @Resource(name = "areasServiceImpl")
    private AreasService areasService;

    @RequestMapping("/getProvince")
    public List<AreasEntity> selectProvince(){
        return areasService.selectProvince();
    }

    @RequestMapping("/getCityByPid")
    public List<AreasEntity> selectCityByProvince(@RequestBody AreasEntity areasEntity){
        return areasService.selectCityByProvince(areasEntity.getId());
    }
}
