package com.misaka.yuban.controller;


import com.misaka.yuban.common.response.BaseResponse;
import com.misaka.yuban.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录注册相关接口
 */
@RestController
@RequestMapping("")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @GetMapping("/login")
    public BaseResponse<String> login(@RequestParam("username") String username,
                                      @RequestParam("password") String password) {
        return BaseResponse.success(loginService.login(username, password));
    }

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @GetMapping("/register")
    public BaseResponse<String> register(@RequestParam("username") String username,
                                         @RequestParam("password") String password) {
        return BaseResponse.success(loginService.register(username, password));
    }
}
