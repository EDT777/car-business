package com.edt.enums;

import lombok.Getter;

@Getter
public enum BusinessReportEnum {

    CONSUME("门店", "b.name"),
    AUDIT("年", "DATE_FORMAT(c.create_time,'%y')"),
    FINISH("月", "DATE_FORMAT(c.create_time,'%y-%m')"),
    FAILURE("日", "DATE_FORMAT(c.create_time,'%y-%m-%d')");
    BusinessReportEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private String value;//数值
    private String name;//中文

    //    传入value值获取对应的name
    public static String findName(String value){
        BusinessReportEnum[] enums = BusinessReportEnum.values();
        for (BusinessReportEnum anEnum: enums){
            if (anEnum.value.equals(value)){
                return anEnum.getName();
            }
        }
        return null;
    }
}
