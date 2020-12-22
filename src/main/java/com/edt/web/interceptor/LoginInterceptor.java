package com.edt.web.interceptor;

import com.edt.domain.Employee;
import com.edt.util.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//登陆拦截器
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//       从session中获取用户信息
//        Object employee=request.getSession().getAttribute("USER_IN_SESSION");
        Employee employee = UserContext.getCurrentUser();
//       如果不存在,代表没有登录,跳转到登陆页面,不放行
        if (employee==null){
            response.sendRedirect("/login.html");
            return false;//不放行
        }
        return true;//放行
    }
}
