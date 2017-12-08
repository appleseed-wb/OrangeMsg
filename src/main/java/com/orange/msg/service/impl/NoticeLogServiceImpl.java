package com.orange.msg.service.impl;

import com.orange.msg.constant.MsgConstant;
import com.orange.msg.entity.Notice;
import com.orange.msg.entity.NoticeLog;
import com.orange.msg.repository.NoticeLogRepository;
import com.orange.msg.service.NoticeLogService;
import com.orange.msg.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeLogServiceImpl implements NoticeLogService {

    @Autowired
    private NoticeLogRepository noticeLogRepository;
    @Autowired
    private NoticeService noticeService;

    @Override
    public Page<NoticeLog> findByTargetUid(Long id, Pageable pageable) {
        return noticeLogRepository.findByTargetUid(id, pageable);
    }

    @Override
    public int syncNoticeLog(Long id) {
        NoticeLog nl = noticeLogRepository.findByTargetUidOrderByOperTimeDesc(id);
        List<Notice> notices = new ArrayList<>();
        if (nl!=null){
            notices = noticeService.findDispatchNotice(nl.getOperTime());
        }else {
            notices = noticeService.findAll();
        }
        List<NoticeLog> noticeLogs = new ArrayList<>();
        for (Notice notice : notices) {
            NoticeLog noticeLog = new NoticeLog();
            noticeLog.setOperTime(notice.getOperTime());
            noticeLog.setUuid(notice.getUuid());
            noticeLog.setDispatch(MsgConstant.MSG_DISPATCH_NO);
            noticeLog.setStatus(MsgConstant.MSG_STATUS_UNREAD);
            noticeLog.setTargetName(null);
            noticeLog.setTargetUid(id);
            noticeLog.setNotice(notice);
            noticeLogs.add(noticeLog);
        }
        noticeLogRepository.save(noticeLogs);
        return noticeLogs.size();
    }

    @Override
    public List<NoticeLog> findDispatchNotice(Long id) {
        List<NoticeLog> list = noticeLogRepository.findDispatchNotice(id);
        for (NoticeLog noticeLog : list) {
            noticeLog.setDispatch(MsgConstant.MSG_DISPATCH_YES);
        }
        noticeLogRepository.save(list);
        return list;
    }

    @Override
    public void setRead(Long id, String uuid) {
        NoticeLog noticeLog = noticeLogRepository.findByTargetUidAndUuid(id, uuid);
        noticeLog.setStatus(MsgConstant.MSG_STATUS_READ);
        noticeLogRepository.save(noticeLog);
    }

    @Override
    public NoticeLog findByTargetUidAndUuid(Long id, String uuid) {
        return noticeLogRepository.findByTargetUidAndUuid(id, uuid);
    }
}
