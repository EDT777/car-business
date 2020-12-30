package com.edt.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class Exam {
    /**
     *
     */
    private Long id;

    /**
     * 试卷名称
     */
    private String title;

    /**
     * 考试时长
     */
    private Integer examMinute;

    /**
     * 试卷总分
     */
    private Integer totalScore;
    //单选题目
    private List<Question> singles = new ArrayList<>();
    //    多选题目
    private List<Question> multiples = new ArrayList<>();
    //    判断题目
    private List<Question> judges = new ArrayList<>();
}