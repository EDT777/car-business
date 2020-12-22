package com.edt.web.controller;

import com.edt.domain.Role;
import com.edt.qo.QueryObject;
import com.edt.service.IPermissionService;
import com.edt.service.IRoleService;
import com.edt.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController {


    private IRoleService roleService;
    @Autowired
    public void setRole2Service(IRoleService roleService){
        this.roleService =roleService;
    }

//    处理查询所有角色的请求,响应HTML
@RequiredPermission(name="角色页面",expression="role:list")
    @RequestMapping("/list")
    public String list(Model model, QueryObject qo){

        model.addAttribute("pageInfo",roleService.query(qo));
                return "role/list";//WEB-INF/views/role/list.jsp
    }

//    处理查询删除角色请求
@RequiredPermission(name="角色删除",expression="role:delete")
    @RequestMapping("/delete")
    public String delete(Long id){
        if (id != null) {
            roleService.delete(id);
        }
        return "redirect:/role/list";
    }


//    处理保存请求
@RequiredPermission(name="角色新增/编辑",expression="role:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role,Long[] ids){
        if (role.getId() ==null){
            roleService.save(role,ids);
        }else {
            roleService.update(role,ids);
        }

            return "redirect:/role/list";
    }

    @Autowired
    private IPermissionService permissionService;

    @RequiredPermission(name="角色新增/编辑",expression="role:saveOrUpdate")
    @RequestMapping("/input")
    public String input(Long id, Model model) {
        model.addAttribute("permissions", permissionService.listAll());
        if (id != null) {
            model.addAttribute("role", roleService.get(id));
        }
        return "role/input";
    }

}
