package com.edt.mapper;

import com.edt.domain.MessageReply;
import com.edt.qo.QueryObject;

import java.util.List;

public interface MessageReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageReply record);

    MessageReply selectByPrimaryKey(Long id);

    List<MessageReply> selectAll();

    int updateByPrimaryKey(MessageReply record);

    List<MessageReply> selectForList(QueryObject qo);

    List<MessageReply> selectByBoardId(Long id);
}