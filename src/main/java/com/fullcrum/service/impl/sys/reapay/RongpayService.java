package com.fullcrum.service.impl.sys.reapay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fullcrum.dao.PaymentDao;
import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.PaymentException;
import com.fullcrum.service.sys.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service(value = "rongpayService")
public class RongpayService implements PaymentService {


	@Autowired
	private PaymentDao paymentDao;

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
   public String pay(PaymentEntity entity) throws PaymentException {
      Map<String, String> sPara = new HashMap<String, String>();
      sPara.put("seller_email",ReapalWebConfig.seller_email);
      sPara.put("merchant_id",ReapalWebConfig.merchant_id);
      sPara.put("notify_url", ReapalWebConfig.notify_url);//后端服务器通知
      sPara.put("return_url", ReapalWebConfig.return_url);//前端页面跳转
      sPara.put("transtime", sdf.format(new java.util.Date()));
//    sPara.put("currency", "156");融宝仅支持默认值人民币
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

		String batch_no = jsonObject.getString("batch_no").trim();
		String batch_count = jsonObject.getString("batch_count").trim();
		String batch_amount = jsonObject.getString("batch_amount").trim();
		String pay_type = jsonObject.getString("pay_type").trim();
		String content = jsonObject.getString("content").trim();

		//AgentPayRequest agentPayRequest = new AgentPayRequest();
		Map<String, String> map = new HashMap<String, String>(0);
		map.put("charset", ReapalUtil.getCharset());
		map.put("trans_time",
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		map.put("notify_url", ReapalUtil.getNotify_url());
		map.put("batch_no", batch_no);
		map.put("batch_count", batch_count);
		map.put("batch_amount", batch_amount);
		map.put("pay_type", pay_type);
		map.put("content", content);

		String mysign = ReapalUtil.BuildMysign(map, ReapalUtil.getKey());

		System.out.println("签名结果==========>" + mysign);
		map.put("sign", mysign);
		map.put("sign_type", ReapalUtil.getSign_type());

		String json = JSON.toJSONString(map);

		Map<String, String> maps = null;
		try {
			maps = ReapalUtil.addkey(json);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		maps.put("merchant_id", ReapalUtil.getMerchant_id());
		maps.put("version", ReapalUtil.getVersion());
		System.out.println("maps==========>" + com.alibaba.fastjson.JSON.toJSONString(maps));

		String post = null;
		try {
			post = HttpClientUtil.post1(ReapalUtil.getUrl()
					+ "agentpay/pay", maps);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		String res = "";
		try {
			 res = ReapalUtil.pubkey(post);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Map<String,Object> result = new HashMap<>();
		PaymentEntity paymentEntity = 	paymentDao.selectByUniqueOrderId(jsonObject.getInteger("orderId"));
		if("".equals(res) && res != null && "SUCCESS".equals(paymentEntity.getStatus())){
			JSONObject jsStr = JSONObject.parseObject(res);
			try{
				if("0000".equals(jsStr.getString("result_code"))){

					result.put("result",res);
				}else{

				}
			}catch (Exception e){
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

			}

		}


		return result;
	}

	@Override
	public String refund(JSONObject jsonObject) {
		//商户号
		String merchant_id = jsonObject.getString("merchant_id");
		//版本
		String version = jsonObject.getString("version");
		//原订单号
		String orig_order_no = jsonObject.getString("orig_order_no");
		//退款金额
		String amount = jsonObject.getString("amount");
		//备注
		String note = jsonObject.getString("note");

		Map<String, String> map = new HashMap<String, String>();
		map.put("version", version);
		map.put("merchant_id", merchant_id);
		map.put("order_no", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		map.put("orig_order_no", orig_order_no);

		BigDecimal total_fee = new BigDecimal(amount.toString()).movePointRight(2);
		map.put("amount", total_fee.toString());
		map.put("note", note);

		String url_pre = ReapalWebConfig.rongpay_api;
		String url = url_pre+"/fast/refund";

		String post = null;
		try {
			post = ReapalSubmit.buildSubmit(map, merchant_id,url);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("返回结果post==========>" + post);

		//解密返回的数据
		try {
			String res = DecipherWeb.decryptData(post);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}


}
