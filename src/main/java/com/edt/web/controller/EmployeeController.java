package com.edt.web.controller;

import com.edt.domain.Employee;
import com.edt.mapper.EmployeeMapper;
import com.edt.qo.EmployeeQueryObject;
import com.edt.qo.JsonResult;
import com.edt.service.IDepartmentService;
import com.edt.service.IEmployeeService;
import com.edt.service.IRoleService;
import com.edt.util.RequiredPermission;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private EmployeeMapper employeeMapper;
    private IDepartmentService departmentService;

    private IEmployeeService employeeService;

    @Autowired
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //    处理查询所有员工的请求,响应HTML
    @RequiredPermission(name = "员工页面", expression = "employee:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo) {
        model.addAttribute("pageInfo", employeeService.query(qo));
        model.addAttribute("departments", departmentService.listAll());
        return "employee/list";//WEB-INF/views/employee/list.jsp
    }

    //    处理查询删除员工请求
    @RequiredPermission(name = "员工删除", expression = "employee:delete")
    @RequestMapping("/delete")
    public String delete(Long id) {
        if (id != null) {
            employeeService.delete(id);
        }
        return "redirect:/employee/list";
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam("ids[]") Long[] ids) {
        Map<String, Object> map = new HashMap<>();
        if (ids != null && ids.length > 0) {
            employeeMapper.batchDelete(ids);
            employeeMapper.batchDeleteRelation(ids);
            map.put("msg", true);
            return map;
        }
        map.put("msg", false);
        return map;
    }

    //    处理去新增/修改的页面请求,有传入id则回显数据,否则则是新增操作不回显数据
    @RequiredPermission(name = "员工新增/编辑", expression = "employee:saveOrUpdate")
    @RequestMapping("/input")
    public String input(Long id, Model model) {
        model.addAttribute("roles", roleService.listAll());
        model.addAttribute("departments", departmentService.listAll());

        if (id != null) {
            model.addAttribute("employee", employeeService.get(id));
        }
        return "employee/input";
    }

    //    处理新增/修改请求
    @RequiredPermission(name = "员工新增/编辑", expression = "employee:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Employee employee, Long[] ids) {

        if (employee.getId() == null) {
            employeeService.save(employee, ids);
        } else {
            employeeService.update(employee, ids);
        }

        return new JsonResult();
    }

//    检查用户名是否存在
    @RequestMapping("/checkName")
    @ResponseBody
    public Map<String,Object> checkName(String username) {
        Map<String,Object> map = new HashMap<>();
        Employee employee = employeeService.selectByUsername(username);
            map.put("valid",employee==null);
            return map;
        }

    @RequiredPermission(name = "员工导出", expression = "employee:exportXls")
    @RequestMapping("/exportXls")
    public void exportXls(HttpServletResponse response, EmployeeQueryObject qo) throws IOException {
//        文件下载的响应头(让浏览器访问资源的时候以下载的方式打开,命名下载文件名)
        response.setHeader("Content-Disposition","attachment;filename=employee.xls");
        Workbook wb=employeeService.exportXls(qo);
        wb.write(response.getOutputStream());
    }

    @RequiredPermission(name = "员工导入", expression = "employee:importXls")
    @RequestMapping("/importXls")
    @ResponseBody
    public JsonResult importXls(MultipartFile file) throws IOException {
            employeeService.importXls(file);
            return new JsonResult();

    }

    @RequiredPermission(name = "员工状态", expression = "employee:status")
    @RequestMapping("/status")
    public String status(Long id){
        Employee employee = employeeService.get(id);
        employee.setStatus(!employee.isStatus());
        employeeMapper.updateByPrimaryKey(employee);
        return "redirect:/employee/list";
    }
}
