package com.fullcrum.service.impl.sys.reapay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.PaymentException;
import com.fullcrum.service.sys.PaymentService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


public class RongpayService implements PaymentService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static String gateway=ReapalWebConfig.rongpay_api+"/web/portal";

	@Override
	public String onPaySuccess(String response, String customerIdentification) {
		return null;
	}

	/**
	 * 功能：构造表单提交HTML
	 * @return 表单提交HTML文本
	 */
	@Override
	public String pay(String payMethod, PaymentEntity entity) throws PaymentException {
		Map<String, String> sPara = new HashMap<String, String>();
		sPara.put("seller_email",ReapalWebConfig.seller_email);
		sPara.put("merchant_id",ReapalWebConfig.merchant_id);
		sPara.put("notify_url", ReapalWebConfig.notify_url);//后端服务器通知
		sPara.put("return_url", ReapalWebConfig.return_url);//前端页面跳转
		sPara.put("transtime", sdf.format(new java.util.Date()));
//		sPara.put("currency", "156");融宝仅支持默认值人民币
		sPara.put("member_ip", entity.getTerminalIp());
		//sPara.put("terminal_type", terminal_type);
		sPara.put("terminal_info", entity.getTerminalInfo() );
		sPara.put("sign_type", ReapalWebConfig.sign_type);
		sPara.put("order_no", ""+entity.getTransacId());
		sPara.put("title", entity.getBillNumber());
		sPara.put("body", entity.getBillNumber());
		//融宝金额以分为单位
		sPara.put("total_fee", ""+entity.getAmount().multiply(BigDecimal.valueOf(10)));
		//支付方式为银行间连时：值为1
        //支付方式为银行直连时：银行代码为B2B支付：
        //值为1
        //银行代码为B2C支付：
        //1借记卡支付，2贷记卡支付
        //采用银行间连接
		sPara.put("payment_type", "1");
		sPara.put("default_bank", "");
//        固定值
//        1. bankPay，银行间接支付，默认值；
//        2. directPay ，银行直连
        sPara.put("pay_method", "bankPay");

		String mysign = Md5Utils.BuildMysign(sPara, ReapalWebConfig.key);//生成签名结果

		sPara.put("sign", mysign);

		String json = JSON.toJSONString(sPara);

        Map<String, String> maps;
        try {
            maps = DecipherWeb.encryptData(json);
        } catch (Exception e) {
            throw PaymentException.newPaymentException(e);
        }

        StringBuffer sbHtml = new StringBuffer();


		//post方式传递
		sbHtml.append("<form id=\"rongpaysubmit\" name=\"rongpaysubmit\" action=\"").append(gateway).append("\" method=\"post\">");

		sbHtml.append("<input type=\"hidden\" name=\"merchant_id\" value=\"").append(ReapalWebConfig.merchant_id).append("\"/>");
		sbHtml.append("<input type=\"hidden\" name=\"data\" value=\"").append(maps.get("data")).append("\"/>");
		sbHtml.append("<input type=\"hidden\" name=\"encryptkey\" value=\"").append(maps.get("encryptkey")).append("\"/>");

		//submit按钮控件请不要含有name属性
		sbHtml.append("<input type=\"submit\" class=\"button_p2p\" value=\"融宝支付确认付款\"></form>");
		return sbHtml.toString();
	}

	@Override
	public Map<String, Object> confirm(JSONObject jsonObject) {
		return null;
	}
}
