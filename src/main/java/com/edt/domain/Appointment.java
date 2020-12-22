package com.edt.domain;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.edt.enums.AppointmentStatusEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;

@Setter
@Getter
@ToString
public class Appointment {

    /** 主键*/
    private Long id;

    /** 预约单流水号*/
    private String ano;

    /** 预约单状态 （预约中/履行中/消费中/归档/废弃单）*/
    private Integer status;

    /** 业务大类*/
    private SystemDictionaryItem category;

    /** 预约说明*/
    private String info;

    /** 联系电话*/
    private String contactTel;

    /** 联系人名称*/
    private String contactName;

    /** 预约门店*/
    private Business business;

    /** 创建时间*/
    private Date createTime;

    /** 预约时间*/
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm")
    private Date appointmentTime;

//    给前端显示状态信息
    public String getStatusName(){
        return AppointmentStatusEnum.findName(status);
    }

    public String toJson(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("appointmentTime", DateUtil.format(appointmentTime,"yyyy-MM-dd HH:mm"));
        map.put("info",info);
        map.put("contactTel",contactTel);
        map.put("contactName",contactName);
        if(category!=null){
            map.put("categoryId",category.getId());
        }
        if(business!=null){
            map.put("businessId",business.getId());
        }
        return JSON.toJSONString(map);
    }
}