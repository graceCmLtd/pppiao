package com.fullcrum.controller.sys.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "无效的参数")
public class InvalidParamException extends Exception{
}
