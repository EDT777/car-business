package com.edt.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodeGenetator {

    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        // 指定模板文件从何处加载的数据源，这里设置文件目录位置。
        cfg.setDirectoryForTemplateLoading(new File("templates"));
        //-----------------提供数据（需要手动修改）-------------
        System.out.println("1.请输入实体类的类名，注意使用驼峰");
        Scanner scanner = new Scanner(System.in);
        String domainName = scanner.nextLine(); //domain类名
        System.out.println("2.请输入实体类的中文名");
        String chinese = scanner.nextLine();
        Map root = new HashMap();
        root.put("chinese",chinese); //中文,用于权限注解
        //------------------------------------------------------
        root.put("capitalize",domainName); //大写开头
        root.put("uncapitalize", StringUtils.uncapitalize(domainName)); //小写开头
        String packageDir = "src/main/java/com/edt/"; //项目目录
        // //--------------------controller控制器---------------
        Template temp = cfg.getTemplate("controller.ftl");
        // 设置输出为新的文件
        Writer out = new OutputStreamWriter(new FileOutputStream(packageDir+"/web/controller/"+domainName+"Controller.java"));
        temp.process(root, out);
        out.flush();
        out.close();
        //--------------------service接口------------------------
        temp = cfg.getTemplate("Iservice.ftl");
        // 设置输出为新的文件
        out = new OutputStreamWriter(new FileOutputStream(packageDir+"/service/"+"I"+domainName+"Service.java"));
        temp.process(root, out);
        out.flush();
        out.close();
        //--------------------service实现类------------------------
        temp = cfg.getTemplate("service.ftl");
        // 设置输出为新的文件
        out = new OutputStreamWriter(new FileOutputStream(packageDir+"/service/impl/"+domainName+"ServiceImpl.java"));
        // 执行输出
        temp.process(root, out);
        out.flush();
        out.close();

        System.out.println("生成完毕...");
    }
}
