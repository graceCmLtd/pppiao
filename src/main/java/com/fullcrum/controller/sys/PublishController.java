package com.fullcrum.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.utils.GoEasyAPI;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/ppp/publish")
public class PublishController {

	@Autowired
	private GoEasyAPI goEasyAPI;
    @RequestMapping("/send")
    public String send(@RequestBody JSONObject jsonObject){
        //GoEasyAPI goEasyAPI = new GoEasyAPI();
        goEasyAPI.sendMessage(jsonObject.getString("uuid"),jsonObject.getString("message"));
        
        //GoEasyAPI.sendMessage(channel, message);
        return "success";
    }
}