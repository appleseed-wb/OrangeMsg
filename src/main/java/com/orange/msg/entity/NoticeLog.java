package com.orange.msg.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 系统通知读取记录
 */
@Document(collection = "mms_notice_log")
public class NoticeLog {

    /**
     * 主键
     */
    @Id
    private ObjectId id;
    /**
     * 消息唯一编号，非主键
     */
    private String uuid;

    /**
     * 操作动作 dispatch.分发 read.读取
     */
    private String action;

    /**
     * 接收人
     */
    private Long targetUid;

    /**
     * 接收人姓名
     */
    private String targetName;

    /**
     * 日志记录时间
     */
    private Long operTime;

    /**
     * 操作状态 1.未操作 2.已操作
     */
    private Integer status;

    /**
     * 关联通知对象，分页查询时使用
     */
    @DBRef
    private Notice notice;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public Long getOperTime() {
        return operTime;
    }

    public void setOperTime(Long operTime) {
        this.operTime = operTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
