package com.edt.util;

import com.edt.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserContext {
    //    抽取常量session中使用的key值
    public static final String USER_IN_SESSION = "USER_IN_SESSION";
    public static final String PREMISSION_IN_SESSION = "PREMISSION_IN_SESSION";


    public static HttpSession getSession() {
        ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest().getSession();
    }

    public static void setCurrentUser(Employee employee) {
//把用户信息存到session
        getSession().setAttribute(UserContext.USER_IN_SESSION, employee);
    }

    public static void setPremissions(List<String> list) {
//把权限信息存到session
        getSession().setAttribute(UserContext.PREMISSION_IN_SESSION, list);
    }

    public static Employee getCurrentUser() {
//        session中取用户信息
        return (Employee) getSession().getAttribute(USER_IN_SESSION);
    }

    public static List<String> getPermissions() {
//        从session中取权限信息
        return (List<String>) getSession().getAttribute(PREMISSION_IN_SESSION);
    }


}
