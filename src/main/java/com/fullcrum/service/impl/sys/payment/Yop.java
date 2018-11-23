package com.fullcrum.service.impl.sys.payment;


import com.fullcrum.dao.PaymentDao;
import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.sys.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

@Service(value="yop")

public class Yop implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public void pay(String payMethod, PaymentEntity entity) {

    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void confirm() {

    }


}
