package com.edt.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.edt.domain.Appointment;
import com.edt.domain.Consumption;
import com.edt.domain.ConsumptionItem;
import com.edt.enums.AppointmentStatusEnum;
import com.edt.enums.ConsumptionStatusEnum;
import com.edt.mapper.ConsumptionMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IAppointmentService;
import com.edt.service.IConsumptionItemService;
import com.edt.service.IConsumptionService;
import com.edt.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class ConsumptionServiceImpl implements IConsumptionService {

    @Autowired
    private ConsumptionMapper consumptionMapper;
    @Autowired
    private IConsumptionItemService consumptionItemService;

    @Override
    public void save(Consumption consumption) {

        consumptionMapper.insert(consumption);
    }


    @Override
    public void delete(Long id) {
        Consumption consumption = consumptionMapper.selectByPrimaryKey(id);
//        删除绑定的所有结算明细
        String cno = consumption.getCno();
        List<ConsumptionItem> consumptionItems = consumptionItemService.selectByCno(cno);
        for (ConsumptionItem consumptionItem:consumptionItems){
            consumptionItemService.delete(consumptionItem.getId());
        }
//        删除结算单
        consumptionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Consumption consumption) {
        consumptionMapper.updateByPrimaryKey(consumption);
    }

    @Override
    public Consumption get(Long id) {
        return consumptionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Consumption> listAll() {
        return consumptionMapper.selectAll();
    }

    @Override
    public PageInfo<Consumption> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),"c.status,c.create_time"); //对下一句sql进行自动分页
        List<Consumption> consumptions = consumptionMapper.selectForList(qo); //里面不需要自己写limit
        return new PageInfo<Consumption>(consumptions);
    }

    @Autowired
    private IAppointmentService appointmentService;

    @Override
    public Consumption save(String appointmentAno) {

        Date date = new Date();
//        根据预约单流水号查询预约单对象
        Appointment appointment = appointmentService.selectByAno(appointmentAno);
//        修改预约单状态为消费中状态
        appointmentService.updateStatus(appointment.getId(), AppointmentStatusEnum.CONSUME.getValue());
//        创建结算单对象
       Consumption consumption =new Consumption();
//       设置结算单状态为待结算状态
        consumption.setStatus(ConsumptionStatusEnum.CONSUME.getValue());
//        把预约单流水号设置到结算单里面关联
        consumption.setAppointmentAno(appointmentAno);
//        生成结算单的流水号
//        设置流水号 年月日分秒+5位随机数组成
        String format = DateUtil.format(new Date(),"yyyyMMddHHmmss");
        String numbers = RandomUtil.randomNumbers(5);
        consumption.setCno(format+numbers);
//        从预约单中获取客户名称,并设置到结算单中
        consumption.setCustomerName(appointment.getContactName());
//        从预约单中获取客户联系方式,并设置到结算单中
        consumption.setCustomerTel(appointment.getContactTel());
//        从预约单中获取预约门店,并设置到结算单中
        consumption.setBusiness(appointment.getBusiness());
//        进店时间设置为当前时间
        consumption.setCheckinTime(date);
//        创建时间设置为当前时间
        consumption.setCreateTime(date);
//        创建人设置为当前登录用户(员工)
        consumption.setCreateUser(UserContext.getCurrentUser());
//        把结算单保存到数据库中
        consumptionMapper.insert(consumption);
        log.info("结算单业务: 生成流水号为{}的结算单,关联流水号为{}的预约单,该预约单的状态转为{}"
                ,format+numbers,appointmentAno,AppointmentStatusEnum.CONSUME.getName());
        return consumption;
    }


    @Override
    public void saveConsume(Long id) {
        Consumption consumption = consumptionMapper.selectByPrimaryKey(id);
        String appointmentAno = consumption.getAppointmentAno();
//        将对应的预约单状态改为归档
        Appointment appointment = appointmentService.selectByAno(appointmentAno);
        appointmentService.updateStatus(appointment.getId(),AppointmentStatusEnum.FINISH.getValue());

        consumption.setPayTime(new Date());
        consumption.setPayee(UserContext.getCurrentUser());
        consumption.setStatus(ConsumptionStatusEnum.AUDIT.getValue());
        consumptionMapper.updateConsumeByPrimaryKey(consumption);
    }

    @Override
    public Long selectIdByCno(String cno) {
        return consumptionMapper.selectIdByCno(cno);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        consumptionMapper.updateStatus(id,status);
    }

    @Override
    public void saveAudit(Long id) {
        Consumption consumption = consumptionMapper.selectByPrimaryKey(id);
        consumption.setAuditTime(new Date());
        consumption.setAuditor(UserContext.getCurrentUser());
        consumptionMapper.updateAudit(consumption);
    }
}
