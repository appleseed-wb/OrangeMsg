package com.orange.msg.service;

/**
 * 日志操作
 */
public interface LogService {

    /**
     * 保存操作日志
     * @param msg
     */
    public void saveLog(String uuid,Long time,String msg);
}
