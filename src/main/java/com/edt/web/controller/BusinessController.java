package com.edt.web.controller;

import com.edt.domain.Business;
import com.edt.qo.BusinessQueryObject;
import com.edt.service.IBusinessService;
import com.edt.util.FileUploadUtil;
import com.edt.util.RequiredPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/business")
public class BusinessController {

    private IBusinessService businessService;

    @Autowired
    public void setBusinessService(IBusinessService businessService) {
        this.businessService = businessService;
    }

    //    处理查询所有门店的请求,响应HTML
    @RequiredPermission(name = "门店页面", expression = "business:list")
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") BusinessQueryObject qo) {
        model.addAttribute("pageInfo", businessService.query(qo));
        return "business/list";//WEB-INF/views/business/list.jsp
    }

    //    处理查询删除门店请求
    @RequiredPermission(name = "门店删除", expression = "business:delete")
    @RequestMapping("/delete")
    public String delete(Long id) {
        if (id != null) {
            businessService.delete(id);
        }
        return "redirect:/business/list";
    }

    @Autowired
    private ServletContext servletContext;

    //    处理保存请求
    @RequiredPermission(name = "门店新增/编辑", expression = "business:saveOrUpdate")
    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Business business, MultipartFile file) throws Exception {
//        判断是否有上传文件
        if (file != null && file.getSize() > 0) {
            //          获取webapp的绝对路径
            String realPath = servletContext.getRealPath("/");
//        如果有则删除之前上传的文件
            if (StringUtils.hasText(business.getLicenseImg())){
                FileUploadUtil.deleteFile(realPath+business.getLicenseImg());
            }
//        然后把新文件保存到webapp/upload目录中
//        获取文件所在的地址 /upload/xxx.jpg
            String url = FileUploadUtil.uploadFile(file, realPath);
//        把地址设置到business对象里
            business.setLicenseImg(url);
        }
        if (business.getId() == null) {
            businessService.save(business);
        } else {
            businessService.update(business);
        }

        return "redirect:/business/list";
    }

    @RequiredPermission(name = "门店新增/编辑", expression = "business:saveOrUpdate")
    @RequestMapping("/input")
    public String input(Long id, Model model) {
        if (id != null) {
            model.addAttribute("business", businessService.get(id));
        }
        return "business/input";
    }

}
