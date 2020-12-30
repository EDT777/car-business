package com.edt.service;

import com.edt.domain.MessageBoard;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IMessageBoardService {
    void save(MessageBoard messageBoard);
    void delete(Long id);
    void update(MessageBoard messageBoard);
    MessageBoard get(Long id);
    List<MessageBoard> listAll();
    // 分页查询的方法
    PageInfo<MessageBoard> query(QueryObject qo);
}
