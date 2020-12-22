package com.edt.qo;

import cn.hutool.core.date.DateUtil;
import com.edt.enums.BusinessReportEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter
@Getter
@ToString
public class BusinessReportQueryObject extends QueryObject {
    private String groupType = BusinessReportEnum.CONSUME.getValue();//分组类型 默认按门店分组
    private Long businessId;//门店id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;//开始
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;//结束
    private Integer status;//结算单类型
    private Boolean ano;//预约类型

    public Date getEndDate(){
        if (endDate==null){
            return null;
        }
//
        return DateUtil.endOfDay(endDate);
    }
}
