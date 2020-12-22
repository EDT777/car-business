package com.edt.service;

import com.edt.domain.ConsumptionItem;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IConsumptionItemService {
    void save(ConsumptionItem consumptionItem);
    void delete(Long id);
    void update(ConsumptionItem consumptionItem);
    ConsumptionItem get(Long id);
    List<ConsumptionItem> listAll();
    // 分页查询的方法
    PageInfo<ConsumptionItem> query(QueryObject qo);

    List<ConsumptionItem> selectByCno(String cno);

    void batchDelete(Long[] ids);
}
