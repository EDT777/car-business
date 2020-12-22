package com.edt.service;


import com.edt.domain.SystemDictionary;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISystemDictionaryService {
    void save(SystemDictionary systemDictionary);
    void delete(Long id);
    void update(SystemDictionary systemDictionary);
    SystemDictionary get(Long id);
    List<SystemDictionary> listAll();
//分页查询方法
     PageInfo<SystemDictionary> query(QueryObject queryobject);

}