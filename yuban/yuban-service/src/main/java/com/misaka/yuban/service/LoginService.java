package com.misaka.yuban.service;

import com.misaka.yuban.common.dao.User;

/**
 * 登录注册相关服务
 */
public interface LoginService {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @return token
     */
    String login(String username, String password);


    /**
     * 用户注册
     * @param username 用户名
     * @param password 用户密码
     * @return token
     */
    String register(String username, String password);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    User getUserByToken(String token);
}
