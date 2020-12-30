package com.edt.web.controller;


import com.edt.domain.SystemLog;
import com.edt.qo.SystemLogQueryObject;
import com.edt.service.ISystemLogService;
import com.edt.qo.JsonResult;
import com.edt.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemLog")
public class SystemLogController {

    @Autowired
    private ISystemLogService systemLogService;

    @RequiredPermission(name = "日志管理页面", expression = "systemLog:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") SystemLogQueryObject qo) {
        PageInfo<SystemLog> pageInfo = systemLogService.query(qo);
        model.addAttribute("pageInfo", pageInfo);

        return "systemLog/list";
    }

    @RequestMapping("/error")
    @RequiredPermission(name = "日志管理异常测试", expression = "systemLog:error")
    public String list() {
        throw new RuntimeException("异常了兄弟");
    }

    @RequestMapping("/delete")
    @RequiredPermission(name = "日志管理删除", expression = "systemLog:delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            systemLogService.delete(id);
        }
        return new JsonResult();
    }


    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "日志管理新增/修改", expression = "systemLog:saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(SystemLog systemLog) {
        if (systemLog.getId() != null) {
            systemLogService.update(systemLog);
        } else {
            systemLogService.save(systemLog);
        }
        return new JsonResult();
    }
}
