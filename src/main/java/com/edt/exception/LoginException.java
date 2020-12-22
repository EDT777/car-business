package com.edt.exception;

/**
 * 自定义逻辑异常
 */
public class LoginException extends RuntimeException{

    public LoginException(String msg){
        super(msg);
    }
}
