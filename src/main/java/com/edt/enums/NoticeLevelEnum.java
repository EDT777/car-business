package com.edt.enums;

import lombok.Getter;

@Getter
public enum NoticeLevelEnum {
    CONSUME("普通", 0),
    AUDIT("重要", 1),
    FINISH("紧急", 2);

    private Integer value;
    private String name;

    NoticeLevelEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static String findName(Integer value) {
        NoticeLevelEnum[] enums = NoticeLevelEnum.values();
        for (NoticeLevelEnum anEnum : enums) {
            if (anEnum.value == value) {
                return anEnum.getName();
            }
        }
        return null;
    }
}
