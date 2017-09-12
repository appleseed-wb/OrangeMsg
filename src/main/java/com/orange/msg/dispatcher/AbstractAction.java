package com.orange.msg.dispatcher;

import com.orange.msg.entity.Message;

/**
 * 消息动作实现抽象类
 */
public abstract class AbstractAction {

    /**
     * 消息对象
     */
    protected Message message;

    /**
     * 构造包含消息的执行动作
     * @param message
     */
    public AbstractAction(Message message){
        this.message = message;
    }

    /**
     * 消息动作执行实现
     */
    public abstract void execute();
}
