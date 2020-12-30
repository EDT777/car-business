package com.edt.web.controller;


import com.edt.domain.Employee;
import com.edt.domain.Notice;
import com.edt.enums.NoticeLevelEnum;
import com.edt.qo.NoticeQueryObject;
import com.edt.service.IEmployeeService;
import com.edt.service.INoticeService;
import com.edt.qo.JsonResult;
import com.edt.util.RequiredPermission;
import com.edt.util.UserContext;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") NoticeQueryObject qo) {
        PageInfo<Notice> pageInfo = noticeService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("levels", NoticeLevelEnum.values());
        model.addAttribute("currentUser", UserContext.getCurrentUser());
        model.addAttribute("totalUserNumber",employeeService.listAll().size()); //总共的后台员工人数
        return "notice/list";
    }


    @RequestMapping("/delete")
    @RequiredPermission(name = "公告删除", expression = "notice:delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            noticeService.delete(id);
        }
        return new JsonResult();
    }


    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "公告新增/修改", expression = "notice:saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Notice notice) {

        notice.setCreator(UserContext.getCurrentUser());
        if (notice.getId() != null) {
            noticeService.update(notice);
        } else {
            List<Long> ids = new ArrayList();
            notice.setCreateTime(new Date());
            List<Employee> lists = employeeService.listAll();
            for (Employee employee : lists) {
                ids.add(employee.getId());
            }
            noticeService.save(notice);
            noticeService.insertRelation(notice.getId(), ids);

        }
        return new JsonResult();
    }

    //查看公告
    @RequestMapping("/show")
    public String show(Model model, Long id, Long empId) {
        if (id != null) {
            noticeService.updateIsRead(id, empId);
            model.addAttribute("notice", noticeService.get(id));
            return "notice/show";
        } else {
            return "此公告有误!";
        }
    }

    //取消公告发布状态
    @RequestMapping("/removeStatus")
    @RequiredPermission(name = "公告取消发布", expression = "notice:saveOrUpdate")
    @ResponseBody
    public JsonResult removeStatus(Long id) {
        JsonResult jsonResult = new JsonResult();
        Employee currentUser = UserContext.getCurrentUser();
        if (!currentUser.isAdmin()) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("你不是管理员,未有此操作权限");
            return jsonResult;
        }
        if (id != null) {
            Notice notice = noticeService.get(id);
            notice.setStatus(false);
            noticeService.update(notice);
            return jsonResult;
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("抱歉,没有接收到公告的id值");
            return jsonResult;
        }

    }

    //公告发布
    @RequestMapping("/pushStatus")
    @RequiredPermission(name = "公告发布", expression = "notice:push")
    @ResponseBody
    public JsonResult pushStatus(Long id) {
        JsonResult jsonResult = new JsonResult();
        Employee currentUser = UserContext.getCurrentUser();
        if (!currentUser.isAdmin()) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("你不是管理员,未有此操作权限");
            return jsonResult;
        }
        if (id != null) {
            Notice notice = noticeService.get(id);
            notice.setStatus(true);
            noticeService.update(notice);
            return jsonResult;
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("抱歉,没有接收到公告的id值");
            return jsonResult;
        }

    }
}
