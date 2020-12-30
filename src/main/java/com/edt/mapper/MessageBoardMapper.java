package com.edt.mapper;

import com.edt.domain.MessageBoard;
import com.edt.qo.QueryObject;

import java.util.List;

public interface MessageBoardMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageBoard record);

    MessageBoard selectByPrimaryKey(Long id);

    List<MessageBoard> selectAll();

    int updateByPrimaryKey(MessageBoard record);

    List<MessageBoard> selectForList(QueryObject qo);
}