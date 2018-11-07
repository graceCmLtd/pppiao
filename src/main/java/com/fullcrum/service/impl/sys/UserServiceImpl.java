package com.fullcrum.service.impl.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fullcrum.dao.LoginTicketDao;
import com.fullcrum.dao.UserDao;
import com.fullcrum.model.sys.LoginTicketEntity;
import com.fullcrum.model.sys.UserEntity;
import com.fullcrum.service.sys.UserService;
import com.mysql.jdbc.StringUtils;

@Service(value="userServiceImpl")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LoginTicketDao loginTicketDao;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	
	@Override
	public void insert(UserEntity userEntity) {
		// TODO Auto-generated method stub
		userDao.insertUser(userEntity);
		//userDao.insert(userEntity);
	}

	@Override
	public UserEntity getUserEntityByLoginName(String login_name) {
		// TODO Auto-generated method stub
		return userDao.getUserEntityByLoginName(login_name);
		
	}

	@Override
	public ArrayList<UserEntity> userList( String login_name,int pageSize, int start) {
		// TODO Auto-generated method stub
		System.out.println("in user list .???????????????????????????????????????????????????????????????????????????????????");
		System.out.println(userDao.userList(login_name, pageSize, start));
		return userDao.userList(login_name, pageSize, start);
	}

	
	//用户注册
	@Override
	public Map<String,String> register(String login_name,String user_phone, String passwd){
        Map<String,String> map = new HashMap<>();
       // Random random = new Random();
        
        System.out.println("register impl XXXXXXXXXXXXXXXXXXXXX");

        if (StringUtils.isEmptyOrWhitespaceOnly(user_phone)){
            map.put("msg","用户名不能为空");
            return map;
        }

        if (StringUtils.isEmptyOrWhitespaceOnly(passwd)){
            map.put("msg","密码不能为空");
            return map;
        }

        UserEntity u = userDao.getUserEntityByPhone(user_phone);
        if (u!=null){
            map.put("msg","用户名手机号已经被占用");
            return map;
        }

        

        UserEntity user = new UserEntity();
        String uuid = UUID.randomUUID().toString();
        user.setLogin_name(login_name);
        user.setUser_phone(user_phone);
        user.setUser_passwd(passwd);
        user.setUser_id(uuid);
        System.out.println("uuid~~:---------------------------------");
        System.out.println(user.getUser_id());
        //user.setSalt(UUID.randomUUID().toString().substring(0,5));
        //user.setHeadUrl(String.format("https://images.nowcoder.com/head/%dm.png",random.nextInt(1000)));
        //user.setPassword(JblogUtil.MD5(password+user.getSalt()));
        //user.setRole("user");
        userDao.insertUser(user);
        

        String ticket = addLoginTicket(user.getUser_phone());
        map.put("ticket",ticket);
        map.put("uuid", uuid);
        return map;
    }
	
	//用户免密码登录
	@Override
	public String addLoginTicket(String userId){
		   LoginTicketEntity loginTicketEntity = new LoginTicketEntity();
		    loginTicketEntity.setUserid(userId);// user_phone
		    Date date = new Date();
		    date.setTime(date.getTime()+1000*3600*30);
		    loginTicketEntity.setExpired(date);
		    loginTicketEntity.setStatus(0);
		    loginTicketEntity.setTicket(UUID.randomUUID().toString().replaceAll("-",""));

		    loginTicketDao.insertLoginTicket(loginTicketEntity);

		    return loginTicketEntity.getTicket();
		}
	
	//用户登录
	@Override
	public Map<String,String> login(String user_phone, String passwd){
        Map<String,String> map = new HashMap<>();
        Random random = new Random();
        if (StringUtils.isEmptyOrWhitespaceOnly(user_phone)){
            map.put("msg","手机号码不能为空");
            return map;
        }

        if (StringUtils.isEmptyOrWhitespaceOnly(passwd)){
            map.put("msg","密码不能为空");
            return map;
        }

        UserEntity u = userDao.getUserEntityByPhone(user_phone);
        if (u==null){
            map.put("msg","手机号码不存在");
            return map;
        }
        if (!passwd.equals(u.getUser_passwd())) {
			map.put("msg", "密码错误");
			return map;
		}
        /*if (!JblogUtil.MD5(password+u.getSalt()).equals(u.getPassword())){
            map.put("msg","密码错误");
            return map;
        }*/

        String ticket = addLoginTicket(u.getUser_id());
        System.out.println("ticket");
        System.out.println(ticket);
        map.put("ticket",ticket);
        map.put("uuid", u.getUser_id());

        return map;
    }

	@Override
	public UserEntity getUserEntityByPhone(String user_phone) {
		// TODO Auto-generated method stub
		return userDao.getUserEntityByPhone(user_phone);
	}

	@Override
	public Map<String, String> loginBySms(String user_phone, String Sms) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<>();
        Random random = new Random();
        if (StringUtils.isEmptyOrWhitespaceOnly(user_phone)){
            map.put("msg","手机号码不能为空");
            return map;
        }

        if (StringUtils.isEmptyOrWhitespaceOnly(Sms)){
            map.put("msg","验证码不能为空");
            return map;
        }

        UserEntity u = userDao.getUserEntityByPhone(user_phone);
        if (u==null){
            map.put("msg","手机号码不存在");
            return map;
        }
        if (!Sms.equals(stringRedisTemplate.opsForValue().get(user_phone))) {
			map.put("msg", "验证码错误");
			return map;
		}
        /*if (!JblogUtil.MD5(password+u.getSalt()).equals(u.getPassword())){
            map.put("msg","密码错误");
            return map;
        }*/

        String ticket = addLoginTicket(u.getUser_id());
        System.out.println("ticket");
        System.out.println(ticket);
        map.put("ticket",ticket);
        map.put("uuid", u.getUser_id());

        return map;
		
	}

    @Override
    public JSONObject updatePassword(JSONObject jsonObject) {
	    JSONObject msg = new JSONObject();
	    System.out.println("ffff"+jsonObject.getString("code"));
	    System.out.println(stringRedisTemplate.opsForValue().get(jsonObject.getString("phoneNum")));
        UserEntity user = getUserEntityByPhone(jsonObject.getString("phoneNum"));
	    if(user==null){
	        msg.put("msg","该用户不存在");
            return msg;
        }else if(StringUtils.isEmptyOrWhitespaceOnly(jsonObject.getString("code"))){
            msg.put("msg","验证码不能为空");
            return msg;
        }else if(!jsonObject.getString("code").equals(stringRedisTemplate.opsForValue().get(jsonObject.getString("phoneNum")))){
	        msg.put("msg","验证码错误");
            return msg;
        }else if(StringUtils.isEmptyOrWhitespaceOnly(jsonObject.getString("password"))){
            msg.put("msg","密码不能为空");
            return msg;
        }else{
            userDao.updatePassword(jsonObject);
            msg.put("msg",null);
            return msg;
        }

    }


}
