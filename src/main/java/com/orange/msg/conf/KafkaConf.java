package com.orange.msg.conf;

import com.alibaba.fastjson.JSON;
import com.orange.msg.constant.MsgConstant;
import com.orange.msg.dispatcher.*;
import com.orange.msg.entity.Message;
import com.orange.msg.service.BusinessService;
import com.orange.msg.service.LogService;
import com.orange.msg.service.MessageService;
import com.orange.msg.service.NoticeService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.util.concurrent.SettableListenableFuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Configuration for kafka producers and consumers.
 */
@Configuration
@EnableKafka
@ConfigurationProperties
public class KafkaConf {

    @Value("${queue-size}")
    private int queueSize;

    @Value("${kafka-brokers}")
	private String kafkaBrokers;

    @Value("${kafka-consumer-group}")
	private String kafkaConsumerGroup;

    @Value("${kafka-auto-reset-offset}")
    private String kafkaAutoResetOffset;

    @Value("${kafka-topic}")
    private String kafkaTopic;

    private static LinkedBlockingQueue<SettableListenableFuture<String>> queue;

	@Autowired
	private LogService logService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private BusinessService businessService;
	@Autowired
	private MessageService messageService;

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();

		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		return props;
	}

	@Bean
	public KafkaTemplate<String, String> template() {
		KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory(), true);

		return kafkaTemplate;
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerGroup);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaAutoResetOffset);

		return props;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
	kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

	@Bean
    public LinkedBlockingQueue<SettableListenableFuture<String>> blockingQueue() {
	    if (null == queue) {
	        queue = new LinkedBlockingQueue<>(queueSize);
        }
	    return queue;
    }

	@KafkaListener(topics = "${kafka-topic}")
	public void listener(String payload) throws Exception {
        SettableListenableFuture<String> future = new SettableListenableFuture<>();
		future.set(payload);
		queue.put(future);
	}

	@KafkaListener(topics = {MsgConstant.TOPIC_LOG, MsgConstant.TOPIC_NOTICE, MsgConstant.TOPIC_BUSINESS})
	public void listener(ConsumerRecord<String,String> record) throws Exception {
		String topic = record.topic();
		String msg = record.value();
		Message message = JSON.parseObject(msg, Message.class);
		message.setTopic(topic);
		//重复uuid消息丢弃
		if (messageService.findOne(message.getUuid())==null){
			messageService.saveMessage(message);

			ActionDispatcher dispatcher = new ActionDispatcher();

			System.out.println("执行消息代码 >>> " + message.getAction());
			switch (message.getAction()){
				case MsgConstant.ACTION_NOTICE_CREATE:
					dispatcher.setAction(new NoticeCreateAction(message));
					break;
				case MsgConstant.ACTION_BUSINESS_CREATE:
					dispatcher.setAction(new BusinessCreateAction(message));
					break;
				case MsgConstant.ACTION_LOG_CREATE:
					dispatcher.setAction(new LogCreateAction(message));
					break;
				case MsgConstant.ACTION_NOTICE_PULL:
					dispatcher.setAction(new NoticeSyncAction(message));
					break;
				default:
					dispatcher.setAction(new DefaultAction(message));
					break;
			}

			dispatcher.dispatch();

		}
	}
}
