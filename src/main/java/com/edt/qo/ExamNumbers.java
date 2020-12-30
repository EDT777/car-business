package com.edt.qo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExamNumbers {

    //    包装的试卷部分
    private Long id;
    private String title;
    private Integer examMinute;
    private Integer totalScore;

    //    单选题数量
    private int singleNum;
    //    单选题每题分数
    private int singleGrade;
    //    多选题数量
    private int multipleNum;
    //    多选题每题分数
    private int multipleGrade;
    //    判断题数量
    private int judgeNum;
    //    判断题每题分数
    private int judgeGrade;
}
