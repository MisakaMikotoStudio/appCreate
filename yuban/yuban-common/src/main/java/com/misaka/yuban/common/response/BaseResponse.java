package com.misaka.yuban.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class BaseResponse<T> implements Serializable {
    @ApiModelProperty("错误码，0代表正常，其他代表异常")
    private Integer code;

    @ApiModelProperty("异常信息")
    private String msg;

    @ApiModelProperty("返回数据")
    private T data;

    public static <T> BaseResponse<T> success() {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = 0;
        response.msg = "ok";
        return response;
    }

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = 0;
        response.msg = "ok";
        response.data = data;
        return response;
    }

    public static <T> BaseResponse<T> fail(String msg) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = -1;
        response.msg = msg;
        return response;
    }

    public static <T> BaseResponse<T> fail(Integer code, String msg) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = code;
        response.msg = msg;
        return response;
    }
}
