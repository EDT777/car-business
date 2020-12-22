package com.edt.web.controller;

import com.edt.domain.SystemDictionaryItem;
import com.edt.qo.JsonResult;
import com.edt.qo.SystemDictionaryItemQueryObject;
import com.edt.service.ISystemDictionaryItemService;
import com.edt.service.ISystemDictionaryService;
import com.edt.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/systemDictionaryItem")
public class SystemDictionaryItemController {
@Autowired
    private ISystemDictionaryService systemDictionaryService;
    private ISystemDictionaryItemService systemDictionaryItemService;

    @Autowired
    public void setSystemDictionaryItemService(ISystemDictionaryItemService systemDictionaryItemService) {
        this.systemDictionaryItemService = systemDictionaryItemService;
    }

    //    处理查询所有字典明细的请求,响应HTML
    @RequiredPermission(name = "字典明细页面", expression = "systemDictionaryItem:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") SystemDictionaryItemQueryObject qo) {
        model.addAttribute("dictionaries",systemDictionaryService.listAll());//获取所有数据目录对象
        model.addAttribute("pageInfo", systemDictionaryItemService.query(qo));//根据过滤信息,获取所有数据明细对象
        return "systemDictionaryItem/list";//WEB-INF/views/systemDictionaryItem/list.jsp
    }

    //    处理查询删除字典明细请求
    @RequiredPermission(name = "字典明细删除", expression = "systemDictionaryItem:delete")
    @RequestMapping("/delete")
    public String delete(Long id) {
        if (id != null) {
            systemDictionaryItemService.delete(id);
        }
        return "redirect:/systemDictionaryItem/list";
    }


    //    处理保存请求
    @RequiredPermission(name = "字典明细新增/编辑", expression = "systemDictionaryItem:saveOrUpdate")
        @RequestMapping("/saveOrUpdate")
        @ResponseBody
        public JsonResult saveOrUpdate(SystemDictionaryItem systemDictionaryItem) {
            if (systemDictionaryItem.getId() == null) {
                systemDictionaryItemService.save(systemDictionaryItem);
            } else {
                systemDictionaryItemService.update(systemDictionaryItem);
            }

            return new JsonResult();
    }

    @RequestMapping("/selectByTypeId")
    @ResponseBody
    public List<SystemDictionaryItem> selectByTypeId(Long id) {
           List<SystemDictionaryItem> list =systemDictionaryItemService.selectByTypeId(id);
        return list;
    }

    @RequestMapping("/selectByTypeSn")
    @ResponseBody
    public List<SystemDictionaryItem> selectByTypeSn(String sn) {
        List<SystemDictionaryItem> list =systemDictionaryItemService.selectByTypeSn(sn);
        return list;
    }

    @RequestMapping("/selectByParentId")
    @ResponseBody
    public List<SystemDictionaryItem> selectByParentId(Long id) {
        return systemDictionaryItemService.selectByParentId(id);
    }

}
