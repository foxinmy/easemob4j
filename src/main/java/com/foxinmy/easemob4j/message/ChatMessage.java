package com.foxinmy.easemob4j.message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.easemob4j.type.ChatType;
import com.foxinmy.weixin4j.util.NameValue;

/**
 * 聊天记录
 *
 * @className ChatMessage
 * @author jinyu(foxinmy@gmail.com)
 * @date 2016年2月22日
 * @since JDK 1.6
 * @see
 */
public class ChatMessage implements Serializable {

	private static final long serialVersionUID = -3005421461294291438L;
	private String type;
	/**
	 * 发送人username
	 */
	private String from;
	/**
	 * 接收人的username或者接收group的id
	 */
	private String to;
	/**
	 * 消息ID
	 */
	@JSONField(name = "msg_id")
	private String msgId;
	/**
	 * 用来判断单聊还是群聊。chat:单聊，groupchat:群聊
	 */
	@JSONField(name = "chat_type")
	private ChatType chatType;
	private String uuid;
	private long created;
	private long modified;
	private long timestamp;
	/**
	 * 消息体
	 */
	private List<Notify> records;
	/**
	 * 扩展属性
	 */
	private List<NameValue> exts;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public ChatType getChatType() {
		return chatType;
	}

	public void setChatType(ChatType chatType) {
		this.chatType = chatType;
	}

	public List<Notify> getRecords() {
		return records;
	}

	public void setRecords(List<Notify> records) {
		this.records = records;
	}

	public List<NameValue> getExts() {
		return exts;
	}

	public void setExts(List<NameValue> exts) {
		this.exts = exts;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getCreated() {
		return created;
	}

	public Date getFormatCreate() {
		return new Date(created * 1000l);
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public long getModified() {
		return modified;
	}

	public Date getFormatModified() {
		return new Date(modified * 1000l);
	}

	public void setModified(long modified) {
		this.modified = modified;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Date getFormatTimestamp() {
		return new Date(timestamp * 1000l);
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ChatMessage [type=" + type + ", from=" + from + ", to=" + to
				+ ", msgId=" + msgId + ", chatType=" + chatType + ", uuid="
				+ uuid + ", created=" + created + ", modified=" + modified
				+ ", timestamp=" + timestamp + ", records=" + records
				+ ", exts=" + exts + "]";
	}
}
