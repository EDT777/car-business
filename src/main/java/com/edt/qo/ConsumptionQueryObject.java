package com.edt.qo;

import com.edt.domain.Business;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter
@Getter
@ToString
public class ConsumptionQueryObject extends QueryObject{
    /** 消费单状态 （待结算/待审核/归档/坏账）*/
    private Integer status;

    /** 消费门店*/
    private Business business;

//    预约类型
    private Integer type;

    /** 客户名称*/
    private String customerName;

    /** 客户联系方式*/
    private String customerTel;

    /** 关联预约单流水号*/
    private String appointmentAno;

    /** 结算单流水号*/
    private String cno;

    /** 结算时间start*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 结算时间end*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
