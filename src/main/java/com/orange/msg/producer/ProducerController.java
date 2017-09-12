package com.orange.msg.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller to deliver the message to Kafka.
 */
@RestController
public class ProducerController {
	@Autowired
	public KafkaTemplate<String, String> template;

	@RequestMapping(value = "/place_message", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Object processPlaceMessage(PutPost post) {
		Assert.notNull(post.getTopic());
		Assert.notNull(post.getValue());

		template.send(post.getTopic(), post.getPartition(), post.getTimestamp(), post.getKey(), post.getValue());
		Map map = new HashMap<>();
		map.put("status",0);
		map.put("msg","发送成功");
		return map;
	}
}
