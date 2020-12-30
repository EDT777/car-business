package com.edt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class Question {
    /** */
    private Long id;

    /** 标题*/
    private String title;

    /** 类型(单选/多选/判断)*/
    private Integer type;

    /** 判断正确(针对判断题才有此项)*/
    private Boolean judgeRight;

    private Exam exam;

    private int score;

    private List<QuestionItem> items = new ArrayList<>();
}