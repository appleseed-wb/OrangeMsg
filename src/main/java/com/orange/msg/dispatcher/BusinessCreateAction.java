package com.orange.msg.dispatcher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orange.msg.constant.MsgConstant;
import com.orange.msg.entity.Business;
import com.orange.msg.entity.BusinessLog;
import com.orange.msg.entity.Message;
import com.orange.msg.service.BusinessLogService;
import com.orange.msg.service.BusinessService;
import com.orange.msg.util.BeanUtils;

import java.util.List;

/**
 * 发送业务消息
 */
public class BusinessCreateAction extends AbstractAction {

    private BusinessService businessService;
    private BusinessLogService businessLogService;

    /**
     * 构造包含消息的执行动作
     *
     * @param message
     */
    public BusinessCreateAction(Message message) {
        super(message);
        businessService = (BusinessService) BeanUtils.getApplicationContext().getBean(BusinessService.class);
        businessLogService = (BusinessLogService) BeanUtils.getApplicationContext().getBean(BusinessLogService.class);
    }

    @Override
    public void execute() {
        System.out.println("业务消息插入数据库");
        //业务消息
        Business business = businessService.saveBusiness(message.getUuid(), message.getTime(), message.getData());
        //读取记录
        JSONObject data = JSON.parseObject(message.getData());
        List<BusinessLog> logs = JSON.parseArray(data.getString("targets"), BusinessLog.class);
        for (BusinessLog log : logs) {
            log.setUuid(business.getUuid());
            log.setOperTime(business.getOperTime());
            log.setStatus(MsgConstant.MSG_STATUS_UNREAD);
            log.setDispatch(MsgConstant.MSG_DISPATCH_NO);
            log.setBusiness(business);
        }
        businessLogService.saveBusinessLog(logs);
    }
}
