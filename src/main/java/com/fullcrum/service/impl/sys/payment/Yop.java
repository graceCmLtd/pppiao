package com.fullcrum.service.impl.sys.payment;


import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.sys.PaymentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

@Service(value="billPicsServiceImpl")
public class Yop implements PaymentService {

    private String platformMerchantNo;
    private String sellerId;
    private Integer transacId;
    private BigDecimal amount;
    private Integer expire;
    private Date requestDate;
    private String redirectUrl;
    private String notifyUrl;
    private String memo;
    private String billNumber;
    private String buyerId;
    private Integer feeType;
    private BigDecimal profit;
    private BigDecimal fee;
    private Integer status;
    private String extra;
    private String errorMsg;
    private Date updateDate;


    @Override
    public void pay(String payMethod) {

    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void confirm() {

    }

    public PaymentEntity toPaymentEntity(){
        PaymentEntity p = new PaymentEntity();
        p.setAmount(this.amount);
        p.setBillNumber(this.billNumber);
        p.setBuyerId(this.buyerId);
        p.setErrorMsg(this.errorMsg);
        p.setExpire(this.expire);
        p.setExtra(this.extra);
        p.setFee(this.fee);
        p.setFeeType(this.feeType);
        p.setMemo(this.memo);
        p.setProfit(this.profit);
        p.setRequestDate(this.requestDate);
        p.setSellerId(this.sellerId);
        p.setStatus(this.status);
        p.setTransacId(this.transacId);
        return p;
    }
}
