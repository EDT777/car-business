package com.edt.qo;

import cn.hutool.core.date.DateUtil;
import com.edt.domain.Employee;
import com.edt.util.UserContext;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@ToString
public class NoticeQueryObject extends QueryObject {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private Integer level;

    private Boolean seeRead;
//当前用户
    private Employee currentUser= UserContext.getCurrentUser();

    public Date getEndTime(){
        if (endTime==null){
            return null;
        }
        return DateUtil.endOfDay(endTime);
    }
}
