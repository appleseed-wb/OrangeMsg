package com.orange.msg.dispatcher;

import com.alibaba.fastjson.JSON;
import com.orange.msg.entity.Message;
import com.orange.msg.entity.NoticeLog;
import com.orange.msg.service.NoticeLogService;
import com.orange.msg.util.BeanUtils;

/**
 * 通知同步
 */
public class NoticeSyncAction extends AbstractAction {

    private NoticeLogService noticeLogService;

    /**
     * 构造包含消息的执行动作
     *
     * @param message
     */
    public NoticeSyncAction(Message message) {
        super(message);
        this.noticeLogService = (NoticeLogService) BeanUtils.getApplicationContext().getBean(NoticeLogService.class);
    }

    @Override
    public void execute() {
        System.out.println("拉取系统通知");
        NoticeLog noticeLog = JSON.parseObject(message.getData(),NoticeLog.class);
        noticeLogService.syncNoticeLog(noticeLog.getTargetUid());
    }
}
