package com.misaka.yuban.common.model;

import com.misaka.yuban.common.dao.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class UserInfo implements Serializable {

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户头像链接")
    private String icon;

    public UserInfo(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.icon = user.getIcon();
    }
}
