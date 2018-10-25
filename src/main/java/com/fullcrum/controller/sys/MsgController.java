package com.fullcrum.controller.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.service.sys.MsgService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/ppp/msg")
public class MsgController {

	@Resource(name="msgServiceImpl")
	private MsgService msgService;
	
	@ApiOperation(value="插入消息", notes="插入消息" )
	
	@ApiImplicitParam(name="jsonObject",value="json格式参数体",defaultValue="{'msgType':'系统'}",dataType="JSONOBJECT",required=true)

	@RequestMapping(value="/addMsg",method= RequestMethod.POST)
	public JSONObject addMsg(@RequestBody JSONObject jsonObject) {
		
		JSONObject result = new JSONObject();
		try {
			msgService.insertMsg(jsonObject);
			result.put("status", "success");
			result.put("errorMsg",null);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.put("status", "fail");
			result.put("errorMsg", e);
		}
		
		return result;
	}
	
	@ApiOperation(value="获取用户消息",notes ="获取用户消息，通过用户的id")
	@RequestMapping(value="/getUserMsg")
	public List<Map<String, Object>> getMsgByReceiverId(@RequestParam("receiverId") String receiverId){
		System.out.println(msgService.selectMsgByReceiverId(receiverId));
		return msgService.selectMsgByReceiverId(receiverId);
	}
	
	@RequestMapping(value="/updateFlag")
	public JSONObject updateFlag(@RequestBody JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		try {
			
			msgService.updateReceiverFlag(jsonObject);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		
		return result;
	}
}
