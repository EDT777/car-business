package com.edt.web.controller;

import com.edt.qo.JsonResult;
import com.edt.qo.QueryObject;
import com.edt.service.IPermissionService;
import com.edt.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {


    private IPermissionService permissionService;

    @Autowired
    public void setPermission2Service(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    //    处理查询所有权限的请求,响应HTML
    @RequiredPermission(name = "权限页面", expression = "permission:list")
    @RequestMapping("/list")
    public String list(Model model, QueryObject qo) {

        model.addAttribute("pageInfo", permissionService.query(qo));
        return "permission/list";//WEB-INF/views/permission/list.jsp
    }


    //    处理权限加载请求
    @RequiredPermission(name = "权限加载", expression = "permission:reload")
    @RequestMapping("/reload")
    @ResponseBody
    public JsonResult reload() {
        try {
            permissionService.reload();
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"权限加载失败");
        }
    }


}
