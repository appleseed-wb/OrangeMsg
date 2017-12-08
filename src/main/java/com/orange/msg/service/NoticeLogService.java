package com.orange.msg.service;

import com.orange.msg.entity.NoticeLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 系统通知读取记录
 */
public interface NoticeLogService {

    /**
     * 分页查询未读消息
     * @param id
     * @param pageable
     * @return
     */
    public Page<NoticeLog> findByTargetUid(Long id, Pageable pageable);

    /**
     * 同步用户系统通知
     * @param id
     * @return
     */
    public int syncNoticeLog(Long id);

    /**
     * 查询未分发消息
     * @param id
     * @return
     */
    public List<NoticeLog> findDispatchNotice(Long id);

    /**
     * 通知设为已读
     * @param id
     * @param uuid
     */
    public void setRead(Long id, String uuid);

    /**
     * 根据用户id和uuid获取通知
     * @param id
     * @param uuid
     * @return
     */
    public NoticeLog findByTargetUidAndUuid(Long id, String uuid);
}
