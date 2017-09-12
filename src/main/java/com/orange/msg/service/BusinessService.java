package com.orange.msg.service;

import com.orange.msg.entity.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 业务操作
 */
public interface BusinessService {

    /**
     * 保存业务操作
     * @param msg
     */
    public void saveBusiness(String uuid,Long time,String msg);

    /**
     * 查询业务消息
     * @return
     */
    public Page<Business> findAll(Pageable pageable);

    /**
     * 查询业务消息
     * @return
     */
    public Page<Business> findByTargetUid(Long id,Pageable pageable);

    /**
     * 查询未读消息
     * @return
     */
    public List<Business> findUnreadMessage(Long id);

    /**
     * 修改消息
     * @param business
     */
    public void update(Business business);

    /**
     * 获取消息
     * @param uuid
     * @return
     */
    public Business findOne(String uuid);

    /**
     * 设置消息已读
     */
    public void setRead(String uuid);

    /**
     * 查询未分发消息
     * @param id
     * @return
     */
    public List<Business> findDispatchBusiness(Long id);
}
