package com.fullcrum.service.impl.sys.payment;


import com.fullcrum.dao.PaymentDao;
import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.sys.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service(value="billPicsServiceImpl")
public class Yop implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public void pay(String payMethod, PaymentEntity entity)  {
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
        result = YeepayService.requestYOP(params, uri, YeepayService.TRADEORDER);

    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void confirm() {

    }


}
