package com.misaka.yuban.service.impl;

import com.misaka.yuban.common.dao.Log;
import com.misaka.yuban.dao.mapper.LogMapper;
import com.misaka.yuban.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public void addLog(Log log){
        logMapper.insert(log);
    };

    @Override
    public void schduleDelete(){
        logMapper.schduleDelete();
    };
}
