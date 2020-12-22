package com.edt.web.controller;


import com.edt.domain.Salary;
import com.edt.qo.SalaryQueryObject;
import com.edt.service.IEmployeeService;
import com.edt.service.ISalaryService;
import com.edt.qo.QueryObject;
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
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/list")
    @RequiredPermission(name = "工资页面", expression = "salary:list")
    public String list(Model model, @ModelAttribute("qo") SalaryQueryObject qo) {
        PageInfo<Salary> pageInfo = salaryService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("employees",employeeService.listAll());
        return "salary/list";
    }


    @RequestMapping("/delete")
    @RequiredPermission(name = "工资删除", expression = "salary:delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            salaryService.delete(id);
        }
        return new JsonResult();
    }


    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "工资新增/修改", expression = "salary:saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Salary salary) {
        if (salary.getId() != null) {
            salaryService.update(salary);
        } else {
            salaryService.save(salary);
        }
        return new JsonResult();
    }
}
