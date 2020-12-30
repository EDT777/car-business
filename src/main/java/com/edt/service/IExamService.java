package com.edt.service;

import com.edt.domain.Exam;
import com.edt.qo.ExamNumbers;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IExamService {
    Long save(ExamNumbers exam);
    void delete(Long id);
    void update(Exam exam);
    Exam get(Long id);
    List<Exam> listAll();
    // 分页查询的方法
    PageInfo<Exam> query(QueryObject qo);

}
