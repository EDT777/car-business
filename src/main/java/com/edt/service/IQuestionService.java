package com.edt.service;

import com.edt.domain.Question;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IQuestionService {
    void save(Question question);
    void delete(Long id);
    void update(Question question);
    Question get(Long id);
    List<Question> listAll();
    // 分页查询的方法
    PageInfo<Question> query(QueryObject qo);
}
