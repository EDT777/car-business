package com.edt.service.impl;

import com.edt.domain.SystemLog;
import com.edt.mapper.SystemLogMapper;
import com.edt.qo.QueryObject;
import com.edt.service.ISystemLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemLogServiceImpl implements ISystemLogService {

    @Autowired
    private SystemLogMapper systemLogMapper;


    @Override
    public void save(SystemLog systemLog) {
        systemLogMapper.insert(systemLog);
    }

    @Override
    public void delete(Long id) {
        systemLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SystemLog systemLog) {
        systemLogMapper.updateByPrimaryKey(systemLog);
    }

    @Override
    public SystemLog get(Long id) {
        return systemLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemLog> listAll() {
        return systemLogMapper.selectAll();
    }

    @Override
    public PageInfo<SystemLog> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize()); //对下一句sql进行自动分页
        List<SystemLog> systemLogs = systemLogMapper.selectForList(qo); //里面不需要自己写limit
        return new PageInfo<SystemLog>(systemLogs);
    }
}
