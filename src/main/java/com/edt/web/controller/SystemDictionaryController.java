package com.edt.web.controller;

import com.edt.domain.SystemDictionary;
import com.edt.qo.QueryObject;
import com.edt.service.ISystemDictionaryService;
import com.edt.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/systemDictionary")
public class SystemDictionaryController {

    private ISystemDictionaryService systemDictionaryService;

    @Autowired
    public void setSystemDictionaryService(ISystemDictionaryService systemDictionaryService) {
        this.systemDictionaryService = systemDictionaryService;
    }

    //    处理查询所有字典目录的请求,响应HTML
    @RequiredPermission(name = "字典目录页面", expression = "systemDictionary:list")
    @RequestMapping("/list")
    public String list(Model model, QueryObject qo) {
        model.addAttribute("pageInfo", systemDictionaryService.query(qo));
        return "systemDictionary/list";//WEB-INF/views/systemDictionary/list.jsp
    }

    //    处理查询删除字典目录请求
    @RequiredPermission(name = "字典目录删除", expression = "systemDictionary:delete")
    @RequestMapping("/delete")
    public String delete(Long id) {
        if (id != null) {
            systemDictionaryService.delete(id);
        }
        return "redirect:/systemDictionary/list";
    }


    //    处理保存请求
    @RequiredPermission(name = "字典目录新增/编辑", expression = "systemDictionary:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(SystemDictionary systemDictionary) {
        if (systemDictionary.getId() == null) {
            systemDictionaryService.save(systemDictionary);
        } else {
            systemDictionaryService.update(systemDictionary);
        }

        return "redirect:/systemDictionary/list";
    }


}
