package com.edt.mapper;

import com.edt.domain.Consumption;
import com.edt.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsumptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Consumption record);

    Consumption selectByPrimaryKey(Long id);

    List<Consumption> selectAll();

    int updateByPrimaryKey(Consumption record);

    List<Consumption> selectForList(QueryObject qo);

    void updateConsumeByPrimaryKey(Consumption consumption);

    Long selectIdByCno(String cno);

    void updateStatus(@Param("id") Long id, @Param("status") Integer status);

    void updateAudit(Consumption consumption);
}