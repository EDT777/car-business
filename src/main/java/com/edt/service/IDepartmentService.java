package com.edt.service;


import com.edt.domain.Department;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    void save(Department department);
    void delete(Long id);
    void update(Department department);
    Department get(Long id);
    List<Department> listAll();
//分页查询方法
     PageInfo<Department> query(QueryObject queryobject);

}