package com.edt.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Setter
@Getter
@ToString
public class SystemDictionaryItem {
    //主键
    private Long id;
    //标题
    private String title;
    //序号
    private Integer sequence;
    //所属目录
    private SystemDictionary type;
    //上级明细
    private SystemDictionaryItem parent;

    public String toJson(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("sequence",sequence);
        if(type!=null){
            map.put("typeId",type.getId());
        }
        if(parent!=null){
            map.put("parentId",parent.getId());
            map.put("parentTitle",parent.getTitle());
        }else {
            map.put("parentTitle","无");
        }
        return JSON.toJSONString(map);
    }
}