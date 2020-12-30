package com.edt.mapper;

import com.edt.domain.Question;
import com.edt.qo.QueryObject;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    Question selectByPrimaryKey(Long id);

    List<Question> selectAll();

    int updateByPrimaryKey(Question record);

    List<Question> selectForList(QueryObject qo);

    List<Question> selectSingles(Long examId);
    List<Question> selectMultiples(Long examId);
    List<Question> selectJudges(Long examId);
}