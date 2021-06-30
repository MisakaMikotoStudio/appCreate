package com.misaka.yuban.handlerIntercepter;

import com.misaka.yuban.common.dao.User;
import com.misaka.yuban.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 登录验证
 */
@Component
public class LoginIntercepter implements HandlerInterceptor {

    /**
     * 忽略掉，不需要进行登录验证的接口
     */
    public static List<String> ignoreLoginUrls = Arrays.asList("/login", "/register");

    /**
     * 常用的http方法，在登录验证时，需要放过options这类请求
     */
    public static List<String> verifyMethods = Arrays.asList(HttpMethod.GET.toString(), HttpMethod.POST.toString(),
            HttpMethod.PUT.toString(), HttpMethod.DELETE.toString());

    @Autowired
    private LoginService loginService;

    public static ThreadLocal<User> CurrentUser = new ThreadLocal<>();

    //在Controller执行之前调用，如果返回false，controller不执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean inVerifyMethods = verifyMethods.stream().anyMatch(item -> item.equalsIgnoreCase(request.getMethod()));
        if (!inVerifyMethods) {
            return true;
        }
        if (ignoreLoginUrls.contains(request.getRequestURI())) {
            return true;
        }
        String token = request.getHeader("token");
        User user = loginService.getUserByToken(token);
        if (user != null && user.getTokenExpire() > System.currentTimeMillis()) {
            CurrentUser.set(user);
            return true;
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

    //controller执行之后，且页面渲染之前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        CurrentUser.remove();
    }

    //页面渲染之后调用，一般用于资源清理操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("---------afterCompletion--------");
    }
}