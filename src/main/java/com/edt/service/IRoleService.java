package com.edt.service;


import com.edt.domain.Role;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRoleService {
    void save(Role role, Long[] ids);
    void delete(Long id);
    void update(Role role, Long[] ids);
    Role get(Long id);
    List<Role> listAll();
    //分页查询方法
    PageInfo<Role> query(QueryObject queryobject);

    void updateRole(Long id, long roleId);
}