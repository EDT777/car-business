package com.edt.service;

import com.edt.domain.Consumption;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IConsumptionService {
    void save(Consumption consumption);

    void delete(Long id);

    void update(Consumption consumption);

    Consumption get(Long id);

    List<Consumption> listAll();

    // 分页查询的方法
    PageInfo<Consumption> query(QueryObject qo);

    //传入预约单号 生成结算单
    Consumption save(String appointmentAno);

    void saveConsume(Long id);

    Long selectIdByCno(String cno);

    void updateStatus(Long id, Integer status);

    void saveAudit(Long id);
}
