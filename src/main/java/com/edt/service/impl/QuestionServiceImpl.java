package com.edt.service.impl;

import com.edt.domain.Question;
import com.edt.mapper.QuestionMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IQuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;


    @Override
    public void save(Question question) {
        questionMapper.insert(question);
    }

    @Override
    public void delete(Long id) {
        questionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Question question) {
        questionMapper.updateByPrimaryKey(question);
    }

    @Override
    public Question get(Long id) {
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Question> listAll() {
        return questionMapper.selectAll();
    }

    @Override
    public PageInfo<Question> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize()); //对下一句sql进行自动分页
        List<Question> questions = questionMapper.selectForList(qo); //里面不需要自己写limit
        return new PageInfo<Question>(questions);
    }
}
