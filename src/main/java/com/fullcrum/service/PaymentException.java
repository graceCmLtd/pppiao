package com.fullcrum.service;

public class PaymentException extends RuntimeException {

    private String code ;  //异常对应的返回码
    private String msg;  //异常对应的描述信息

    public static PaymentException newPaymentException( Throwable cause){
        return new PaymentException("560", "发起支付失败，请联系客服",cause);
    }
    public static PaymentException newOffLineException(Throwable cause){
        return new PaymentException("561","第三方支付系统无法连接，请稍后重试",cause);
    }
    public static PaymentException newThirdPartyInterException(Throwable cause){
        return new PaymentException("562","第三方支付系统异常，请稍后重试",cause);
    }
    //无权限/未开通该服务/被冻结等
    public static PaymentException newInvalidOrderException(Throwable cause){
        return new PaymentException("563","无效订单，请联系客服",cause);
    }
    //参数格式不匹配等
    public static PaymentException newInvalidParamException(Throwable cause){
        return new PaymentException("564","无效参数，请联系客服",cause);
    }



    public PaymentException(String code, String msg, Throwable e) {
        super(e);
        this.code = code;
        this.msg = msg;
    }
    public PaymentException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
