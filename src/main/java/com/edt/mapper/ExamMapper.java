package com.edt.mapper;

import com.edt.domain.Exam;
import com.edt.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Exam record);

    Exam selectByPrimaryKey(Long id);

    List<Exam> selectAll();

    int updateByPrimaryKey(Exam record);

    List<Exam> selectForList(QueryObject qo);

    List<Long> selectSingleIds(int singleNum);

    List<Long> selectMultipleIds(int singleNum);
    List<Long> selectJudgeIds(int singleNum);

    void insertRelation(@Param("id") Long id, @Param("questionId") Long questionId, @Param("sequence") int sequence, @Param("score") int score);
}