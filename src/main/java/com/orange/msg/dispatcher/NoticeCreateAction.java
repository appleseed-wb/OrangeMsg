package com.orange.msg.dispatcher;

import com.orange.msg.entity.Message;
import com.orange.msg.service.NoticeService;
import com.orange.msg.util.BeanUtils;

/**
 * 发送系统通知
 */
public class NoticeCreateAction extends AbstractAction {

    private NoticeService noticeService;

    /**
     * 构造包含消息的执行动作
     *
     * @param message
     */
    public NoticeCreateAction(Message message) {
        super(message);
        this.noticeService = (NoticeService) BeanUtils.getApplicationContext().getBean(NoticeService.class);
    }

    @Override
    public void execute() {
        System.out.println("系统通知插入数据库");
        noticeService.saveNotice(message.getUuid(), message.getTime(), message.getData());
    }
}
