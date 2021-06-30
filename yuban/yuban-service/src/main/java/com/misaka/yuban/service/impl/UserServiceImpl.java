package com.misaka.yuban.service.impl;

import com.misaka.yuban.common.dao.User;
import com.misaka.yuban.common.exception.BusinessException;
import com.misaka.yuban.dao.mapper.UserMapper;
import com.misaka.yuban.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new BusinessException("用户名不能为空");
        }
        return userMapper.selectByName(name);
    }

    @Override
    public User getUserById(Integer userId) {
        if (userId == null) {
            throw new BusinessException("用户id不能为空");
        }
        return userMapper.selectById(userId);
    }

    @Override
    public void saveUserIcon(User user, String icon) {
        if (StringUtils.isBlank(icon)) {
            throw new BusinessException("头像地址不能为空");
        }
        userMapper.saveUserIcon(user.getId(), icon);
    }
}
