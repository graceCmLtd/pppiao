package com.fullcrum.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class AipOcrImage {
	
	private static final String APP_ID = "11803131";
	private static final String API_KEY = "vnVMfDGv3dSFGQHVIUKOsoeG";
	private static final String SECRET_KEY = "nIRTxF2jAceEf5cV4RK1zjCP5YYAYRRn";
	
	//票据识别
	public JSONObject orcImage(String image) {
		
		HashMap<String,String> options = new HashMap<String,String>();
		Map<String, Object> results = new HashMap<String,Object>();
		
		options.put("language_type", "CHN_ENG");
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		byte [] file = org.apache.commons.codec.binary.Base64.decodeBase64(image);
		JSONObject res = client.receipt(file, options);
		//对API文字识别的结果处理
		JSONArray jsonArray = res.getJSONArray("words_result");
		if(jsonArray.length()>0){
		  	for(int i=0;i<jsonArray.length();i++){  
		  		// 遍历 jsonarray 数组，把每一个对象转成 json 对象  
		  		JSONObject word = jsonArray.getJSONObject(i);   
		  		// 得到 每个对象中的属性值  
		  		//System.out.println(word.get("words")) ;
		  		results.put(String.valueOf(i),word.get("words"));
		  	}
		}
		
		List<String> list = new ArrayList<String>();
		Iterator<String> iter = results.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			String value = (String) results.get(key);
			list.add(value);
		}
		String billNumber = "";
		String date = "";
		if(list.size()>0) {
			for(int i = 0;i<list.size();i++) {
				//System.out.println(list.get(i));
				if(list.get(i).contains("票据号码")) {
					if(list.get(i).matches("票据号码")) {
						billNumber = list.get(i+1);
					}else if(list.get(i).matches("票据号码:(.*)")) {
						billNumber = list.get(i).substring(5);
					}else if(list.get(i).matches("票据号码(.*)")) {
						billNumber = list.get(i).substring(4);
					}
					
				}
				if(list.get(i).contains("汇票到期日")) {
					if(list.get(i).matches("汇票到期日")) {
						date = list.get(i+1);
					}else if(list.get(i).matches("汇票到期日:(.*)")) {
						date = list.get(i).substring(6);
					}else if(list.get(i).matches("汇票到期日(.*)")) {
						date = list.get(i).substring(5);
					}
					
				}
			}
		}
		//System.out.println(billNumber+"--"+date);
		
		JSONObject result = new JSONObject();
		result.put("billNumber", billNumber);
		result.put("time", date);
		
		return result;
		
	}
}
