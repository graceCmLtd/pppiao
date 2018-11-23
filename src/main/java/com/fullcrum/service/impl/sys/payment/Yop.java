package com.fullcrum.service.impl.sys.payment;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.sys.PaymentService;
import com.yeepay.g3.sdk.yop.encrypt.CertTypeEnum;
import com.yeepay.g3.sdk.yop.encrypt.DigitalEnvelopeDTO;
import com.yeepay.g3.sdk.yop.utils.DigitalEnvelopeUtils;
import com.yeepay.g3.sdk.yop.utils.InternalConfig;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service(value="yop")
public class Yop implements PaymentService {

    private String platformMerchantNo;
    private String sellerId;
    private Integer transacId;
    private BigDecimal amount;
    private Integer expire;
    private Date requestDate;
    private String redirectUrl;
    private String notifyUrl;
    private String memo;
    private String billNumber;
    private String buyerId;
    private Integer feeType;
    private BigDecimal profit;
    private BigDecimal fee;
    private Integer status;
    private String extra;
    private String errorMsg;
    private Date updateDate;


    @Override
    public void pay(String payMethod) {

    }
    
    
    @Override
    public void onPaySuccess(String response1,String customerIdentification) {
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
    		
    	} catch (Exception e) {
    		throw new RuntimeException("回调解密失败！");
    	}
    	
    	
    }

    @Override
    public void confirm() {

    }

    public PaymentEntity toPaymentEntity(){
        PaymentEntity p = new PaymentEntity();
        p.setAmount(this.amount);
        p.setBillNumber(this.billNumber);
        p.setBuyerId(this.buyerId);
        p.setErrorMsg(this.errorMsg);
        p.setExpire(this.expire);
        p.setExtra(this.extra);
        p.setFee(this.fee);
        p.setFeeType(this.feeType);
        p.setMemo(this.memo);
        p.setProfit(this.profit);
        p.setRequestDate(this.requestDate);
        p.setSellerId(this.sellerId);
        p.setStatus(this.status);
        p.setTransacId(this.transacId);
        return p;
    }
    
    public static Map<String, String> parseResponse(String response){
		
		Map<String,String> jsonMap  = new HashMap<>();
		jsonMap	= JSON.parseObject(response, 
				new TypeReference<TreeMap<String,String>>() {});
		
		return jsonMap;
	}
}
