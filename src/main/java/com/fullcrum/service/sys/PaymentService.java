package com.fullcrum.service.sys;


import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.PaymentException;

public interface PaymentService {
	enum PaymentMethod {
		YOP_TRANSFER, //易宝代付：

	}

    String pay(String payMethod, PaymentEntity entity) throws PaymentException;
	void onPaySuccess();
	void confirm();


}
