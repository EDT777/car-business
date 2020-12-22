package com.edt.service.impl;

import com.edt.domain.SystemDictionaryItem;
import com.edt.mapper.SystemDictionaryItemMapper;
import com.edt.qo.QueryObject;
import com.edt.service.ISystemDictionaryItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService {

    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;

    @Override
    public void save(SystemDictionaryItem systemDictionaryItem) {
        systemDictionaryItemMapper.insert(systemDictionaryItem);
    }

    @Override
    public void delete(Long id) {
        systemDictionaryItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(SystemDictionaryItem systemDictionaryItem) {
        systemDictionaryItemMapper.updateByPrimaryKey(systemDictionaryItem);
    }

    @Override
    public SystemDictionaryItem get(Long id) {

        return systemDictionaryItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionaryItem> listAll() {
        return systemDictionaryItemMapper.selectAll();
    }

    @Override
    public PageInfo<SystemDictionaryItem> query(QueryObject qo) {
//分页插件  不需要我们写count语句,它会自动生成
//分页插件  高查sql不需要我们自己写limt,它会在你原本的sql上自动拼接limit上去并执行
//分页插件  limit是插件自动计算参数并拼接上去,qo
//分页插件  提供PageInfo类,用于封装分页相关数据,不需要自己写PageResult类
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());//开始分页(对下一个sql进行分页),传当前页和每页显示数量
        List<SystemDictionaryItem> systemDictionaryItems = systemDictionaryItemMapper.selectForList(qo);
        return new PageInfo<>(systemDictionaryItems);
    }

    @Override
    public List<SystemDictionaryItem> selectByTypeId(Long id) {
        return systemDictionaryItemMapper.selectByTypeId(id);
    }

    @Override
    public List<SystemDictionaryItem> selectByTypeSn(String sn) {
        return systemDictionaryItemMapper.selectByTypeSn(sn);
    }

    @Override
    public List<SystemDictionaryItem> selectByParentId(Long id) {
        return systemDictionaryItemMapper.selectByParentId(id);
    }
}
