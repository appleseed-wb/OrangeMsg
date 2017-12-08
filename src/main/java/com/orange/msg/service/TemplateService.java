package com.orange.msg.service;

import com.orange.msg.entity.Template;

/**
 * 消息模版
 */
public interface TemplateService {

    /**
     * 获取消息模版
     * @param uuid
     * @return
     */
    public Template getOne(String uuid);

    /**
     * 保存消息模版
     * @param template
     */
    public void save(Template template);

    /**
     * 删除消息模版
     * @param uuid
     */
    public void delete(String uuid);
}
