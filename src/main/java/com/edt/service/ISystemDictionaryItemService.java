package com.edt.service;


import com.edt.domain.SystemDictionaryItem;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemDictionaryItemService {
    void save(SystemDictionaryItem systemDictionaryItem);
    void delete(Long id);
    void update(SystemDictionaryItem systemDictionaryItem);
    SystemDictionaryItem get(Long id);
    List<SystemDictionaryItem> listAll();
//分页查询方法
     PageInfo<SystemDictionaryItem> query(QueryObject queryobject);

    List<SystemDictionaryItem> selectByTypeId(Long id);

    List<SystemDictionaryItem> selectByTypeSn(String sn);

    List<SystemDictionaryItem> selectByParentId(Long id);
}