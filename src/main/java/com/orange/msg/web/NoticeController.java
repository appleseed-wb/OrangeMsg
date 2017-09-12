package com.orange.msg.web;

import com.orange.msg.constant.MsgConstant;
import com.orange.msg.service.NoticeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NoticeController {

    @Autowired
    private NoticeLogService noticeLogService;

    /**
     * 系统通知列表
     * @param id
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/notice/{id}", method = RequestMethod.GET)
    public Object getAll(@PathVariable Long id,@PageableDefault(sort = {"operTime"},direction = Sort.Direction.DESC) Pageable pageable){
        return noticeLogService.findByTargetUidAndActionAndStatus(id, MsgConstant.NOTICE_LOG_READ, 1, pageable);
    }

    /**
     * 系统通知详情
     * @param id
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/notice/{id}/{uuid}", method = RequestMethod.GET)
    public Object getOne(@PathVariable Long id,@PathVariable String uuid){
        return noticeLogService.findByTargetUidAndUuid(id, uuid);
    }

    /**
     * 系统通知设为已读
     * @param id
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/notice/{id}/{uuid}", method = RequestMethod.PUT)
    public Object put(@PathVariable Long id,@PathVariable String uuid){
        noticeLogService.setRead(id, uuid);
        Map result = new HashMap<>();
        result.put("status",0);
        result.put("msg","已读设置成功");
        return result;
    }

    @RequestMapping(value = "/notice/sync/{id}", method = RequestMethod.GET)
    public Object sync(@PathVariable Long id){
        int totalElements = noticeLogService.syncNoticeLog(id);
        Map result = new HashMap<>();
        result.put("status",0);
        result.put("msg","通知同步成功");
        result.put("totalElements",totalElements);
        return result;
    }
}
