package com.edt.qo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    private boolean success =true;//默认true
    private String msg;//用于返回错误信息的
    private Object data;//用于成功时返回数据给前端
    private Long id;
    public JsonResult(boolean success,String msg){
        this.success = success;
        this.msg = msg;
    }
}
