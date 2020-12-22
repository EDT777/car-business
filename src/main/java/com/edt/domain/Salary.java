package com.edt.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ToString
public class Salary {
    /** */
    private Long id;

    /** 工资金额*/
    private BigDecimal money;

    /** 年份*/
    private Integer year;

    /** 月份*/
    private Integer month;

    /** 员工*/
    private Employee employee;

    /** 发放方式*/
    private SystemDictionaryItem payout;

    public String toJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("year",year);
        map.put("month",month);
        map.put("money",money);
        map.put("employeeId",employee.getId());
        map.put("payoutId",payout.getId());
        return JSON.toJSONString(map);
    }
}