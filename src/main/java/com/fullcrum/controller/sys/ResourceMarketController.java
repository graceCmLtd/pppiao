package com.fullcrum.controller.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.ResourceMarketEntity;
import com.fullcrum.service.sys.ResourceMarketService;

@RestController
@CrossOrigin
@RequestMapping("/ppp/resourceMarket")
public class ResourceMarketController {

	@Resource(name="resourceMarketImpl")
	private ResourceMarketService resourceMarketService;
	
	@RequestMapping("/getAll")
	public ArrayList<ResourceMarketEntity> getAll(){
		
		return resourceMarketService.selectAll();
	}
	//获取资源市场页面的所有信息
	@RequestMapping("/getAllInfo")
	public List<Map<String,Object>> getAllInfo(@RequestParam Integer pageSize,@RequestParam Integer currentPage){
		return resourceMarketService.selectAllInfo(pageSize,(currentPage-1)*pageSize);
	}
	
	@RequestMapping("/getByOrderId")
	public ArrayList<ResourceMarketEntity> getByOrderId(@RequestParam(value="orderId") String orderId){
	
		return resourceMarketService.selectByOrderId(orderId);
	}
	
	@RequestMapping("/getByBuyerId")
	public ArrayList<ResourceMarketEntity> getByBuyerId(@RequestParam(value="buyerId") String buyerId,
			@RequestParam(value="pageSize") Integer pageSize,@RequestParam(value="currentPage") Integer currentPage){
		return resourceMarketService.selectByBuyerId(buyerId,pageSize,(currentPage-1)*pageSize);
	}
	
	@RequestMapping("/getCountByBuyerId")
	public Integer getCountByBuyerId(@RequestParam(value="buyerId") String buyerId) {
		return resourceMarketService.getCountByBuyerId(buyerId);
	}
	
	@RequestMapping("/add")
	public JSONObject add(@RequestBody JSONObject jsonObject ) {
		
		JSONObject result = new JSONObject();
		JSONObject temp = jsonObject.getJSONObject("resourceMarketInfo");
		JSONObject param = new JSONObject();
		
		param.put("amountRange", temp.getString("amountRange"));
		param.put("timeLimit", temp.getString("timeLimit"));
		param.put("buyerId", temp.getString("buyerId"));
		int n = resourceMarketService.checkRepetition(param);
		
		if (n > 0 ) {
			result.put("status", "fail");
			result.put("errorMsg", "line repetition");
		}else {
			resourceMarketService.insert(JSONObject.toJavaObject(jsonObject.getJSONObject("resourceMarketInfo"), ResourceMarketEntity.class));
			System.out.println(jsonObject.getJSONObject("resourceMarketInfo"));
			result.put("status", "success");
		}
		
		
		
		return result;
	}
	
	@RequestMapping("/deleteByOrderId")
	public JSONObject deleteByOrderId (@RequestParam(value="orderId") String orderId) {
		
		JSONObject result = new JSONObject();
		resourceMarketService.deleteByOrderId(orderId);
		result.put("status", "success");
		return result;
	}
	
	@RequestMapping("/updateByOrderId")
	public JSONObject updateByOrderId(@RequestBody JSONObject jsonObject ) {
		JSONObject result = new JSONObject();
		JSONObject param = new JSONObject();
		param.put("amountRange", jsonObject.getString("amountRange"));
		param.put("timeLimit", jsonObject.getString("timeLimit"));
		param.put("buyerId", jsonObject.getString("buyerId"));
		int n = resourceMarketService.checkRepetition(param);
		System.out.println("count lines in resourcemarket");
		System.out.println(n);
		if ( n > 1 ) {
			result.put("status", "fail");
			result.put("errorMsg", "line repetition");
		}else {
			resourceMarketService.updateByOrderId(jsonObject);
			result.put("status", "success");
			result.put("errorMsg", null);
		}
		return result;
	}
	
	//根据用户id更新资源市场备注信息
	@RequestMapping("/updateNoteByUserId")
	public JSONObject updateNoteByUserId(@RequestBody JSONObject jsonObject) {
		JSONObject result = new JSONObject();
		try {
			resourceMarketService.updateNoteByUserId(jsonObject);
			result.put("status", "success");
			result.put("errorMsg", null);
		} catch (Exception e) {
			result.put("status", "fail");
			result.put("errorMsg", e);
		}
		
		
		return result;
	}
	//获取资源市场数据总条数，用于分页
	@RequestMapping("/getPriorityItem")
	public List<Map<String, Object>> getPriorityItems(){
		return resourceMarketService.selectPriorityForResourcePool();
	}
	
	@RequestMapping("/getByBuyerIdOfResoucePool")
	public List<Map<String, Object>> getByBuyerIdOfResourcePool(@RequestBody JSONObject jsonObject){
		
		return resourceMarketService.selectByBuyerIDForResourcePool(jsonObject);
	}
	
	@RequestMapping("/getCount")
	public Integer getCount() {
		return resourceMarketService.getCount();
	}
	
	/*根据条件查询，返回符合条件的行*/
	@RequestMapping("/getByConditions")
	public ArrayList<ResourceMarketEntity> selectByConditions(@RequestBody JSONObject jsonObject){
		return  resourceMarketService.selectByConditions(jsonObject);
	}
}
