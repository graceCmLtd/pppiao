package com.fullcrum.utils;

import org.springframework.stereotype.Component;

import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;

@Component
public class GoEasyAPI {

    public  void sendMessage(String channel,String message){
        GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io","BC-a9752c0d240f407298d5346075fb6de4");
        System.out.println("saaaaaa"+message);
        goEasy.publish(channel,message,new PublishListener() {
        	@Override
        	public void onSuccess() {
        		System.out.println("sendSuccess");
        	}
        	@Override
        	public void onFailed(GoEasyError error) {
        		System.out.println(error);
        	}
        });

        System.out.println("sss"+message + channel);
    }
    
    
}
