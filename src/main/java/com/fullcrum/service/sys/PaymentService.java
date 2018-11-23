package com.fullcrum.service.sys;


import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.PaymentException;

public interface PaymentService {
	enum PaymentMethod {
		YOP_TRANSFER, //易宝代付：

	}
	
	/**通知地址 : 商户的通知地址（下单接口 notifyUrl 参数）,只有订单支付成功才有此服务器点对点通知
	*	说明：通知回调参数有：response、customerIdentification ，其中 response 为密文串，获取到之后解密成明文，明文信息如下：
	*	通知策略：收到易宝回调通知需回写大写“SUCCESS”,如没有回写则每 5 分钟通知一次，总共 3 次，3 次后没有拿到回写则停止通知。
	*	同一笔订单收到多次易宝回调，切忌请不要重复入账。
	**/
	String onPaySuccess(String response,String customerIdentification);
	

    String pay(String payMethod, PaymentEntity entity) throws PaymentException;
	

	void confirm();


}
