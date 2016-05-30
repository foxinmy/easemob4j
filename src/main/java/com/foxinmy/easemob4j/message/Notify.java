package com.foxinmy.easemob4j.message;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.easemob4j.type.MessageType;

/**
 * 消息基类
 * 
 * @className Notify
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年3月18日
 * @since JDK 1.6
 * @see
 */
public abstract class Notify implements Serializable {

	private static final long serialVersionUID = -7191320079281358428L;

	@JSONField(name = "type")
	private MessageType messageType;

	public Notify(MessageType messageType) {
		this.messageType = messageType;
	}

	public MessageType getMessageType() {
		return messageType;
	}
}
