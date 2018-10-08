package com.fullcrum.controller.sys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.QuoteEntity;
import com.fullcrum.service.sys.QuoteService;
import com.fullcrum.service.sys.TransactionService;

@RestController
@CrossOrigin
@RequestMapping("/ppp/quote")
public class QuoteController {

	@Resource(name="quoteServiceImpl")
	private QuoteService quoteService;
	
	@Resource(name="transactionServiceImpl")
	private TransactionService transactionService;
	
	
	/*根据报价id 查询报价详情*/
	@RequestMapping("/getByQuoteId")
	public ArrayList<QuoteEntity>  getQuoteByQuoteId(@RequestParam(value="quoteId") int quoteId){
		
		return quoteService.selectQuoteByQuoteId(quoteId);
	}
	
	/*根据报价  者 id 查询报价详情*/
	@RequestMapping("/getByQuoterId")
	public ArrayList<QuoteEntity> getQuoteByQuoterId( @RequestParam(value="quoterId") String quoterId){
		return quoteService.selectQuoteByQuoterId(quoterId);
	}
	
	/*根据报 票据的 票号 billNumber  查询报价详情*/
	@RequestMapping("/getByBillNumber")
	public ArrayList<QuoteEntity> getQuoteByBillNumber(@RequestParam(value="billNumber") String billNumber ){
		return quoteService.selectQuoteByBillNumber(billNumber);
	}
	
	/*增加 报价 */
	@RequestMapping("/addQuote")
	public String addQuote(@RequestBody QuoteEntity quoteEntity) {
		
		quoteService.insertQuote(quoteEntity);
		return "success";
		
	}
	
	/*根据报价id 删除 报价 */
	@RequestMapping("/deleteQuote")
	public String deleteQuote(@RequestParam(value="quoteId") int quoteId) {
		
		quoteService.deleteQuoteByQuoteId(quoteId);
		return "success";
	}
	
	
	/*根据筛选条件 查询报价详情
	 * 
	 * 当filter字段 为  1 时，查询全部报价
	 * 当filter字段为2时 ，查询 已经被接受的报价
	 * 当filter字段为3时，查询正在报价的报价
	 * 当filter字段为4时，查询失败的报价
	 * 
	 * */
	@RequestMapping("/getMyQuote")
	public List<Map<String, Object>> getMyQuote(@RequestBody JSONObject jsonObject){
		jsonObject.put("curr_time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		Integer currentPage = jsonObject.getInteger("currentPage");
		Integer pageSize = jsonObject.getInteger("pageSize");
		jsonObject.put("currentPage", (currentPage-1)*pageSize);
		switch (jsonObject.get("filter").toString()) {
		case "1":
			//全部报价
			return quoteService.getALLQuote(jsonObject);
		case "2":
			return quoteService.getAcceptedQuote(jsonObject);
		case "3":
			//报价中
			return quoteService.getUnderQuote(jsonObject);
		case "4":
			//已失效
			return quoteService.getFailQuote(jsonObject);
		default:
			return null;
		}
	}
	//获取条数
	@RequestMapping("/getQuoteCount")
	public Integer getQuoteCount(@RequestBody JSONObject jsonObject){
		jsonObject.put("curr_time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		switch (jsonObject.get("filter").toString()) {
		case "1":
			//全部报价
			return quoteService.getALLQuoteCount(jsonObject);
		case "2":
			//return quoteService.getAcceptedQuote(jsonObject);
		case "3":
			//报价中
			return quoteService.getUnderQuoteCount(jsonObject);
		case "4":
			//已失效
			return quoteService.getFailQuoteCount(jsonObject);
		default:
			return null;
		}
	}
	
	/*  
	 * 增加交易意向
	 * 
	 * */
	@RequestMapping("/submitIntention")
	public JSONObject updateQuoteStatus(@RequestBody JSONObject jsonObject) {
		
		JSONObject result = new JSONObject();
		//quoteService.updateQuoteStatus(jsonObject);
		//卖家确定某一个买家的报价
		
		//更新交易信息
		
		result.put("status", "success");
		
		return result;
	}
	
	//通过票据号码来查询我的报价页面票据详情的信息
	@RequestMapping("/getDetail")
	public List<Map<String,Object>> getDetail(@RequestParam("billNumber")String billNumber){
		System.out.println(billNumber);
		List<Map<String,Object>> list = quoteService.selectBillByBillNum(billNumber);
		for (Map<String, Object> map : list) {
				Object object = map.get("pic1");
				System.out.println(object);
		}
		
		return list;
	}
	
	/*修改quote表中real_money字段，即实际价格
	 * jsonobject 字段：
	 * billNumber
	 * quoterId
	 * new_money
	 * 
	 * */
	@RequestMapping("/updateRealMoney")
	public JSONObject updateRealMoney(@RequestBody JSONObject jsonObject) {
		
		JSONObject result = new JSONObject();
		try {
			quoteService.updateRealMoney(jsonObject);
			result.put("status", "success");
			result.put("errorMsg", null);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("status", "fail");
			result.put("errorMsg", e);
		}
		
		return result;
	}
}
