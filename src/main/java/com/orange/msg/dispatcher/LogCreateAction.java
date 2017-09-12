package com.orange.msg.dispatcher;

import com.orange.msg.entity.Message;
import com.orange.msg.service.LogService;
import com.orange.msg.util.BeanUtils;

/**
 * 发送系统通知
 */
public class LogCreateAction extends AbstractAction {

    private LogService logService;

    /**
     * 构造包含消息的执行动作
     *
     * @param message
     */
    public LogCreateAction(Message message) {
        super(message);
        logService = (LogService) BeanUtils.getApplicationContext().getBean(LogService.class);
    }

    @Override
    public void execute() {
        System.out.println("插入日志记录");
        logService.saveLog(message.getUuid(), message.getTime(), message.getData());
    }
}
