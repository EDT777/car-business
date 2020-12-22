package com.edt.service;

import com.edt.domain.Permission;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IPermissionService {
    void delete(Long id);
    void save(Permission permission);
    Permission get(Long id);
    List<Permission> listAll();
    void update(Permission permission);
    void reload();
    //分页查询方法
    PageInfo<Permission> query(QueryObject queryobject);

    List<String> selectByEmpId(Long id);
}
