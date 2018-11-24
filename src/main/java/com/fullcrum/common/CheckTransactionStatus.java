package com.fullcrum.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fullcrum.service.sys.TransactionService;

@Component
public class CheckTransactionStatus {
	
	@Resource(name="transactionServiceImpl")
	TransactionService transactionService;
	
	static Map<String, Integer> orderMap  ;
	
	static {
		orderMap = new HashMap<String,Integer>();
		orderMap.put("待接单", 1);
		orderMap.put("已接单,待支付", 2);
		orderMap.put("已支付,待背书", 3);
		orderMap.put("已背书,待签收", 4);
		orderMap.put("已签收", 5);
		orderMap.put("已失效", 6);
	}

	
	public Integer getOrderChainPos(String key) {
		
		return orderMap.get(key);
	}
	
	public Boolean updatable(String key1,String key2) {
		
		Integer weight = 1;
		

		
		if (orderMap.get(key2) == 6) {
			weight = 0;
		}
		if (orderMap.get(key2) <=3) {
			return orderMap.get(key2) - orderMap.get(key1) <= 2 || weight == 0 ;
		}else {
			return orderMap.get(key2) - orderMap.get(key1) == 1 || weight == 0 ;
		}
		
	}
}
