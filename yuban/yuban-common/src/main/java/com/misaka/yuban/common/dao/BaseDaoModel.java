package com.misaka.yuban.common.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 数据库表的通用字段
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDaoModel implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 记录是否有效：0代表有效、1代表无效
     */
    private Byte isDeleted;

    /**
     * 记录创建人
     */
    private String createdBy;

    /**
     * 记录最后的更新人
     */
    private String updatedBy;

    /**
     * 记录创建时间
     */
    private Timestamp createdAt;

    /**
     * 记录更新时间
     */
    private Timestamp updatedAt;
}
