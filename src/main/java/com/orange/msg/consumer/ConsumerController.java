package com.orange.msg.consumer;

import com.orange.msg.constant.MsgConstant;
import com.orange.msg.entity.BusinessLog;
import com.orange.msg.entity.NoticeLog;
import com.orange.msg.service.BusinessLogService;
import com.orange.msg.service.NoticeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller to retrieve Kafka messages.
 */
@RestController
public class ConsumerController {

	@Autowired
	private BusinessLogService businessLogService;
	@Autowired
	private NoticeLogService noticeLogService;

	@RequestMapping(value = "/receive_message/{id}", method = RequestMethod.GET)
	public Object processReceiveMessage(@PathVariable Long id) {
		//业务消息
		List<BusinessLog> businessLogs = businessLogService.findDispatchBusiness(id);
		if (businessLogs==null)businessLogs = new ArrayList<>();
		List<NoticeLog> noticeLogs = noticeLogService.findDispatchNotice(id);
		if (noticeLogs==null)noticeLogs = new ArrayList<>();
		//系统通知
		List<Map> content = new ArrayList<>();
		for (BusinessLog businessLog : businessLogs) {
			Map item = new HashMap<>();
			item.put("type", MsgConstant.TOPIC_BUSINESS);
			item.put("uuid",businessLog.getUuid());
			item.put("content",businessLog.getBusiness().getContent());
			content.add(item);
		}
		for (NoticeLog noticeLog : noticeLogs) {
			Map item = new HashMap<>();
			item.put("type",MsgConstant.TOPIC_NOTICE);
			item.put("uuid",noticeLog.getUuid());
			item.put("content",noticeLog.getNotice().getContent());
			content.add(item);
		}

		Map map = new HashMap<>();
		map.put("status",0);
		map.put("msg","成功");
		map.put("content",content);
		map.put("totalBusinesses",businessLogs.size());
		map.put("totalNotices",noticeLogs.size());
		map.put("totalElements",content.size());
		return map;
	}

}
