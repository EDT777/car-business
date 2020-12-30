package com.edt.mapper;

import com.edt.domain.Role;
import com.edt.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectForList(QueryObject qo);

    List<Role>selectByEmpId(Long id);

    void insertRelation(@Param("roleId") Long roleId, @Param("ids") Long[] ids);

    void deleteRelation(Long roleId);

    void updateRole(@Param("id") Long id, @Param("roleId") long roleId);
}