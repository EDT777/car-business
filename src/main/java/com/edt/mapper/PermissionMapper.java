package com.edt.mapper;

import com.edt.domain.Permission;
import com.edt.qo.QueryObject;

import java.util.List;

public interface PermissionMapper {

    int insert(Permission record);
    List<String> selectAllExpression();

    List<Permission> selectAll();
    Permission selectByPrimaryKey(Long id);
    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(Permission record);

    List<Permission> selectForList(QueryObject qo);

    List<Permission> selectByRoleId(Long roleId);

    List<String> selectByEmpId(Long empId);
}