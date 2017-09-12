package com.orange.msg.dispatcher;

import com.orange.msg.entity.Message;

/**
 * 默认动作
 */
public class DefaultAction extends AbstractAction {
    /**
     * 构造包含消息的执行动作
     *
     * @param message
     */
    public DefaultAction(Message message) {
        super(message);
    }

    @Override
    public void execute() {
        System.out.println("消息动作代码无效");
    }
}
