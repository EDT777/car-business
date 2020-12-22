package com.edt.mapper;

import com.edt.domain.Appointment;
import com.edt.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Appointment record);

    Appointment selectByPrimaryKey(Long id);

    List<Appointment> selectAll();

    int updateByPrimaryKey(Appointment record);

    List<Appointment> selectForList(QueryObject qo);

    void updateStatus(@Param("id") Long id, @Param("status") Integer status);

    Appointment selectByAno(String appointmentAno);
}