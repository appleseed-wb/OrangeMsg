package com.orange.msg.service;

import com.orange.msg.entity.Business;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 业务操作
 */
public interface BusinessService {

    /**
     * 保存业务操作
     * @param business
     * @return
     */
    public Business save(Business business);

    /**
     * 保存业务操作
     * @param uuid
     * @param time
     * @param msg
     * @return
     */
    public Business saveBusiness(String uuid, Long time, String msg);

    /**
     * 查询业务消息
     * @return
     */
    public Page<Business> findAll(Pageable pageable);

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

}
