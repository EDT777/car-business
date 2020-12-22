package com.edt.web.controller;

import com.edt.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private IBusinessService businessService;

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("business",businessService.selectMainStore());
        model.addAttribute("businesses",businessService.listAll());
        return "index";
    }
}
