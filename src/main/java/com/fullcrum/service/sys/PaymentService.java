package com.fullcrum.service.sys;


import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.PaymentException;

import java.util.Map;

public interface PaymentService {
	enum PaymentMethod {
		YOP_TRANSFER, //易宝代付：
        REAPAL,//融宝
	}
	
	/**通知地址 : 商户的通知地址（下单接口 notifyUrl 参数）,只有订单支付成功才有此服务器点对点通知
	*	说明：通知回调参数有：response、customerIdentification ，其中 response 为密文串，获取到之后解密成明文，明文信息如下：
	*	通知策略：收到易宝回调通知需回写大写“SUCCESS”,如没有回写则每 5 分钟通知一次，总共 3 次，3 次后没有拿到回写则停止通知。
	*	同一笔订单收到多次易宝回调，切忌请不要重复入账。
	**/
	String onPaySuccess(String response,String customerIdentification);
	

//    PROCESSING 处理中(非终态)
//    SUCCESS 订单成功(终态)
//    CLOSED订单关闭(终态)
//    TIME_OUT 订单超时(终态)
//    REJECT订单拒绝(终态)
//    REPEALED 订单撤销(分账订单退款后查询)
//    REVOKED 订单取消(网银订单)
//    REVERSAL 冲正',
//    UNCONFIRM 打款中(非终态)
//	  CONFIRM 打款成功(终态)
	public static String PAYMENT_STATUS_PROCESSING = "PROCESSING";
	public static String PAYMENT_STATUS_SUCCESS = "SUCCESS";
	public static String PAYMENT_STATUS_CLOSED = "CLOSED";
	public static String PAYMENT_STATUS_TIME_OUT = "TIME_OUT";
	public static String PAYMENT_STATUS_REJECT = "REJECT";
	public static String PAYMENT_STATUS_REPEALED = "REPEALED";
	public static String PAYMENT_STATUS_REVOKED = "REVOKED";
	public static String PAYMENT_STATUS_REVERSAL = "REVERSAL";
	public static String PAYMENT_STATUS_UNCONFIRM = "UNCONFIRM";
	public static String PAYMENT_STATUS_CONFIRM = "CONFIRM";

    String pay(PaymentEntity entity) throws PaymentException;


	Map<String,Object> confirm(JSONObject jsonObject);

	String refund(JSONObject jsonObject);
}
