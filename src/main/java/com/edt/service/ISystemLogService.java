package com.edt.service;

import com.edt.domain.SystemLog;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemLogService {
    void save(SystemLog systemLog);
    void delete(Long id);
    void update(SystemLog systemLog);
    SystemLog get(Long id);
    List<SystemLog> listAll();
    // 分页查询的方法
    PageInfo<SystemLog> query(QueryObject qo);
}
