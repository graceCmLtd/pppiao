package com.fullcrum.service.impl.sys.payment;


import com.fullcrum.dao.PaymentDao;
import com.fullcrum.service.sys.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="billPicsServiceImpl")
public class Yop implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public void pay(String payMethod) {

    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void confirm() {

    }


}