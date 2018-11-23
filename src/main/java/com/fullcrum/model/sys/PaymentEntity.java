package com.fullcrum.model.sys;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ppp_tx_payment
 * @author 
 */
public class PaymentEntity implements Serializable {
    private Integer id;

    /**
     * 三方支付：yop:易宝
     */
    private String paymentWay;

    /**
     * 三方支付的订单id（如易宝的uniqueOrderNo）
     */
    private String externalId;

    private Integer transacId;

    /**
     * 冗余字段，billNumber
     */
    private String billNumber;

    /**
     * 冗余字段，即transaction中的seller
     */
    private String sellerId;

    /**
     * 冗余字段，即transaction中的buyer
     */
    private String buyerId;

    /**
     * 冗余字段，即transaction中的amount
     */
    private BigDecimal amount;

    /**
     * 请求时间
     */
    private Date requestDate;

    /**
     * 订单有效期，单位秒
     */
    private Integer expire;

    /**
     * 自定义对账备注
     */
    private String memo;

    /**
     * 收费方式：（将来如需定制收费方案在添加收费方案表）
1: amount中抽1%作为平台收益（profit），另外每笔10元支付给第三方平台（fee）
     */
    private Integer feeType;

    /**
     * 第三方支付平台手续费，卖方实得=amount-profit-fee
     */
    private BigDecimal fee;

    /**
     * 订单状态:暂时参考易宝状态
													PROCESSING 处理中(非终态)
													SUCCESS 订单成功(终态)
													CLOSED订单关闭(终态)
													TIME_OUT 订单超时(终态)
													REJECT订单拒绝(终态)
													REPEALED 订单撤销(分账订单退款后查询)
													REVOKED 订单取消(网银订单)
													REVERSAL 冲正
     */
    private Integer status;

    /**
     * 额外信息，json字符串形式
     */
    private String extra;

    /**
     * 调用中的错误提示,三方调用出错时存储返回码等
     */
    private String errorMsg;

    private Date updateDate;

    /**
     * 公司收益部分，卖方实得=amount-profit-fee
     */
    private BigDecimal profit;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Integer getTransacId() {
        return transacId;
    }

    public void setTransacId(Integer transacId) {
        this.transacId = transacId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PaymentEntity other = (PaymentEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPaymentWay() == null ? other.getPaymentWay() == null : this.getPaymentWay().equals(other.getPaymentWay()))
            && (this.getExternalId() == null ? other.getExternalId() == null : this.getExternalId().equals(other.getExternalId()))
            && (this.getTransacId() == null ? other.getTransacId() == null : this.getTransacId().equals(other.getTransacId()))
            && (this.getBillNumber() == null ? other.getBillNumber() == null : this.getBillNumber().equals(other.getBillNumber()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getRequestDate() == null ? other.getRequestDate() == null : this.getRequestDate().equals(other.getRequestDate()))
            && (this.getExpire() == null ? other.getExpire() == null : this.getExpire().equals(other.getExpire()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getFeeType() == null ? other.getFeeType() == null : this.getFeeType().equals(other.getFeeType()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getErrorMsg() == null ? other.getErrorMsg() == null : this.getErrorMsg().equals(other.getErrorMsg()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getProfit() == null ? other.getProfit() == null : this.getProfit().equals(other.getProfit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPaymentWay() == null) ? 0 : getPaymentWay().hashCode());
        result = prime * result + ((getExternalId() == null) ? 0 : getExternalId().hashCode());
        result = prime * result + ((getTransacId() == null) ? 0 : getTransacId().hashCode());
        result = prime * result + ((getBillNumber() == null) ? 0 : getBillNumber().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getRequestDate() == null) ? 0 : getRequestDate().hashCode());
        result = prime * result + ((getExpire() == null) ? 0 : getExpire().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getFeeType() == null) ? 0 : getFeeType().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getErrorMsg() == null) ? 0 : getErrorMsg().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getProfit() == null) ? 0 : getProfit().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", paymentWay=").append(paymentWay);
        sb.append(", externalId=").append(externalId);
        sb.append(", transacId=").append(transacId);
        sb.append(", billNumber=").append(billNumber);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", amount=").append(amount);
        sb.append(", requestDate=").append(requestDate);
        sb.append(", expire=").append(expire);
        sb.append(", memo=").append(memo);
        sb.append(", feeType=").append(feeType);
        sb.append(", fee=").append(fee);
        sb.append(", status=").append(status);
        sb.append(", extra=").append(extra);
        sb.append(", errorMsg=").append(errorMsg);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", profit=").append(profit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}