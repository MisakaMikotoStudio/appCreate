package com.misaka.yuban.controller;

import com.misaka.yuban.common.dao.User;
import com.misaka.yuban.common.exception.BusinessException;
import com.misaka.yuban.handlerIntercepter.LoginIntercepter;
import com.misaka.yuban.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseMethod {

   /**
     * 获取当前登录用户
     * @return
     */
    public User getLoginUser() {
        return LoginIntercepter.CurrentUser.get();
    }
}
