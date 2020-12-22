package com.edt.enums;

import lombok.Getter;

/**
 * 预约单状态枚举类
 */
@Getter
public enum AppointmentStatusEnum {

    PEND("待确认", 0),
    PERFORM("履行中", 1),
    CONSUME("消费中", 2),
    FINISH("归档", 3),
    FAILURE("废弃", 4);

    AppointmentStatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    private Integer value;//数值
    private String name;//中文

//    传入value值获取对应的name
    public static String findName(Integer value){
        AppointmentStatusEnum[] enums = AppointmentStatusEnum.values();
        for (AppointmentStatusEnum anEnum: enums){
            if (anEnum.getValue() == value){
                return anEnum.getName();
            }
        }
        return null;
    }
}
