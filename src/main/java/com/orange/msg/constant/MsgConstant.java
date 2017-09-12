package com.orange.msg.constant;

/**
 * 消息常量
 */
public class MsgConstant {

    //消息基础类型
    public static final String TOPIC_LOG = "log";                   //日志
    public static final String TOPIC_NOTICE = "notice";             //通知
    public static final String TOPIC_BUSINESS = "business";         //业务

    //消息读取状态
    public static final Integer MSG_STATUS_UNREAD = 1;              //未读
    public static final Integer MSG_STATUS_READ = 2;                //已读

    //消息分发状态
    public static final Integer MSG_DISPATCH_NO = 1;                //未分发
    public static final Integer MSG_DISPATCH_YES = 2;               //已分发

    //系统通知范围
    public static final Integer NOTICE_SCOPE_GLOBAL = 1;            //全局通知

    //系统通知日志操作动作
    public static final String NOTICE_LOG_READ = "read";            //读取

    //消息动作代码
    //notice 相关
    public static final String ACTION_NOTICE_CREATE = "notice_create";              //发送系统通知
    public static final String ACTION_NOTICE_PULL = "notice_pull";                  //拉取系统通知
    //business 相关
    public static final String ACTION_BUSINESS_CREATE = "business_create";          //发送业务消息
    //log 相关
    public static final String ACTION_LOG_CREATE = "log_create";                    //发送系统通知

}
