package com.edt.mapper;

import com.edt.domain.SystemLog;
import com.edt.qo.QueryObject;

import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog record);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog record);

    List<SystemLog> selectForList(QueryObject qo);
}