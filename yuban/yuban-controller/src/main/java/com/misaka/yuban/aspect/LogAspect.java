package com.misaka.yuban.aspect;

import com.alibaba.fastjson.JSON;
import com.misaka.yuban.common.dao.Log;
import com.misaka.yuban.common.dao.User;
import com.misaka.yuban.handlerIntercepter.LoginIntercepter;
import com.misaka.yuban.service.LogService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@Aspect
@Component
@Order(1)
public class LogAspect {

    @Pointcut("execution(* com.misaka.yuban.controller..*.*(..))")
    private void permissionCheck() {
    }

    @Autowired
    private LogService logService;

    @Around("permissionCheck()")
    public Object permissionCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Log log =new Log();
        //获取HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();
        //获取请求用户id
        User user = LoginIntercepter.CurrentUser.get();
        int userId = user == null ? 0 : user.getId();
        log.setUserId(userId);
        // 获取请求 URL
        String url = request.getRequestURL().toString();
        log.setUrl(url);
        //获取请求参数
        Object[] args = joinPoint.getArgs();
        log.setRequest(JSON.toJSONString(args));

        Object resObj;
        try {
            //执行切面方法并获取返回
            resObj = joinPoint.proceed();
            log.setResponse(JSON.toJSONString(resObj));
        } catch (Exception ex) {
            //获取异常信息
            log.setException(ex.getMessage() + getStackTrace(ex));
            long costTime = System.currentTimeMillis() - startTime;
            log.setTimeCost(costTime);
            logService.addLog(log);
            throw ex;
        }

        long costTime = System.currentTimeMillis() - startTime;
        log.setTimeCost(costTime);
        logService.addLog(log);
        return resObj;
    }

    /**
     * 获取异常栈信息
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
