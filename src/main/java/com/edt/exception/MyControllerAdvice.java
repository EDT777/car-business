package com.edt.exception;

import com.alibaba.fastjson.JSON;
import com.edt.qo.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
贴@ControllerAdvice注解后,代表该类是对控制器进行增强的
 */
@ControllerAdvice
public class MyControllerAdvice {

    /*
    @ExceptionHandler 代表该方法适用于处理某种异常
     */
    @ExceptionHandler(RuntimeException.class)
    public String handler(RuntimeException e, HttpServletResponse response, HandlerMethod method) {
        e.printStackTrace();
//        需要根据前端请求的方式来响应不同的结果
//        如果原本是需要返回视图的就可以返回error页面
//        如果原本需要返回JsonResult,现在也要返回JsonResult
        if (!method.hasMethodAnnotation(ResponseBody.class)) {//没贴返回json对象注解 则返回视图
            return "common/error";
        } else {
            JsonResult jsonResult = new JsonResult(false, "系统异常,请联系管理员");
            try {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().println(JSON.toJSONString(jsonResult));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 处理自定义异常
     */
    @ResponseBody
    @ExceptionHandler(LoginException.class)
    public JsonResult handler(LoginException e) {
        e.printStackTrace();
        return new JsonResult(false, e.getMessage());
    }
}