package com.fullcrum.utils;

import io.goeasy.GoEasy;

public class GoEasyAPI {

    public void sendMessage(String channel,String message){
        GoEasy goEasy = new GoEasy("https://rest-hangzhou.goeasy.io","BC-a9752c0d240f407298d5346075fb6de4");
        System.out.println("saaaaaa"+message);
        goEasy.publish(channel,message);
        System.out.println("sss"+message);
    }
}
