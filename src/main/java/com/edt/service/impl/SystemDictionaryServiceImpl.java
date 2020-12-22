package com.edt.service.impl;

import com.edt.domain.SystemDictionary;
import com.edt.mapper.SystemDictionaryMapper;
import com.edt.qo.QueryObject;
import com.edt.service.ISystemDictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {

    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Override
    public void save(SystemDictionary systemDictionary) {
        systemDictionaryMapper.insert(systemDictionary);
    }

    @Override
    public void delete(Long id) {
        systemDictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SystemDictionary systemDictionary) {
        systemDictionaryMapper.updateByPrimaryKey(systemDictionary);
    }

    @Override
    public SystemDictionary get(Long id) {

        return systemDictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionary> listAll() {
        return systemDictionaryMapper.selectAll();
    }

    @Override
    public PageInfo<SystemDictionary> query(QueryObject qo) {
//分页插件  不需要我们写count语句,它会自动生成
//分页插件  高查sql不需要我们自己写limt,它会在你原本的sql上自动拼接limit上去并执行
//分页插件  limit是插件自动计算参数并拼接上去,qo
//分页插件  提供PageInfo类,用于封装分页相关数据,不需要自己写PageResult类
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());//开始分页(对下一个sql进行分页),传当前页和每页显示数量
        List<SystemDictionary> systemDictionarys = systemDictionaryMapper.selectForList(qo);
        return new PageInfo<>(systemDictionarys);
    }
}
