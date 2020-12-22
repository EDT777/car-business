package com.edt.enums;

import lombok.Getter;

@Getter
public enum ConsumptionStatusEnum {

    CONSUME("待结算", 0),
    AUDIT("待审核", 1),
    FINISH("归档", 2),
    FAILURE("坏账", 3);

    private Integer value;
    private String name;

    ConsumptionStatusEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static String findName(Integer value) {
        ConsumptionStatusEnum[] enums = ConsumptionStatusEnum.values();
        for (ConsumptionStatusEnum anEnum : enums) {
            if (anEnum.value == value) {
                return anEnum.getName();
            }
        }
        return null;
    }

}
