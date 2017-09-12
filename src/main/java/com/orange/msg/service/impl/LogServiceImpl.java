package com.orange.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.orange.msg.entity.Log;
import com.orange.msg.repository.LogRepository;
import com.orange.msg.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void saveLog(String uuid,Long time,String msg) {
        Log log = JSON.parseObject(msg,Log.class);
        log.setUuid(uuid);
        if (log.getOperTime()==null){
            log.setOperTime(time);
        }
        logRepository.save(log);
    }
}
