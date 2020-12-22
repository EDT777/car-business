package com.edt.service;

import com.edt.domain.Appointment;
import com.edt.domain.SystemDictionaryItem;
import com.edt.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IAppointmentService {
    void save(Appointment appointment);
    void delete(Long id);
    void update(Appointment appointment);
    Appointment get(Long id);
    List<Appointment> listAll();
    // 分页查询的方法
    PageInfo<Appointment> query(QueryObject qo);
//更新预约单状态
    void updateStatus(Long id, Integer status);

    Appointment selectByAno(String appointmentAno);
}
