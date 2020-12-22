package com.edt.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Setter
@Getter
@ToString
public class SystemDictionary {
    //主键
    private Long id;
    //别名
    private String sn;
    //标题
    private String title;
    //简介
    private String intro;

    public String toJson(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("sn",sn);
        map.put("title",title);
        map.put("intro",intro);
        return JSON.toJSONString(map);
    }
}