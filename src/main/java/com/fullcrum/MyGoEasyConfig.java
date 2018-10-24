package com.fullcrum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.goeasy.GoEasy;

@Configuration
public class MyGoEasyConfig {

	@Bean
	public GoEasy apiGoEasy() {
		System.out.println("apigoeasy .......................................");
		return new GoEasy("rest-hangzhou.goeasy.io", "BC-db95b6ba89f440f0a26596574183a467");
	}
}
