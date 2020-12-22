package com.edt.web.controller;


import com.edt.domain.ConsumptionItem;
import com.edt.qo.ConsumptionItemQueryObject;
import com.edt.service.IConsumptionItemService;
import com.edt.qo.QueryObject;
import com.edt.qo.JsonResult;
import com.edt.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consumptionItem")
public class ConsumptionItemController {

    @Autowired
    private IConsumptionItemService consumptionItemService;


    @RequestMapping("/list")
    @RequiredPermission(name = "结算明细页面", expression = "consumptionItem:list")
    public String list(Model model, @ModelAttribute("qo") ConsumptionItemQueryObject qo) {
        PageInfo<ConsumptionItem> pageInfo = consumptionItemService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        return "consumptionItem/list";
    }


    @RequestMapping("/delete")
    @RequiredPermission(name = "结算明细删除", expression = "consumptionItem:delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            consumptionItemService.delete(id);
        }
        return new JsonResult();
    }


    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "结算明细新增/修改", expression = "consumptionItem:saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(ConsumptionItem consumptionItem) {
        if (consumptionItem.getId() != null) {
            consumptionItemService.update(consumptionItem);
        } else {
            consumptionItemService.save(consumptionItem);
        }
        return new JsonResult();
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public JsonResult batchDelete(@RequestParam("ids[]") Long[] ids){
        if (ids!=null&&ids.length>0){
            consumptionItemService.batchDelete(ids);
            return new JsonResult();
        }
        return new JsonResult(false,"删除失败");

    }
}
