package com.misaka.yuban.controller;

import com.misaka.yuban.common.dao.User;
import com.misaka.yuban.common.model.UserInfo;
import com.misaka.yuban.common.response.BaseResponse;
import com.misaka.yuban.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户相关接口", description = "获取、编辑用户信息")
@RestController
@RequestMapping("/user")
public class UserController extends BaseMethod {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名")
    })
    @GetMapping("/info")
    public BaseResponse<UserInfo> getUserInfo() {
        User user = getLoginUser();
        return BaseResponse.success(new UserInfo(user));
    }

    @ApiOperation("保存用户头像")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "icon", value = "头像地址")
    })
    @PostMapping("/icon/save")
    public BaseResponse<Object> saveUserIcon(@RequestParam String icon) {
        userService.saveUserIcon(getLoginUser(), icon);
        return BaseResponse.success();
    }
}
