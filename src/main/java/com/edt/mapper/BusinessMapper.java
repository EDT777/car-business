package com.edt.mapper;

import com.edt.domain.Business;
import com.edt.qo.QueryObject;

import java.util.List;

public interface BusinessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Business record);

    Business selectByPrimaryKey(Long id);

    List<Business> selectAll();

    int updateByPrimaryKey(Business record);

    List<Business> selectForList(QueryObject qo);

    Business selectMainStore();

}