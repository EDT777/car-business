package com.edt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;

@Setter
@Getter
@ToString
public class SystemLog {
    /** */
    private Long id;
    /** 操作的用户名称*/
    private String userName;

    /** 操作模块名称*/
    private String operationName;

    /** 操作的请求方法*/
    private String operationMethod;

    /** 访问参数*/
    private String operationParameters;

    /** 操作时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operationTime;

    /** ip地址*/
    private String ipAddress;

    /** 执行时长*/
    private Long operationDuration;

    /** 操作成功与否*/
    private Boolean operationResult;

    /** 操作错误信息*/
    private String errorInfo;

}