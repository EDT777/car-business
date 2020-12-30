package com.edt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Setter
@Getter
@ToString
public class MessageReply {
    /** */
    private Long id;

    /** 回复内容*/
    private String content;

    /** 所属留言*/
    private MessageBoard message;

    /** 回复人*/
    private Employee replyUser;

    /** 回复时间*/
    private Date createTime;

}