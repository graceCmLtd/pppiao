package com.fullcrum.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;



public class PicValidateUtil {
	
	// validate charactor collection
	private static final char[] theChars = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
			   'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
			   'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
			   'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',  
			   'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	//amount of char
	private static final int SIZE = 4;
	
	//amount of interference lines
	private static final int LINES = 5;
	
	
	private static final int WIDTH =80;
	
	private static final int HEIGHT =40;
	
	private static final int FONT_SIZE  = 30;
	
	
	public static Object[] createImage() {
		
		StringBuffer  sb = new StringBuffer();
		
		//white image
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		
		//get brash
		Graphics  graphics = image.getGraphics();
		
		graphics.setColor(Color.LIGHT_GRAY);
		
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		
		Random random = new Random();
		
		for(int i = 0; i< SIZE ;i++) {
			//获取随机字符索引
			int  image_index = random.nextInt(theChars.length);
			
			//设置字体颜色
			graphics.setColor(getRandomColor());
			
			//设置字体大小
			graphics.setFont(new Font(null, Font.BOLD, FONT_SIZE));
			
			//画字符串
			graphics.drawString(theChars[image_index]+"", i*WIDTH / SIZE, HEIGHT *2 / 3);
			
			//记录字符
			sb.append(theChars[image_index]);

		}
		// draw interference lines 
		for(int i = 0;i< LINES; i++) {
			graphics.setColor(getRandomColor());
			
			graphics.drawLine(random.nextInt(WIDTH), random.nextInt(HEIGHT), random.nextInt(WIDTH), random.nextInt(HEIGHT));
		}
		
		JSONObject result = new JSONObject();
		System.out.println("the image  is  :............>>>>>>>>>>>>>>>>>>>");
		System.out.println(sb.toString());
		System.out.println(image);
		result.put("str", sb.toString());
		result.put("image", image.toString());
		
		return new Object[] {sb.toString(),image};
	}
	
	public static Color getRandomColor() {
		Random random = new Random();
		Color color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
		
		return color;
	}
	
	
	
	
}
