package com.edt.mapper;

import com.edt.domain.Department;
import com.edt.qo.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);


    List<Department> selectForList(QueryObject qo);

    Department selectByName(String name);
}