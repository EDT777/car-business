package com.edt.mapper;

import com.edt.domain.Salary;
import com.edt.qo.QueryObject;

import java.util.List;

public interface SalaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Salary record);

    Salary selectByPrimaryKey(Long id);

    List<Salary> selectAll();

    int updateByPrimaryKey(Salary record);

    List<Salary> selectForList(QueryObject qo);
}