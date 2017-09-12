package com.orange.msg.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 系统通知
 */
@Document(collection = "mms_notice")
public class Notice {

    /**
     * 消息唯一编号，主键
     */
    @Id
    private String uuid;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 通知时间
     */
    private Long operTime;

    /**
     * 通知范围，备用
     */
    private Integer scope;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getOperTime() {
        return operTime;
    }

    public void setOperTime(Long operTime) {
        this.operTime = operTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }
}
