package com.misaka.yuban.service.impl;

import com.misaka.yuban.common.dao.User;
import com.misaka.yuban.dao.mapper.UserMapper;
import com.misaka.yuban.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String login(String username, String password) {
        User user = userMapper.selectByName(username);
        if (user != null && DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            if (StringUtils.isNotBlank(user.getToken()) && System.currentTimeMillis() < user.getTokenExpire()) {
                return user.getToken();
            }
            String originStr = username + System.currentTimeMillis() + password;
            String token = DigestUtils.md5DigestAsHex(originStr.getBytes());
            Long tokenExpire = System.currentTimeMillis() + 7 * 24 * 3600 * 1000L;
            userMapper.updateToken(user.getId(), token, tokenExpire);
            return token;
        }
        throw new RuntimeException("用户名或密码不正确");
    }

    @Override
    public String register(String username, String password) {
        User user = userMapper.selectByName(username);
        if (user != null) {
            throw new RuntimeException("账号已存在");
        }
        String originStr = username + System.currentTimeMillis() + password;
        String token = DigestUtils.md5DigestAsHex(originStr.getBytes());
        Long tokenExpire = System.currentTimeMillis() + 7 * 24 * 3600 * 1000L;
        userMapper.register(username, DigestUtils.md5DigestAsHex(password.getBytes()), token, tokenExpire);
        return token;
    }

    @Override
    public User getUserByToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return userMapper.selectTokenRecord(token);
    }
}
