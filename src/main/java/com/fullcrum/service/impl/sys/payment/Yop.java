package com.fullcrum.service.impl.sys.payment;


import com.fullcrum.dao.PaymentDao;
import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.PaymentException;
import com.fullcrum.service.sys.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service(value="yopPaymentServiceImpl")
public class Yop implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public String pay(String payMethod, PaymentEntity entity) throws PaymentException {
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
        try {
            result = YeepayService.requestYOP(params, uri, YeepayService.TRADEORDER);
            String token = result.get("token");
            String codeRe = result.get("code");
            if(!"OPR00000".equals(codeRe)){
                throw PaymentException.newPaymentException(
                        new RuntimeException(String.format("易宝下单失败，返回错误码[%s], 提示信息[%s]",codeRe,result.get("message"))));
            }
            String parentMerchantNo = YeepayService.getParentMerchantNo();
            String merchantNo = YeepayService.getMerchantNo();
            params.put("parentMerchantNo", parentMerchantNo);
            params.put("merchantNo", merchantNo);
            params.put("token", token);
            params.put("timestamp", ""+new java.util.Date().getTime());
            params.put("userNo", entity.getBuyerId());
            params.put("userType", "MAC");
            String url = YeepayService.getUrl(params);

            //接口请求成功后实例化到数据库
            java.util.Date d = sdf.parse(result.get("requestDate"));
            entity.setRequestDate(d);
            entity.setMemo("test memo");
            entity.setPaymentWay("yop");
            entity.setExternalId(result.get("uiqueOrderNo"));
//            entity.setExpire();
            entity.setStatus(PaymentService.PAYMENT_STATUS_PROCESSING);

            return url;

        } catch (IOException e) {
            throw PaymentException.newOffLineException(e);
        } catch (ParseException e) {//第三方返回数据格式有变
            throw PaymentException.newThirdPartyInterException(e);
        }

    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void confirm() {

    }


}
