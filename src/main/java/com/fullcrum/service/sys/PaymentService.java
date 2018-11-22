package com.fullcrum.service.sys;


public interface PaymentService {
	enum PaymentMethod {
		YOP_TRANSFER, //易宝代付：

	}

	void pay(String payMethod);
	void onPaySuccess();
	void confirm();


}
