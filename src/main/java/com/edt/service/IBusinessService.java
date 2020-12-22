package com.edt.service;


import com.edt.domain.Business;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IBusinessService {
    void save(Business business);
    void delete(Long id);
    void update(Business business);
    Business get(Long id);
    List<Business> listAll();
//分页查询方法
     PageInfo<Business> query(QueryObject queryobject);
//查询总店
    Business  selectMainStore();

}