package com.fullcrum.service.impl.sys.payment;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import com.fullcrum.dao.PaymentDao;

import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.PaymentException;
import com.fullcrum.service.sys.PaymentService;

import com.yeepay.g3.sdk.yop.encrypt.CertTypeEnum;
import com.yeepay.g3.sdk.yop.encrypt.DigitalEnvelopeDTO;
import com.yeepay.g3.sdk.yop.utils.DigitalEnvelopeUtils;
import com.yeepay.g3.sdk.yop.utils.InternalConfig;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;

@Service(value="yopPaymentServiceImpl")
public class Yop implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public String pay(String payMethod, PaymentEntity entity) throws PaymentException {
        Map<String, String> params = new HashMap<>();
        params.put("orderId", entity.getTransacId().toString());
        params.put("orderAmount", entity.getAmount().toString());
//        params.put("timeoutExpress", String.format("%d",entity.getExpire()/60));
//        params.put("requestDate", entity.getRequestDate());
//        params.put("redirectUrl", redirectUrl);
        params.put("notifyUrl", "empty for now");
//        params.put("goodsParamExt", goodsParamExt);
//        params.put("paymentParamExt", paymentParamExt);
//        params.put("industryParamExt", industryParamExt);
        params.put("memo", "test");
//        params.put("riskParamExt", riskParamExt);
//        params.put("csUrl", csUrl);
        Map<String, String> result = new HashMap<>();
        String uri = YeepayService.getUrl(YeepayService.TRADEORDER_URL);
        try {
            result = YeepayService.requestYOP(params, uri, YeepayService.TRADEORDER);
            String token = result.get("token");
            String codeRe = result.get("code");
            if(!"OPR00000".equals(codeRe)){
                throw PaymentException.newPaymentException(
                        new RuntimeException(String.format("易宝下单失败，返回错误码[%s], 提示信息[%s]",codeRe,result.get("message"))));
            }
            String parentMerchantNo = YeepayService.getParentMerchantNo();
            String merchantNo = YeepayService.getMerchantNo();
            params.put("parentMerchantNo", parentMerchantNo);
            params.put("merchantNo", merchantNo);
            params.put("token", token);
            params.put("timestamp", ""+new java.util.Date().getTime());
            params.put("userNo", entity.getBuyerId());
            params.put("userType", "MAC");
            String url = YeepayService.getUrl(params);

            //接口请求成功后实例化到数据库
            java.util.Date d = sdf.parse(result.get("requestDate"));
            entity.setRequestDate(d);
            entity.setMemo("test memo");
            entity.setPaymentWay("yop");
            entity.setExternalId(result.get("uiqueOrderNo"));
//            entity.setExpire();
            entity.setStatus(PaymentService.PAYMENT_STATUS_PROCESSING);

            return url;

        } catch (IOException e) {
            throw PaymentException.newOffLineException(e);
        } catch (ParseException e) {//第三方返回数据格式有变
            throw PaymentException.newThirdPartyInterException(e);
        }

    }
    
    
    @Override
    public String onPaySuccess(String response1,String customerIdentification) {
    	String response="iZLsNqljI0S4Edin9NctaLxZZpF7coaX5GOB-jpYynO8Wyv2ELzZHMOStZm0vIZ-DQnX8m2E6LNv3AN2Z_k2-MGgFKAvFq2_BIM-JF3vSiaUkvW0YVyvJUSNBkv9v_JsFYDVsP5h4zbtZQbbB6Uavj53_LWWYYGriWl2qkyHQbxEqszDPgayGo45oeCoxVLZW8QWyClzKRIAuhAFp2IP7NtWhl7lFln_CO_g6KK6_QbUqYACUtxhfSznbXYfLPGtgc5uQIfUtSpJqWOx_zUd3-qEuFl27Z4vjSmoN93YlNDeDx6gMKJlKaPKuJp7aGX9ptj3w8PPN11TWEIpbd0t5A$sKGZIndTkBKi9kp8MAo-BVxqEk0UN_dlmmr5I3diLRmfqBs4E3LurNHBzhW1iZ9LO5sm5oP87r6-s7sYzZdmPtDayoLF7KkZaDlYyxuTRnFYUXo28dDR293fFuaTPSFfqH3omysxy2ncZyQPo2yky50RcMDxhLr2nwX714gnmk-cJBQ8MA6P87PfNgLoF5rPopFaj-PTXJlM6_coX2yo_6JDhG8TrB0Ig3ntLzA9kKOR_1vbCPizv0FtVaNKyfrxlUwabD-eIlxBXhmnt10NTuTcM18OFvUFTHxxBrR0dMEUJww0MvudKJEjVZwwh2Hmv4NOVAPk4c5zeVhiHTQHWG4JQmCxvpG-mltxbv-66dVbNi45julQN7AcrbCJwKChj6amyXVQUNkGkmQr53iCDFBG4GtxneKOQ9mIap27YtPihemFcuBF6vfii4m9bBpXvorIqGie4kDT_8U_IHAwByUHyTBZBYx6gmbDHPhqD4_pkSRKzs6lAN1z1A1fY-WCbEwCNMEPxr_r7-5yumrlXSrG4uiR_1wBt8vBTlv2ludl1rRKWVFxN5jaQQp0MahJIq1AssuORDF3o1fx0gwwf-gjkxkUfz39VNWW2vXPG4dEBFJgj8zgyUHrOxNsfTMT_6Mak77Qgej2d8fnh5DqNNSMrt8L4LRLC4gV98GiRjf8BfJXDgHhY3Wsskxr3EBLE1sKoV7bST5H9Fl0t3l0OKkHbHQyuVh7iYawyZwvpEXnHesbZr_ZaBieQfjUKHcvA0L0B_exFcWPDm8oQt01WZ9zP8a86Lc82l-Cll1gk0gwjtDw3cQZ5T20BdDm3TLfCRqWA9oEBprBpaHij6HJo0XB5Zw_V1YmDP1PSm9tQamXiwrNoP7FoI7JzOzVkq8YRXTaFlFDJBk8n8pRrlNVggui5mScWVPsmg-l2MZgR93IaVrETKzZTWfFlIy2Uebhs9WOfDgU7WgL9jUaZ28P2VzXs6r9EQgkK_D8iLKFVEE8vnMfLkm-41AWLFHMzl24XUkKKAzcwZaasJYeG_Ob-aGcnAOQSulMLPT0gymFvOOG6keM7zxxzie86usakW4Gpqziyv64VZ7HugfEvPE5C09KW7Ln4Tgbtagh1_x67iEEJKCCPuZhuUvkdMQ9OpYAhmCBkGaWCUFsNIqKO9_59NpYKny8xHklk1XXCWNsQCeE6VXXyWsrZywR4TgalLR6$AES$SHA256";
    	try {
    		//开始解密
    		Map<String,String> jsonMap  = new HashMap<>();
    		DigitalEnvelopeDTO dto = new DigitalEnvelopeDTO();
    		dto.setCipherText(response);
    		//InternalConfig internalConfig = InternalConfig.Factory.getInternalConfig();
    		PrivateKey privateKey = InternalConfig.getISVPrivateKey(CertTypeEnum.RSA2048);
    		System.out.println("privateKey: "+privateKey);
    		PublicKey publicKey = InternalConfig.getYopPublicKey(CertTypeEnum.RSA2048);
    		System.out.println("publicKey: "+publicKey);
	
    		dto = DigitalEnvelopeUtils.decrypt(dto, privateKey, publicKey);
    		System.out.println("解密结果:"+dto.getPlainText());
    		jsonMap = parseResponse(dto.getPlainText());
    		System.out.println(jsonMap);
    		PaymentEntity paymentEntity = paymentDao.selectByUniqueOrderId(Integer.getInteger(jsonMap.get("uniqueOrderNo")));
    		
    		if (paymentEntity != null ) {
				if (paymentEntity.getStatus().equals("SUCCESS") ) {
					return "SUCCESS";
				}else {
					paymentEntity.setStatus("SUCCESS");
					paymentDao.updateByPrimaryKey(paymentEntity);
					return "SUCCESS";
				}
			}else {
				throw new Exception("cannot find UniqueOrderNo in db");
			}
    	} catch (Exception e) {
    		throw new RuntimeException("回调解密失败！");
    	
    	}
    	
    }

    @Override
    public Map<String,Object> confirm(JSONObject jsonObject) {
        Map<String,Object> map = new HashMap<>();
        map.put("customerNumber ",YeepayService.getParentMerchantNo());
        map.put("groupNumber ",YeepayService.getParentMerchantNo());
        map.put("batchNo","201811230000001");
        map.put("orderId",jsonObject.getString("orderId"));
        map.put("amount",jsonObject.getString("amount"));
        map.put("accountName",jsonObject.getString("accountName"));
        map.put("accountNumber",jsonObject.getString("accountNumber"));
        map.put("bankCode",jsonObject.getString("bankCode"));
        map.put("feeType","SOURCE");

        String url = YeepayService.getUrl(YeepayService.PAYMENT_URL);
        Map<String,Object> result = null;
        try{
            result = YeepayService.yeepayYOP(map,url);
            return result;
        }catch (IOException e){
            e.printStackTrace();
            result.put("msg",e.getMessage());
            return result;
        }
    }
  
    public static Map<String, String> parseResponse(String response){
		
		Map<String,String> jsonMap  = new HashMap<>();
		jsonMap	= JSON.parseObject(response, 
				new TypeReference<TreeMap<String,String>>() {});
		
		return jsonMap;
	}


}
