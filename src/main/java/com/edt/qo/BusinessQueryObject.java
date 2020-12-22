package com.edt.qo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@ToString
public class BusinessQueryObject extends QueryObject {
    //   店铺名称
    private String name;
    //    经营访问
    private String scope;
    //    门店电话
    private String tel;
    //    法人名称
    private String legalName;
    //    开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startOpenDate;
    //    结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endOpenDate;
}
