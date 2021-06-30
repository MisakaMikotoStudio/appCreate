package com.misaka.yuban.service;

import com.misaka.yuban.common.dao.Log;

/**
 * 接口日志
 */
public interface LogService {
    /**
     * 添加日志
     *  @param log 日志单位对象
     */
    void addLog(Log log);

    /**
     * 日志定时删除
     */
    void schduleDelete();
}
