package com.misaka.yuban.common.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseDaoModel implements Serializable {

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * token
     */
    private String token;

    /**
     * token的过期时间，毫秒级时间戳
     */
    private Long tokenExpire;

    /**
     * 用户头像链接
     */
    private String icon;
}
