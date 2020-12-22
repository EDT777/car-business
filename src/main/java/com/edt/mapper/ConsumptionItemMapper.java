package com.edt.mapper;

import com.edt.domain.ConsumptionItem;
import com.edt.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsumptionItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ConsumptionItem record);

    ConsumptionItem selectByPrimaryKey(Long id);

    List<ConsumptionItem> selectAll();

    int updateByPrimaryKey(ConsumptionItem record);

    List<ConsumptionItem> selectForList(QueryObject qo);

    List<ConsumptionItem> selectByCno(String cno);

    void batchDelete(@Param("ids") Long[] ids);
}