package com.fullcrum.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.utils.GoEasyAPI;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/ppp/publish")
public class PublishController {

    @RequestMapping("/send")
    public String send(@RequestBody JSONObject jsonObject){
        GoEasyAPI goEasyAPI = new GoEasyAPI();
        goEasyAPI.sendMessage(jsonObject.getString("uuid"),jsonObject.getString("message"));
        return "success";
    }
}
