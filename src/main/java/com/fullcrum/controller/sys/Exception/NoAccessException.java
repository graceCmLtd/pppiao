package com.fullcrum.controller.sys.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "无权访问，请使用正确的角色登录")
public class NoAccessException  extends Exception{
}
