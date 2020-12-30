package com.edt.service;

import com.edt.domain.QuestionItem;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IQuestionItemService {
    void save(QuestionItem questionItem);
    void delete(Long id);
    void update(QuestionItem questionItem);
    QuestionItem get(Long id);
    List<QuestionItem> listAll();
    // 分页查询的方法
    PageInfo<QuestionItem> query(QueryObject qo);
}
