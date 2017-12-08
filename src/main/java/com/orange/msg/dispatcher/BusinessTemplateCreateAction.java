package com.orange.msg.dispatcher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orange.msg.constant.MsgConstant;
import com.orange.msg.entity.Business;
import com.orange.msg.entity.BusinessLog;
import com.orange.msg.entity.Message;
import com.orange.msg.entity.Template;
import com.orange.msg.service.BusinessLogService;
import com.orange.msg.service.BusinessService;
import com.orange.msg.service.TemplateService;
import com.orange.msg.util.BeanUtils;

import java.util.List;

/**
 * 发送模版消息
 */
public class BusinessTemplateCreateAction extends AbstractAction {

    private BusinessService businessService;
    private TemplateService templateService;
    private BusinessLogService businessLogService;

    /**
     * 构造包含消息的执行动作
     *
     * @param message
     */
    public BusinessTemplateCreateAction(Message message) {
        super(message);
        businessService = (BusinessService) BeanUtils.getApplicationContext().getBean(BusinessService.class);
        templateService = (TemplateService) BeanUtils.getApplicationContext().getBean(TemplateService.class);
        businessLogService = (BusinessLogService) BeanUtils.getApplicationContext().getBean(BusinessLogService.class);
    }

    @Override
    public void execute() {
        System.out.println("发送模版消息插入数据库");
        //获取消息json对象
        JSONObject data = JSON.parseObject(message.getData());
        String templateId = data.getString("templateId");
        JSONObject values = data.getJSONObject("values");
        //获取模版
        Template template = templateService.getOne(templateId);
        if (template!=null){
            //填充数据
            String content = template.fill(values);
            //保存业务消息
            Business business = JSON.parseObject(message.getData(),Business.class);
            business.setUuid(message.getUuid());
            business.setContent(content);
            businessService.save(business);
            //保存业务消息读取记录
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
}
