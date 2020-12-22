package com.edt.web.interceptor;

import com.edt.domain.Employee;
import com.edt.util.RequiredPermission;
import com.edt.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PermissionInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
// 从session中获取用户信息
//        Employee employee = (Employee) request.getSession().getAttribute(UserContext.USER_IN_SESSION);
        Employee employee = UserContext.getCurrentUser();
        if (employee!=null&&employee.isAdmin()){
            return true;//放行
        }
//         handler代表被拦截对象(可能是静态资源相关,也可能是处理方法)
//        强转为HandlerMethod(当前被拦截的处理方法)
        if (!(handler instanceof HandlerMethod)){//如果不是HandlerMethod类型,就直接放行
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;
//        获取方法上的注解
        RequiredPermission annotation = method.getMethodAnnotation(RequiredPermission.class);
        if (annotation==null){
            return true;//放行
        }
//        查询用户拥有的权限信息(权限表达式集合)
//        List<String> stringList = (List<String>) request.getSession().getAttribute(UserContext.PREMISSION_IN_SESSION);
        List<String> stringList = UserContext.getPermissions();
//        获取权限注解中的表达式
        String expression = annotation.expression();
//        判断集合中是否包含此表达式
        if (stringList.contains(expression)){
            return true;//放行
        }
//        request进行请求转发,不会经过视图解析器,导致ftl的指令没有办法解析
        response.sendRedirect("/nopermission");
        return false;
    }
}
