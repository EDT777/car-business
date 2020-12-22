package com.edt.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@ToString
@Setter
@Getter
public class Department {
    private Long id;

    private String name;

    private String sn;

    public String toJson(){//不能用get 要写成to 不然出事
//        转为标准的JSON格式字符串
        return JSON.toJSONString(this);
    }

}