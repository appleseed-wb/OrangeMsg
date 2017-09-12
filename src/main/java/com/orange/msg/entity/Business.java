package com.orange.msg.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 业务
 */
@Document(collection = "mms_business")
public class Business {

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

    /**
     * 接收人
     */
    private Long targetUid;

    /**
     * 接收人姓名
     */
    private String targetName;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 读取状态 1.未读 2.已读
     */
    private Integer status;

    /**
     * 分发状态 1.未分发 2.已分发
     */
    private Integer dispatch;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getOperUid() {
        return operUid;
    }

    public void setOperUid(Long operUid) {
        this.operUid = operUid;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Long getOperTime() {
        return operTime;
    }

    public void setOperTime(Long operTime) {
        this.operTime = operTime;
    }

    public Long getTargetUid() {
        return targetUid;
    }

    public void setTargetUid(Long targetUid) {
        this.targetUid = targetUid;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDispatch() {
        return dispatch;
    }

    public void setDispatch(Integer dispatch) {
        this.dispatch = dispatch;
    }
}
