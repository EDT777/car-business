package com.edt.web.controller;

import com.edt.domain.Employee;
import com.edt.mapper.EmployeeMapper;
import com.edt.qo.JsonResult;
import com.edt.service.IEmployeeService;
import com.edt.service.IPermissionService;
import com.edt.util.UserContext;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/userLogin")
    @ResponseBody
    public JsonResult login(String username, String password, HttpSession session) {
            Employee employee = employeeService.login(username, password);//验证登录,登陆失败则抛出异常
//          把用户信息存到session
            UserContext.setCurrentUser(employee);
            if (!employee.isAdmin()) {
//                查询该用户拥有的权限信息(集合表达式)
                List<String> stringList = permissionService.selectByEmpId(employee.getId());
//            把权限信息存到session
                UserContext.setPremissions(stringList);
            }
//
        if (employee.isStatus()==false){
            return new JsonResult(false,"此用户已被禁用");
        }
            return new JsonResult();

    }

    @RequestMapping("/userLogout")
    public String logout(HttpSession session) {
        session.invalidate();//销毁当前session
        return "redirect:/login.html";
    }

    @RequestMapping("/updatePassword")
       @ResponseBody
    public JsonResult updatePassword(String password,String newPassword,HttpSession session){
        Employee employee = (Employee) session.getAttribute(UserContext.USER_IN_SESSION);
        if (!(employee.getPassword().equals(password))){
            return new JsonResult(false,"原密码有误");
        }
        employee.setPassword(newPassword);
        employeeMapper.updatePassword(employee);
        return new JsonResult();
    }


    @RequestMapping("/nopermission")
    public String noPermission() {
        return "common/nopermission";
    }
}
