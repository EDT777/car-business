package com.edt.web.controller;

import com.edt.domain.Department;
import com.edt.qo.QueryObject;
import com.edt.service.IDepartmentService;
import com.edt.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private IDepartmentService departmentService;

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //    处理查询所有部门的请求,响应HTML
    @RequiredPermission(name = "部门页面", expression = "department:list")
    @RequestMapping("/list")
    public String list(Model model, QueryObject qo) {
        model.addAttribute("pageInfo", departmentService.query(qo));
        return "department/list";//WEB-INF/views/department/list.jsp
    }

    //    处理查询删除部门请求
    @RequiredPermission(name = "部门删除", expression = "department:delete")
    @RequestMapping("/delete")
    public String delete(Long id) {
        if (id != null) {
            departmentService.delete(id);
        }
        return "redirect:/department/list";
    }


    //    处理保存请求
    @RequiredPermission(name = "部门新增/编辑", expression = "department:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Department department) {
        if (department.getId() == null) {
            departmentService.save(department);
        } else {
            departmentService.update(department);
        }

        return "redirect:/department/list";
    }


}
