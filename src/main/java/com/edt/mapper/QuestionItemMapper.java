package com.edt.mapper;

import com.edt.domain.QuestionItem;
import com.edt.qo.QueryObject;

import java.util.List;

public interface QuestionItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuestionItem record);

    QuestionItem selectByPrimaryKey(Long id);

    List<QuestionItem> selectAll();

    int updateByPrimaryKey(QuestionItem record);

    List<QuestionItem> selectForList(QueryObject qo);

    List<QuestionItem> selectByExamId(Long examId);

    List<QuestionItem> selectByQuestionId(Long questionId);

}