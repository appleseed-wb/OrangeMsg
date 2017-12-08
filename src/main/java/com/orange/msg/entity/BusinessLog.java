package com.orange.msg.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 业务消息读取记录
 */
@Document(collection = "mms_business_log")
public class BusinessLog {

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
     * 接收人
     */
    private Long targetUid;

    /**
     * 接收人姓名
     */
    private String targetName;

    /**
     * 消息时间
     */
    private Long operTime;

    /**
     * 操作状态 1.未读 2.已读
     */
    private Integer status;

    /**
     * 分发状态 1.未分发 2.已分发
     */
    private Integer dispatch;

    /**
     * 关联通知对象，分页查询时使用
     */
    @DBRef
    private Business business;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Integer getDispatch() {
        return dispatch;
    }

    public void setDispatch(Integer dispatch) {
        this.dispatch = dispatch;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
