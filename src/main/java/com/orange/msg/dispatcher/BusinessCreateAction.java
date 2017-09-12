package com.orange.msg.dispatcher;

import com.orange.msg.entity.Message;
import com.orange.msg.service.BusinessService;
import com.orange.msg.util.BeanUtils;

/**
 * 发送业务消息
 */
public class BusinessCreateAction extends AbstractAction {

    private BusinessService businessService;

    /**
     * 构造包含消息的执行动作
     *
     * @param message
     */
    public BusinessCreateAction(Message message) {
        super(message);
        businessService = (BusinessService) BeanUtils.getApplicationContext().getBean(BusinessService.class);
    }

    @Override
    public void execute() {
        System.out.println("业务消息插入数据库");
        businessService.saveBusiness(message.getUuid(), message.getTime(), message.getData());
    }
}
