package com.edt.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.edt.domain.Appointment;
import com.edt.mapper.AppointmentMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IAppointmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;


    @Override
    public void save(Appointment appointment) {
        //        设置创建时间
        appointment.setCreateTime(new Date());
//        设置流水号
        String format = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        String numbers = RandomUtil.randomNumbers(5);
        appointment.setAno(format+numbers);
        appointmentMapper.insert(appointment);
    }

    @Override
    public void delete(Long id) {
        appointmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Appointment appointment) {
        appointmentMapper.updateByPrimaryKey(appointment);
    }

    @Override
    public Appointment get(Long id) {
        return appointmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Appointment> listAll() {
        return appointmentMapper.selectAll();
    }

    @Override
    public PageInfo<Appointment> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),"a.status,a.appointment_time"); //对下一句sql进行自动分页
        List<Appointment> appointments = appointmentMapper.selectForList(qo); //里面不需要自己写limit
        return new PageInfo<Appointment>(appointments);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        appointmentMapper.updateStatus(id,status);
    }

    @Override
    public Appointment selectByAno(String appointmentAno) {
        return appointmentMapper.selectByAno(appointmentAno);
    }
}
