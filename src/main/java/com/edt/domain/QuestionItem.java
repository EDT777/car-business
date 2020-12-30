package com.edt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuestionItem {
    /** */
    private Long id;

    /** 标题*/
    private String title;

    /** 题目id*/
    private Question question;

    /** 是否正确*/
    private Boolean judgeRight;


}