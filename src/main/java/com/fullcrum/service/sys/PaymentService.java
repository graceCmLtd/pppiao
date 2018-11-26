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
	

	String onPaySuccess(JSONObject request);
	

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
//    REFUND 退款成功(终态)
//    UNREFUND 退款失败(非终态)
	public static String PAYMENT_STATUS_PROCESSING = "PROCESSING";
    public static String PAYMENT_STATUS_SENDING = "SENDING";
	public static String PAYMENT_STATUS_SUCCESS = "SUCCESS";
	public static String PAYMENT_STATUS_CLOSED = "CLOSED";
	public static String PAYMENT_STATUS_TIME_OUT = "TIME_OUT";
	public static String PAYMENT_STATUS_REJECT = "REJECT";
	public static String PAYMENT_STATUS_REPEALED = "REPEALED";
	public static String PAYMENT_STATUS_REVOKED = "REVOKED";
	public static String PAYMENT_STATUS_REVERSAL = "REVERSAL";
	public static String PAYMENT_STATUS_UNCONFIRM = "UNCONFIRM";
	public static String PAYMENT_STATUS_CONFIRM = "CONFIRM";
	public static String PAYMENT_STATUS_UNREFUND = "UNREFUND";
	public static String PAYMENT_STATUS_REFUND = "REFUND";

    String pay(PaymentEntity entity) throws PaymentException;


	Map<String,Object> confirm(JSONObject jsonObject);

	Map<String,Object> refund(JSONObject jsonObject);
}
