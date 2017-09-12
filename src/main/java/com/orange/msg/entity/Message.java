package com.orange.msg.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 消息记录
 */
@Document(collection = "mms_message")
public class Message {

    //消息通用属性
    @Id
    private String uuid;        //消息唯一编号，主键
    private String topic;       //消息主题
    private String channel;     //消息渠道  短信、推送、IM等
    private String type;        //消息类别  文本 、图文、语音等
    private Long time = System.currentTimeMillis()/1000;          //消息时间戳  单位：秒

    //业务通用属性
    private String action;      //消息动作代码，参见MsgConstant
    private String data;        //消息数据

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
