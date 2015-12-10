package com.foxinmy.easemob4j.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.easemob4j.type.TargetType;

/**
 * 聊天消息
 * 
 * @className NotifyMessage
 * @author jy
 * @date 2015年3月18日
 * @since JDK 1.6
 * @see com.foxinmy.easemob4j.type.TargetType
 * @see com.foxinmy.easemob4j.message.Text
 */
public class NotifyMessage implements Serializable {

	private static final long serialVersionUID = 1312805080497508328L;

	@JSONField(name = "target_type")
	private TargetType targetType; // 发送类型
	@JSONField(name = "target")
	private List<String> targetIds; // 发送目标
	@JSONField(name = "msg")
	private Notify notify; // 消息主体
	private String from; // 消息来源
	private Map<String, String> ext; // 扩展属性

	public NotifyMessage(TargetType targetType, Notify notify,
			String... targetIds) {
		this.targetType = targetType;
		this.notify = notify;
		this.targetIds = Arrays.asList(targetIds);
	}

	public TargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}

	public List<String> getTargetIds() {
		return targetIds;
	}

	public void setTargetIds(List<String> targetIds) {
		this.targetIds = targetIds;
	}

	public void setTargetIds(String... targetIds) {
		this.targetIds = Arrays.asList(targetIds);
	}

	public void pushTargetId(String targetId) {
		if (this.targetIds == null) {
			this.targetIds = new ArrayList<String>();
		}
		this.targetIds.add(targetId);
	}

	public Notify getNotify() {
		return notify;
	}

	public void setNotify(Notify notify) {
		this.notify = notify;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Map<String, String> getExt() {
		return ext;
	}

	public void setExt(Map<String, String> ext) {
		this.ext = ext;
	}

	public void pushExt(String key, String value) {
		if (this.ext == null) {
			this.ext = new HashMap<String, String>();
		}
		this.ext.put(key, value);
	}

	@Override
	public String toString() {
		return "NotifyMessage [targetType=" + targetType + ", targetIds="
				+ targetIds + ", notify=" + notify + ", from=" + from
				+ ", ext=" + ext + "]";
	}
}
