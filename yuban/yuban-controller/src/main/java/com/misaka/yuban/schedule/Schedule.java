package com.misaka.yuban.schedule;

import com.misaka.yuban.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {
    @Autowired
    private LogService logService;

    /**
     * 每天4点执行一次清除7天前日志记录
     */
    @Scheduled(cron="0 0 4 * * ?")
    public void schduleDel(){
        logService.schduleDelete();
    }
}
