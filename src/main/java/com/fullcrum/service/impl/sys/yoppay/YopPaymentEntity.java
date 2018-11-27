package com.fullcrum.service.impl.sys.yoppay;

import com.fullcrum.model.sys.PaymentEntity;

import java.math.BigDecimal;
import java.sql.Date;

public class YopPaymentEntity {
    private String platformMerchantNo;
    private String sellerId;
    private String transacId;
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
    private String status;
    private String extra;
    private String errorMsg;
    private Date updateDate;
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
