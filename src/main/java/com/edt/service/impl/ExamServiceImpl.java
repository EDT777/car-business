package com.edt.service.impl;

import com.edt.domain.Exam;
import com.edt.qo.ExamNumbers;
import com.edt.mapper.ExamMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IExamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements IExamService {

    @Autowired
    private ExamMapper examMapper;


    @Override
    public Long save(ExamNumbers examNumbers) {
//        将试卷部分拿出来
        Exam exam = new Exam();
        exam.setTitle(examNumbers.getTitle());
        exam.setTotalScore(examNumbers.getTotalScore());
        exam.setExamMinute(examNumbers.getExamMinute());
//        新增试卷后 就会拥有新增的id值
        examMapper.insert(exam);

//        新增试卷_题目 中间表
//
        int count=1;//记录顺序
//        将单选题目的ids和此试卷绑定
        List<Long> singleIds = examMapper.selectSingleIds(examNumbers.getSingleNum());
        for (Long singleId:singleIds){
            examMapper.insertRelation(exam.getId(),singleId,count++,examNumbers.getSingleGrade());
        }
//        多选题目的ids和此试卷绑定
        List<Long> multipleIds = examMapper.selectMultipleIds(examNumbers.getMultipleNum());
        for (Long multipleId:multipleIds){
            examMapper.insertRelation(exam.getId(),multipleId,count++,examNumbers.getMultipleGrade());
        }
        //判断题目的ids和此试卷绑定
        List<Long> judgeIds = examMapper.selectJudgeIds(examNumbers.getJudgeNum());
        for (Long judgeId:judgeIds){
            examMapper.insertRelation(exam.getId(),judgeId,count++,examNumbers.getJudgeGrade());
        }

        return exam.getId();
    }

    @Override
    public void delete(Long id) {
        examMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Exam exam) {
        examMapper.updateByPrimaryKey(exam);
    }

    @Override
    public Exam get(Long id) {
        return examMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Exam> listAll() {
        return examMapper.selectAll();
    }

    @Override
    public PageInfo<Exam> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize()); //对下一句sql进行自动分页
        List<Exam> exams = examMapper.selectForList(qo); //里面不需要自己写limit
        return new PageInfo<Exam>(exams);
    }

}
