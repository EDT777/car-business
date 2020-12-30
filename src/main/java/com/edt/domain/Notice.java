package com.edt.domain;

import com.alibaba.fastjson.JSON;
import com.edt.enums.NoticeLevelEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@ToString
public class Notice {
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Employee creator;
    private Integer level;
    private boolean seeRead;
    private boolean status;
    private String content;
    //当前用户
    private Employee currentUser;

    //已读人数
    private Long readNumber;

    public String getLevelName() {
        return NoticeLevelEnum.findName(level);
    }

    public String toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("content", content);
        map.put("level", level);
        return JSON.toJSONString(map);
    }
}