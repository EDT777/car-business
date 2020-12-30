package com.edt.config;

import com.edt.domain.SystemLog;
import com.edt.service.ISystemLogService;
import com.edt.util.ParameterMapUtil;
import com.edt.util.RequiredPermission;
import com.edt.util.UserContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component //把当前类注入到spring容器中
@Aspect    //把当前类标识为一个切面供容器读取
public class AopConfig {

    @Autowired
    private ISystemLogService systemLogService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 指定切入点
     * 对贴有自定义注解@RequiredPermission的方法，进行切面
     */
    @Pointcut("@annotation(com.edt.util.RequiredPermission)")
    private void useMethod() {
    }

    /**
     * 对切入点进行增强
     */
    @Around(value = "useMethod()")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        if (UserContext.getCurrentUser().isAdmin()) {
            return proceedingJoinPoint.proceed();
        }

        SystemLog systemLog = new SystemLog();//日志管理对象
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
//        注解中用户操作方法的操作名
        String operationName = annotation.name();
        systemLog.setOperationName(operationName);//存入操作名
//      注解中用户操作方法的权限表达式(请求方法)
        String expression = annotation.expression();
        systemLog.setOperationMethod(expression);//存入请求方法
        systemLog.setOperationTime(new Date());//存入操作时间
        systemLog.setUserName(UserContext.getCurrentUser().getName());//存入当前用户名称
        if (ParameterMapUtil.getParameterMap(httpServletRequest).toString().equals("{}")) {
            systemLog.setOperationParameters("无");
        } else {
            systemLog.setOperationParameters(ParameterMapUtil.getParameterMap(httpServletRequest).toString());//存入请求参数
        }
        if (httpServletRequest.getRemoteAddr().equals("0:0:0:0:0:0:0:1")) {
            systemLog.setIpAddress("本机");//存入ip地址
        } else {
            systemLog.setIpAddress(httpServletRequest.getRemoteAddr());//存入ip地址
        }
        Object result = null;
        long startTime = System.currentTimeMillis();   //获取开始时间
        try {
            //执行处理方法（执行控制器中的方法）
            result = proceedingJoinPoint.proceed();
            systemLog.setOperationResult(true);

        } catch (Exception e)  {
            systemLog.setOperationResult(false);
            systemLog.setErrorInfo(e.toString());
            throw  e;
        } finally {
            long endTime = System.currentTimeMillis(); //获取结束时间
            systemLog.setOperationDuration(endTime - startTime);
            systemLogService.save(systemLog);
        }


        //result是方法的返回值，直接return回去就会返回给springmvc处理
        return result;
    }


}