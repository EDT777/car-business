package com.edt.mapper;

import com.edt.domain.Employee;
import com.edt.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    List<Employee> selectForList(QueryObject qo);
    void insertRelation(@Param("empId") Long empId, @Param("roleIds") Long[] roleIds);

    void deleteRelation(Long id);

    void batchDelete(@Param("ids") Long[] ids);

    void batchDeleteRelation(@Param("ids") Long[] ids);

    Employee selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Employee selectByUsername(String username);

    void updatePassword(Employee record);


    void updateAllacivity(Long id);
}