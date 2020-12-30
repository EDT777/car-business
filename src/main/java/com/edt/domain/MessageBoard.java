package com.edt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Setter
@Getter
@ToString
public class MessageBoard {
    /** */
    private Long id;

    /** 昵称*/
    private String nickname;

    /** 留言内容*/
    private String content;

    /** 留言时间*/
    private Date createTime;

    /** 业务大类*/
    private SystemDictionaryItem category;

    /** 业务小类*/
    private SystemDictionaryItem categoryItem;

    /** 回复状态(未回复/已回复)*/
    private Boolean replystatus;

}