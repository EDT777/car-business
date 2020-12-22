package com.edt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter
@Getter
@ToString
/**
 * 商铺门店
 */
public class Business {
    private Long id;

    private String name;

    private String intro;

    private String scope;

    private String tel;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date openDate;

    private String licenseImg;

    private String licenseNumber;

    private String legalName;

    private String legalTel;

    private String legalIdcard;

    private boolean mainStore;

}