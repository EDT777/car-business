package com.edt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class Employee {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String username;
    private Integer age;
    private boolean admin;
    private boolean status;
    private Department dept;
//角色集合
    List<Role> roles = new ArrayList<>();
}