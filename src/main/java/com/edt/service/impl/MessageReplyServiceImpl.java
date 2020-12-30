package com.edt.service.impl;

import com.edt.domain.MessageBoard;
import com.edt.domain.MessageReply;
import com.edt.mapper.MessageBoardMapper;
import com.edt.mapper.MessageReplyMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IMessageReplyService;
import com.edt.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageReplyServiceImpl implements IMessageReplyService {

    @Autowired
    private MessageReplyMapper messageReplyMapper;
@Autowired
    private MessageBoardMapper messageBoardMapper;

    @Override
    public void save(Long boardId, MessageReply messageReply) {
        MessageBoard messageBoard = messageBoardMapper.selectByPrimaryKey(boardId);
        messageBoard.setReplystatus(true);
        messageBoardMapper.updateByPrimaryKey(messageBoard);
        messageReply.setMessage(messageBoard);
        messageReply.setReplyUser(UserContext.getCurrentUser());
        messageReply.setCreateTime(new Date());
        messageReplyMapper.insert(messageReply);
    }

    @Override
    public void delete(Long id) {
        messageReplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(MessageReply messageReply) {
        messageReplyMapper.updateByPrimaryKey(messageReply);
    }

    @Override
    public MessageReply get(Long id) {
        return messageReplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MessageReply> listAll() {
        return messageReplyMapper.selectAll();
    }

    @Override
    public PageInfo<MessageReply> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize()); //对下一句sql进行自动分页
        List<MessageReply> messageReplys = messageReplyMapper.selectForList(qo); //里面不需要自己写limit
        return new PageInfo<MessageReply>(messageReplys);
    }

    @Override
    public List<MessageReply> selectByBoardId(Long id) {
        return messageReplyMapper.selectByBoardId(id);
    }
}
