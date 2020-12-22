package com.edt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class Role {
    private Long id;

    private String name;

    private String sn;

    private List<Permission> permissions = new ArrayList<>();
}