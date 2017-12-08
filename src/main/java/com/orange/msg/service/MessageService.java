package com.orange.msg.service;

import com.orange.msg.entity.Message;

/**
 * 消息统一记录
 */
public interface MessageService {

    /**
     * 保存kafka消息
     * @param message
     */
    public void saveMessage(Message message);

    /**
     * 根据uuid获取消息
     * @param uuid
     * @return
     */
    public Message findOne(String uuid);
}
