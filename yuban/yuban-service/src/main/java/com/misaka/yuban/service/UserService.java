package com.misaka.yuban.service;

import com.misaka.yuban.common.dao.User;

/**
 * 用户接口
 */
public interface UserService {

    /**
     * 根据用户id获取用户信息
     * @param userId 用户id
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 根据用户名获取用户信息
     * @param name 用户名
     * @return
     */
    User getUserByName(String name);

    /**
     * 保存用户头像信息
     * @param user 用户
     * @param icon 头像信息
     */
    void saveUserIcon(User user, String icon);
}
