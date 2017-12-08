package com.orange.msg.service;

import com.orange.msg.entity.BusinessLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 业务消息读取记录
 */
public interface BusinessLogService {

    /**
     * 保存业务读取记录
     * @param list
     */
    public void saveBusinessLog(List<BusinessLog> list);

    /**
     * 根据用户id分页查询
     * @param id
     * @param pageable
     * @return
     */
    public Page<BusinessLog> findByTargetUid(Long id, Pageable pageable);

    /**
     * 根据用户id和uuid获取业务消息
     * @param id
     * @param uuid
     * @return
     */
    public BusinessLog findByTargetUidAndUuid(Long id, String uuid);

    /**
     * 设置消息已读
     * @param id
     * @param uuid
     */
    public void setRead(Long id, String uuid);

    /**
     * 查询未分发消息
     * @param id
     * @return
     */
    public List<BusinessLog> findDispatchBusiness(Long id);
}
