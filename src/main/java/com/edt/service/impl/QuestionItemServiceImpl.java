package com.edt.service.impl;

import com.edt.domain.QuestionItem;
import com.edt.mapper.QuestionItemMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IQuestionItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionItemServiceImpl implements IQuestionItemService {

    @Autowired
    private QuestionItemMapper questionItemMapper;


    @Override
    public void save(QuestionItem questionItem) {
        questionItemMapper.insert(questionItem);
    }

    @Override
    public void delete(Long id) {
        questionItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(QuestionItem questionItem) {
        questionItemMapper.updateByPrimaryKey(questionItem);
    }

    @Override
    public QuestionItem get(Long id) {
        return questionItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<QuestionItem> listAll() {
        return questionItemMapper.selectAll();
    }

    @Override
    public PageInfo<QuestionItem> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize()); //对下一句sql进行自动分页
        List<QuestionItem> questionItems = questionItemMapper.selectForList(qo); //里面不需要自己写limit
        return new PageInfo<QuestionItem>(questionItems);
    }
}
