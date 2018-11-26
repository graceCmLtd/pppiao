package com.fullcrum.service.impl.sys.reapay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fullcrum.dao.PaymentDao;
import com.fullcrum.model.sys.PaymentEntity;
import com.fullcrum.service.PaymentException;
import com.fullcrum.service.sys.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(value = "rongpayService")
public class RongpayService implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private static String gateway=ReapalWebConfig.rongpay_api+"/web/portal";

   //TODO 完善日志打印
   @Override
   public String onPaySuccess( JSONObject request) {
       String key = ReapalWebConfig.key;
       String merchantId = request.getString("merchant_id");
       String data = request.getString("data");
       String encryptkey = request.getString("encryptkey");
       //解析密文数据
       String decryData = null;
       try {
           decryData = DecipherWeb.decryptData(encryptkey,data);
       } catch (Exception e) {
           throw PaymentException.newThirdPartyInterException(e);
       }

       //获取融宝支付的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
       JSONObject jsonObject = JSON.parseObject(decryData);

       String merchant_id = jsonObject.getString("merchant_id");
       String trade_no = jsonObject.getString("trade_no");
       String order_no = jsonObject.getString("order_no");
       String total_fee = jsonObject.getString("total_fee");
       String status = jsonObject.getString("status");
       String result_code = jsonObject.getString("result_code");
       String result_msg = jsonObject.getString("result_msg");
       String sign = jsonObject.getString("sign");
       String notify_id = jsonObject.getString("notify_id");


       Map<String, String> map = new HashMap<String, String>();
       map.put("merchant_id", merchant_id);
       map.put("trade_no", trade_no);
       map.put("order_no", order_no);
       map.put("total_fee", total_fee);
       map.put("status", status);
       map.put("result_code", result_code);
       map.put("result_msg", result_msg);
       map.put("notify_id", notify_id);
       //将返回的参数进行验签
       String mysign = Md5Utils.BuildMysign(map, key);

       System.out.println("mysign:" + mysign);
       System.out.println("sign:" + sign);


       String verifyStatus = "";

       Integer txId = Integer.parseInt(order_no);
       //建议校验responseTxt，判读该返回结果是否由融宝发出
       if(mysign.equals(sign) ){

           //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
           if(status.equals("TRADE_FINISHED")){
               //支付成功，如果没有做过处理，根据订单号（out_trade_no）及金额（total_fee）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
               //一定要做金额判断，防止恶意窜改金额，只支付了小金额的订单。
               //如果有做过处理，不执行商户的业务程序
               PaymentEntity p = paymentDao.selectByTxId(txId);
               if (p == null){
                   throw PaymentException.newPaymentException(
                           new RuntimeException("第三方支付已成功支付订单，但没有在系统数据库查找到该订单[transac_id: "+"order_no"+"]!"));
               }else if(p.getAmount().movePointRight(2).compareTo(BigDecimal.valueOf(Integer.parseInt(total_fee))) != 0){
                   throw PaymentException.newPaymentException(
                           new RuntimeException("第三方支付已成功支付订单，但订单金额与数据库不一致[transac_id: "+"order_no"+", amount: "+p.getAmount().toString()+", reaPayAmount: "+total_fee+"]!"));
               }else {
                   p.setStatus(PaymentService.PAYMENT_STATUS_SUCCESS);
                   paymentDao.updateByPrimaryKey(p);
               }


           }else{
               PaymentEntity p = paymentDao.selectByTxId(txId);
               p.setStatus(PaymentService.PAYMENT_STATUS_REJECT);
               paymentDao.updateByPrimaryKey(p);
//               throw PaymentException.newPaymentException(
//                       new RuntimeException("第"));
               //打印失败日志
               //TODO 自动调用查询接口，写入失败原因
               System.out.printf("融宝调用支付失败，{merchant_id: %s, order_no: %s, sign: %s}", merchant_id, order_no, sign);
           }

           verifyStatus = "验证成功";
           //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

       }else{
           verifyStatus = "验证失败";
            //打印验证失败日志
       }
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

        //TODO 暂时使用全局锁
       synchronized (this){
           PaymentEntity p = paymentDao.selectByTxId(entity.getTransacId());
           if (p != null
                   && ((!PaymentService.PAYMENT_STATUS_REJECT.equals(p.getStatus()))
                   && (!PaymentService.PAYMENT_STATUS_CLOSED.equals(p.getStatus()))
                   && (!PaymentService.PAYMENT_STATUS_REPEALED.equals(p.getStatus()))
                   && (!PaymentService.PAYMENT_STATUS_REVOKED.equals(p.getStatus()))
                   && (!PaymentService.PAYMENT_STATUS_TIME_OUT.equals(p.getStatus())))
           ){
               throw PaymentException.newInvalidOrderException(new RuntimeException("重复付款"));
           }else if (p!=null){
               paymentDao.deleteByPrimaryKey(p.getId());
           }

           entity.setRequestDate(new java.util.Date());
           entity.setMemo("test memo");
           entity.setPaymentWay("reapal");
           //        entity.setExternalId();等待返回时可以得到融宝流水号
           //            entity.setExpire();
           entity.setStatus(PaymentService.PAYMENT_STATUS_SENDING);
           paymentDao.insert(entity);
       }


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
		}
		String res = "";
		try {
			 res = ReapalUtil.pubkey(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("".equals(res) && res != null){
			JSONObject jsStr = JSONObject.parseObject(res);
			if("0000".equals(jsStr.getString("result_code"))){
				try{

					System.out.println(jsStr);
				}catch (Exception e){
					System.out.println(e.getMessage());
				}
			}
		}
		Map<String,Object> result = new HashMap<>();
		result.put("result",res);
		return result;
	}

}
