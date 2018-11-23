package com.fullcrum.service.sys;


import com.fullcrum.model.sys.PaymentEntity;

public interface PaymentService {
	enum PaymentMethod {
		YOP_TRANSFER, //易宝代付：

	}

	void pay(String payMethod, PaymentEntity entity);
	void onPaySuccess();
	void confirm();


}
