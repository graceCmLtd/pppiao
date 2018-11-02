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
import springfox.documentation.spring.web.json.Json;

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
	public List<Map<String, Object>> getMsgByReceiverId(@RequestParam("receiverId") String receiverId,@RequestParam("currentPage")Integer currentPage,
														@RequestParam("pageSize") Integer pageSize){
		return msgService.selectMsgByReceiverId(receiverId,(currentPage-1)*pageSize,pageSize);
	}

	@ApiOperation(value = "获取用户消息总数",notes = "获取用户消息总数，通过用户的id")
	@RequestMapping("/getMsgCount")
	public Integer getMsgCount(@RequestParam("receiverId") String receiverId){
		return msgService.selectMsgCount(receiverId);
	}

	@ApiOperation(value = "批量将未读消息标记为已读",notes = "批量将未读消息标记为已读,根据选中数据的每条msgId")
	@RequestMapping("/updateAllFlag")
	public JSONObject updateAllFlag(@RequestBody JSONObject jsonObject){
		JSONObject result = new JSONObject();
		try{
			msgService.updateAllFlag(jsonObject);
			result.put("status","success");
			return result;
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			result.put("status","failed");
			return result;
		}
	}

	@ApiOperation(value = "将某个用户的某条消息标记为已读",notes = "将某个用户的某条消息标记为已读，通过receiverId和msgId")
	@RequestMapping("/updateOneFlag")
	public JSONObject updateFlagByReceiverIdAndMsgId(@RequestBody JSONObject jsonObject){
		JSONObject result = new JSONObject();
		try{
			msgService.updateFlagByReceiverIdAndMsgId(jsonObject);
			result.put("status","success");
			return result;
		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			result.put("status","failed");
			return result;
		}
	}

	@RequestMapping(value="/updateFlag")
	public JSONObject updateFlag(@RequestBody JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		try {
			
			msgService.updateReceiverFlag(jsonObject);
			result.put("status","success");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			result.put("status","failed");
		}
		
		
		return result;
	}
}
