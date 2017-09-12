package com.orange.msg.producer;

/**
 * Message body from HTTP Post.
 */

public class PutPost {
	private String key;
	private String value;
	private String topic;
	private Long timestamp;
	private Integer partition;

	public PutPost() {}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setPartition(Integer partition) {
		this.partition = partition;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getTopic() {
		return topic;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public Integer getPartition() {
		return partition;
	}
}
