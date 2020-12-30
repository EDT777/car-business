package com.edt.web.controller;


import com.edt.domain.Appointment;
import com.edt.enums.AppointmentStatusEnum;
import com.edt.qo.AppointmentQueryObject;
import com.edt.service.IAppointmentService;
import com.edt.qo.QueryObject;
import com.edt.qo.JsonResult;
import com.edt.service.IBusinessService;
import com.edt.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;
    @Autowired
    private IBusinessService businessService;

    @RequestMapping("/list")
    @RequiredPermission(name = "预约单页面", expression = "appointment:list")
    public String list(Model model, @ModelAttribute("qo") AppointmentQueryObject qo) {
        PageInfo<Appointment> pageInfo = appointmentService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("enums", AppointmentStatusEnum.values());
        model.addAttribute("businesses", businessService.listAll());

        return "appointment/list";
    }


    @RequestMapping("/delete")
    @RequiredPermission(name = "预约单删除", expression = "appointment:delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            appointmentService.delete(id);
        }
        return new JsonResult();
    }

//后台使用
    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "预约单新增/修改", expression = "appointment:saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Appointment appointment) {
        if (appointment.getId() != null) {
            appointmentService.update(appointment);
        } else {
//            设置状态为履行中
            appointment.setStatus(AppointmentStatusEnum.PERFORM.getValue());
            appointmentService.save(appointment);
        }
        return new JsonResult();
    }

//    互联网首页所用,无权限限制(匿名访问)
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Appointment appointment) {
//        设置状态为待确认
        appointment.setStatus(AppointmentStatusEnum.PEND.getValue());
        appointmentService.save(appointment);
        return new JsonResult();
    }


    @RequestMapping("/updateStatus")
    @RequiredPermission(name = "预约单状态修改", expression = "appointment:updateStatus")
    @ResponseBody
    public JsonResult saveOrUpdate(Long id,Integer status) {
        appointmentService.updateStatus(id,status);
        return new JsonResult();
    }

}
