package com.edt.qo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@ToString
public class SystemLogQueryObject extends QueryObject {
    /** 操作的用户名称*/
    private String userName;
    /** 操作的请求方法*/
    private String operationMethod;
    /** ip地址*/
    private String ipAddress;
    /** 操作成功与否*/
    private Boolean operationResult;
    /** 开始操作时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /** 结束操作时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
