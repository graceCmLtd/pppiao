package com.fullcrum.controller.sys;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fullcrum.model.sys.CompanyEntity;
import com.fullcrum.model.sys.UserEntity;
import com.fullcrum.service.sys.CompanyService;
import com.fullcrum.service.sys.UserService;
import com.fullcrum.utils.PicValidateUtil;
import com.fullcrum.utils.SendSms;

import io.goeasy.GoEasy;

@RestController
@CrossOrigin
@RequestMapping("/ppp")
public class UserController {
	
	private Logger logger= LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userServiceImpl")
	private UserService userService;
	
	@Resource(name="companyServiceImpl")
	private CompanyService companyService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@CrossOrigin
	@PostMapping("/adduser")
	public String insert(@RequestBody UserEntity userEntity) {
		System.out.println("/user    xxxxxxxx");
		userService.insert(userEntity);
		logger.debug("insert userEntity.");
		return "success";
	}
	
	@CrossOrigin
	@RequestMapping("/getusers")
	 /*List<UserEntity>*/
	public ArrayList<UserEntity> getuser(@RequestParam("login_name") String login_name,@RequestParam("pageSize") int pageSize,@RequestParam("start") int start) {
		System.out.println("user list ..................................................................................................................");
		System.out.println(login_name);
		System.out.println(userService.userList(login_name,pageSize,start));
		return userService.userList(login_name,pageSize,start);
		
	}
	//登录成功后将ticket放入cookie，保存到客户端浏览器
	

	@RequestMapping("/login")
 /*   public String login(Model model, HttpServletResponse httpResponse,
                        @RequestParam(value="login_name",required=false) String login_name,@RequestParam(value="passwd" ,required=false) String passwd,@RequestParam(value = "next",required = false)String next){*/
	public JSONObject login(Model model, HttpServletResponse httpResponse,
            @RequestBody UserEntity userEntity){
		System.out.println("login_name");
        System.out.println(userEntity.getUser_phone());
        System.out.println(userEntity.getUser_passwd());
		//String next = "xx";
		JSONObject result  = new JSONObject();
		Map<String,String> map = userService.login(userEntity.getUser_phone(),userEntity.getUser_passwd());
		
		
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket",map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);
            result.put("uuid", map.get("uuid"));
            result.put("status", "success");
            result.put("ticket", map.get("ticket"));
            result.put("errorMsg", null);
            result.put("user_phone",userEntity.getUser_phone());
            /*System.out.println("on success////////////////");
            System.out.println(result);
            if (StringUtils.isEmptyOrWhitespaceOnly(next)){
                return result;
            }*/
            ArrayList<CompanyEntity> companyData = companyService.selectByContactsId(map.get("uuid"));
            System.out.println("login companydata :");
            System.out.println(companyData);
            if (companyData.isEmpty()) {
				result.put("CompanyAuthentication", false);
				result.put("role", "未审核");
			}else {
				result.put("CompanyAuthentication", true);
				result.put("role", companyData.get(0).getRole());
				
			}
            return result;
        }else {
            model.addAttribute("msg", map.get("msg"));
            result.put("uuid", map.get("uuid"));
            result.put("status", "fail");
            result.put("ticket", null);
            result.put("errorMsg", map.get("msg"));
            result.put("role", "未审核");
            /*System.out.println("on fail..............................");
            System.out.println(result);*/
            return  result;
        }
    }
	
	//验证码登录
	//param  user_phone   Sms 
	@RequestMapping("/loginBySms")
	public JSONObject loginBySms(Model model, HttpServletResponse httpResponse, @RequestBody JSONObject jsonObject) {
		
		System.out.println("login_name");
		//String next = "xx";
		JSONObject result  = new JSONObject();
		Map<String,String> map = userService.loginBySms(jsonObject.getString("user_phone"), jsonObject.getString("Sms"));
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket",map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);
            result.put("uuid", map.get("uuid"));
            result.put("status", "success");
            result.put("ticket", map.get("ticket"));
            result.put("errorMsg", null);
            result.put("user_phone",jsonObject.getString("user_phone"));
            /*System.out.println("on success////////////////");
            System.out.println(result);
            if (StringUtils.isEmptyOrWhitespaceOnly(next)){
                return result;
            }*/
            ArrayList<CompanyEntity> companyData = companyService.selectByContactsId(map.get("uuid"));
            System.out.println("login companydata :");
            System.out.println(companyData);
            if (companyData.isEmpty()) {
				result.put("CompanyAuthentication", false);
				result.put("role", "未审核");
			}else {
				result.put("CompanyAuthentication", true);
				result.put("role", companyData.get(0).getRole());
			}
            return result;
        }else {
            model.addAttribute("msg", map.get("msg"));
            result.put("uuid", map.get("uuid"));
            result.put("status", "fail");
            result.put("ticket", null);
            result.put("errorMsg", map.get("msg"));
            result.put("role", "未审核");
            /*System.out.println("on fail..............................");
            System.out.println(result);*/
            return  result;
        }
	}
	//注册成功后将凭证放入cookie，保存到客户端浏览器
	//@RequestMapping("/register")
/*    public String register(Model model, HttpServletResponse httpResponse,
                           @RequestParam String login_name, @RequestParam String passwd,@RequestParam(value = "next",required = false)String next){*/
/*	public String register(Model model, HttpServletResponse httpResponse,
            @RequestBody UserEntity userEntity){

        Map<String,String> map = userService.register(userEntity.getLogin_name(),userEntity.getUser_phone(),userEntity.getUser_passwd());
        String next = "xxxx";
        
        if (map.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket",map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);

            if (StringUtils.isEmptyOrWhitespaceOnly(next))
                return "redirect:"+next;
            else
                return "redirect:/";
        }else {
            model.addAttribute("msg",map.get("msg"));
            return "register";
        }
    }*/
	
	@RequestMapping("/getPhoneSms")
	public void getPhoneSms(@RequestBody JSONObject jsonObject) {
		
		System.out.println(jsonObject);
		//Integer string_code = SendSms.SendValidateMsgTo(jsonObject.getString("phone"));
		System.out.println(jsonObject.getString("phone"));
		Integer string_code = SendSms.SendValidateMsgTo(jsonObject.getString("phone"));
		stringRedisTemplate.opsForValue().set(jsonObject.getString("phone"), string_code.toString(), 300, TimeUnit.SECONDS);
		
	}
	
	
	@RequestMapping("/register")
	public JSONObject register(Model model, HttpServletResponse httpServletResponse,@RequestBody JSONObject jsonObject) {
		Map<String, String> map = null;
		
		try {
			 map = userService.register(jsonObject.getString("login_name"), jsonObject.getString("user_phone"), jsonObject.getString("user_passwd"));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception  .................");
			System.out.println(e);
		}
		System.out.println("get phone code .................................");
		//System.out.println(stringRedisTemplate.opsForValue().get(jsonObject.getString("user_phone")));
		
		//String next = "xxxx";
		JSONObject result = new JSONObject();
		String msgString = stringRedisTemplate.opsForValue().get(jsonObject.getString("user_phone"));
		System.out.println(msgString);
		System.out.println(jsonObject.getString("code"));
		if ( msgString.equals(jsonObject.getString("code"))) {
			System.out.println("phone code is validated .......");
			
	        if (map.containsKey("ticket")){
	            Cookie cookie = new Cookie("ticket",map.get("ticket"));
	            cookie.setPath("/");
	            httpServletResponse.addCookie(cookie);
	/*
	            if (StringUtils.isEmptyOrWhitespaceOnly(next))
	                return "redirect:"+next;
	            else
	                return "redirect:/";*/
	            result.put("status","success");
	            result.put("errorMsg", null);
	            return result;
	        }else {
	            model.addAttribute("msg",map.get("msg"));
	            //return "register";
	            result.put("status", "fail");
	            result.put("errorMsg", map.get("msg"));
	            return result;
	        }
		}else {
			result.put("status", "fail");
			result.put("errorMsg", "the phone sms is wrong");
			
			return result;
		}
		

	}
	
	@RequestMapping("/getValidatePic")
	public void getValidatePic(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		/*HttpSession session = request.getSession();
		System.out.println("output if seesion exist");
		System.out.println(session);
		request.getCookies();*/
		
		Object[] objects = PicValidateUtil.createImage();

		httpServletResponse.setContentType("image/png");
		try {
			OutputStream outputStream = httpServletResponse.getOutputStream();
			ImageIO.write((RenderedImage) objects[1], "png", outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("http session ...........................");
		System.out.println(httpServletResponse.getHeaderNames());

	}

	@RequestMapping("/updatePassword")
	public JSONObject updatePassword(@RequestBody JSONObject jsonObject){
		JSONObject res = new JSONObject();
		//String password = jsonObject.getString("password");
		//password = new BCryp
		try{
			JSONObject result = userService.updatePassword(jsonObject);
			if(result.getString("msg") == null){
				res.put("status","success");
				return res;
			}else{
				return result;
			}

		}catch (Exception e){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			res.put("status","failed");
			return res;
		}
	}
}
