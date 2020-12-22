package com.edt.web.controller;


import com.edt.domain.${capitalize};
import com.edt.service.I${capitalize}Service;
import com.edt.qo.QueryObject;
import com.edt.qo.JsonResult;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/${uncapitalize}")
public class ${capitalize}Controller {

@Autowired
private I${capitalize}Service ${uncapitalize}Service;


@RequestMapping("/list")
@RequiredPermission(name = "${chinese}页面", expression = "${uncapitalize}:list")
public String list(Model model, @ModelAttribute("qo") QueryObject qo){
PageInfo<${capitalize}> pageInfo = ${uncapitalize}Service.query(qo);
model.addAttribute("pageInfo", pageInfo);
return "${uncapitalize}/list";
}


@RequestMapping("/delete")
@RequiredPermission(name = "${chinese}删除", expression = "${uncapitalize}:delete")
@ResponseBody
public JsonResult delete(Long id){
if (id != null) {
${uncapitalize}Service.delete(id);
}
return new JsonResult();
}


@RequestMapping("/saveOrUpdate")
@RequiredPermission(name = "${chinese}新增/修改", expression = "${uncapitalize}:saveOrUpdate")
@ResponseBody
public JsonResult saveOrUpdate(${capitalize} ${uncapitalize}){
if (${uncapitalize}.getId() != null) {
${uncapitalize}Service.update(${uncapitalize});
}else {
${uncapitalize}Service.save(${uncapitalize});
}
return new JsonResult();
}
}
