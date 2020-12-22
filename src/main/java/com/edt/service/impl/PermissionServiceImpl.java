package com.edt.service.impl;

import com.edt.domain.Permission;
import com.edt.mapper.PermissionMapper;
import com.edt.qo.QueryObject;
import com.edt.service.IPermissionService;
import com.edt.util.RequiredPermission;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void delete(Long id) {

    }

    @Override
    public void save(Permission permission) {

    }

    @Override
    public Permission get(Long id) {
        return null;
    }

    @Override
    public List<Permission> listAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public void update(Permission permission) {

    }

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;//爆红没关系,问题不大

    @Override
    public void reload() {
        //        第一种方法
        List<String> expressions = permissionMapper.selectAllExpression();//        查询所有的权限表达式
//        直接通过springmvc收集映射方法信息来获取我们每个方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        System.out.println(handlerMethods);
        Collection<HandlerMethod> handlermethods = handlerMethods.values();
        for (HandlerMethod method : handlermethods) {
            RequiredPermission methodAnnotation = method.getMethodAnnotation(RequiredPermission.class);
//其他步骤都相同
            if (methodAnnotation==null){
                continue;
            }
//            从注解中获取权限名称和权限表达式
            String expression = methodAnnotation.expression();
            String name = methodAnnotation.name();
//            判断数据库是否已经存在该权限表达式
            if (expressions.contains(expression)){
                continue;//若存在 则跳过
            }
//            把数据封装成权限对象
            Permission permission = new Permission();
            permission.setExpression(expression);
            permission.setName(name);
            permissionMapper.insert(permission);
            expressions.add(expression);
        }
    }

//        第二种方法
//        查询所有的权限表达式
//        List<String> expressions = permissionMapper.selectAllExpression();
//
////获取每个控制器对象,从spring容器中获取带有@Controller注解的bean (获取每个控制器对象)
//        Map<String, Object> map = ctx.getBeansWithAnnotation(Controller.class);
////       获取所有控制器对象
//        Collection<Object> controllers = map.values();
//        for (Object controller : controllers) {
//            Class<?> aClass = controller.getClass();
////            获取所有本类方法
//            Method[] methods = aClass.getDeclaredMethods();
//            for (Method method : methods) {
////                获取方法上自定义的权限注解
//                RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
////                判断方法上是否有此注解
//                if (annotation == null) {
//                    continue;//跳过这部分,继续循环
//                }
////从注解中获取权限名称和权限表达式
//                String expression = annotation.expression();
//                String name = annotation.name();
////                判断是否已经存在该权限表达式
//                if (expressions.contains(expression)) {
//                    continue;//跳过这部分,继续循环
//                }
////                把数据封装成权限对象
//                Permission permission = new Permission();
//                permission.setName(name);
//                permission.setExpression(expression);
////                插入到数据库
//                permissionMapper.insert(permission);
//                expressions.add(expression); //加回去此数组,避免遍历的时候出现问题导致数据库数据重复
//            }
//        }


    @Override
    public PageInfo<Permission> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());//开始分页(对下一个sql进行分页),传当前页和每页显示数量
        List<Permission> permissions = permissionMapper.selectForList(qo);
        return new PageInfo<>(permissions);

    }

    @Override
    public List<String> selectByEmpId(Long id) {
        return permissionMapper.selectByEmpId(id);
    }

}
