package com.orange.msg.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常统一处理
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    @ResponseBody
    public Object formatExp(HttpServletRequest request, Exception ex){
        Map map = new HashMap<>();
        map.put("status",10000);
        map.put("msg",ex.getMessage());
        return map;
    }
}
