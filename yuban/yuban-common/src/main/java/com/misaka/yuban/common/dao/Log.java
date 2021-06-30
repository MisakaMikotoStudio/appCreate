package com.misaka.yuban.common.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 数据库接口日志表的通用字段
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 请求url
     */
    private String url;

    /**
     * 请求参数
     */
    private String request;

    /**
     * 正常返回
     */
    private String response;
    /**
     * 异常信息
     */
    private String exception;

    /**
     * 接口花费时间：ms
     */
    private Long timeCost;

    /**
     * 访问时间
     */
    private Timestamp createdAt;
    /**
     * 记录更新时间
     */
    private Timestamp updatedAt;
}
