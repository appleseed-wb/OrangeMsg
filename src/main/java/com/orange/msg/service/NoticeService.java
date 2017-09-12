package com.orange.msg.service;

import com.orange.msg.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 通知操作
 */
public interface NoticeService {

    /**
     * 保存通知操作
     * @param msg
     */
    public void saveNotice(String uuid,Long time,String msg);

    /**
     * 获取所有通知
     * @return
     */
    public List<Notice> findAll();

    /**
     * 分页获取通知
     * @return
     */
    public Page<Notice> findAll(Pageable pageable);

    /**
     * 修改消息
     * @param business
     */
    public void update(Notice business);

    /**
     * 获取消息
     * @param uuid
     * @return
     */
    public Notice findOne(String uuid);

    /**
     * 查询未分发消息
     * @param operTime
     * @return
     */
    public List<Notice> findDispatchNotice(Long operTime);
}
