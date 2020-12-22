package com.edt.qo;

import com.edt.domain.Business;
import com.edt.domain.SystemDictionaryItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@ToString
public class AppointmentQueryObject extends QueryObject {
    private String ano;

    private SystemDictionaryItem category;

    private Integer status;

    private Business business;

    private String contactName;

    private String contactTel;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;
}
