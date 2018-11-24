package com.fullcrum.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fullcrum.service.sys.PaymentService;

@RestController
@RequestMapping("/ppp/YOP")
public class YOPCallBackController {

	@Resource(name="yopPaymentServiceImpl")
	PaymentService yop ;
	
	@RequestMapping(value="/payCallBack" ,method=RequestMethod.GET)
	public void  payCallBack(@RequestParam String response,@RequestParam String customerIdentification, HttpServletResponse response2 ) {
		if (yop.onPaySuccess(response, customerIdentification).equals("SUCCESS")) {
			try {
				response2.getWriter().write("SUCCESS");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
