package com.orange.msg.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 业务日志
 */
@Document(collection = "mms_log")
public class Log {

    /**
     * 消息唯一编号，主键
     */
    @Id
    private String uuid;

    /**
     * 业务类型
     */
    private String action;

    /**
     * 操作内容
     */
    private String content;

    /**
     * 操作人
     */
    private Long operUid;

    /**
     * 操作人姓名
     */
    private String operName;

    /**
     * 操作时间
     */
    private Long operTime;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getOperUid() {
        return operUid;
    }

    public void setOperUid(Long operUid) {
        this.operUid = operUid;
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

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }
}
