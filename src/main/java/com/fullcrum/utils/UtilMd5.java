package com.fullcrum.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UtilMd5 {

	
	public String encrypt(String rawPassword) {
		
		return new BCryptPasswordEncoder().encode(rawPassword);
		 
	}
	
	
	public Boolean matches(String rawPassword,String encodedPassword) {
			
		return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
	}
}
