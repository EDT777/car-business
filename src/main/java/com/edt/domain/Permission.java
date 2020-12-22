package com.edt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Permission {
//    主键
    private Long id;

    private String name;

    private String expression;

}