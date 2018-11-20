package com.fullcrum.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoginAuthorization {
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Pointcut("execution( public * com.fullcrum.controller.sys..*.*(..) )")
	public void LoginPointCut() {
		
	}
	
	
	@Before("LoginPointCut()")
	public void checkAuthorization(JoinPoint joinPoint) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpServletResponse response = requestAttributes.getResponse();
		System.out.println("do authorization check before receiving htttp request");
		System.out.println(request.getHeader("Authorization"));
		String authTicket = request.getHeader("Authorization");
		System.out.println(authTicket);

		System.out.println(request.getRequestURI());
		if (request.getRequestURI().equals("/ppp/login") || request.getRequestURI().equals("/ppp/loginBySms") || request.getRequestURI().equals("/ppp/admin/login") || request.getRequestURI().equals("/ppp/msg/getUserMsg")
				|| request.getRequestURI().equals("/ppp/resourceMarket/getAllInfo") || request.getRequestURI().equals("/ppp/resourceMarket/getCount") || request.getRequestURI().equals("/ppp/bills/filterbill")
				|| request.getRequestURI().equals("/ppp/getValidatePic") || request.getRequestURI().equals("/ppp/register")) {
			System.out.println("login page ......");
		}else if(authTicket == null) {
			System.out.println("authticket is null ............");
			try {
				response.getWriter().println("authnticket is nu");;
				response.sendError(602, "Authorization cannot be null");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (stringRedisTemplate.opsForValue().get(authTicket) != null) {
			stringRedisTemplate.expire(authTicket, 60, TimeUnit.SECONDS);
		}
		
		//System.out.println(request.getAuthType());

		/*try {
			response.sendError(666);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/*System.out.println(joinPoint.getTarget());
		System.out.println(joinPoint.getThis());
		System.out.println(request.getServletContext());
		System.out.println(request.getCookies());
		System.out.println(request.getPathInfo());
		System.out.println(request.getRequestedSessionId());
		System.out.println(request.getRequestURI());
		System.out.println(request.getHeaderNames());*/
		/*Enumeration<String> headn = request.getHeaderNames();
		while(headn.hasMoreElements()) {
			System.out.println(headn.nextElement());
		}*/
/*		Cookie [] cc = request.getCookies();
		
		for (int i = 0; i < cc.length; i++) {
			Cookie cookie = cc[i];
			System.out.println(cookie.getValue());
			System.out.println(cookie.getName());
		}
		
		System.out.println(request.getCookies());*/
	}
}
