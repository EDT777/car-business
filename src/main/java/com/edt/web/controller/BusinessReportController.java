package com.edt.web.controller;

import com.alibaba.fastjson.JSON;
import com.edt.enums.BusinessReportEnum;
import com.edt.enums.ConsumptionStatusEnum;
import com.edt.qo.BusinessReportQueryObject;
import com.edt.service.IBusinessReportService;
import com.edt.service.IBusinessService;
import com.edt.util.RequiredPermission;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/businessReport")
public class BusinessReportController {

    @Autowired
    private IBusinessReportService businessReportService;
    @Autowired
    private IBusinessService businessService;

    @RequiredPermission(name = "门店收入报表页面", expression = "businessReport:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") BusinessReportQueryObject qo) {
        model.addAttribute("pageInfo", businessReportService.selectReport(qo));
        model.addAttribute("business", businessService.listAll());
        model.addAttribute("consumptionStatus", ConsumptionStatusEnum.values());
        model.addAttribute("businessReportEnums", BusinessReportEnum.values());
        return "businessReport/list";
    }

    @RequestMapping("/listBar")
    @RequiredPermission(name = "门店收入柱状图报表", expression = "businessReport:listBar")
    public String listBar(Model model, @ModelAttribute("qo") BusinessReportQueryObject qo) {
//        准备5个容器
        ArrayList<Object> groupTypeList = new ArrayList<>(); //分组类型
        ArrayList<Object> totalAmountList = new ArrayList<>(); //总消费金额
        ArrayList<Object> discountAmountTypeList = new ArrayList<>(); //总优惠金额
        ArrayList<Object> payAmountList = new ArrayList<>(); //总实收金额
        ArrayList<Object> numberList = new ArrayList<>(); //总订单数
//       获取分页查询的数据(不分页)
        qo.setPageSize(0);
        List<HashMap> list = businessReportService.selectReport(qo).getList();
//        遍历集合,获取每个hashmao
        list.forEach(hashMap -> {
            groupTypeList.add(hashMap.get("groupType"));
            totalAmountList.add(hashMap.get("totalAmount"));
            discountAmountTypeList.add(hashMap.get("discountAmount"));
            payAmountList.add(hashMap.get("payAmount"));
            numberList.add(hashMap.get("number"));
        });
        //要转为JsonString  数据才有""
        model.addAttribute("groupTypeList", JSON.toJSONString(groupTypeList));
        model.addAttribute("totalAmountList", JSON.toJSONString(totalAmountList));
        model.addAttribute("discountAmountTypeList", JSON.toJSONString(discountAmountTypeList));
        model.addAttribute("payAmountList", JSON.toJSONString(payAmountList));
        model.addAttribute("numberList", JSON.toJSONString(numberList));
        model.addAttribute("groupType",BusinessReportEnum.findName(qo.getGroupType()));
        return "/businessReport/listBar";
    }
}
