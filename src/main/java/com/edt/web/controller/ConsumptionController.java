package com.edt.web.controller;


import com.edt.domain.Consumption;
import com.edt.qo.ConsumptionQueryObject;
import com.edt.service.IBusinessService;
import com.edt.service.IConsumptionItemService;
import com.edt.service.IConsumptionService;
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
@RequestMapping("/consumption")
public class ConsumptionController {

    @Autowired
    private IConsumptionService consumptionService;



    @RequestMapping("/list")
    @RequiredPermission(name = "结算单页面", expression = "consumption:list")
    public String list(Model model, @ModelAttribute("qo") ConsumptionQueryObject qo) {
        PageInfo<Consumption> pageInfo = consumptionService.query(qo);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("businesses", businessService.listAll());
        return "consumption/list";
    }


    @RequestMapping("/delete")
    @RequiredPermission(name = "结算单删除", expression = "consumption:delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        if (id != null) {
            consumptionService.delete(id);
        }
        return new JsonResult();
    }


    @RequestMapping("/saveOrUpdate")
    @RequiredPermission(name = "结算单新增/修改", expression = "consumption:saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Consumption consumption) {
        if (consumption.getId() != null) {
            consumptionService.update(consumption);
        } else {
            consumptionService.save(consumption);
        }
        return new JsonResult();
    }

    @Autowired
    private IBusinessService businessService;
    @Autowired
    private IConsumptionItemService consumptionItemService;

    @RequiredPermission(name = "结算单新增/编辑(关联预约单)", expression = "consumption:saveOrUpdate")
    @RequestMapping("/input")
    public String input(Long id, Model model) {
        model.addAttribute("businesses", businessService.listAll());
        if (id != null) {//处理去请求修改
            Consumption consumption = consumptionService.get(id);
            model.addAttribute("consumption", consumption);
                //        根据结算单的流水号查询结算明细数据
                model.addAttribute("items",consumptionItemService.selectByCno(consumption.getCno()));
        }
        return "consumption/input";
    }

    @RequestMapping("/save")
    @RequiredPermission(name = "结算单新增(关联预约单)", expression = "consumption:save")
    @ResponseBody
    public JsonResult save(String appointmentAno) {
        Consumption consumption = consumptionService.save(appointmentAno);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(consumption.getId());
        return jsonResult;
    }

    @RequestMapping("/saveConsume")
    @RequiredPermission(name = "结算单结算", expression = "consumption:saveConsume")
    @ResponseBody
    public JsonResult saveConsume(Long id) {
        consumptionService.saveConsume(id);
        return new JsonResult();
    }

    @RequestMapping("/saveAudit")
    @RequiredPermission(name = "结算单审核", expression = "consumption:saveConsume")
    @ResponseBody
    public JsonResult saveAudit(Long id) {
        consumptionService.saveAudit(id);
        return new JsonResult();
    }

    @RequestMapping("/selectIdByCno")
    @ResponseBody
    public JsonResult selectIdByCno(String cno) {
        Long id = consumptionService.selectIdByCno(cno);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setId(id);
        return jsonResult;
    }

    @RequestMapping("/updateStatus")
    @ResponseBody
    public JsonResult updateStatus(Long id,Integer status) {
       consumptionService.updateStatus(id,status);
        return new JsonResult();
    }
}
