package com.edt.service.impl;

import com.edt.domain.MessageBoard;
import com.edt.mapper.MessageBoardMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IMessageBoardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageBoardServiceImpl implements IMessageBoardService {

    @Autowired
    private MessageBoardMapper messageBoardMapper;


    @Override
    public void save(MessageBoard messageBoard) {
        messageBoard.setCreateTime(new Date());
        messageBoard.setReplystatus(false);
        messageBoardMapper.insert(messageBoard);
    }

    @Override
    public void delete(Long id) {
        messageBoardMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(MessageBoard messageBoard) {
        messageBoardMapper.updateByPrimaryKey(messageBoard);
    }

    @Override
    public MessageBoard get(Long id) {
        return messageBoardMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MessageBoard> listAll() {
        return messageBoardMapper.selectAll();
    }

    @Override
    public PageInfo<MessageBoard> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize()); //对下一句sql进行自动分页
        List<MessageBoard> messageBoards = messageBoardMapper.selectForList(qo); //里面不需要自己写limit
        return new PageInfo<MessageBoard>(messageBoards);
    }
}
