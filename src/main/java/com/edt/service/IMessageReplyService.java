package com.edt.service;

import com.edt.domain.MessageReply;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IMessageReplyService {
    void save(Long boardId, MessageReply messageReply);
    void delete(Long id);
    void update(MessageReply messageReply);
    MessageReply get(Long id);
    List<MessageReply> listAll();
    // 分页查询的方法
    PageInfo<MessageReply> query(QueryObject qo);

    List<MessageReply> selectByBoardId(Long id);
}
